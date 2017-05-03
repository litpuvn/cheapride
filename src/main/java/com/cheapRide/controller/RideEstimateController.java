package com.cheapRide.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheapRide.model.LoginResponse;
import com.cheapRide.service.RideEstimateService;

/**
 * Created by Amit
 */
@RestController
public class RideEstimateController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RideEstimateController.class);

	
    @Autowired
    public RideEstimateService rideService;



    @RequestMapping(value = "/getEstimate" , method = RequestMethod.GET)
    public ResponseEntity<String> getEstimatedPrice(@RequestHeader HttpHeaders headers,@RequestParam(value="pick_up_lattitude") float originLat,
    		@RequestParam(value="pick_up_longitude") float originLon,
    		@RequestParam(value="drop_off_lattitude") float destLat,
    		@RequestParam(value="drop_off_longitude") float destLon,
    		@RequestParam(value="car_type", required = false ) String carType){
    	
    	logger.debug("Start : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
					+ " origin longitude " + originLon + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
    	ResponseEntity<String> responseEntity;
    //	headers.get("")
        String resString = null;
     /*   Map<String, String> options = new HashMap<String,String>();
        options.put("uber_car_type", uber_car_type);
        options.put("lyft_car_type", lyft_car_type);*/
        try {
            resString = rideService.getEstimates(originLat, originLon, destLat, destLon, null, carType);
        } catch (Exception e) {
        	 logger.error("ERROR : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
     				+ " origin longitude " + originLon + " destination lattitude " + destLat + " destination longitude "
     				+ destLon);
        	 responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
            e.printStackTrace();
        }
        logger.debug("End : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLon + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
        
        logger.debug("OUTPUT : "+resString);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(resString);
        return responseEntity;
    }
    @RequestMapping(value="/getEstimatedTime" , method = RequestMethod.GET)
    public String register(@RequestParam(value="pick_up_lattitude") float pickUpLat,
    		@RequestParam(value="pick_up_longitude") float pickUpLong) {
    	String resString = null;
    	try {
            resString = rideService.getEstimatedTime(pickUpLat, pickUpLong, null);
    	 } catch (Exception e) {
             e.printStackTrace();
         }

         return resString;
    }
}





