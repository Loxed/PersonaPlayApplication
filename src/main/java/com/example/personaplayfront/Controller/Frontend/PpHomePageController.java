package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.ImageHandler;
import com.example.personaplayfront.Controller.Handler.SessionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Arrays;

public class PpHomePageController {
    @FXML
    public HBox mediaDescriptionContainer;
    @FXML
    public Circle userIcon;

    @FXML
    public HBox homeBox;
    @FXML
    public Text homeButton;

    @FXML
    public HBox searchBox;

    @FXML
    public Text searchButton;

    @FXML
    public HBox settingsBox;

    @FXML
    public Text settingsButton;

    @FXML
    public HBox trendingBox;

    @FXML
    public Text trendingButton;

    @FXML
    public HBox playlistsBox;

    @FXML
    public Text playlistsButton;

    @FXML
    public HBox logOutBox;

    @FXML
    public Text logOutButton;

    @FXML
    Text userName;

    //set email to the email of the user that's logged in, depending on the session id

    //todo: get user image from db
    int userImage = 1;
    int userVariant = 0;
    @FXML
    public void initialize() {
        userName.setText(SessionHandler.decryptSessionId(SessionHandler.getSessionId())[0]);

        Parent root;

        //display media description
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_description.fxml"));
            root = loader.load();
            PpMediaDescriptionController movieDescriptionController = loader.getController();
            mediaDescriptionContainer.getChildren().add(root);
            //todo: set movie details based on the movie that was clicked on, and db
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageHandler.getImageAsync(userImage, userVariant, image -> {
            userIcon.setFill(new ImagePattern(image));
        });

        //if mouse entered and mouse click for icon
        userIcon.setOnMouseEntered(event -> {

            userIcon.setStroke(Color.CYAN);

            userIcon.setOnMouseClicked(event1 -> {
                //switch to profile view
                try {
                    handleProfileView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        });

        userIcon.setOnMouseExited(event2 -> userIcon.setStroke(Color.TRANSPARENT));

        Arrays.asList(homeBox, searchBox, settingsBox, trendingBox, playlistsBox, logOutBox)
                .forEach(node -> node.setOnMouseEntered(event3 -> {
                    if (node.getChildren().size() > 1 && node.getChildren().get(1) instanceof Text text) {
                        //Eras Bold ITC
                        text.setFont(Font.font("Eras Medium ITC", 20));

                        //on mouse clicked
                        node.setOnMouseClicked(event1 -> {
                            //switch to corresponding view when clicking on the box

                            System.out.println(text.getText());
                            switch (text.getText()) {
                                case "Home":
                                    // switch to Home view
                                    break;
                                case "Search":
                                    // switch to Search view
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_page.fxml"));
                                    break;
                                case "Settings":
                                    // switch to Settings view
                                    FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_settings.fxml"));
                                    Parent root1 = null;
                                    try {
                                        root1 = loader1.load();
                                        Scene scene1 = new Scene(root1);
                                        Stage stage1 = (Stage) userName.getScene().getWindow();
                                        stage1.setScene(scene1);
                                        stage1.show();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    break;
                                case "Trending":
                                    // switch to Trending view
                                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_trending.fxml"));
                                    break;
                                case "Playlists":
                                    // switch to Playlists view
                                    FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_playlists.fxml"));
                                    break;
                                case "Log out":
                                    // switch to Login view
                                    try {
                                        handleLogOut();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        });
                    }
                }));

//HBox on mouse exited

        Arrays.asList(homeBox, searchBox, settingsBox, trendingBox, playlistsBox, logOutBox)
                .forEach(node -> node.setOnMouseExited(event4 -> {
                    if (node.getChildren().size() > 1 && node.getChildren().get(1) instanceof Text text) {
                        //Eras Bold ITC
                        text.setFont(Font.font("Eras Light ITC", 20));
                    }
                }));

    }

    @FXML
    private void handleProfileView() throws IOException{
        //go to the profile page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_profile_switcher.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) userName.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLogOut() throws IOException {
        //when logging out, we need to remove the session id from the cache
        SessionHandler.removeSessionId();

        //go to the log in page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
        Parent root = loader.load();
        PpLogInController logInController = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) userName.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
