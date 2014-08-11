/*
package com.cagnosolutions.nei.shipping.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service(value = "mailServiceConstruction")
public class MailServiceConstruction {

    @Autowired
    private JavaMailSender mailSender;

	@Autowired
	Configuration freeMarkerConfiguration;


    public void sendSimpleEmail(String from, String subject, String body, String... to) {
        new Thread(
        	() -> {
        	    SimpleMailMessage email = new SimpleMailMessage();
        	    email.setFrom(from);
        	    email.setReplyTo(from);
        	    email.setSubject(subject);
        	    email.setText(body);
        	    email.setTo(to);
        	    mailSender.send(email);
        	}
        ).start();
    }

	public void sendMimeMail(String from, String subject, String template, Map model, String... to) {
		new Thread(
			() -> {
				MimeMessagePreparator preparator = new MimeMessagePreparator() {
					public void prepare(MimeMessage mimeMessage) throws Exception {
						MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
						email.setTo(to);
						email.setFrom(from);
						email.setReplyTo(from);
						email.setSubject(subject);
						Template temp = freeMarkerConfiguration.getTemplate(template);
						String text = FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
						email.setText(text, true);
					}
				};
				mailSender.send(preparator);
			}
		).start();
	}
}
*/
