package com.example.personaplayfront.Model;

//CREATE TABLE `personaplay`.`ratings` (
//  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//  `rating` INT NOT NULL,
//  `rating_count` INT NOT NULL,
//  `medias_id` INT NOT NULL,
//    FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
//        ON DELETE NO ACTION
//        ON UPDATE NO ACTION
//) ENGINE = InnoDB;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "rating_count")
    private int ratingCount;

    @ManyToOne
    @JoinColumn(name = "medias_id")
    private Medias media;

    public Ratings() {
    }

    public Ratings(int rating, int ratingCount, Medias media) {
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.media = media;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Medias getMedia() {
        return media;
    }

    public void setMedia(Medias media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Ratings{" +
                "id=" + id +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", media=" + media +
                '}';
    }
}