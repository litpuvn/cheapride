package com.cheapRide.service.impl;

import com.cheapRide.model.lyft.Car;
import com.cheapRide.model.lyft.Driver;
import com.cheapRide.model.lyft.RideResponseModel;
import com.cheapRide.service.RideRequestService;

/**
 * Created by pshayegh on 4/28/2017.
 */
public class RideRequestServiceImpl implements RideRequestService {
    @Override
    public RideResponseModel getLyftRide(String originLat, String originLong, String destLat, String destLon) {
        RideResponseModel rideResponseModel =new RideResponseModel();
        rideResponseModel.setCar(getCar());
        rideResponseModel.setDriver(getDriver());
        return rideResponseModel;
    }

    private Driver getDriver() {
            return null;
    }

    private Car getCar() {
        return null;
    }
}
