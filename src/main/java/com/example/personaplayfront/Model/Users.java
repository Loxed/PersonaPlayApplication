package com.example.personaplayfront.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.Set;

@Entity
@Table(name = "users")
public class Users implements java.io.Serializable{

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

    @Column(name = "creation_date") //is a timestamp "2022-04-16 15:30:00"
    private String creationDate;



    public Users() {

        //convert 2023-04-16T15:56:57.380277200 to 2023-04-16 15:56:57
        LocalDateTime now = LocalDateTime.now();
        String[] date = now.toString().split("T");
        this.creationDate = date[0] + " " + date[1].substring(0, 8);

        this.contentFilter = false;
        this.username = "default";
        this.password = "default";
        this.email = "default";
        this.role = new Roles();
    }


    public Users(String username, String password, String email, boolean contentFilter, Roles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contentFilter = contentFilter;
        this.role = role;

        //convert 2023-04-16T15:56:57.380277200 to 2023-04-16 15:56:57
        LocalDateTime now = LocalDateTime.now();
        String[] date = now.toString().split("T");
        this.creationDate = date[0] + " " + date[1].substring(0, 8);

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

    public String getCreationDate() {
        return creationDate;
    }

    public void updateCreationDate() {
        LocalDateTime now = LocalDateTime.now();
        String[] date = now.toString().split("T");
        this.creationDate = date[0] + " " + date[1].substring(0, 8);
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
                ", creationDate=" + creationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != users.id) return false;
        if (contentFilter != users.contentFilter) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;
        if (role != null ? !role.equals(users.role) : users.role != null) return false;
        return creationDate != null ? creationDate.equals(users.creationDate) : users.creationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (contentFilter ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

}