package com.cagnosolutions.nei.shipping.mail
import freemarker.template.Configuration
import freemarker.template.Template
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils

import javax.mail.internet.MimeMessage

@Service(value = "mailService")
class MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration freeMarkerConfiguration

	def sendSimpleEmail(String from, String subject, String body, String... to) {
		Thread.start {
			def email = new SimpleMailMessage()
			email.from = from
			email.replyTo = from
			email.subject = subject
			email.text = body
			email.to = to
			mailSender.send(email)
		}
	}
	def sendMimeMail(String from, String subject, String template, Map model, String... to) {
		Thread.start {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper email = new MimeMessageHelper(mimeMessage)
					email.setTo(to)
					email.setFrom(from)
					email.setReplyTo(from)
					email.setSubject(subject)
					Template temp = freeMarkerConfiguration.getTemplate(template)
					String text = FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
					email.setText(text, true)
				}
			};
			mailSender.send(preparator)
		}
	}
}
