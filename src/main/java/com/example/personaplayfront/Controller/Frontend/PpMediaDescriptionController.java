package com.example.personaplayfront.Controller.Frontend;

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
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private ImageView poster;

    @FXML
    private Pane movie_desc_window;

    @FXML
    private Pane my_gradient;

    private String _title = "Battle Royale";

    private String _plot= "In the future, the Japanese government captures a class of ninth-grade students and forces them to kill each other under the revolutionary " +
            "B.R. Act. One hundred and eighty-two students are forced to fight to the death on an isolated island until only one remains. The lone survivor will have " +
            "their every wish granted.";

    private String _actors = "Tatsuya Fujiwara, Aki Maeda, Takeshi Kitano, Taro Yamamoto";

    private String _genres = "Action, Drama, Sci-Fi";
    //init
    @FXML
    public void initialize() throws MalformedURLException {
        URL url = new URL("https://m.media-amazon.com/images/M/MV5BMDc2MGYwYzAtNzE2Yi00YmU3LTkxMDUtODk2YjhiNDM5NDIyXkEyXkFqcGdeQXVyMTEwNDU1MzEy._V1_SX300.jpg");
        //URL url = new URL("https://m.media-amazon.com/images/M/MV5BZDZiYjc3MWYtODE5Mi00MDM5LWFkZTAtNjAzZmUxMzc4ZGQxL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg");
        //create image from url
        Image image = new Image(url.toString());

        //measure the image width from the current image and height scale to 450
        double width = image.getWidth()*450/image.getHeight();

        //get the distance between 1080 and the width of the image
        double diff = 1080 - width; //diff = 480

        // Create a Region for the gradient
        Region gradientRegion = new Region();
        gradientRegion.setBackground(new Background(new BackgroundFill(
                new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                        //212121
                        new Stop(0, Color.color(0.13, 0.13, 0.13)),
                        //the stop needs to be where the poster begins, so we will use the diff
                        new Stop(0+diff/1080, Color.color(0.13, 0.13, 0.13)),
                        new Stop(1, Color.TRANSPARENT)
                ), CornerRadii.EMPTY, Insets.EMPTY)));

        // Set the gradient region to be the same size as the movie description window
        gradientRegion.setPrefSize(1080, 450);

        //set gradient to my_gradient
        my_gradient.setBackground(gradientRegion.getBackground());

        // Set the background image for the movie description window
        movie_desc_window.setStyle("-fx-background-image: url(" + url.toString() + "); " +
                "-fx-background-position: "+diff+"px 0px; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-color: #212121; " +
                "-fx-background-size: auto 450px;");

        updatePage();

        play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 1px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;");
        //on hover, set contour to cyan with 2px
        play_media.setOnMouseEntered(event -> play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #00ffff; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));
        //on exit, set contour to transparent
        play_media.setOnMouseExited(event -> play_media.setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 5px; -fx-border-color: #363636; -fx-border-radius: 2px; -fx-text-fill: #ffffff; -fx-padding: 5px 10px;"));

        //make it so that the hboxes that contain an icon and text are interactive using the mouseEntered
        setInteractions();
        //on hover, set contour to cyan with 2px
        //on exit, set contour to transparent
    }

    private void setInteractions() {
        //for every "button" inside a hbox, set the mouseEntered and mouseExited events

    }

    //update info
    // TODO: update content inside the description with backend data
    private void updatePage() {
        //set title
        title.setText(_title);
        //set position of title

        //set plot
        plot.setText(_plot);

        //set actors
        actors.setText(_actors);

        //set genres
        genres.setText(_genres);

        boolean available = false;
        //set play media button to unavailable
        play_media.setDisable(available);
    }

    @FXML
    public void playMedia(ActionEvent actionEvent) {
        //if the selected media is available, play it
        if(!play_media.isDisabled()){
            //create a new window with the media player

            //todo: get media link
            String media_link = "src/main/resources/com/example/personaplayfront/media/video.mp4";
            //switch to the media player
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
            try {
                //Create a new stage
                Stage stage = new Stage();

                //get the controller
                PpMediaWatcherController controller = loader.getController();
                System.out.println(controller);
                //Set the scene to the stage
                stage.setScene(new Scene(loader.load()));
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

}
