package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.SessionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PpLogInController {
    @FXML
    public CheckBox rememberMeCheckBox;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private static boolean sessionPreference=false;

    private static final Map<String, String> USERS = new HashMap<>(); // Map of usernames to hashed passwords
    private static final Map<String, String> SESSIONS = new HashMap<>(); // Map of session IDs to usernames

    static {
        // Add some example users to the USERS map
        USERS.put("alice", SessionHandler.hashPassword("password1"));
        USERS.put("bob", SessionHandler.hashPassword("password2"));
    }

    @FXML
    public void initialize() {
        rememberMeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Checkbox is checked, perform action
                sessionPreference=true;

            } else {
                // Checkbox is unchecked, perform action
                sessionPreference=false;
            }

            System.out.println("sessionPreference : "+sessionPreference);
        });
    }

    @FXML
    private void handleLogIn(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String hashedPassword = USERS.get(username);
        if (hashedPassword != null && hashedPassword.equals(SessionHandler.hashPassword(password))) {
            //save session id in the SessionHandler
            if(sessionPreference) {
                SessionHandler.saveSessionId(SessionHandler.encryptSessionId(username,hashedPassword));
            } else {
                SessionHandler.saveSessionId(SessionHandler.encryptSessionId(username,""));
            }
            SessionHandler.displaySessionId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_home_page.fxml"));
            Parent root = loader.load();
            PpHomePageController homeController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameTextField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            // Log in failed
            System.out.println("Log in failed");
        }
    }

    @FXML
    private void switchToSignUpView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_sign_up.fxml"));
        Parent root = loader.load();
        PpSignUpController signUpController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchToForgotPasswordView(ActionEvent actionEvent) throws IOException {
        //TODO : implement forgot password view and controller

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_forgot_password.fxml"));
        Parent root = loader.load();
        PpForgotPasswordController forgotPasswordController = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //action lister for remember me

}
