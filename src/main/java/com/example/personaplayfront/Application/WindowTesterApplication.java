package com.example.personaplayfront.Application;

import com.example.personaplayfront.Repo.HibernateFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowTesterApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        HibernateFactory.getInstance();

        //load MediaWatcherController
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_search_page.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();

        root.setOnMouseClicked(event -> System.out.println(event.getSceneX() + " " + event.getSceneY()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
