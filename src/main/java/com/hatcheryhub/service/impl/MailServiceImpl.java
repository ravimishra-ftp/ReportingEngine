package com.hatcheryhub.service.impl;


import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hatcheryhub.service.MailService;

@Service("mailService")
public class MailServiceImpl implements MailService{
    private static final Log log = LogFactory.getLog(SchedulingServiceImpl.class);
	private static String UNAME = "auto.reporting.engine@gmail.com";
	private static String PASSWORD = "great@123";
	
	public static String startBody = "<font color='black' size='3'><i>Dear User,<br><br>You are reciving this auto generated mail according to description you given to Reporting Engine.<br>To change settings call your system administrator.<br><br></i><b>[Report]</b><table style = 'margin:0;' cellpadding='4' cellspacing='0' border='1' >";
	public static String endBody = "</table><br>Regards,<br><b>Reporting Engine.</b></font>";

	public void sendMail(String to, String sub, String body) {
		Authenticator auth = new JlcPasswordAuthrnticator();
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		Session sess = Session.getDefaultInstance(p, auth);
		Message msg = new MimeMessage(sess);
		try {
			InternetAddress toAdd = new InternetAddress(to);
			InternetAddress ccAdd = new InternetAddress("ravimishra.bglr@gmail.com");
			InternetAddress fromAdd = new InternetAddress(UNAME,"Reporting Engine");
			msg.setRecipient(RecipientType.TO, toAdd);
			msg.setRecipient(RecipientType.CC, ccAdd);
			msg.setFrom(fromAdd);
			msg.setSubject(sub);
			msg.setContent(body, "text/html");
			// msg.setContent(text, "text/plain");
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMailWithAttachment(String to, String sub, String text, List<File> files) {
		Authenticator auth = new JlcPasswordAuthrnticator();
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		Session sess = Session.getDefaultInstance(p, auth);
		Message msg = new MimeMessage(sess);
		try {
			InternetAddress toAdd = new InternetAddress(to);
			InternetAddress ccAdd = new InternetAddress("ravimishra.bglr@gmail.com");
			InternetAddress fromAdd = new InternetAddress(UNAME,"Reporting Engine");
			msg.setRecipient(RecipientType.TO, toAdd);
			msg.setRecipient(RecipientType.CC, ccAdd);
			msg.setFrom(fromAdd);
			msg.setSubject(sub);
			InternetAddress add[] = new InternetAddress[1];
			add[0] = new InternetAddress("ravimishra.bglr@gmail.com");
			msg.setReplyTo(add);

			Multipart body = new MimeMultipart();
			BodyPart part1 = new MimeBodyPart();
			part1.setContent(text, "text/html");
			body.addBodyPart(part1);
			if (files != null) {
				for (File f : files) {
					BodyPart part = new MimeBodyPart();
					part.setFileName(f.getName());
					DataSource source = new FileDataSource(f);
					DataHandler handler = new DataHandler(source);
					part.setDataHandler(handler);
					body.addBodyPart(part);
				}
			}
			msg.setContent(body);
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class JlcPasswordAuthrnticator extends Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
		    log.info("Reporting Engine mail Authentication called...");
			return new PasswordAuthentication(UNAME, PASSWORD);
		}
	}
}