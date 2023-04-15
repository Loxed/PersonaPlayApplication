package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PpLogInController {
    @FXML
    public CheckBox rememberMeCheckBox;

    @FXML
    public HBox page;
    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    private static boolean sessionPreference=false;

    UsersDaoImpl usersDaoImpl = new UsersDaoImpl();

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

        //set image background for page
        page.setStyle("-fx-background-image: url('/com/example/personaplayfront/Icon/personaPlayBg.jpg'); -fx-background-size: cover;");
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
