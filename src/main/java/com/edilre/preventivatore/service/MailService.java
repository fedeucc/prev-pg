package com.edilre.preventivatore.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMessageWithAttachment(
			  String to, String subject, String text, byte[] file) throws IOException, MessagingException {
			    // ...
			    
			    MimeMessage message = mailSender.createMimeMessage();
			     
			    MimeMessageHelper helper = new MimeMessageHelper(message, true);
			    
			    helper.setFrom("info@costruzionipg.it");
			    helper.setTo(to);
			    helper.setSubject(subject);
			    helper.setText(text);
			        
			    FileSystemResource fil = new FileSystemResource(Files.write(File.createTempFile("preventivo", ".pdf").toPath(), file));
			    helper.addAttachment("preventivo.pdf", fil);

			    mailSender.send(message);
			}

}
