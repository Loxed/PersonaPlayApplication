package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class PpAdminSettingsController {

    public Button searchButton;
    public TextField searchBar;
    public ScrollPane scrollPage;
    public VBox mediaContainer;
    public Text titleCat;

    public Text idCat;

    public Text yearCat;

    public Text visibleCat;

    public Text availableCat;

    public Text locationCat;
    MediaDaoImpl mediaDao = new MediaDaoImpl();

    UsersDaoImpl userDao = new UsersDaoImpl();
    public void initialize() {

        search(null);
        //on enter key press, search
        searchBar.setOnAction(this::search);
    }

    public void search(ActionEvent actionEvent) {

        if(searchBar.getText().startsWith("user:")) {

            String text = searchBar.getText().substring(5);

            //remove all spaces until next word
            if(text.length()!=0)
                while (text.charAt(0) == ' ') {
                    text = text.substring(1);
                    if(text.length()==0)
                        break;
                }
            List<Users> users = userDao.findAllByPropertyLike("username", text);

            mediaContainer.getChildren().clear();

            titleCat.setText("Icon");
            idCat.setText("Username");
            yearCat.setText("Email");
            visibleCat.setText("Password");
            availableCat.setText("Role");
            locationCat.setText("Mature");

            for (Users user : users) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_admin_user_obj.fxml"));

                    mediaContainer.getChildren().add(loader.load());

                    //adapt mediaContainer size based on number of children
                    mediaContainer.setPrefHeight(50 * users.size());

                    if (mediaContainer.getPrefHeight() < 620)
                        mediaContainer.setPrefHeight(620);

                    //same with scrollPage
                    PpAdminUserObjController controller = loader.getController();

                    controller.setUser(user);

                    if (mediaContainer.getChildren().size() % 2 == 0)
                        controller.container.setStyle("-fx-background-color: #2d2d2d;");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            titleCat.setText("Title");
            idCat.setText("imdbID");
            yearCat.setText("Year");
            visibleCat.setText("Visible");
            availableCat.setText("Available");
            locationCat.setText("Location");

            List<Medias> medias = mediaDao.findAllByPropertyLike("mediaName", searchBar.getText());

//        System.out.println(medias);

            mediaContainer.getChildren().clear();

            for (Medias media : medias) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_admin_media_obj.fxml"));

                    mediaContainer.getChildren().add(loader.load());

                    //adapt mediaContainer size based on number of children
                    mediaContainer.setPrefHeight(50 * medias.size());

                    if (mediaContainer.getPrefHeight() < 620)
                        mediaContainer.setPrefHeight(620);

                    //same with scrollPage
                    PpAdminMediaObjController controller = loader.getController();

                    controller.setMedia(media);

                    if (mediaContainer.getChildren().size() % 2 == 0) {
                        controller.container.setStyle("-fx-background-color: #2d2d2d;");
                        //stylize button to match #202020, border radius 5px, white text
                        controller.locationButton.setStyle("-fx-background-color: #202020; -fx-border-radius: 5px; -fx-text-fill: white;");
                        //on mouse hover, border radius 5px with cyan contour
                        controller.locationButton.setOnMouseEntered(event -> controller.locationButton.setStyle("-fx-background-color: #202020; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-color: cyan; -fx-border-width: 1px;"));
                        controller.locationButton.setOnMouseExited(event -> controller.locationButton.setStyle("-fx-background-color: #202020; -fx-border-radius: 5px; -fx-text-fill: white;"));
                    } else {
                        controller.locationButton.setStyle("-fx-background-color: #2d2d2d; -fx-border-radius: 5px; -fx-text-fill: white;");

                        controller.locationButton.setOnMouseEntered(event -> controller.locationButton.setStyle("-fx-background-color: #2d2d2d; -fx-border-radius: 5px; -fx-text-fill: white; -fx-border-color: cyan; -fx-border-width: 1px;"));
                        controller.locationButton.setOnMouseExited(event -> controller.locationButton.setStyle("-fx-background-color: #2d2d2d; -fx-border-radius: 5px; -fx-text-fill: white;"));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
