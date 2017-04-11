package com.cheapRide.service.impl;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheapRide.model.lyft.ListLyftPriceModel;
import com.cheapRide.model.uber.ListUberPriceModel;
import com.cheapRide.service.RideEstimateService;
import com.cheapRide.service.UberEstmiateService;

@Service
public class RideEstimateServiceImpl implements RideEstimateService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RideEstimateServiceImpl.class);

	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	LyftEstimateService lyftEstimateService;
	
	@Autowired
	UberEstmiateService uberEstimateService;

	public String getEstimatedPrice(float originLat, float originLong, float destLat, float destLon, Map<String,String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		String returnVal = null;
		try {
			
			ListUberPriceModel listUberModel = uberEstimateService.getPriceEstmiate(""+originLat, ""+originLong,""+destLat, ""+destLon);
			ListLyftPriceModel listLyftModel = lyftEstimateService.getPriceEstmiate(""+originLat, ""+originLong,""+destLat, ""+destLon);
			
			//getCheapRideEstimate(listUberModel, listLyftModel, options);
			
		} catch (Exception exc) {
			logger.error("ERROR : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
					+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
					+ destLon);
		}
		logger.debug("Start : RideEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		return returnVal;
	}




	private void getCheapMinCostUber(ListUberPriceModel listUberModel,
			ListLyftPriceModel listLyftModel, Map<String, String> options) {

		
		
	}

	



	@Test
	public String getEstimatedTime(float originLat, float originLong, Map<String,String> options) {
		logger.debug("Start : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
				+ " origin longitude " + originLong );
		String returnVal = null;
		try {
			
		} catch (Exception exc) {
			logger.error("ERROR : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
					+ " origin longitude " + originLong);
		}
		logger.debug("Start : RideEstimateServiceImpl => getEstimatedTime  for origin lattitude" + originLat
				+ " origin longitude " + originLong );
		return returnVal;
	}


}
