package com.cheapRide.service;

/**
 * Created by Amit
 */
public interface RideEstimateService {
	
	 String getEstimatedPrice(float pickUpLat, float pickUpLong, float dropOffLat, float dropOffLong);
	
	 String getEstimatedTime(float pickUpLat, float pickUpLong) ;

	
}
