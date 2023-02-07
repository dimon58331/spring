package com.demo.online.cinema.demoonlinecinema.entity;

import jakarta.persistence.*;

@Entity(name = "demo_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "is_active")
    private boolean isActive;

    public User() {
    }

    public User(String userName, String userPassword, String userEmail, boolean isActive) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
