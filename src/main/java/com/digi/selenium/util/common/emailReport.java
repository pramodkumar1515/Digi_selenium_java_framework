package com.digi.selenium.util.common;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;
import java.util.Properties;



public class emailReport {

    public static void main(String[] args) {

  emailReport example = new emailReport();

  example.sendEmail();

    }
    
    
    
    public void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host.ssl.trust", "mail.hcl.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("pramod-ku@hcl.com", "Impetus@21sep");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pramod-ku@hcl.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("pramod-ku@hcl.com"));
            message.setSubject("Testing Subject");
            message.setText("PFA");

            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file = "D:\\";
            String fileName = "sample.txt";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail_geeks() {

  // Strings that contain from, to, subject, body and file path to the attachment

  String from = "pramod-ku@hcl.com";

  String to = "pramod-ku@hcl.com";

  String subject = "Test mail";

  String body = "Test body";

  String filename = "D:\\sample.txt";

  // Set smtp properties

  Properties properties = new Properties();

  properties.put("mail.smtp.host", "mail.hcl.com");

  properties.put("mail.smtp.port", "587");

  Session session = Session.getDefaultInstance(properties, null);

  try {

MimeMessage message = new MimeMessage(session);

message.setFrom(new InternetAddress(from));

message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

message.setSubject(subject);

message.setSentDate(new Date());

// Set the email body

MimeBodyPart messagePart = new MimeBodyPart();

messagePart.setText(body);

// Set the email attachment file

MimeBodyPart attachmentPart = new MimeBodyPart();

FileDataSource fileDataSource = new FileDataSource(filename) {

    @Override

    public String getContentType() {

  return "application/octet-stream";

    }

};

attachmentPart.setDataHandler(new DataHandler(fileDataSource));

attachmentPart.setFileName(fileDataSource.getName());

// Add all parts of the email to Multipart object

Multipart multipart = new MimeMultipart();

multipart.addBodyPart(messagePart);

multipart.addBodyPart(attachmentPart);

message.setContent(multipart);



// Send email

Transport.send(message);

  } catch (MessagingException e) {

e.printStackTrace();

  }
    }
}