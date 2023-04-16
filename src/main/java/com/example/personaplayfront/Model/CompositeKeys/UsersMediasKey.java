package com.example.personaplayfront.Model.CompositeKeys;

import jakarta.persistence.*;

@Embeddable
public class UsersMediasKey implements java.io.Serializable{
    @Column(name = "users_id")
    private int users_id;

    @Column(name = "medias_id")
    private int medias_id;

    public UsersMediasKey() {
    }

    public UsersMediasKey(int usersId, int mediasId) {
        this.users_id = usersId;
        this.medias_id = mediasId;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int usersId) {
        this.users_id = usersId;
    }

    public int getMedias_id() {
        return medias_id;
    }

    public void setMedias_id(int mediasId) {
        this.medias_id = mediasId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersMediasKey that = (UsersMediasKey) o;

        if (users_id != that.users_id) return false;
        return medias_id == that.medias_id;
    }

    @Override
    public int hashCode() {
        int result = users_id;
        result = 31 * result + medias_id;
        return result;
    }

    @Override
    public String toString() {
        return "UsersMediasKey{" +
                "usersId=" + users_id +
                ", mediasId=" + medias_id +
                '}';
    }
}
