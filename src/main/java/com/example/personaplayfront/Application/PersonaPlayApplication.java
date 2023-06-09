package com.example.personaplayfront.Application;

import com.example.personaplayfront.Controller.Frontend.PpHomePageController;
import com.example.personaplayfront.Controller.Frontend.PpLogInController;
import com.example.personaplayfront.Controller.Handler.ImageHandler;
import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Repo.HibernateFactory;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;


public class PersonaPlayApplication extends Application {
    static ImageHandler imageHandler;

    UsersDaoImpl usersDao = new UsersDaoImpl();
    @Override

    public void init() {
        //initialize image handler in a thread
        Thread imageHandlerThread = new Thread(() -> {
            System.out.println("Loading images...");
            imageHandler = new ImageHandler();});

        imageHandlerThread.start();

        HibernateFactory.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application");

        //check if there is a session id in the SessionHandler
        String sessionId = SessionHandler.getSessionId();

        System.out.println(sessionId);

        Parent root;

        //if second part of session id is not null, then we need to load the main menu page
        //session id is separated by a colon
        if (sessionId != null && sessionId.contains(":")) {

            String uname = SessionHandler.decryptSessionId(sessionId)[0];

            System.out.println("Session username: "+uname);

            //create Users object from uname
            Users user = usersDao.findByPropertyLike("username", uname);

            System.out.println("User:"+user);

            String[] parts = sessionId.split(":");
            if (parts.length == 2 ) {
                if(user!=null && uname.equals(user.getUsername()) ){
                    // yyyy is not null or empty
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_home_page.fxml"));
                    root = loader.load();
                    PpHomePageController homeController = loader.getController();
                } else {
                    // yyyy is null or empty
                    SessionHandler.removeSessionId();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
                    root = loader.load();
                    PpLogInController logInController = loader.getController();
                }
            } else {
                // yyyy is null or empty
                SessionHandler.removeSessionId();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
                root = loader.load();
                PpLogInController logInController = loader.getController();
            }
        } else {
            // sessionId is null or does not contain a colon
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personaplayfront/Vue/pp_log_in.fxml"));
            root = loader.load();
            PpLogInController logInController = loader.getController();
        }

        Scene scene = new Scene(root);

        //set size to 1080x720
        primaryStage.setResizable(false);
        primaryStage.setTitle("PersonaPlay");

        //

        primaryStage.setScene(scene);
        primaryStage.show();

        //on mouse click, prient mouse coordinates
        root.setOnMouseClicked(event -> System.out.println(event.getSceneX() + " " + event.getSceneY()));

    }

    @Override
    public void stop() {
        Platform.exit();
    }

}
