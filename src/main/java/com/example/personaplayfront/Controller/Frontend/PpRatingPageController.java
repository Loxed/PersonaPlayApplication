package com.example.personaplayfront.Controller.Frontend;

import com.example.personaplayfront.Controller.Handler.SessionHandler;
import com.example.personaplayfront.Model.CompositeKeys.UsersMediasKey;
import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Users;
import com.example.personaplayfront.Model.UsersMedias;
import com.example.personaplayfront.Repo.MediaDaoImpl;
import com.example.personaplayfront.Repo.UsersDaoImpl;
import com.example.personaplayfront.Repo.UsersMediasDaoImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class PpRatingPageController {

    public Label ratingtext;
    MediaDaoImpl mediaDao = new MediaDaoImpl();
    UsersMediasDaoImpl userMediaDao = new UsersMediasDaoImpl();
    UsersDaoImpl usersDao = new UsersDaoImpl();

    public RadioButton com;

    public RadioButton ptw;

    public RadioButton wat;

    public RadioButton dro;

    int rating;

    String watch_status;


    Users user;

    Medias media;

    UsersMedias userMedia;


    public void initialize() {

        //get user from session
        String uname = SessionHandler.decryptSessionId(SessionHandler.getSessionId())[0];

        user = usersDao.findByPropertyLike("username", uname);

        System.out.println("User:"+user);
    }

    public void lowerRating(ActionEvent actionEvent) {

        //if rating is 0, do nothing
        if(userMedia.getRating() == 0) {
            return;
        }
        userMedia.setRating(userMedia.getRating()-1);

        rating = userMedia.getRating();

        ratingtext.setText(String.valueOf(rating));

    }
    
    public void increaseRating(ActionEvent actionEvent) {
        //if rating is 10, do nothing
        if(userMedia.getRating() == 10) {
            return;
        }
        userMedia.setRating(userMedia.getRating()+1);

        rating = userMedia.getRating();

        ratingtext.setText(String.valueOf(rating));
    }


    public void setCompleted(ActionEvent actionEvent) {
        //untoggle other radio buttons
        ptw.setSelected(false);
        wat.setSelected(false);
        dro.setSelected(false);
        com.setSelected(true);

        userMedia.setWatchStatus("Completed");
    }

    public void setWatching(ActionEvent actionEvent) {
        //untoggle other radio buttons
        ptw.setSelected(false);
        dro.setSelected(false);
        com.setSelected(false);
        wat.setSelected(true);

        userMedia.setWatchStatus("Watching");
    }

    public void setPTW(ActionEvent actionEvent) {
        //untoggle other radio buttons
        wat.setSelected(false);
        dro.setSelected(false);
        com.setSelected(false);
        ptw.setSelected(true);

        userMedia.setWatchStatus("Plan to Watch");

    }

    public void setDropped(ActionEvent actionEvent) {
        //untoggle other radio buttons
        ptw.setSelected(false);
        wat.setSelected(false);
        com.setSelected(false);
        dro.setSelected(true);

        userMedia.setWatchStatus("Dropped");
    }

    public void close(ActionEvent actionEvent) {

        System.out.println("UserMedia:"+userMedia);

        //get from db
        UsersMedias dbUserMedia = userMediaDao.findById(user.getId(), media.id);

        System.out.println("DBUserMedia:"+dbUserMedia);


        //if userMedia is null, insert
        if(dbUserMedia == null) {
            userMedia.setId(new UsersMediasKey(user.getId(), media.id));

            userMediaDao.save(userMedia);
        } else {
            //else update
            userMediaDao.update(userMedia);
        }

        //close window
        Stage stage = (Stage) com.getScene().getWindow();
        stage.close();

    }

    public void setMedia(Medias media) {
        this.media = media;

        System.out.println("Media:"+media);

        System.out.println("User:"+user);

        userMedia = userMediaDao.findById(user.getId(), media.id);

        if(userMedia == null) {
            userMedia = new UsersMedias(user, media, 5, "PTW", false);
        }

        System.out.println("UserMedia:"+userMedia);

        //update rating text and buttons
        rating = userMedia.getRating();
        ratingtext.setText(String.valueOf(rating));

        //update watch status
        watch_status = userMedia.getWatchStatus();

        switch (watch_status) {
            case "PTW":
                ptw.setSelected(true);
                break;
            case "Watching":
                wat.setSelected(true);
                break;
            case "Completed":
                com.setSelected(true);
                break;
            case "Dropped":
                dro.setSelected(true);
                break;
        }


    }
}

