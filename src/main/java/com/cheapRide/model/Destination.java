package com.cheapRide.model;

/**
 * Created by Parvaneh on 5/1/2017.
 */
public class Destination {
    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Destination(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
