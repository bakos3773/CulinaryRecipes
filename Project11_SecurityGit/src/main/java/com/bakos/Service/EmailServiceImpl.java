package com.bakos.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired(required=false)
	MailSender mailSender;
	
//	@Autowired(required=false)
//	JavaMailSender mailSender;
	
    protected String mailSmtpAuth = "true";
    protected String mailSmtpHost = "smtp.gmail.com";
    protected String mailSmtpPort = "587";
    protected String mailSmtpStarttlsEnable = "true";
    protected String mailEmailFrom = "p.bakowski1@gmail.com";
    protected String username = "p.bakowski1@gmail.com";
    protected String password = "xxx";

    public boolean sendEmail(String recipientEmail, String subject, String content, String userLogin, String userPassword) {
    	
    	subject = "Przepisnik ;)";
    	content = "Witaj "+userLogin+"! Zarejestrowalas/es sie jako nowy uzytkownik. Od teraz mozesz swobodnie korzystac ze stron, ktora"
    			+ " posluzy Ci jako narzedzie do przechowywania twoich ulubionych przepisow, ktore bedziesz miala zawsze"
    			+ " na wyciagniecie reki a przy okazji bedziesz sie mogla dzielic z innymi ;) \nDANE DOSTEPU DO STRONY:\nLogin: "+userLogin+"\nHaslo:"+userPassword;

        Properties props = new Properties();
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
        props.put("mail.smtp.host", mailSmtpHost);
        props.put("mail.smtp.port", mailSmtpPort);
        props.put("mail.smtp.ssl.trust", mailSmtpHost);
        
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailEmailFrom));
            message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            //do nothing
        }

        return false;
    }
    
    @Async
    public void sendAsync(String recipientEmail, String subject,
			String content, String login, String password) {
    	sendEmail2(recipientEmail, subject, content, login, password);    	
    }

	@Override
	public void sendEmail2(String recipientEmail, String subject,
			String content, String login, String password) {
		
		   SimpleMailMessage message = new SimpleMailMessage(); 	   
		   message.setFrom("p.bakowski1@gmail.com");	
		   message.setTo(recipientEmail);
		   message.setSubject(subject);
		   message.setText(content);
		   mailSender.send(message);			
		
	}
    
}
