package com.example.personaplayfront.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "content_filter")
    private boolean contentFilter;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    @OneToMany(mappedBy = "users")
    Set<UsersMedias> usersMedias;


    public Users() {

    }


    public Users(String username, String password, String email, boolean contentFilter, Roles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contentFilter = contentFilter;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isContentFilter() {
        return contentFilter;
    }

    public void setContentFilter(boolean contentFilter) {
        this.contentFilter = contentFilter;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<UsersMedias> getUsersMedias() {
        return usersMedias;
    }

    public void setUsersMedias(Set<UsersMedias> usersMedias) {
        this.usersMedias = usersMedias;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", contentFilter=" + contentFilter +
                ", role=" + role +
                '}';
    }

}