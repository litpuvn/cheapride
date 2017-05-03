package com.cheapRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cheapRide.model.RideRequestModel;
import com.cheapRide.model.RideResponseModel;
import com.cheapRide.service.LyftRideBookingService;
import com.cheapRide.service.RideRequestSimulatorService;

/**
 * Created by pshayegh on 4/28/2017.
 */

@Controller
public class RideRequestSimulatorController {
    @Autowired
    private RideRequestSimulatorService rideRequestSimulatorService;
    
    @Autowired
    private LyftRideBookingService liftRideBookingService;
    
    @RequestMapping(value = "/bookRide", method = RequestMethod.POST)
    public ResponseEntity<RideResponseModel> requestRide(@RequestBody RideRequestModel rideRequestModel) {
        ResponseEntity<RideResponseModel> responseEntity;
        RideResponseModel rideResponseModel;
        if("lyft".equalsIgnoreCase(rideRequestModel.getProvider())){
        	rideResponseModel= liftRideBookingService.requestLyftRide(rideRequestModel);
        	//rideResponseModel= rideRequestSimulatorService.getLyftRide();
        }else{
        	rideResponseModel= rideRequestSimulatorService.getLyftRide();
        }
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(rideResponseModel);
        return responseEntity;
    }
}
