package com.example.personaplayfront.Model;

//-- watchlist_tags
//CREATE TABLE `personaplay`.`watchlist_tags` (
//  `watchlist_id` INT NOT NULL,
//  `tags_id` INT NOT NULL,
//  PRIMARY KEY (`watchlist_id`, `tags_id`),
//  FOREIGN KEY (`watchlist_id`) REFERENCES `personaplay`.`watchlist` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//  FOREIGN KEY (`tags_id`) REFERENCES `personaplay`.`tags` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION
//) ENGINE = InnoDB;

import com.example.personaplayfront.Model.CompositeKeys.WatchlistTagsKey;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "watchlist_tags")
public class WatchlistTags {

    @EmbeddedId
    private WatchlistTagsKey id;

    @ManyToOne
    @MapsId("watchlist_id")
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @ManyToOne
    @MapsId("tags_id")
    @JoinColumn(name = "tags_id")
    private Tags tags;

    public WatchlistTags() {
    }

    public WatchlistTags(Watchlist watchlist, Tags tags) {
        this.watchlist = watchlist;
        this.tags = tags;
    }

    public WatchlistTagsKey getId() {
        return id;
    }

    public void setId(WatchlistTagsKey id) {
        this.id = id;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "WatchlistTags{" +
                "id=" + id +
                ", watchlist=" + watchlist +
                ", tags=" + tags +
                '}';
    }
}
