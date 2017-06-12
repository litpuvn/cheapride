package com.cheapRide.service;

import com.cheapRide.model.Destination;
import com.cheapRide.model.PopularPlaceInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Amit
 */
public interface RideEstimateService {
	
	 String getEstimates(float pickUpLat, float pickUpLong, float dropOffLat, float dropOffLong,Map<String,String> optionsString ,String carType);

	 List<PopularPlaceInfo> getEstimatePopularInfo(Date date);

	 String getEstimatedTime(float pickUpLat, float pickUpLong, Map<String,String> options) ;



}
