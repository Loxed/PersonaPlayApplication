package com.example.personaplayfront.Model;

//--user-medias
//CREATE TABLE `personaplay`.`users_medias` (
//  `users_id` INT NOT NULL,
//  `medias_id` INT NOT NULL,
//  PRIMARY KEY (`users_id`, `medias_id`),
//  `rating` INT NOT NULL,
//  `watch_status` VARCHAR(45) NOT NULL,
//  `favorite` TINYINT(1) NOT NULL DEFAULT 0,
//  FOREIGN KEY (`users_id`) REFERENCES `personaplay`.`users` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//  FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION
//) ENGINE = InnoDB;

import com.example.personaplayfront.Model.CompositeKeys.UsersMediasKey;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users_medias")
public class UsersMedias {
    @EmbeddedId
    private UsersMediasKey id;

    @ManyToOne
    @MapsId("users_id")
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @MapsId("medias_id")
    @JoinColumn(name = "medias_id")
    private Medias medias;

    @Column(name = "rating")
    private int rating;

    @Column(name = "watch_status")
    private String watchStatus;

    @Column(name = "favorite")
    private boolean favorite;

    public UsersMedias() {
    }

    public UsersMedias(Users user, Medias media, int rating, String watchStatus, boolean favorite) {
        this.users = user;
        this.medias = media;
        this.rating = rating;
        this.watchStatus = watchStatus;
        this.favorite = favorite;
    }

    public UsersMediasKey getId() {
        return id;
    }

    public void setId(UsersMediasKey id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users user) {
        this.users = user;
    }

    public Medias getMedia() {
        return medias;
    }

    public void setMedia(Medias media) {
        this.medias = media;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(String watchStatus) {
        this.watchStatus = watchStatus;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "UsersMedias{" +
                "id=" + id +
                ", user=" + users +
                ", media=" + medias +
                ", rating=" + rating +
                ", watchStatus='" + watchStatus + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}