package com.cheapRide.model;

/**
 * Created by pshayegh on 3/8/2017.
 */
public class User {
    private String name;
    private String tempValidCode;

    public User(String name, String tempValidCode) {
        this.name = name;
        this.tempValidCode = tempValidCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTempValidCode() {
        return tempValidCode;
    }

    public void setTempValidCode(String tempValidCode) {
        this.tempValidCode = tempValidCode;
    }
}
