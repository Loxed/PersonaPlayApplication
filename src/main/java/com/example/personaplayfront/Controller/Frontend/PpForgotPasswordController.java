package com.example.personaplayfront.Controller.Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class PpForgotPasswordController {

    @FXML
    public TextField emailTextField;
    @FXML
    public VBox forgotPasswordVBox;
    @FXML
    public VBox enterCodeVBox;
    @FXML
    public VBox resetPasswordVBox;
    @FXML
    public TextField codeTextField;

    @FXML
    public TextField passwordTextField;

    private String code;

    //init
    @FXML
    public void initialize() {
        //hide enter code and reset password views
        enterCodeVBox.setVisible(false);
        resetPasswordVBox.setVisible(false);
    }

    @FXML
    public void switchToLogInView(ActionEvent actionEvent) throws IOException {
        //switch to log in view

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
        Parent root = loader.load();
        PpLogInController logInController = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = (Stage) emailTextField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToForgotPasswordView(ActionEvent actionEvent) throws IOException {

        //TODO: Check if email exists in database
        if(emailTextField.getText().equals("leopold@rombaut.org")) {
            //then switch to reset password view
            resetPasswordMail();

            //hide current view, and show hidden view
            forgotPasswordVBox.setVisible(false);
            enterCodeVBox.setVisible(true);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Email not found");
            alert.setContentText("The email you entered is not associated with any account.");
            alert.showAndWait();
        }
    }

    //send email to user with a 6 digit code to reset password
    private void resetPasswordMail() {
        //10minutemail link
        //https://10minutemail.com/
        String to = emailTextField.getText();

        String from = "auto.personaplay@gmail.com";

        // Assuming you are sending email from Gmail's SMTP server
        String host = "smtp.gmail.com";

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
                return new PasswordAuthentication("auto.personaplay@gmail.com", "mvnx kxly mksp kihv");
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
            message.setSubject("PersonaPlay : Reset Password");

            //load the html in resources/email.html, into a string
            String html = null;

            try {
                html = new String(Files.readAllBytes(Paths.get("src/main/resources/com/example/personaplayfront/Mail/pp_reset_password.html")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //TODO : repplace placeholder with value from database (search by email)
            html = html.replace("{{user}}", "Lox");

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

            code = verificationCode;
            html = html.replace("{{code}}", String.valueOf(code));


            // Send the actual HTML message, as big as you like
            message.setContent(html, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @FXML
    public void confirmResetCode(ActionEvent actionEvent) {
        if(codeTextField.getText().equals(code)) {
            //hide current view, and show hidden view
            enterCodeVBox.setVisible(false);
            resetPasswordVBox.setVisible(true);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect code");
            alert.setContentText("The code you entered is incorrect.");
            alert.showAndWait();
        }
    }

    @FXML
    public void resetPassword(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password reset");
        alert.setHeaderText("Password reset");
        alert.setContentText("Your password has been reset to " + passwordTextField.getText());
        alert.showAndWait();

        //TODO: update the password in the database

        //switch to log in view
        try {
            switchToLogInView(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
