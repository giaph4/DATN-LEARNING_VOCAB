package com.team.vocab_learn.media;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import com.team.vocab_learn.media.application.ImageSanitizer;
import com.team.vocab_learn.shared.errors.ErrorCode;

/** TK-06: nhận diện ảnh theo chữ ký byte và bỏ siêu dữ liệu. */
public class ImageSanitizerTest {

	static final String SECRET = "GPS-10.7769,106.7009";

	@Test
	void jpegDropsExifKeepsPixels() throws Exception {
		byte[] clean = ImageSanitizer.sanitize(jpegWithExif(40, 30)).bytes();
		assertThat(new String(clean, StandardCharsets.ISO_8859_1)).doesNotContain(SECRET).doesNotContain("Exif");
		BufferedImage img = ImageIO.read(new ByteArrayInputStream(clean));
		assertThat(img.getWidth()).isEqualTo(40);
		assertThat(img.getHeight()).isEqualTo(30);
	}

	@Test
	void pngDropsTextChunks() throws Exception {
		var result = ImageSanitizer.sanitize(pngWithText(12, 7));
		assertThat(result.mimeType()).isEqualTo("image/png");
		assertThat(result.width()).isEqualTo(12);
		assertThat(new String(result.bytes(), StandardCharsets.ISO_8859_1)).doesNotContain(SECRET);
		assertThat(ImageIO.read(new ByteArrayInputStream(result.bytes())).getHeight()).isEqualTo(7);
	}

	@Test
	void webpDropsExifChunkAndClearsFlag() {
		var result = ImageSanitizer.sanitize(webpWithExif());
		byte[] out = result.bytes();
		assertThat(result.mimeType()).isEqualTo("image/webp");
		assertThat(result.width()).isEqualTo(64);
		assertThat(result.height()).isEqualTo(48);
		assertThat(new String(out, StandardCharsets.ISO_8859_1)).doesNotContain(SECRET).doesNotContain("EXIF");
		assertThat(out[20] & 0x08).isZero();
		assertThat(le32(out, 4)).isEqualTo(out.length - 8);
	}

	@Test
	void rejectsFakeAndTruncatedImages() throws Exception {
		assertThatThrownBy(() -> ImageSanitizer.sanitize("<svg onload=alert(1)>".getBytes()))
				.extracting("code").isEqualTo(ErrorCode.UNSUPPORTED_MEDIA_TYPE);
		byte[] jpeg = jpegWithExif(10, 10);
		assertThatThrownBy(() -> ImageSanitizer.sanitize(java.util.Arrays.copyOf(jpeg, 30)))
				.extracting("code").isEqualTo(ErrorCode.UNSUPPORTED_MEDIA_TYPE);
	}

	public static byte[] jpegWithExif(int w, int h) throws Exception {
		var buf = new ByteArrayOutputStream();
		ImageIO.write(new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB), "jpg", buf);
		byte[] jpeg = buf.toByteArray();
		byte[] payload = ("Exif\0\0" + SECRET).getBytes(StandardCharsets.ISO_8859_1);
		var out = new ByteArrayOutputStream();
		out.write(jpeg, 0, 2);
		out.write(0xFF);
		out.write(0xE1);
		out.write((payload.length + 2) >> 8);
		out.write((payload.length + 2) & 0xFF);
		out.writeBytes(payload);
		out.write(jpeg, 2, jpeg.length - 2);
		return out.toByteArray();
	}

	public static byte[] pngWithText(int w, int h) throws Exception {
		var buf = new ByteArrayOutputStream();
		ImageIO.write(new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB), "png", buf);
		byte[] png = buf.toByteArray();
		byte[] text = ("Comment\0" + SECRET).getBytes(StandardCharsets.ISO_8859_1);
		var out = new ByteArrayOutputStream();
		out.write(png, 0, 33); // chữ ký + IHDR
		out.writeBytes(new byte[] { 0, 0, 0, (byte) text.length });
		out.writeBytes("tEXt".getBytes(StandardCharsets.US_ASCII));
		out.writeBytes(text);
		out.writeBytes(new byte[4]); // CRC không được kiểm ở bước này
		out.write(png, 33, png.length - 33);
		return out.toByteArray();
	}

	/** RIFF tối giản: VP8X (cờ EXIF) + VP8L 1 byte giả + EXIF. */
	static byte[] webpWithExif() {
		byte[] exif = SECRET.getBytes(StandardCharsets.ISO_8859_1);
		var chunks = new ByteArrayOutputStream();
		chunk(chunks, "VP8X", new byte[] { 0x08, 0, 0, 0, 63, 0, 0, 47, 0, 0 });
		chunk(chunks, "VP8L", new byte[] { 0x2f, 0, 0, 0, 0 });
		chunk(chunks, "EXIF", exif);
		byte[] body = chunks.toByteArray();
		var out = new ByteArrayOutputStream();
		out.writeBytes("RIFF".getBytes(StandardCharsets.US_ASCII));
		int size = body.length + 4;
		out.writeBytes(new byte[] { (byte) size, (byte) (size >> 8), (byte) (size >> 16), (byte) (size >> 24) });
		out.writeBytes("WEBP".getBytes(StandardCharsets.US_ASCII));
		out.writeBytes(body);
		return out.toByteArray();
	}

	private static void chunk(ByteArrayOutputStream out, String type, byte[] data) {
		out.writeBytes(type.getBytes(StandardCharsets.US_ASCII));
		out.writeBytes(new byte[] { (byte) data.length, 0, 0, 0 });
		out.writeBytes(data);
		if ((data.length & 1) == 1) {
			out.write(0);
		}
	}

	private static int le32(byte[] b, int i) {
		return (b[i] & 0xFF) | ((b[i + 1] & 0xFF) << 8) | ((b[i + 2] & 0xFF) << 16) | ((b[i + 3] & 0xFF) << 24);
	}
}
