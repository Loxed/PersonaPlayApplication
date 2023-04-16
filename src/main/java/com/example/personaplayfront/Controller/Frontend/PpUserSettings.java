package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PpUserSettings {

    public CheckBox matureToggle;
    public Button passwordButton;
    UsersDaoImpl userDao = new UsersDaoImpl();

    public TextField username;
    public TextField usermail;

    public TextField oldPassword;

    public TextField newPassword;
    public TextField repeatPassword;

    public void initialize() {

        //TODO: get user data from database thanks to the session

        username.setText(SessionHandler.decryptSessionId(SessionHandler.getSessionId())[0]);

        Users user = userDao.findByPropertyLike("username", username.getText());

        usermail.setText(user.getEmail());

        matureToggle.setSelected(user.isContentFilter());

        //on mature toggle change
        matureToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            user.setContentFilter(newValue);
            userDao.update(user);
        });

        //on password button click
        passwordButton.setOnAction(event -> {
            changePassword();
        });

        passwordButton.setOnMouseEntered(event -> {
            passwordButton.setBorder(new Border(new BorderStroke(Color.web("#00ffff"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
        });

        passwordButton.setOnMouseExited(event -> {
            passwordButton.setBorder(new Border(new BorderStroke(Color.web("#202020"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
        });

    }

    public void changePassword() {
        //make sure old password is correct
        Users user = userDao.findByPropertyLike("username", username.getText());

        if (user.getPassword().equals(oldPassword.getText())) {
            //make sure new password and repeat password are the same, and not empty, and regex is correct
            //regex: At least 8 characters long, at least one uppercase letter, at least one lowercase letter, at least one number, at least one special character.
            String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
            if (newPassword.getText().equals(repeatPassword.getText()) && !newPassword.getText().isEmpty() && newPassword.getText().matches(regex)) {
                user.setPassword(newPassword.getText());
                userDao.update(user);
                //Alert user that password has been changed
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password changed successfully");
                alert.setHeaderText("Your password has been changed successfully");
                alert.showAndWait();
            } else {
                //Alert user that password not the same, or not corresponding to regex
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(" Either the new password and the repeated password are not the same, or the new password does not match the regex requirements.");
                alert.setHeaderText("Regex requirements: ");
                alert.setContentText("At least 8 characters long, at least one uppercase letter, at least one lowercase letter, at least one number, at least one special character.");
                alert.showAndWait();
            }
        } else {
            //Alert user that old password is not correct
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Old password is not correct");
            alert.setHeaderText("Please enter your old password correctly. If you have forgotten your password, please log out, and reset your password via email.");
            alert.showAndWait();
        }
    }

}

