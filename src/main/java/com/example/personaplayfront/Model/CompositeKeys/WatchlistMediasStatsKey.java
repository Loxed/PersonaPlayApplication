package com.example.personaplayfront.Model.CompositeKeys;

//CREATE TABLE `personaplay`.`watchlist_medias_stats` (
//  `watchlist_id` INT NOT NULL,
//  `medias_id` INT NOT NULL,
//  `stats_id` INT NOT NULL,
//  PRIMARY KEY (`watchlist_id`, `medias_id`, `stats_id`),
//  FOREIGN KEY (`watchlist_id`) REFERENCES `personaplay`.`watchlist` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//  FOREIGN KEY (`medias_id`, `stats_id`) REFERENCES `personaplay`.`medias_stats` (`medias_id`, `stats_id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION
//) ENGINE = InnoDB;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.example.personaplayfront.Model.MediasStats;
import com.example.personaplayfront.Model.Watchlist;


@Embeddable
public class WatchlistMediasStatsKey implements java.io.Serializable {

    @Column(name = "watchlist_id")
    private int watchlistId;

    @Column(name = "medias_id")
    private int mediasId;

    @Column(name = "stats_id")
    private int statsId;

    public WatchlistMediasStatsKey() {
    }

    public WatchlistMediasStatsKey(Watchlist watchlist, MediasStats mediasStats) {
        this.watchlistId = watchlist.getId();
        this.mediasId = mediasStats.getId().getMediasId();
        this.statsId = mediasStats.getId().getStatsId();
    }

    public WatchlistMediasStatsKey(int watchlistId, int mediasId, int statsId) {
        this.watchlistId = watchlistId;
        this.mediasId = mediasId;
        this.statsId = statsId;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getMediasId() {
        return mediasId;
    }

    public void setMediasId(int mediasId) {
        this.mediasId = mediasId;
    }

    public int getStatsId() {
        return statsId;
    }

    public void setStatsId(int statsId) {
        this.statsId = statsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchlistMediasStatsKey that = (WatchlistMediasStatsKey) o;
        return watchlistId == that.watchlistId && mediasId == that.mediasId && statsId == that.statsId;
    }

    @Override
    public int hashCode() {
        int result = watchlistId;
        result = 31 * result + mediasId;
        result = 31 * result + statsId;
        return result;
    }

    @Override
    public String toString() {
        return "WatchlistMediasStatsKey{" +
                "watchlistId=" + watchlistId +
                ", mediasId=" + mediasId +
                ", statsId=" + statsId +
                '}';
    }

}
