package com.example.personaplayfront.Model.CompositeKeys;

import com.example.personaplayfront.Model.Tags;
import com.example.personaplayfront.Model.Watchlist;

import jakarta.persistence.*;

public class WatchlistTagsKey implements java.io.Serializable{

    @Column(name = "watchlist_id")
    private int watchlistId;

    @Column(name = "tags_id")
    private int tagsId;

    public WatchlistTagsKey() {
    }

    public WatchlistTagsKey(int watchlistId, int tagsId) {
        this.watchlistId = watchlistId;
        this.tagsId = tagsId;
    }

    public int getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(int watchlistId) {
        this.watchlistId = watchlistId;
    }

    public int getTagsId() {
        return tagsId;
    }

    public void setTagsId(int tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WatchlistTagsKey that = (WatchlistTagsKey) o;

        if (watchlistId != that.watchlistId) return false;
        return tagsId == that.tagsId;
    }

    @Override
    public int hashCode() {
        int result = watchlistId;
        result = 31 * result + tagsId;
        return result;
    }

    @Override
    public String toString() {
        return "WatchlistTagsKey{" +
                "watchlistId=" + watchlistId +
                ", tagsId=" + tagsId +
                '}';
    }
}
