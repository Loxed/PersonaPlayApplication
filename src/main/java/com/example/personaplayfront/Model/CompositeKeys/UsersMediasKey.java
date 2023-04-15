package com.example.personaplayfront.Model.CompositeKeys;

import com.example.personaplayfront.Model.Medias;
import com.example.personaplayfront.Model.Users;

import jakarta.persistence.*;

public class UsersMediasKey implements java.io.Serializable{

    @Column(name = "users_id")
    private int usersId;

    @Column(name = "medias_id")
    private int mediasId;

    public UsersMediasKey() {
    }

    public UsersMediasKey(int usersId, int mediasId) {
        this.usersId = usersId;
        this.mediasId = mediasId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getMediasId() {
        return mediasId;
    }

    public void setMediasId(int mediasId) {
        this.mediasId = mediasId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersMediasKey that = (UsersMediasKey) o;

        if (usersId != that.usersId) return false;
        return mediasId == that.mediasId;
    }

    @Override
    public int hashCode() {
        int result = usersId;
        result = 31 * result + mediasId;
        return result;
    }

    @Override
    public String toString() {
        return "UsersMediasKey{" +
                "usersId=" + usersId +
                ", mediasId=" + mediasId +
                '}';
    }
}
