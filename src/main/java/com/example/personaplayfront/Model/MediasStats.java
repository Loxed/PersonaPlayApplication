package com.example.personaplayfront.Model;

//-- medias_stats
//CREATE TABLE `personaplay`.`medias_stats` (
//  `medias_id` INT NOT NULL,
//  `stats_id` INT NOT NULL,
//  PRIMARY KEY (`medias_id`, `stats_id`),
//  FOREIGN KEY (`medias_id`) REFERENCES `personaplay`.`medias` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION,
//  FOREIGN KEY (`stats_id`) REFERENCES `personaplay`.`stats` (`id`)
//    ON DELETE NO ACTION
//    ON UPDATE NO ACTION
//) ENGINE = InnoDB;

import com.example.personaplayfront.Model.CompositeKeys.MediasStatsKey;
import jakarta.persistence.*;

@Entity
@Table(name = "medias_stats")
public class MediasStats {

        @EmbeddedId
        private MediasStatsKey id;

        @ManyToOne
        @MapsId("medias_id")
        @JoinColumn(name = "medias_id")
        private Medias medias;

        @ManyToOne
        @MapsId("stats_id")
        @JoinColumn(name = "stats_id")
        private Stats stats;

        public MediasStats() {
        }

        public MediasStats(Medias medias, Stats stats) {
            this.medias = medias;
            this.stats = stats;
        }

        public MediasStatsKey getId() {
            return id;
        }

        public void setId(MediasStatsKey id) {
            this.id = id;
        }

        public Medias getMedias() {
            return medias;
        }

        public void setMedias(Medias medias) {
            this.medias = medias;
        }

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }
}
