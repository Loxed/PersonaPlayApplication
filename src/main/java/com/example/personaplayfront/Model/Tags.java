package com.example.personaplayfront.Model;

import jakarta.persistence.*;

import java.util.Set;

//CREATE TABLE `personaplay`.`tags` (
//  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//  `name` VARCHAR(45) NOT NULL UNIQUE
//) ENGINE = InnoDB;

@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tags")
    private Set<WatchlistTags> watchlistTags;

    public Tags() {
    }

    public Tags(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}