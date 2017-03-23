package com.cheapRide.model;

import org.springframework.data.annotation.Id;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class User {

    private String username;
    private String password;

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
