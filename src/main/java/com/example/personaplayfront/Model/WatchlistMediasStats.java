package com.example.personaplayfront.Model;

import com.example.personaplayfront.Model.CompositeKeys.WatchlistMediasStatsKey;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "watchlist_medias_stats")
public class WatchlistMediasStats {

        @EmbeddedId
        private WatchlistMediasStatsKey id;

        @ManyToOne
        @MapsId("watchlist_id")
        @JoinColumn(name = "watchlist_id")
        private Watchlist watchlist;

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

        public WatchlistMediasStats() {
        }

        public WatchlistMediasStats(Watchlist watchlist, Medias medias, int rating, String watchStatus, boolean favorite) {
            this.watchlist = watchlist;
            this.medias = medias;
            this.rating = rating;
            this.watchStatus = watchStatus;
            this.favorite = favorite;
        }

        public WatchlistMediasStatsKey getId() {
            return id;
        }

        public void setId(WatchlistMediasStatsKey id) {
            this.id = id;
        }

        public Watchlist getWatchlist() {
            return watchlist;
        }

        public void setWatchlist(Watchlist watchlist) {
            this.watchlist = watchlist;
        }

        public Medias getMedias() {
            return medias;
        }

        public void setMedias(Medias medias) {
            this.medias = medias;
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
            return "WatchlistMediasStats{" +
                    "id=" + id +
                    ", watchlist=" + watchlist +
                    ", medias=" + medias +
                    ", rating=" + rating +
                    ", watchStatus='" + watchStatus + '\'' +
                    ", favorite=" + favorite +
                    '}';
        }
}
