package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class PpMediaDescriptionController {

    @FXML
    public Text title;
    @FXML
    public Text plot;
    @FXML
    public Text actors;
    @FXML
    public Text genres;

    @FXML
    public Button play_media;

    @FXML
    public Pane color_gradient;

    @FXML
    private ImageView poster;

    @FXML
    private Pane movie_desc_window;

    @FXML
    private Pane my_gradient;

    String media_link;
    //init
    @FXML
    public void initialize() {
        updateInfo(new Medias());
    }

    @FXML
    public void playMedia(ActionEvent actionEvent) {
        //if the selected media is available, play it
        if(!play_media.isDisabled()){
            //create a new window with the media player
            //switch to the media player
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
            try {
                //Create a new stage
                Stage stage = new Stage();

                //Set the scene to the stage
                stage.setScene(new Scene(loader.load()));

                //get the controller
                PpMediaWatcherController controller = loader.getController();
                //set the media
                controller.loadMedia(media_link);

                System.out.println(controller);

                //Set the title of the stage
                stage.setTitle("PersonaPlayer");
                //size 1280x720
                stage.setMinWidth(1280);
                stage.setMinHeight(720);
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //if mouse clicked and on top of a hbox, change color to #2b2b2b
    @FXML
    public void mouseEntered(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof HBox){
            HBox hBox = (HBox) mouseEvent.getSource();
            hBox.setStyle("-fx-background-color: #2b2b2b;");
        }
    }

    //if mouse exited and on top of a hbox, change color to #212121
    @FXML
    public void mouseExited(MouseEvent mouseEvent) {
            HBox hBox = (HBox) mouseEvent.getSource();
            hBox.setStyle("-fx-background-color: #212121;");

    }

    public void updateInfo(Medias medias) {

        Image image = new Image(medias.posterUrl);

        //measure the image width from the current image and height scale to 450
        double width = image.getWidth() * 450 / image.getHeight();

        //get the distance between 1080 and the width of the image
        double diff = 1080 - width; //diff = 480

        // Create a Region for the gradient
        Region gradientRegion = new Region();
        gradientRegion.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                        //212121
                        new Stop(0, Color.color(0.13, 0.13, 0.13)),
                        //the stop needs to be where the poster begins, so we will use the diff
                        new Stop(0 + diff / 1080, Color.color(0.13, 0.13, 0.13)),
                        new Stop(1, Color.TRANSPARENT)
                ), CornerRadii.EMPTY, Insets.EMPTY)));

        // Set the gradient region to be the same size as the movie description window
        gradientRegion.setPrefSize(1080, 450);

        //set gradient to my_gradient
        my_gradient.setBackground(gradientRegion.getBackground());

        // Set the background image for the movie description window
        movie_desc_window.setStyle("-fx-background-image: url(" + medias.posterUrl + "); " +
                "-fx-background-position: " + diff + "px 0px; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-color: #212121; " +
                "-fx-background-size: auto 450px;");

        title.setText(medias.mediaName);

        //set plot
        plot.setText(medias.plot);

        //set actors
        actors.setText(medias.actors);

        //set genres
        genres.setText(medias.genres);


        //if the medias is available, set the button to enabled
        if(medias.available){

            //load medias.media_location into a javafx Medias
            File file = new File(medias.mediaLocation);
            try {
                Media media1 = new Media(file.toURI().toURL().toString());

                System.out.println(media1);

                    play_media.setDisable(false);

                    media_link = medias.mediaLocation;

                    play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
                    //on hover, set contour to cyan with 2px
                    play_media.setOnMouseEntered(event -> play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
                    //on exit, set contour to transparent
                    play_media.setOnMouseExited(event -> play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));

            } catch (Exception e) {
               //e.printStackTrace();

                play_media.setDisable(true);
            }

        }else{
            play_media.setDisable(true);
        }

    }
}

