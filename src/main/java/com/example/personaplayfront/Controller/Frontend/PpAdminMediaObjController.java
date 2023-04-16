package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PpAdminMediaObjController {


    public HBox container;
    MediaDaoImpl mediaDao = new MediaDaoImpl();

    public Text title;


    public Text imdb_id;
    public Text year;
    public CheckBox visibleToggle;
    public CheckBox availableToggle;
    public Button locationButton;

    public void initialize() {

        //make sure the text doesnt overflow
        title.setWrappingWidth(180);
        imdb_id.setWrappingWidth(180);
        year.setWrappingWidth(180);

        //on visible toggle, update media
        visibleToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Medias media = mediaDao.findByPropertyLike("imdbId", imdb_id.getText());

            media.visible = newValue;

            mediaDao.update(media);
        });

        //on available toggle, update media
        availableToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Medias media = mediaDao.findByPropertyLike("imdbId", imdb_id.getText());

            media.available = newValue;

            mediaDao.update(media);
        });

        locationButton.setOnAction(event -> {
            // Create a new FileChooser object
            FileChooser fileChooser = new FileChooser();

            // Set the title of the file chooser dialog box
            fileChooser.setTitle("Select Location");

            // Show the dialog box and wait for the user to select a file
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            // If a file was selected, do something with it
            if (selectedFile != null) {
                // Get the path of the selected file and use it as the location
                String location = selectedFile.getAbsolutePath();

                Medias media = mediaDao.findByPropertyLike("imdbId", imdb_id.getText());

                media.mediaLocation = location;

                mediaDao.update(media);

            }
        });
    }

    //set media data
    public void setMedia(Medias media) {
        title.setText(media.mediaName);
        imdb_id.setText(media.imdbId);
        year.setText(media.year);
        visibleToggle.setSelected(media.visible);
        availableToggle.setSelected(media.available);
    }
}
