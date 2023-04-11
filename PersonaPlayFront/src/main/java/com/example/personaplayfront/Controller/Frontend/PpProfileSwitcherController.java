package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.ImageHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class PpProfileSwitcherController {
    @FXML
    public Circle avatar_1;
    @FXML
    public Circle avatar_2;
    @FXML
    public Circle avatar_3;
    @FXML
    public Circle avatar_4;
    @FXML
    public Circle avatar_5;
    @FXML
    public Circle avatar_6;
    @FXML
    public Circle avatar_7;
    @FXML
    public Circle avatar_8;
    @FXML
    public Circle avatar_9;
    @FXML
    public Circle avatar_10;
    @FXML
    public GridPane img_grid;
    @FXML
    public Button variant_1_button;

    @FXML
    public Button variant_2_button;

    @FXML
    public Button variant_3_button;

    @FXML
    public Button variant_4_button;
    @FXML
    public Button confirm_button;

    //todo: get info from database
    int currentAvatar = 0;
    int currentVariant = 0;

    @FXML
    private Rectangle chosenImg;

    public void initialize() {
        // loop from 0 to 9 instead of using count variable
        for (int i = 0; i < 10; i++) {

            int count[] = {i};

            Circle avatar = Arrays.asList(avatar_1, avatar_2, avatar_3, avatar_4, avatar_5, avatar_6, avatar_7, avatar_8, avatar_9, avatar_10).get(i);

            //async
            ImageHandler.getImageAsync(i, 0,image -> avatar.setFill(new ImagePattern(image)));

            //on hover, set border color to cyan (mouse entered/exited)
            avatar.setOnMouseEntered(event -> {
                avatar.setStroke(javafx.scene.paint.Color.CYAN);

                //on mouse click, set avatar image to the one selected
                avatar.setOnMouseClicked(event1 -> {
                    //get avatar number from id
                    String avatarId = avatar.getId();

                    //get number after last underscore
                    currentAvatar = count[0]; //for input "avatar_1" returns 1

                    currentVariant = 0;

                    System.out.println("Avatar: " + currentAvatar + ", Variant: " + currentVariant);

                    //chosenImg.setFill(new ImagePattern(ImageHandler.getImage(currentAvatar, currentVariant)));
                    //async
                    ImageHandler.getImageAsync(currentAvatar, currentVariant, image -> chosenImg.setFill(new ImagePattern(image)));
                });
            });

            //#505050
            avatar.setOnMouseExited(event -> avatar.setStroke(javafx.scene.paint.Color.color(0.31, 0.31, 0.31)));
        }

        //set chosen_img (rectangle) to image in img_grid depending on avatar and variant
        //chosenImg.setFill(new ImagePattern(ImageHandler.getImage(currentAvatar,currentVariant)));
        //async
        ImageHandler.getImageAsync(currentAvatar, currentVariant, image -> chosenImg.setFill(new ImagePattern(image)));

        variant_1_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
        variant_2_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
        variant_3_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
        variant_4_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
        confirm_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");

        variant_1_button.setOnMouseEntered(event -> variant_1_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_2_button.setOnMouseEntered(event -> variant_2_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_3_button.setOnMouseEntered(event -> variant_3_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_4_button.setOnMouseEntered(event -> variant_4_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        confirm_button.setOnMouseEntered(event -> confirm_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));

        //mouse exited
        variant_1_button.setOnMouseExited(event -> variant_1_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_2_button.setOnMouseExited(event -> variant_2_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_3_button.setOnMouseExited(event -> variant_3_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        variant_4_button.setOnMouseExited(event -> variant_4_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        confirm_button.setOnMouseExited(event -> confirm_button.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));

    }

    public void handleVariant1(ActionEvent actionEvent) {
        currentVariant = 0;
//        chosenImg.setFill(new ImagePattern(ImageHandler.getImage(currentAvatar,0)));
        //async
        ImageHandler.getImageAsync(currentAvatar,0,image -> chosenImg.setFill(new ImagePattern(image)));
    }

    public void handleVariant2(ActionEvent actionEvent) {
        currentVariant = 1;
        ImageHandler.getImageAsync(currentAvatar,1,image -> chosenImg.setFill(new ImagePattern(image)));
    }

    public void handleVariant3(ActionEvent actionEvent) {
        currentVariant = 2;
        ImageHandler.getImageAsync(currentAvatar,2,image -> chosenImg.setFill(new ImagePattern(image)));
    }

    public void handleVariant4(ActionEvent actionEvent) {
        currentVariant = 3;
        ImageHandler.getImageAsync(currentAvatar,3,image -> chosenImg.setFill(new ImagePattern(image)));
    }

    @FXML
    public void handleConfirm(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_home_page.fxml"));
        Parent root = loader.load();
        PpHomePageController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) chosenImg.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
