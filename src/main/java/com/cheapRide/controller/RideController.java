package com.cheapRide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheapRide.service.RideEstimateService;

/**
 * Created by Amit
 */
@RestController
public class RideController {
	
    @Autowired
    public RideEstimateService rideService;



    @RequestMapping(value = "/getEstimatedPrice" , method = RequestMethod.GET)
    public String getEstimatedPrice(@RequestParam(value="pick_up_lattitude") float pickUpLat,
    		@RequestParam(value="pick_up_longitude") float pickUpLong,
    		@RequestParam(value="drop_off_lattitude") float dropOffLat,
    		@RequestParam(value="drop_off_longitude") float dropOffLong){
        String resString = null;
        try {
            resString = rideService.getEstimatedPrice(pickUpLat, pickUpLong, dropOffLat, dropOffLong);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resString;
    }
    @RequestMapping(value="/getEstimatedTime" , method = RequestMethod.GET)
    public String register(@RequestParam(value="pick_up_lattitude") float pickUpLat,
    		@RequestParam(value="pick_up_longitude") float pickUpLong) {
    	String resString = null;
    	try {
            resString = rideService.getEstimatedTime(pickUpLat, pickUpLong);
    	 } catch (Exception e) {
             e.printStackTrace();
         }

         return resString;
    }
}





