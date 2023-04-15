package com.example.personaplayfront.Model.CompositeKeys;


import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Stats;

import jakarta.persistence.*;

public class MediasStatsKey implements java.io.Serializable{

        @Column(name = "medias_id")
        private int mediasId;

        @Column(name = "stats_id")
        private int statsId;

        public MediasStatsKey() {
        }

        public MediasStatsKey(int mediasId, int statsId) {
            this.mediasId = mediasId;
            this.statsId = statsId;
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

            MediasStatsKey that = (MediasStatsKey) o;

            if (mediasId != that.mediasId) return false;
            return statsId == that.statsId;
        }

        @Override
        public int hashCode() {
            int result = mediasId;
            result = 31 * result + statsId;
            return result;
        }

        @Override
        public String toString() {
            return "MediasStatsKey{" +
                    "mediasId=" + mediasId +
                    ", statsId=" + statsId +
                    '}';
        }
}
