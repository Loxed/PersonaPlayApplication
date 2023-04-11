//      ____                                   ____  __
//     / __ \___  ______________  ____  ____ _/ __ \/ /___ ___  __
//    / /_/ / _ \/ ___/ ___/ __ \/ __ \/ __ `/ /_/ / / __ `/ / / /
//   / ____/  __/ /  (__  ) /_/ / / / / /_/ / ____/ / /_/ / /_/ /
//  /_/    \___/_/  /____/\____/_/ /_/\__,_/_/   /_/\__,_/\__, /
//                                                       /____/
//
//  Created by:  Lox
//  Date:        27/03/2023
//  Description: API Controller for the application
//  Notes: This code uses the OkHttp library to make HTTP requests from different APIs, such as OMDB, OpenSubtitles, myanimelist, etc.
package com.example.personaplayfront.Controller.Handler;

//java
import java.io.IOException;
import java.net.URL;

//okhttp
import com.example.personaplayfront.Model.Media;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//jackson
import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiHandler {
    //get http request from url
    final OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    //    ____   __  ___ ___   ___
    //   / __ \ /  |/  // _ \ / _ )
    //  / /_/ // /|_/ // // // _  |
    //  \____//_/  /_//____//____/
    //

    //get title from omdbapi
    //http://www.omdbapi.com/?apikey=24becab2&
    //Parameter	Required	Valid Options	Default Value	Description
    //i	Optional*		<empty>	A valid IMDb ID (e.g. tt1285016)
    //t	Optional*		<empty>	Movie title to search for.
    //type	No	movie, series, episode	<empty>	Type of result to return.
    //y	No		<empty>	Year of release.
    //plot	No	short, full	short	Return short or full plot.
    //r	No	json, xml	json	The data type to return.
    //callback	No		<empty>	JSONP callback name.
    //v	No		1	API version (reserved for future use).

    //get from omdbapi by title
    //https://www.omdbapi.com/?apikey=24becab2&t=the+matrix
    public static String OMDBGetByTitleLike(String title) throws IOException {
        String apikey = "24becab2";
        String urlString = "https://www.omdbapi.com/?apikey=" + apikey + "&t=" + title;
        URL url = new URL(urlString);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String json = response.body().string();
            System.out.println(json);

            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //get from omdbapi by id
    //https://www.omdbapi.com/?apikey=24becab2&i=tt0133093
    public static String OMDBGetById(String id){
        //complete for id
        String apikkey = "24becab2";
        String urlString = "https://www.omdbapi.com/?apikey=" + apikkey + "&i=" + id;
        URL url;

        try {
             url = new URL(urlString);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {

                String json = response.body().string();
                System.out.println(json);

                return json;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //get movies that contain the title
    //https://www.omdbapi.com/?apikey=24becab2&s=matrix
    public static String OMDBSearchByTitleLike(String name) {
        //complete for id
        String apikkey = "24becab2";
        String urlString = "https://www.omdbapi.com/?apikey=" + apikkey + "&s=" + name;

        try {
            URL url = new URL(urlString);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {

                String json = response.body().string();
                System.out.println(json);

                return json;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}