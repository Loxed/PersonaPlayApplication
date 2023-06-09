package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Roles;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.RolesDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Properties;
import java.util.Random;

public class PpSignUpController {

    @FXML
    public VBox signUpView;
    @FXML
    public VBox confirmSignUpView;
    @FXML
    public TextField confirmationCodeTextField;
    @FXML
    public TextField emailTextField;
    public ScrollPane scrollpage;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private String confirmationCode;

    UsersDaoImpl usersDao;

    Users usermail;
    Users user;

    //init
    public void initialize() {
        usersDao = new UsersDaoImpl();

        //disable
        confirmSignUpView.setDisable(true);


        //disable scrolling for scrollpage
        scrollpage.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpage.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        //regex to check correct mail
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        if(emailTextField.getText().matches(regex)) {

            usermail = usersDao.findByPropertyLike("email", emailTextField.getText());

            user = usersDao.findByPropertyLike("username", usernameTextField.getText());

            //if mail exists
            if(usermail!=null) {
                //if mail is already taken
                if (usermail.getEmail().equals(emailTextField.getText())) {
                    //show error
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Email already taken");
                    alert.showAndWait();
                    //else if username is not null
                } else if (user != null) {
                    //if username is already taken
                    if (user.getUsername().equals(usernameTextField.getText())) {
                        //show error
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Username already taken");
                        alert.showAndWait();
                    } else {
                        //else send confirmation code
                        System.out.println("Sending confirmation code");
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sendConfirmationCode();
                            }
                        });
                        thread.start();

                        //switch to confirm sign up view
                        signUpView.setDisable(true);
                        confirmSignUpView.setDisable(false);

                        //transition to scroll down the scroll pane to the bottom
                        Timeline timeline = new Timeline();

                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.getKeyFrames().add(new javafx.animation.KeyFrame(javafx.util.Duration.millis(10), event1 -> {
                            scrollpage.setVvalue(scrollpage.getVvalue() + 0.01);
                            if (scrollpage.getVvalue() == 1.0) {
                                timeline.stop();
                            }
                        }));
                        timeline.play();
                    }
                } else {
                    //else send confirmation code
                    System.out.println("Sending confirmation code");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sendConfirmationCode();
                        }
                    });
                    thread.start();

                    //switch to confirm sign up view
                    signUpView.setDisable(true);
                    confirmSignUpView.setDisable(false);

                    //transition to scroll down the scroll pane to the bottom
                    Timeline timeline = new Timeline();

                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.getKeyFrames().add(new javafx.animation.KeyFrame(javafx.util.Duration.millis(10), event1 -> {
                        scrollpage.setVvalue(scrollpage.getVvalue() + 0.01);
                        if (scrollpage.getVvalue() == 1.0) {
                            timeline.stop();
                        }
                    }));
                    timeline.play();
                }
            } else if (user != null && user.getUsername().equals(usernameTextField.getText())) {
                //show error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Username already taken");
                alert.showAndWait();
            } else {
                //else send confirmation code
                System.out.println("Sending confirmation code");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendConfirmationCode();
                    }
                });
                thread.start();

                //switch to confirm sign up view
                signUpView.setDisable(true);
                confirmSignUpView.setDisable(false);

                //transition to scroll down the scroll pane to the bottom
                Timeline timeline = new Timeline();

                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(new javafx.animation.KeyFrame(javafx.util.Duration.millis(10), event1 -> {
                    scrollpage.setVvalue(scrollpage.getVvalue() + 0.01);
                    if (scrollpage.getVvalue() == 1.0) {
                        timeline.stop();
                    }
                }));
                timeline.play();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid email");
            alert.setContentText("Please enter a valid email");
            alert.showAndWait();
        }
    }

    @FXML
    private void switchToLogInView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
        Parent root = loader.load();
        PpLogInController logInController = loader.getController();
        //logInController.setUsername(usernameTextField.getText());
        //logInController.setPassword(passwordTextField.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleConfirmSignUp(ActionEvent actionEvent) {

        if(confirmationCodeTextField.getText().equals(confirmationCode)) {
            //TODO: add user to db
            RolesDaoImpl rolesDao = new RolesDaoImpl();

            Users user = new Users(usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), false, rolesDao.findById(1));

            usersDao.save(user);

            //switch to log in view
            try {
                switchToLogInView(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //send confirmation code to email
    public void sendConfirmationCode() {
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
            message.setSubject("PersonaPlay : Confirm your account");

            //load the html in resources/email.html, into a string
            String html = null;

            try {
                html = new String(Files.readAllBytes(Paths.get("src/main/resources/com/example/personaplayfront/Mail/pp_confirmation_code.html")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //replace the placeholders with the actual values
            html = html.replace("{{user}}", usernameTextField.getText());

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

            confirmationCode = verificationCode;

            html = html.replace("{{code}}", String.valueOf(verificationCode));

            // Send the actual HTML message, as big as you like
            message.setContent(html, "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
