package com.team.vocab_learn.shared.mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import com.team.vocab_learn.shared.config.AppMailProperties;
import com.team.vocab_learn.shared.jobs.Job;
import com.team.vocab_learn.shared.jobs.JobHandler;
import com.team.vocab_learn.shared.mail.MailService.MailPayload;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

/** Renders templates/mail/{name}.html ({{var}} placeholders, subject = &lt;title&gt;) and sends via SMTP. */
@Component
@RequiredArgsConstructor
public class SendEmailJobHandler implements JobHandler {

	private static final Pattern VAR = Pattern.compile("\\{\\{(\\w+)}}");
	private static final Pattern TITLE = Pattern.compile("<title>(.*?)</title>", Pattern.DOTALL);

	private final JavaMailSender sender;
	private final AppMailProperties props;
	private final JsonMapper json;

	@Override
	public String type() {
		return MailService.JOB_TYPE;
	}

	@Override
	public void handle(Job job) throws Exception {
		MailPayload mail = json.readValue(job.payload(), MailPayload.class);
		String html = render(mail.template(), mail.model());
		Matcher title = TITLE.matcher(html);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
		helper.setFrom(props.from(), props.fromName());
		helper.setTo(mail.to());
		helper.setSubject(title.find() ? title.group(1).trim() : "VocabLearning");
		helper.setText(html, true);
		sender.send(message);
	}

	static String render(String template, Map<String, String> model) throws IOException {
		if (!template.matches("[a-z0-9-]+")) {
			throw new IllegalArgumentException("Bad template name: " + template);
		}
		String html = new ClassPathResource("templates/mail/" + template + ".html")
				.getContentAsString(StandardCharsets.UTF_8);
		return VAR.matcher(html).replaceAll(m -> Matcher.quoteReplacement(
				HtmlUtils.htmlEscape(model.getOrDefault(m.group(1), ""))));
	}
}
