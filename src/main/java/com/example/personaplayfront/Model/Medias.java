package com.example.personaplayfront.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medias")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medias implements java.io.Serializable {

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

    @OneToMany(mappedBy = "medias")
    Set<UsersMedias> usersMedias;

    @OneToMany(mappedBy = "medias")
    Set<MediasStats> mediasStats;

    public Medias() {
        //default constructor
        this.imdbId = "tt0106062";
        this.mediaName = "The Matrix";
        this.posterUrl = "https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg";
        this.genres = "Action, Sci-Fi";
        this.year = "1999";
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
                "id=" + id +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medias medias = (Medias) o;

        if (id != medias.id) return false;
        if (available != medias.available) return false;
        if (visible != medias.visible) return false;
        if (imdbId != null ? !imdbId.equals(medias.imdbId) : medias.imdbId != null) return false;
        if (mediaName != null ? !mediaName.equals(medias.mediaName) : medias.mediaName != null) return false;
        if (posterUrl != null ? !posterUrl.equals(medias.posterUrl) : medias.posterUrl != null) return false;
        if (genres != null ? !genres.equals(medias.genres) : medias.genres != null) return false;
        if (year != null ? !year.equals(medias.year) : medias.year != null) return false;
        if (actors != null ? !actors.equals(medias.actors) : medias.actors != null) return false;
        if (director != null ? !director.equals(medias.director) : medias.director != null) return false;
        if (plot != null ? !plot.equals(medias.plot) : medias.plot != null) return false;
        return mediaLocation != null ? mediaLocation.equals(medias.mediaLocation) : medias.mediaLocation == null;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imdbId != null ? imdbId.hashCode() : 0);
        result = 31 * result + (mediaName != null ? mediaName.hashCode() : 0);
        result = 31 * result + (posterUrl != null ? posterUrl.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (visible ? 1 : 0);
        result = 31 * result + (mediaLocation != null ? mediaLocation.hashCode() : 0);
        return result;
    }
}
