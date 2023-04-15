package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class PpMediaCarouselController {

    @FXML
    public VBox Box1;

    @FXML
    public VBox Box2;

    @FXML
    public VBox Box3;

    @FXML
    public VBox Box4;

    @FXML
    public VBox Box5;

    @FXML
    public VBox Box6;

    @FXML
    public ImageView img1;

    @FXML
    public ImageView img2;

    @FXML
    public ImageView img3;

    @FXML
    public ImageView img4;

    @FXML
    public ImageView img5;
    @FXML
    public AnchorPane movieCarousel;

    //carousel counter
    int counter = 0;

    //number of medias found
    int numberOfMedias = 0;

    @FXML
    public ImageView img6;
    public Text categoryName;
    public Button previousButton;
    public Button nextButton;

    List<Medias> carouselPage;

    MediaDaoImpl mediaDaoImpl = new MediaDaoImpl();

    PpMediaDescriptionController container;

    //Parent controller
    PpHomePageController parentController;

    //init
    public void initialize() {

        List<Image> images = new ArrayList<Image>();

        categoryName.setText("Drama");

        //do this in a thread
        //get carousel page
        Thread thread = new Thread(this::getCarouselPage);

        thread.start();

        //disable previous button
        previousButton.setDisable(true);

        //I want a rounded button with background #2d2d2d
        previousButton.setStyle("-fx-background-radius: 50em; " +
                "-fx-min-width: 40px; " +
                "-fx-min-height: 40px; " +
                "-fx-max-width: 40px; " +
                "-fx-max-height: 40px; " +
                "-fx-background-color: #2d2d2d;");

        nextButton.setStyle("-fx-background-radius: 50em; " +
                "-fx-min-width: 40px; " +
                "-fx-min-height: 40px; " +
                "-fx-max-width: 40px; " +
                "-fx-max-height: 40px; " +
                "-fx-background-color: #2d2d2d;");

        handleInteractions();

    }

    //get carousel page
    public void getCarouselPage() {
        //select medias from db based on category name
        List<Medias> medias = mediaDaoImpl.findAllByPropertyLike("genres", categoryName.getText());

        //remove from list every media that is not visible
        medias.removeIf(media -> !media.visible);

        numberOfMedias = medias.size();

        //if number of medias is < 6, disable next button
        if(numberOfMedias <= 6) {
            nextButton.setDisable(true);
        }

        System.out.println(categoryName.getText()+" medias: " + numberOfMedias);

        if(medias == null) {
            medias = new ArrayList<Medias>();
        }

        carouselPage = new ArrayList<Medias>();

        //make sure coutner doesnt go under 0
        if(counter <= 0) {
            counter = 0;
            previousButton.setDisable(true);
        }

        //get 6 medias from db
        while (counter < numberOfMedias && carouselPage.size() < 6) {
            carouselPage.add(medias.get(counter));
            counter++;
        }
        counter -= 6;

        System.out.println("CarouselPage: " + carouselPage);

        //todo, load images async
        //update images 1-6

        if (carouselPage.size() > 0) {
            img1.setImage(new Image(carouselPage.get(0).posterUrl));
        }
        if (carouselPage.size() > 1) {
            img2.setImage(new Image(carouselPage.get(1).posterUrl));
        }
        if (carouselPage.size() > 2) {
            img3.setImage(new Image(carouselPage.get(2).posterUrl));
        }
        if (carouselPage.size() > 3) {
            img4.setImage(new Image(carouselPage.get(3).posterUrl));
        }
        if (carouselPage.size() > 4) {
            img5.setImage(new Image(carouselPage.get(4).posterUrl));
        }
        if (carouselPage.size() > 5) {
            img6.setImage(new Image(carouselPage.get(5).posterUrl));
        }

    }

    //action for next button
    public void nextButtonAction() {
        counter += 6;

        Thread thread = new Thread(this::getCarouselPage);

        thread.start();

        previousButton.setDisable(false);

        //if counter > nummedias, disable next button
        if(counter > numberOfMedias) {
            nextButton.setDisable(true);
        }
    }

    //action for previous button
    public void previousButtonAction() {

        counter -= 6;

        Thread thread = new Thread(this::getCarouselPage);

        thread.start();


        if(counter <= 0) {
            counter = 0;
            previousButton.setDisable(true);
        }

        if(counter < numberOfMedias) {
            nextButton.setDisable(false);
        }
    }

    private void handleInteractions() {
        //set on hover for each image
        img1.setOnMouseEntered(event -> {

//            //zoom in, without disturbing the layout
//            img1.setFitWidth(170);
//            img1.setFitHeight(240);
//
//            //insets to 0
            Box1.setPadding(new javafx.geometry.Insets(0, 0, 0, 0));

            //scale transition
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img1);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img1.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(0);
                System.out.println(medias);

                //get parent controller
                parentController.mediaDescriptionController.updateInfo(medias);


            });
        });

        img1.setOnMouseExited(event -> {
//            img1.setFitWidth(135);
//            img1.setFitHeight(203);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img1);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box1.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });

        img2.setOnMouseEntered(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img2);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img2.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(1);
                System.out.println(medias);

                parentController.mediaDescriptionController.updateInfo(medias);
            });
        });

        img2.setOnMouseExited(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img2);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box2.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });

        img3.setOnMouseEntered(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img3);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img3.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(2);
                System.out.println(medias);

                parentController.mediaDescriptionController.updateInfo(medias);

            });
        });

        img3.setOnMouseExited(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img3);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box3.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });

        img4.setOnMouseEntered(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img4);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img4.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(3);
                System.out.println(medias);

                parentController.mediaDescriptionController.updateInfo(medias);

            });
        });

        img4.setOnMouseExited(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img4);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box4.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });

        img5.setOnMouseEntered(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img5);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img5.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(4);
                System.out.println(medias);

                parentController.mediaDescriptionController.updateInfo(medias);

            });
        });

        img5.setOnMouseExited(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img5);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box5.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });

        img6.setOnMouseEntered(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img6);

            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);

            scaleTransition.play();

            img6.setOnMouseClicked(event1 -> {
                //get medias from img1
                Medias medias = carouselPage.get(5);
                System.out.println(medias);

                parentController.mediaDescriptionController.updateInfo(medias);

            });
        });

        img6.setOnMouseExited(event -> {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), img6);

            scaleTransition.setFromX(1.1);
            scaleTransition.setFromY(1.1);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();

            Box6.setPadding(new javafx.geometry.Insets(0, 10, 0, 10));
        });
    }

    //set parent controller
    public void setParentController(PpHomePageController homeController) {
        this.parentController = homeController;
    }
}
