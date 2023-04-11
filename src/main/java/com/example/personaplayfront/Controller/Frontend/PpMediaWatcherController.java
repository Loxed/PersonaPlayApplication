package com.example.personaplayfront.Controller.Frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class PpMediaWatcherController {

    @FXML
    public ImageView rewindButton;
    @FXML
    public ImageView playButton;
    @FXML
    public ImageView fastForwardButton;
    @FXML
    public ImageView volumeButton;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Text progressTimestamp;
    @FXML
    public VBox myVbox;
    boolean isPlaying = false;
    boolean isMuted = false;
    boolean loadValid;

    boolean isFast = false;
    private int mediaTime;
    private File f;

    private MediaPlayer mp;

    private Media m;

    @FXML
    private MediaView mediaView;


    private Timeline timeline;

    Popup popup;

    @FXML
    public void initialize() throws IOException {

        //todo: get media path from database
        String path = "src/main/resources/com/example/personaplayfront/media/video.mp4";

        //loadMedia(path,true);
        loadMedia("https://www.youtube.com/watch?v=L0fw0WzFaBM",false);

        FileInputStream input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_pause-outline.png");

        //set play button to play
        playButton.setImage(new Image(input));

        //set volume button to volume high
        input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_volume-high-outline.png");
        volumeButton.setImage(new Image(input));

        //set rewind button to rewind
        input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_play-back-outline.png");
        rewindButton.setImage(new Image(input));

        //set fast forward button to fast forward
        input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_play-forward-outline.png");
        fastForwardButton.setImage(new Image(input));

        //make timeline for progress bar and timestamp
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING) {
                double currentTime = mp.getCurrentTime().toMillis();
                double duration = mp.getTotalDuration().toMillis();
                double progress = currentTime / duration;

                progressBar.setProgress(progress);

                String timestamp = formatTimestamp(currentTime) + " / " + formatTimestamp(duration);
                progressTimestamp.setText(timestamp);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        progressBar.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double progressBarWidth = progressBar.getWidth();
            double newMediaTime = (mouseX / progressBarWidth) * mp.getTotalDuration().toMillis();
            mp.seek(Duration.millis(newMediaTime));
        });

        myVbox.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            System.out.println("key pressed");
            switch (event.getCode()) {
                case SPACE:
                    event.consume();   // <-- stops passing the event to next node
                    togglePlay(null);
                    break;
                case LEFT:
                    mp.seek(mp.getCurrentTime().subtract(Duration.seconds(5)));
                    break;
                case RIGHT:
                    mp.seek(mp.getCurrentTime().add(Duration.seconds(10)));
                    break;
                //escape key
                case ESCAPE:
                    //close media
                    mp.stop();
                    //close window
                    Stage stage = (Stage) mediaView.getScene().getWindow();
                    stage.close();
                    break;
                //case 0 to 9 for % of progress bar
                case DIGIT0:
                    mp.seek(mp.getMedia().getDuration().multiply(0));
                    break;
                case DIGIT1:
                    mp.seek(mp.getMedia().getDuration().multiply(0.1));
                    break;
                case DIGIT2:
                    mp.seek(mp.getMedia().getDuration().multiply(0.2));
                    break;
                case DIGIT3:
                    mp.seek(mp.getMedia().getDuration().multiply(0.3));
                    break;
                case DIGIT4:
                    mp.seek(mp.getMedia().getDuration().multiply(0.4));
                    break;
                case DIGIT5:
                    mp.seek(mp.getMedia().getDuration().multiply(0.5));
                    break;
                case DIGIT6:
                    mp.seek(mp.getMedia().getDuration().multiply(0.6));
                    break;
                case DIGIT7:
                    mp.seek(mp.getMedia().getDuration().multiply(0.7));
                    break;
                case DIGIT8:
                    mp.seek(mp.getMedia().getDuration().multiply(0.8));
                    break;
                case DIGIT9:
                    mp.seek(mp.getMedia().getDuration().multiply(0.9));
                    break;
                    //m for mute
                case M:
                    toggleVolume(null);
                    break;
                    //x and z for 10s forward and back
                case X:
                    mp.seek(mp.getCurrentTime().add(Duration.seconds(10)));
                    break;
                case Z:
                    mp.seek(mp.getCurrentTime().subtract(Duration.seconds(10)));
                    break;
                    //g for togglespeed
                case G:
                    toggleSpeed();
                    speedTooltip();
                    break;
                    // a and d for 0.1 increments to speed, make sure to check if speed is 0
                case A:
                    if (mp.getRate() <= 0.1) {
                        mp.setRate(0.1);
                    } else {
                        mp.setRate(mp.getRate() - 0.1);
                    }
                    speedTooltip();
                    break;
                case D:
                    mp.setRate(mp.getRate() + 0.1);
                    speedTooltip();
                    break;

                default:
                    // Do nothing
            }

            //instantly update progress bar and timestamp
            double currentTime = mp.getCurrentTime().toMillis();
            double duration = mp.getTotalDuration().toMillis();
            double progress = currentTime / duration;

            progressBar.setProgress(progress);

            String timestamp = formatTimestamp(currentTime) + " / " + formatTimestamp(duration);
            progressTimestamp.setText(timestamp);
        });
    }



    private String formatTimestamp(double milliseconds) {
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) ((milliseconds % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((milliseconds % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void loadMedia(String path,boolean isLocal) {
        //todo: get mediatime from db
        mediaTime = 0;
        try {

            if(isLocal){
                System.out.println("local path:"+path);

                f = new File(path);
                m = new Media(f.toURI().toString());
            } else {
                System.out.println("web path:"+path);
                m = new Media(path);
            }

            mp = new MediaPlayer(m);

            mediaView.setMediaPlayer(mp);
            mp.setAutoPlay(true);
            isPlaying = true;
            //setsound to max
            mp.setVolume(1.0);

            loadValid = true;

            if(mediaTime > 0){
                //set media position to mediaTime
                mp.seek(mp.getMedia().getDuration().multiply(mediaTime));
            }
        } catch (Exception e) {
            //alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            loadValid = false;
            e.printStackTrace();
        }
        System.out.println("loadValid:"+loadValid);
        System.out.println("mediaView:"+mediaView);
    }
    @FXML
    public void togglePlay(ActionEvent actionEvent) {
        try {
            FileInputStream input;

            if (isPlaying) {
                //pause
                mediaView.getMediaPlayer().pause();
                isPlaying = false;

                //set play button to pause
                input= new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_play-outline.png");
                playButton.setImage(new Image(input));
            } else {
                //play
                mediaView.getMediaPlayer().play();
                isPlaying = true;
                input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_pause-outline.png");
                playButton.setImage(new Image(input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void toggleVolume(ActionEvent actionEvent) {
        try {
            FileInputStream input;

            if (isMuted) {
                //unmute
                mediaView.getMediaPlayer().setMute(false);
                isMuted = false;
                input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_volume-high-outline.png");
                volumeButton.setImage(new Image(input));
            } else {
                //mute
                mediaView.getMediaPlayer().setMute(true);
                isMuted = true;
                input = new FileInputStream("src/main/resources/com/example/personaplayfront/Icon/white_volume-mute-outline.png");
                volumeButton.setImage(new Image(input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void fastForward(ActionEvent actionEvent) {
        //increment 10s
        mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getCurrentTime().add(javafx.util.Duration.seconds(10)));
    }
    @FXML
    public void rewind(ActionEvent actionEvent) {
        //decrement 10s
        mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getCurrentTime().subtract(javafx.util.Duration.seconds(10)));
    }

    //toggle speed
    public void toggleSpeed() {
        if(isFast){
            //set speed to normal
            mediaView.getMediaPlayer().setRate(1.0);
            isFast = false;
        }else{
            //set speed to fast
            mediaView.getMediaPlayer().setRate(1.8);
            isFast = true;
        }
    }

    //drawspeed tooltip when toggling speed
    public void speedTooltip() {
        // Create a new Text node with the speed value (1 digit after the decimal point if < 1)
        Text text = new Text(String.format("%.1f", mp.getRate()));

        text.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set the font and size
        text.setFill(Color.WHITE); // Set the text color
        text.setStroke(Color.BLACK); // Set the text stroke color
        text.setStrokeWidth(1); // Set the text stroke width

        //if popup is already open, close it
        if(popup != null){
            popup.hide();
        }
        // Create a new Popup
        popup = new Popup();
        popup.setAutoHide(true); // Hide the Popup when the user clicks outside of it
        popup.setOpacity(0.8); // Set the Popup opacity to 0.8

        // Add the Text node to a new Pane
        Pane pane = new Pane(text);

        // Set the size of the Pane to the size of the Text node plus padding
        double width = text.getLayoutBounds().getWidth() + 20;
        double height = text.getLayoutBounds().getHeight() + 20;

        pane.setPrefSize(0, 0);
        //position pane at 10,10 in screen
        pane.setLayoutX(10);
        pane.setLayoutY(10);

        //set text position inside pane
        text.setLayoutX(0);
        text.setLayoutY(0);

        // Set the content of the Popup to the new Pane
        popup.getContent().add(pane);

        // Set the position of the Popup
        double x = mediaView.localToScreen(mediaView.getBoundsInLocal()).getMinX() + 10;
        double y = mediaView.localToScreen(mediaView.getBoundsInLocal()).getMinY() + 10;
        popup.setX(x);
        popup.setY(y);

        // Show the Popup
        popup.show(mediaView.getScene().getWindow());

        // Close the Popup after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> popup.hide()));
        timeline.play();
    }

}
