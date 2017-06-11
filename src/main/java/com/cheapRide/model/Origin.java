package com.cheapRide.model;

/**
 * Created by Parvaneh on 5/1/2017.
 */
public class Origin {
    private String lat;
    private String lng;
    private String name;
    public Origin(){}
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
