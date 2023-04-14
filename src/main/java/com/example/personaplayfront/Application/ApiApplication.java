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

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Repo.HibernateFactory;
import com.example.personaplayfront.Repo.MediaDaoImpl;


public class ApiApplication {
    public static void main(String[] args) throws IOException {
        HibernateFactory.getInstance();

        MediaDaoImpl mediaDAO = new MediaDaoImpl();

        //open movie_ids.txt
        BufferedReader file = new BufferedReader(new FileReader("src/main/resources/com/example/personaplayfront/movie_ids.txt"));

        //big list of movie series
        List<String> my_movies = new ArrayList<>();

        for (int i = 0; i < 250; i++) {
            String line = file.readLine();
            my_movies.add(line);
        }

        System.out.println(my_movies);

        List<Medias> medias = new ArrayList<>();

//        medias.add(new Medias(ApiHandler.OMDBGetById(my_movies.get(0))));

        System.out.println(medias);


        for (String movie : my_movies) {
            medias.add(new Medias(ApiHandler.OMDBGetById(movie)));

            //save in db
            mediaDAO.save(medias.get(medias.size() - 1));
        }



//        Medias medias = ApiHandler.OMDBFindAllByTitleLike("Sharknado").get(1);
//        mediaDAO.save(medias);
//
//        System.out.println(medias);


    }

}
