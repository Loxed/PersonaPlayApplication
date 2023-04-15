package com.example.personaplayfront.Model;

//CREATE TABLE `icon` (
//  `id` int NOT NULL AUTO_INCREMENT,
//  `icon` int NOT NULL,
//  `variant` int NOT NULL,
//  PRIMARY KEY (`id`),
//  CONSTRAINT `users_id` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

import com.example.personaplayfront.Repo.IconDaoImpl;
import jakarta.persistence.*;

@Entity
@Table(name = "icon")
public class Icon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "icon")
    private int icon;

    @Column(name = "variant")
    private int variant;

    public Icon() {

    }

    public Icon(int icon, int variant) {
        this.icon = icon;
        this.variant = variant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "id=" + id +
                ", icon=" + icon +
                ", variant=" + variant +
                '}';
    }
}