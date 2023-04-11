package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.ImageHandler;
import com.example.personaplayfront.Controller.Handler.SessionHandler;
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
    public HBox passwordBox;
    @FXML
    public HBox profileBox;
    @FXML
    public HBox overviewBox;


    @FXML
    Circle PersonaPlayLogo;

    @FXML
    public void initialize() throws FileNotFoundException {
        Parent root;
        //display media description
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_settings_container.fxml"));
//            root = loader.load();
//            PpMediaDescriptionController movieDescriptionController = loader.getController();
//            settings_page.getChildren().add(root);
//            //todo: set movie details based on the movie that was clicked on, and db
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("src/main/resources/com/example/personaplayfront/Icon/PersonaPlayLogo.png"));

        FileInputStream reader = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/PersonaPlayLogo.png");
        //set logo
        PersonaPlayLogo.setFill(new ImagePattern(new Image(reader)));
//
//        reader = new FileInputStream("src/main/resources/com/example/personaplayfront/Font/p5hatty.ttf");
//
//        Font font = Font.loadFont(reader, 18);

        //personaPlayText.setFont(font);

        Arrays.asList(homeBox, privacyPolicyBox, subsBox, passwordBox, profileBox, overviewBox)
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
                                case "Password" -> {
//                                    try {
//                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_password.fxml"));
//                                        SessionHandler.getMainStage().getScene().setRoot(root1);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                                case "Profile" -> {
//                                    try {
//                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_profile.fxml"));
//                                        SessionHandler.getMainStage().getScene().setRoot(root1);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                                case "Overview" -> {
//                                    try {
//                                        Parent root1 = FXMLLoader.load(getClass().getResource("/com/example/personaplayfront/Vue/pp_overview.fxml"));
//                                        SessionHandler.getMainStage().getScene().setRoot(root1);
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                    }
                                }
                            }
                        });
                    }
                }));


        Arrays.asList(homeBox, privacyPolicyBox, subsBox, passwordBox, profileBox, overviewBox)
                .forEach(node -> node.setOnMouseExited(event4 -> {
                    if (node.getChildren().size() > 1 && node.getChildren().get(1) instanceof Text text) {
                        //Eras Bold ITC
                        text.setFont(Font.font("Eras Light ITC", 18.5));
                    }
                }));

    }
}
