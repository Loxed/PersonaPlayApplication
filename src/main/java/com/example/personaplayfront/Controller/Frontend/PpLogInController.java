package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PpLogInController {
    @FXML
    public CheckBox rememberMeCheckBox;

    @FXML
    public HBox page;
    public AnchorPane bgPane;
    public Pane gradientPane;
    public Button LogInButton;
    public Button SignUpButton;
    public Button PasswordButton;
    public Circle logo;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    private static boolean sessionPreference=false;

    UsersDaoImpl usersDaoImpl = new UsersDaoImpl();

    @FXML
    public void initialize() throws FileNotFoundException {
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

        //fileinputstream

        FileInputStream input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/personaPlayLogo.png");

        //set logo as image pattern
        logo.setFill(new ImagePattern(new Image(input)));

    }

    @FXML
    private void handleLogIn(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        //get user from db
        Users user = usersDaoImpl.findByPropertyLike("username", username);
        System.out.println("user : "+user);

        if(user==null)
            user = usersDaoImpl.findByPropertyLike("email", username);

        if(user!=null && (username.equals(user.getUsername()) || username.equals(user.getEmail())) && password.equals(user.getPassword())) {
            //save session id in the SessionHandler
            if(sessionPreference) {
                SessionHandler.saveSessionId(SessionHandler.encryptSessionId(user.getUsername(), user.getPassword()));
            } else {
                SessionHandler.saveSessionId(SessionHandler.encryptSessionId(user.getUsername(),""));
            }
            SessionHandler.displaySessionId();

            System.out.println(SessionHandler.decrypt(SessionHandler.getSessionId()));

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
