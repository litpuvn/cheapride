package com.cheapRide.controller;

import com.cheapRide.model.RideResponseModel;
import com.cheapRide.model.RideRequestModel;
import com.cheapRide.service.RideRequestSimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pshayegh on 4/28/2017.
 */

@Controller
public class RideRequestSimulatorController {
    @Autowired
    private RideRequestSimulatorService rideRequestSimulatorService;

//    @RequestMapping(value = "/bookRide", method = RequestMethod.POST)
//    public ResponseEntity<RideResponseModel> requestRide(@RequestBody RideRequestModel rideRequestModel) {
//        ResponseEntity<RideResponseModel> responseEntity;
//        RideResponseModel rideResponseModel= rideRequestSimulatorService.getLyftRide();
//        responseEntity = ResponseEntity.status(HttpStatus.ACCEPTED).body(rideResponseModel);
//        return responseEntity;
//    }
    @RequestMapping(value = "/bookRide", method = RequestMethod.GET)
    public ResponseEntity<RideResponseModel> requestRide(@RequestParam String rideRequestId, @RequestParam String rideType) {
        ResponseEntity<RideResponseModel> responseEntity;
        RideResponseModel rideResponseModel= rideRequestSimulatorService.getLyftRide(rideRequestId);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(rideResponseModel);
        return responseEntity;
    }
}
