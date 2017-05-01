package com.cheapRide.model;

import com.cheapRide.model.Car;
import com.cheapRide.model.Driver;

/**
 * Created by pshayegh on 4/28/2017.
 */
public class RideResponseModel {
    private String rideId;
    private Car car;
    private Driver driver;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }
}
