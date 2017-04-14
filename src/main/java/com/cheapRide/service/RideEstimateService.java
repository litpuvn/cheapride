package com.cheapRide.service;

import java.util.Map;

/**
 * Created by Amit
 */
public interface RideEstimateService {
	
	 String getEstimates(float pickUpLat, float pickUpLong, float dropOffLat, float dropOffLong,Map<String,String> options);
	
	 String getEstimatedTime(float pickUpLat, float pickUpLong, Map<String,String> options) ;

	
}
