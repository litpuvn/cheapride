package com.cheapRide.service;

import com.cheapRide.model.uber.ListUberETAModel;
import com.cheapRide.model.uber.ListUberPriceModel;

/**
 * 
 * @author Amit
 *
 */
public interface UberEstmiateService {

	ListUberPriceModel getPriceEstmiate(String originLat, String originLong, String destLat, String destLon);
	
	ListUberETAModel getETA(String originLat, String originLong);
}
