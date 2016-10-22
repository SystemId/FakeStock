package com.ani.stock.datasvc.email;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component("sendEmail")
public class SendEmailImpl implements SendEmail {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void executeDelivery(String fromAddress, List<String> toAddresses, List<String> ccAddresses, 
			List<String> bccAddresses, String emailSubject, String emailbody, List<CommonsMultipartFile> file) 
					throws MessagingException, IOException {
		
		MimeMessage message = mailsender.createMimeMessage();
		
		message.setHeader("Content-Type", "text/html; charset=UTF-8");
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		messageHelper.setTo(toAddresses.toArray(new String[toAddresses.size()]));
		messageHelper.setFrom(fromAddress);
		messageHelper.setSubject("test");
		messageHelper.setSentDate(new Date());
		messageHelper.setText("asdsadsadsadasdsad", true);
		mailsender.send(messageHelper.getMimeMessage());
		
		
	}

}
