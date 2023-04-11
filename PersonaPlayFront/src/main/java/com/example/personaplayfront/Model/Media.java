package com.example.personaplayfront.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {
    @JsonProperty("imdbID")
    public String imdbId;
    @JsonProperty("Title")
    public String mediaName;
    @JsonProperty("Poster")
    public String posterUrl;
    @JsonProperty("Genre")
    public String genres;

    @JsonProperty("Year")
    public String year;

    @JsonProperty("Actors")
    public String actors;

    @JsonProperty("Director")
    public String director;

    @JsonProperty("Plot")
    public String plot;


    public Media() {
        //default
        mediaName = "myMediaName";
        imdbId = "myImdbId";
        posterUrl = "myPosterUrl";
        genres = "myGenres";
        year = "myYear";
        actors = "myActors";
        director = "myDirector";
        plot = "myPlot";
    }


    //ctor
    public Media(String myjson) {
        Media new_media;
        try {
            //System.out.println("Media created");
            new_media = Media.deserialize(myjson);
        } catch (JsonProcessingException e) {
            //if it fails, create empty default media
            //System.out.println("Media not created");
            new_media = new Media();
        }

        this.mediaName = new_media.mediaName;
        this.imdbId = new_media.imdbId;
        this.posterUrl = new_media.posterUrl;
        this.genres = new_media.genres;
        this.year = new_media.year;
        this.actors = new_media.actors;
        this.director = new_media.director;
        this.plot = new_media.plot;
    }

    @Override
    public String toString() {
        return "Media{" +
                "imdbId='" + imdbId + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", genres='" + genres + '\'' +
                ", year='" + year + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }

    //deserialization
    static Media deserialize(String myJson) throws JsonProcessingException {
        //map using jackson anotation
        ObjectMapper mapper = new ObjectMapper();
        Media media = mapper.readValue(myJson, Media.class);
        return media;
    }

    //serialization
    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }
}
