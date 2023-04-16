package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.ImageHandler;
import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.Roles;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.RolesDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class PpSettingsController {


    @FXML
    public VBox settings_page;
    @FXML
    public Text personaPlayText;

    @FXML
    public HBox settingsContainer;
    @FXML
    public HBox homeBox;
    @FXML
    public HBox privacyPolicyBox;
    @FXML
    public HBox subsBox;
    @FXML
    public HBox adminBox;
    @FXML
    public HBox profileBox;
    @FXML
    public HBox overviewBox;

    @FXML
    Circle PersonaPlayLogo;

    UsersDaoImpl userDao = new UsersDaoImpl();
    @FXML
    public void initialize() throws FileNotFoundException {
        Parent root;

        String username = SessionHandler.decryptSessionId(SessionHandler.getSessionId())[0];

        Users user = userDao.findByPropertyLike("username", username);

        if(user.getRole().getName().equals("admin")) {
            adminBox.setVisible(true);
            overviewBox.setVisible(true);
        } else {
            adminBox.setVisible(false);
            overviewBox.setVisible(false);
        }

        //InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("src/main/resources/com/example/personaplayfront/Icon/PersonaPlayLogo.png"));

        FileInputStream reader = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/PersonaPlayLogo.png");
        //set logo
        PersonaPlayLogo.setFill(new ImagePattern(new Image(reader)));

        Arrays.asList(homeBox, privacyPolicyBox, subsBox, adminBox, profileBox, overviewBox)
                .forEach(node -> node.setOnMouseEntered(event3 -> {
                    if (node.getChildren().size() > 1 && node.getChildren().get(1) instanceof Text text) {
                        //Eras Bold ITC
                        text.setFont(Font.font("Eras Medium ITC", 18.5));

                        //on mouse clicked
                        node.setOnMouseClicked(event1 -> {
                            //switch to corresponding view when clicking on the box
                            System.out.println(text.getText());

                            switch (text.getText()) {

                                case "Return Home" -> {
                                    try {
                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_home_page.fxml"));
                                        Scene scene = new Scene(root1);
                                        Stage stage = (Stage) settingsContainer.getScene().getWindow();
                                        stage.setScene(scene);
                                        stage.show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case "Privacy Policy" -> {
                                    try {
                                        Parent root2 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_privacy_policy.fxml"));
                                        //set inside settings container as child
                                        settingsContainer.getChildren().clear();
                                        settingsContainer.getChildren().add(root2);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case "Subscriptions" -> {
//                                    try {
//                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_subscriptions.fxml"));
//                                        SessionHandler.getMainStage().getScene().setRoot(root1);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                                case "Admin" -> {
                                    try {
                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_admin_settings.fxml"));
                                        settingsContainer.getChildren().clear();
                                        settingsContainer.getChildren().add(root1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case "Profile" -> {
                                    try {
                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_user_settings.fxml"));
                                        settingsContainer.getChildren().clear();
                                        settingsContainer.getChildren().add(root1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case "Overview" -> {
                                    try {
                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_admin_overview.fxml"));
                                        settingsContainer.getChildren().clear();
                                        settingsContainer.getChildren().add(root1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }));


        Arrays.asList(homeBox, privacyPolicyBox, subsBox, adminBox, profileBox, overviewBox)
                .forEach(node -> node.setOnMouseExited(event4 -> {
                    if (node.getChildren().size() > 1 && node.getChildren().get(1) instanceof Text text) {
                        //Eras Bold ITC
                        text.setFont(Font.font("Eras Light ITC", 18.5));
                    }
                }));

    }
}
