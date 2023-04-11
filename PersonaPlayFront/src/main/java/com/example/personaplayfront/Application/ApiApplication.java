package com.example.personaplayfront.Application;

//java
import java.io.IOException;

//personal
import com.example.personaplayfront.Controller.Handler.ApiHandler;

import com.example.personaplayfront.Model.Media;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ApiApplication {
    public static void main(String[] args) throws IOException {
        //launch(args);

        String myJson = ApiHandler.OMDBGetByTitleLike("Matrix");

        //System.out.println(myJson);

        //set OMDB Media into a Media object
        Media Media = new Media(myJson);
        System.out.println(Media);

        System.out.println(Media.serialize());
//
//        ApiHandler.OMDBSearchByTitleLike("Matrix");
//
//        ApiHandler.OMDBGetById("tt0133093");

    }

}
