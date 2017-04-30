package com.cheapRide.model.lyft;

/**
 * Created by pshayegh on 4/28/2017.
 */
public class RideResponseModel {
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
}
