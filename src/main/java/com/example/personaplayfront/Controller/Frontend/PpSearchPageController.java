package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.ApiHandler;
import com.example.personaplayfront.Model.Medias;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public VBox CarouselContainer;
    public ScrollPane containerScroll;
    public Button onlineButton;
    public ImageView wifiImage;
    MediaDaoImpl mediaDao;
    List<Medias> medias = new ArrayList<>();

    public void initialize() throws FileNotFoundException {

        mediaDao = new MediaDaoImpl();

        //set style
        searchBar.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #202020; -fx-background-radius: 25;");

        //on key enter search
        searchBar.setOnAction(this::search);

        onlineButton.setVisible(false);
        onlineButton.setDisable(true);

        //on hover
        onlineButton.setOnMouseEntered(event -> {
            onlineButton.setStyle("-fx-background-color: #202020; -fx-border-color: #00FFFF; -fx-border-radius: 25px; -fx-border-width: 1px; -fx-background-radius: 24px");
        });

        //on hover exit
        onlineButton.setOnMouseExited(event -> {
            onlineButton.setStyle("-fx-background-color: #202020; -fx-border-color: #202020; -fx-border-width: 0px; -fx-background-radius: 25px");
        });
    }

    public void search(ActionEvent actionEvent) {
        System.out.println("searching for: " + searchBar.getText());

        CarouselContainer.getChildren().clear();

        int total_count = 0;

        {
            //get movies from based on search
            medias = mediaDao.findAllByPropertyLike("mediaName", searchBar.getText());

            //remove medias.visible = false
            medias.removeIf(media -> !media.visible);

            System.out.println(medias);

            //if not null, display in carousel
            if (medias != null) {

                containerScroll.setVvalue(0);

                try {
//                    CarouselContainer.getChildren().clear();

                    System.out.println("medias size: " + medias.size());

                    //shrink medias to 30 max
                    if (medias.size() > 20) {
                        medias = medias.subList(0, 20);
                    }

                    total_count += medias.size();

                    //depending on how mant movies are found add n/6 carousels
                    for (int i = 0; i < medias.size(); i++) {

                        System.out.println("page: " + (int) i / 6);

                        if (i % 6 == 0) {

                            //load carousel
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_carousel.fxml"));

                            CarouselContainer.getChildren().add(loader.load());
                            PpSearchCarouselController controller = loader.getController();

                            controller.setCategoryName("By Title");

                            //if i=0
                            if (i == 0) {
                                controller.CategoryBox.setVisible(true);
                            }

                            //setmedias, but a max of 6 without going out of bounds
                            if (medias.size() - i > 6) {
                                controller.setMedias(medias.subList(i, i + 6));
                            } else {
                                controller.setMedias(medias.subList(i, medias.size()));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        {
            //get movies from based on search
            medias = mediaDao.findAllByPropertyLike("genres", searchBar.getText());

            //remove medias.visible = false
            medias.removeIf(media -> !media.visible);

            System.out.println(medias);

            //if not null, display in carousel
            if (medias != null) {

                containerScroll.setVvalue(0);

                try {
//                    CarouselContainer.getChildren().clear();

                    System.out.println("medias size: " + medias.size());

                    //shrink medias to 30 max
                    if (medias.size() > 20) {
                        medias = medias.subList(0, 20);
                    }

                    total_count += medias.size();


                    //depending on how mant movies are found add n/6 carousels
                    for (int i = 0; i < medias.size(); i++) {

                        System.out.println("page: " + (int) i / 6);

                        if (i % 6 == 0) {

                            //load carousel
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_carousel.fxml"));

                            CarouselContainer.getChildren().add(loader.load());
                            PpSearchCarouselController controller = loader.getController();

                            controller.setCategoryName("By Genre");

                            //if i=0
                            if (i == 0) {
                                controller.CategoryBox.setVisible(true);
                            }

                            //setmedias, but a max of 6 without going out of bounds
                            if (medias.size() - i > 6) {
                                controller.setMedias(medias.subList(i, i + 6));
                            } else {
                                controller.setMedias(medias.subList(i, medias.size()));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        {
            //get movies from based on search
            medias = mediaDao.findAllByPropertyLike("actors", searchBar.getText());

            //remove medias.visible = false
            medias.removeIf(media -> !media.visible);

            System.out.println(medias);

            //if not null, display in carousel
            if (medias != null) {

                containerScroll.setVvalue(0);

                try {
//                    CarouselContainer.getChildren().clear();

                    System.out.println("medias size: " + medias.size());

                    //shrink medias to 30 max
                    if (medias.size() > 20) {
                        medias = medias.subList(0, 20);
                    }

                    total_count += medias.size();

                    //depending on how mant movies are found add n/6 carousels
                    for (int i = 0; i < medias.size(); i++) {

                        System.out.println("page: " + (int) i / 6);

                        if (i % 6 == 0) {

                            //load carousel
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_carousel.fxml"));

                            CarouselContainer.getChildren().add(loader.load());
                            PpSearchCarouselController controller = loader.getController();

                            controller.setCategoryName("By Actor");

                            //if i=0
                            if (i == 0) {
                                controller.CategoryBox.setVisible(true);
                            }

                            //setmedias, but a max of 6 without going out of bounds
                            if (medias.size() - i > 6) {
                                controller.setMedias(medias.subList(i, i + 6));
                            } else {
                                controller.setMedias(medias.subList(i, medias.size()));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        if(total_count <= 10) {
            //set onlineButton to visible
            onlineButton.setVisible(true);
            //enable
            onlineButton.setDisable(false);
        } else {
            onlineButton.setVisible(false);
            //disable
            onlineButton.setDisable(true);
        }
    }


    public void onlineSearch(ActionEvent actionEvent) {
        //search using api
        try {
            //get movies from based on search
            medias = ApiHandler.OMDBFindAllByTitleLike(searchBar.getText());

            //remove medias.visible = false
            medias.removeIf(media -> !media.visible);

            System.out.println(medias);

            //if not null, display in carousel
            if (medias != null) {

                try {

                    System.out.println("medias size: " + medias.size());

                    //shrink medias to 30 max
                    if (medias.size() > 20) {
                        medias = medias.subList(0, 20);
                    }

                    //depending on how mant movies are found add n/6 carousels
                    for (int i = 0; i < medias.size(); i++) {

                        System.out.println("page: " + (int) i / 6);

                        if (i % 6 == 0) {

                            //load carousel
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_carousel.fxml"));

                            CarouselContainer.getChildren().add(loader.load());
                            PpSearchCarouselController controller = loader.getController();

                            controller.setCategoryName("By Online Search");

                            //if i=0
                            if (i == 0) {
                                controller.CategoryBox.setVisible(true);
                            }

                            //setmedias, but a max of 6 without going out of bounds
                            if (medias.size() - i > 6) {
                                controller.setMedias(medias.subList(i, i + 6));
                            } else {
                                controller.setMedias(medias.subList(i, medias.size()));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

