package com.example.personaplayfront.Application;

//java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//personal
import com.example.personaplayfront.Controller.Handler.ApiHandler;

import com.example.personaplayfront.Model.*;
import com.example.personaplayfront.Model.CompositeKeys.UsersMediasKey;
import com.example.personaplayfront.Repo.*;


public class ApiApplication {
    public static void main(String[] args) throws IOException {
        HibernateFactory.getInstance();

        UsersDaoImpl usersDao = new UsersDaoImpl();
        MediaDaoImpl mediaDao = new MediaDaoImpl();
        UsersMediasDaoImpl userMediaDao = new UsersMediasDaoImpl();

//        Users user = usersDao.findAll().get(1);
//
//        System.out.println("user: "+user);
//
//        Medias media = mediaDao.findAll().get(1);
//
//        System.out.println("media: "+media);
//
//        UsersMedias userMedia = new UsersMedias(user, media, 5, "Watching", false);
//
//        userMedia.setId(new UsersMediasKey(user.getId(), media.id));
//
//        System.out.println(userMedia);
//
//        userMediaDao.save(userMedia);

        List<UsersMedias> userMediaList = userMediaDao.findAll();

        for(UsersMedias userMedia : userMediaList) {
            System.out.println(userMedia);
        }

    }
}
