package com.cheapRide.controller;

import com.cheapRide.model.lyft.RideResponseModel;
import com.cheapRide.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pshayegh on 4/28/2017.
 */

@Controller
public class RideRequestSimulatorController {
    @Autowired
    private RideRequestService rideRequestService;

    @RequestMapping(value = "/requestRide", method = RequestMethod.POST)
    public ResponseEntity<RideResponseModel> login(@RequestBody RideResponseModel rideResponseModel) {
        return null;
//        rideRequestService.getLyftRide();
    }
}
