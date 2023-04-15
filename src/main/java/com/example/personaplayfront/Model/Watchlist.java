package com.example.personaplayfront.Model;

import jakarta.persistence.*;

import java.util.Set;

//CREATE TABLE `personaplay`.`watchlist` (
//  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//  `type` VARCHAR(45) NOT NULL,
//  `users_id` INT NOT NULL,
//    FOREIGN KEY (`users_id`) REFERENCES `personaplay`.`users` (`id`)
//        ON DELETE NO ACTION
//        ON UPDATE NO ACTION
//) ENGINE = InnoDB;

@Entity
@Table(name = "watchlist")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    @OneToMany(mappedBy = "watchlist")
    private Set<WatchlistTags> watchlistTags;

    public Watchlist() {
    }

    public Watchlist(String type, Users user) {
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<WatchlistTags> getWatchlistTags() {
        return watchlistTags;
    }

    public void setWatchlistTags(Set<WatchlistTags> watchlistTags) {
        this.watchlistTags = watchlistTags;
    }

    @Override
    public String toString() {
        return "Watchlist{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", user=" + user +
                '}';
    }
}