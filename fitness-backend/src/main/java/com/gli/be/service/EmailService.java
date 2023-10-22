package com.gli.be.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

// import org.springframework.boot.rsocket.server.RSocketServer.Transport;

@Service
public class EmailService {

    public void send(String subject, String textbody, String recipient) {
        final String username = "fitnessbni9@gmail.com";
        final String password = "Bismillah@321";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        // props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(textbody);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendOutlool(String subject, String textbody, String recipient) {
        try {
            String email = "e1n2j3i@outlook.co.id";
            String password = "pdfjaya123";

            ExchangeService service = new ExchangeService();
            ExchangeCredentials credentials = new WebCredentials(email, password);
            service.setCredentials(credentials);

            // Set the URL of the Exchange server (Outlook server)
            service.setUrl(new java.net.URI("https://outlook.office365.com/EWS/Exchange.asmx"));

            // Create a new email message
            EmailMessage emailMessage = new EmailMessage(service);
            emailMessage.setSubject(subject);
            emailMessage.setBody(MessageBody.getMessageBodyFromText(textbody));

            // Set the recipient email address
            emailMessage.getToRecipients().add(new EmailAddress(recipient));

            // Send the email
            emailMessage.send();

            System.out.println("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOutloolSmtp(String subject, String textbody, String recipient) {
             String username = "e1n2j3i@outlook.co.id";
            String password = "pdfjaya123";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com"); // Gunakan server SMTP Outlook/Office 365
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
            message.setSubject("Subject of the Email");
            message.setText("This is the message body.");

            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
