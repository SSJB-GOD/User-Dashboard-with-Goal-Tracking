package com.jitesh.onboarding.entites;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password_hash")
    private String password;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(name = "created_at")
    private Date createdAt;

    public User() {
    }

    public User(int id, String email, String password, Date createdAt, Date lastLogin, Date updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.updatedAt = updatedAt;
    }

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Getters and Setters


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", lastLogin=" + lastLogin +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
