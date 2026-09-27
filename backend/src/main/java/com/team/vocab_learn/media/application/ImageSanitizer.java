package com.team.vocab_learn.media.application;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import com.team.vocab_learn.shared.errors.ApiException;
import com.team.vocab_learn.shared.errors.ErrorCode;

/**
 * TK-06 / THIET_KE §13: nhận diện ảnh bằng chữ ký byte (không tin Content-Type của trình duyệt), kiểm cấu trúc,
 * bỏ khối siêu dữ liệu (EXIF, XMP, chú thích) mà không giải mã lại ảnh nên không giảm chất lượng.
 */
public final class ImageSanitizer {

	public record CleanImage(String mimeType, byte[] bytes, int width, int height) {
	}

	static final int MAX_SIDE = 8000;

	private static final Set<String> PNG_DROP = Set.of("tEXt", "zTXt", "iTXt", "eXIf", "tIME");

	private ImageSanitizer() {
	}

	public static CleanImage sanitize(byte[] in) {
		try {
			CleanImage out;
			if (startsWith(in, 0xFF, 0xD8, 0xFF)) {
				out = jpeg(in);
			} else if (startsWith(in, 0x89, 'P', 'N', 'G', 13, 10, 26, 10)) {
				out = png(in);
			} else if (ascii(in, 0, "RIFF") && ascii(in, 8, "WEBP")) {
				out = webp(in);
			} else {
				throw invalid("Tệp không phải ảnh JPEG, PNG hoặc WebP.");
			}
			if (out.width() < 1 || out.height() < 1 || out.width() > MAX_SIDE || out.height() > MAX_SIDE) {
				throw invalid("Kích thước ảnh phải từ 1 đến %d điểm ảnh mỗi chiều.".formatted(MAX_SIDE));
			}
			return out;
		} catch (IndexOutOfBoundsException e) {
			throw invalid("Tệp ảnh bị hỏng hoặc không đầy đủ.");
		}
	}

	// JPEG: giữ APP0 (JFIF), APP2 (hồ sơ màu ICC), APP14 (Adobe); bỏ APP1 (EXIF/XMP), APP3–13, APP15, COM
	private static CleanImage jpeg(byte[] in) {
		var out = new ByteArrayOutputStream(in.length);
		out.write(0xFF);
		out.write(0xD8);
		int pos = 2;
		int width = 0;
		int height = 0;
		while (true) {
			if ((in[pos] & 0xFF) != 0xFF) {
				throw invalid("Tệp JPEG bị hỏng.");
			}
			int marker = in[pos + 1] & 0xFF;
			if (marker == 0xFF) {
				pos++;
				continue;
			}
			if (marker == 0xD9) {
				throw invalid("Tệp JPEG không có dữ liệu ảnh.");
			}
			int length = u16(in, pos + 2);
			int end = pos + 2 + length;
			if (length < 2 || end > in.length) {
				throw invalid("Tệp JPEG bị hỏng.");
			}
			boolean sof = marker >= 0xC0 && marker <= 0xCF && marker != 0xC4 && marker != 0xC8 && marker != 0xCC;
			if (sof) {
				height = u16(in, pos + 5);
				width = u16(in, pos + 7);
			}
			if (marker == 0xDA) {
				// Từ SOS trở đi là dữ liệu nén: chép nguyên
				out.write(in, pos, in.length - pos);
				break;
			}
			boolean drop = marker == 0xE1 || (marker >= 0xE3 && marker <= 0xED) || marker == 0xEF || marker == 0xFE;
			if (!drop) {
				out.write(in, pos, end - pos);
			}
			pos = end;
		}
		return new CleanImage("image/jpeg", out.toByteArray(), width, height);
	}

	private static CleanImage png(byte[] in) {
		var out = new ByteArrayOutputStream(in.length);
		out.write(in, 0, 8);
		int pos = 8;
		int width = 0;
		int height = 0;
		boolean ended = false;
		while (!ended) {
			int length = u32(in, pos);
			String type = new String(in, pos + 4, 4, StandardCharsets.US_ASCII);
			int end = pos + 12 + length;
			if (length < 0 || end > in.length || (pos == 8 && !type.equals("IHDR"))) {
				throw invalid("Tệp PNG bị hỏng.");
			}
			if (type.equals("IHDR")) {
				width = u32(in, pos + 8);
				height = u32(in, pos + 12);
			}
			ended = type.equals("IEND");
			if (!PNG_DROP.contains(type)) {
				out.write(in, pos, end - pos);
			}
			pos = end;
		}
		return new CleanImage("image/png", out.toByteArray(), width, height);
	}

	// WebP (RIFF): bỏ khối EXIF/XMP, tắt cờ tương ứng trong VP8X rồi tính lại kích thước RIFF
	private static CleanImage webp(byte[] in) {
		int riffEnd = 8 + le32(in, 4);
		if (riffEnd > in.length || riffEnd < 20) {
			throw invalid("Tệp WebP bị hỏng.");
		}
		var body = new ByteArrayOutputStream(in.length);
		int pos = 12;
		int width = 0;
		int height = 0;
		while (pos + 8 <= riffEnd) {
			String type = new String(in, pos, 4, StandardCharsets.US_ASCII);
			int size = le32(in, pos + 4);
			int data = pos + 8;
			if (size < 0 || data + size > riffEnd) {
				throw invalid("Tệp WebP bị hỏng.");
			}
			int end = Math.min(data + size + (size & 1), riffEnd);
			switch (type) {
				case "VP8X" -> {
					width = 1 + le24(in, data + 4);
					height = 1 + le24(in, data + 7);
				}
				case "VP8 " -> {
					if (width == 0) {
						width = u16le(in, data + 6) & 0x3FFF;
						height = u16le(in, data + 8) & 0x3FFF;
					}
				}
				case "VP8L" -> {
					if (width == 0) {
						int bits = le32(in, data + 1);
						width = (bits & 0x3FFF) + 1;
						height = ((bits >>> 14) & 0x3FFF) + 1;
					}
				}
				default -> {
				}
			}
			if (!type.equals("EXIF") && !type.equals("XMP ")) {
				byte[] chunk = java.util.Arrays.copyOfRange(in, pos, end);
				if (type.equals("VP8X")) {
					chunk[8] &= (byte) ~0x0C;
				}
				body.writeBytes(chunk);
			}
			pos = end;
		}
		byte[] chunks = body.toByteArray();
		int riffSize = chunks.length + 4;
		var out = new ByteArrayOutputStream(chunks.length + 12);
		out.writeBytes("RIFF".getBytes(StandardCharsets.US_ASCII));
		out.writeBytes(new byte[] { (byte) riffSize, (byte) (riffSize >> 8), (byte) (riffSize >> 16), (byte) (riffSize >> 24) });
		out.writeBytes("WEBP".getBytes(StandardCharsets.US_ASCII));
		out.writeBytes(chunks);
		return new CleanImage("image/webp", out.toByteArray(), width, height);
	}

	private static ApiException invalid(String detail) {
		return new ApiException(ErrorCode.UNSUPPORTED_MEDIA_TYPE, detail);
	}

	private static boolean startsWith(byte[] in, int... sig) {
		if (in.length < sig.length) {
			return false;
		}
		for (int i = 0; i < sig.length; i++) {
			if (in[i] != (byte) sig[i]) {
				return false;
			}
		}
		return true;
	}

	private static boolean ascii(byte[] in, int at, String s) {
		return in.length >= at + s.length() && new String(in, at, s.length(), StandardCharsets.US_ASCII).equals(s);
	}

	private static int u16(byte[] b, int i) {
		return ((b[i] & 0xFF) << 8) | (b[i + 1] & 0xFF);
	}

	private static int u32(byte[] b, int i) {
		return ((b[i] & 0xFF) << 24) | ((b[i + 1] & 0xFF) << 16) | ((b[i + 2] & 0xFF) << 8) | (b[i + 3] & 0xFF);
	}

	private static int u16le(byte[] b, int i) {
		return (b[i] & 0xFF) | ((b[i + 1] & 0xFF) << 8);
	}

	private static int le24(byte[] b, int i) {
		return (b[i] & 0xFF) | ((b[i + 1] & 0xFF) << 8) | ((b[i + 2] & 0xFF) << 16);
	}

	private static int le32(byte[] b, int i) {
		return le24(b, i) | ((b[i + 3] & 0xFF) << 24);
	}
}
