package com.IndianRailway.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class Emailservice {
	
	public boolean SendEmail(String subject,String message,String to) {
		
	
	
	boolean f = false;
	
	String from ="madhugowda41826@gmail.com";
	
	
	String host ="smtp.gmail.com";
	
	
	Properties properties = System.getProperties();
	System.out.println("Properties"+properties);


	
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	
	
	
	
	Session session = Session.getInstance(properties, new Authenticator() {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			
			return new PasswordAuthentication("madhugowda41826@gmail.com", "mpmgcwabnnlroudq");
		}
		
		
		
	});
	
	session.setDebug(true);
	
	MimeMessage m = new MimeMessage(session);
	
	try {
		m.setFrom(from);
		
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		m.setSubject(subject);
		
//		m.setText(message);
		m.setContent(message, "text/html");
		
		Transport.send(m);
		
		System.out.println("send ...");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return f;
	
	
	
	
	
	
	}
}
