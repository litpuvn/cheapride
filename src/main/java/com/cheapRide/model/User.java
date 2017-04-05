package com.cheapRide.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class User {

    private String username;
    private String password;
    private String token;
    private Date date;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
