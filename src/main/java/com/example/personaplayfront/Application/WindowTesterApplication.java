package com.example.personaplayfront.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowTesterApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //load MediaWatcherController
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_media_watcher.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
