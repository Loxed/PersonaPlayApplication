package com.example.personaplayfront.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;

@Entity
@Table(name = "medias")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @JsonProperty("imdbID")
    @Column(name = "imdb_id")
    public String imdbId; //length of imdb id = 9
    @JsonProperty("Title")
    @Column(name = "title")
    public String mediaName; //length of media name = 50
    @JsonProperty("Poster")
    @Column(name = "poster")
    public String posterUrl; //length of poster link = 255
    //length of poster link
    @JsonProperty("Genre")
    @Column(name = "genre")
    public String genres; //length of genres =

    @JsonProperty("Year")
    @Column(name = "year")
    public String year;

    @JsonProperty("Actors")
    @Column(name = "actors")
    public String actors;

    @JsonProperty("Director")
    @Column(name = "director")
    public String director;

    @JsonProperty("Plot")
    @Column(name = "plot")
    public String plot;

    //default 1
    @Column(name = "available" , columnDefinition = "TINYINT DEFAULT 1")
    public boolean available;

    //default 1
    @Column(name = "visible" , columnDefinition = "TINYINT DEFAULT 1")
    public boolean visible;

    //default ""
    @Column(name = "media_location", columnDefinition = "VARCHAR(255) DEFAULT ''")
    public String mediaLocation;

    public Medias() {
        //default constructor
        this.imdbId = "tt0106062";
        this.mediaName = "Matrix";
        this.posterUrl = "https://m.media-amazon.com/images/M/MV5BYzUzOTA5ZTMtMTdlZS00MmQ5LWFmNjEtMjE5MTczN2RjNjE3XkEyXkFqcGdeQXVyNTc2ODIyMzY@._V1_SX300.jpg";
        this.genres = "Action, Sci-Fi";
        this.year = "1993";
        this.actors = "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving";
        this.director = "Lana Wachowski";
        this.plot = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.";
        this.available = false;
        this.visible = true;
        this.mediaLocation = "";
    }

    //ctor
    public Medias(String myjson) {
        Medias new_medias;
        try {
            //System.out.println("Medias created");
            new_medias = Medias.deserialize(myjson);
        } catch (JsonProcessingException e) {
            //if it fails, create empty default media
            //System.out.println("Medias not created");
            new_medias = new Medias();
        }

        this.mediaName = new_medias.mediaName;
        this.imdbId = new_medias.imdbId;
        this.posterUrl = new_medias.posterUrl;
        this.genres = new_medias.genres;
        this.year = new_medias.year;
        this.actors = new_medias.actors;
        this.director = new_medias.director;
        this.plot = new_medias.plot;
        this.available = new_medias.available;
        this.visible = new_medias.visible;
        this.mediaLocation = new_medias.mediaLocation;
    }

    @Override
    public String toString() {
        return "Medias{" +
                "imdbId='" + imdbId + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", genres='" + genres + '\'' +
                ", year='" + year + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                ", plot='" + plot + '\'' +
                ", available=" + available +
                ", visible=" + visible +
                ", mediaLocation='" + mediaLocation + '\'' +
                '}';
    }

    //deserialization
    static Medias deserialize(String myJson) throws JsonProcessingException {
        //map using jackson anotation
        ObjectMapper mapper = new ObjectMapper();
        Medias medias = mapper.readValue(myJson, Medias.class);
        return medias;
    }

    //serialization
    public String serialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        return json;
    }
}
