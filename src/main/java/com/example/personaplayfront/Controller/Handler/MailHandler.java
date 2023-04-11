package com.example.personaplayfront.Controller.Handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.*;

public class MailHandler {

    private static final String from = "auto.personaplay@gmail.com";
    private static final String host = "smtp.gmail.com";
    private static final String username = from;
    private static final String password = "mvnx kxly mksp kihv";

    public static void sendConfirmationCode(String to, String name) {
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //load the html in resources/email.html, into a string
            String html = null;

            try {
                html = new String(Files.readAllBytes(Paths.get("src/main/resources//com/example/personaplayfront/Mail/pp_confirmation_code.html")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //replace the placeholders with the actual values
            html = html.replace("{{user}}", name);

            //generate a random verification code that's 6 digits long (each digit is either a capitalized letter or a number)
            Random random = new Random();

            String verificationCode = "";

            for (int i = 0; i < 6; i++) {
                int randomInt = random.nextInt(36);
                if (randomInt < 10) {
                    verificationCode += randomInt;
                } else {
                    verificationCode += (char) (randomInt + 55);
                }
            }
            // Set Subject: header field
            message.setSubject("PersonaPlay Confirmation Code :" + String.valueOf(verificationCode));

            html = html.replace("{{code}}", String.valueOf(verificationCode));

            // Send the actual HTML message, as big as you like
            message.setContent(html, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent confirmation code to " + to + " successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void sendResetPassword(String to, String name) {

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("PersonaPlay: Reset Password");

            //load the html in resources/email.html, into a string
            String html = null;

            try {
                html = new String(Files.readAllBytes(Paths.get("src/main/resources//com/example/personaplayfront/Mail/pp_reset_password.html")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //replace the placeholders with the actual values
            html = html.replace("{{user}}", name);

            //generate a random verification code that's 6 digits long (each digit is either a capitalized letter or a number)
            Random random = new Random();

            String verificationCode = "";

            for (int i = 0; i < 6; i++) {
                int randomInt = random.nextInt(36);
                if (randomInt < 10) {
                    verificationCode += randomInt;
                } else {
                    verificationCode += (char) (randomInt + 55);
                }
            }

            html = html.replace("{{code}}", String.valueOf(verificationCode));

            // Send the actual HTML message, as big as you like
            message.setContent(html, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent reset password to " + to + " successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
