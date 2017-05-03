package com.cheapRide.model;

/**
 * Created by pshayegh on 4/28/2017.
 */
public class Car {
    private String color;
    private String make;
    private String imageUrl;
    private String year;
    private String license_plate_state;
    private String license_plate;
    private String model;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLicense_plate_state() {
        return license_plate_state;
    }

    public void setLicense_plate_state(String license_plate_state) {
        this.license_plate_state = license_plate_state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }
}
