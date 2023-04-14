package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.personaplayfront.Repo.MediaDaoImpl;

public class PpSearchPageController {
    public TextField searchBar;
    public Button searchButton;

    public VBox searchContainer;

    public MediaDaoImpl mediaDao = new MediaDaoImpl();
    List<Medias> medias = new ArrayList<>();

    public void initialize() throws FileNotFoundException {

        //set style
        searchBar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #202020; -fx-background-radius: 25;");

    }

    public void search(ActionEvent actionEvent) {
        //todo: search for movies using value in search bar
        System.out.println("searching for: " + searchBar.getText());

        //get movies from based on search
        medias = mediaDao.findAllByPropertyLike("mediaName", searchBar.getText());

        System.out.println(medias);

        //if not null, display in carousel
        if(medias != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_carousel.fxml"));
            try {

                searchContainer.getChildren().add(loader.load());


//                PpSearchCarouselContainer searchCarouselController = loader.getController();
//                searchCarouselController.setMedias(medias);
//
//                System.out.println("searching for: " + searchBar.getText());
//
//                searchContainer.getChildren().add(loader.load());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
