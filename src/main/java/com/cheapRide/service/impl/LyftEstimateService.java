package com.cheapRide.service.impl;

import com.cheapRide.model.lyft.ListLyftETAModel;
import com.cheapRide.model.lyft.ListLyftPriceModel;

/**
 * 
 * @author Amit
 *
 */
public interface LyftEstimateService {
	
	ListLyftPriceModel getPriceEstmiate(String originLat, String originLong, String destLat, String destLon);
	
	ListLyftETAModel getETA(String originLat, String originLong);

}
