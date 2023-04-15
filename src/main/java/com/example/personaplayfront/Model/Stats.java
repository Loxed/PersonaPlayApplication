package com.example.personaplayfront.Model;

import jakarta.persistence.*;

import java.util.Set;

//CREATE TABLE `personaplay`.`stats` (
//  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//  `watch_time` INT NOT NULL DEFAULT 0
//) ENGINE = InnoDB;

@Entity
@Table(name = "stats")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "watch_time")
    private int watchTime;

    @OneToMany(mappedBy = "stats")
    private Set<MediasStats> mediasStats;

    public Stats() {
    }

    public Stats(int watchTime) {
        this.watchTime = watchTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(int watchTime) {
        this.watchTime = watchTime;
    }

    public Set<MediasStats> getMediasStats() {
        return mediasStats;
    }

    public void setMediasStats(Set<MediasStats> mediasStats) {
        this.mediasStats = mediasStats;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "id=" + id +
                ", watchTime=" + watchTime +
                '}';
    }
}
