package com.cheapRide.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cheapRide.model.uber.ListUberETAModel;
import com.cheapRide.model.uber.ListUberPriceModel;
import com.cheapRide.service.UberEstmiateService;
import com.cheapRide.util.CommonUtil;

/**
 * 
 * @author Amit
 *
 */
@Service
public class UberEstmiateServiceImpl implements UberEstmiateService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UberEstmiateServiceImpl.class);

	@Autowired
	CommonUtil util;

	@Value("${UBER_BASE_URL}")
	private String uberBaseUrl;

	@Value("${UBER_SERVER_TOKEN}")
	private String uberServerToken;

	private String UBER_PRC_ESMT_URL = "/estimates/price";

	private String UBER_ETA_URL = "/estimates/time";

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public ListUberPriceModel getPriceEstmiate(String originLat, String originLong, String destLat, String destLon) {
		logger.debug("Start : UberEstmiateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		ListUberPriceModel uberPriceModel = null;
		try {
			String reqUrl = uberBaseUrl + UBER_PRC_ESMT_URL;
			Map<String, String> prmMap = new HashMap<String, String>();
			prmMap.put("start_latitude", "" + originLat);
			prmMap.put("start_longitude", "" + originLong);
			prmMap.put("end_latitude", "" + destLat);
			prmMap.put("end_longitude", "" + destLon);
			prmMap.put("server_token", "" + uberServerToken);

			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Accept-Language", "en_EN");

			uberPriceModel = mapper.readValue(util.makeGetReuqest(reqUrl, prmMap, hdrMap), ListUberPriceModel.class);

		} catch (Exception e) {
			logger.error("ERROR : UberEstmiateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
					+ " origin longitude " + originLong + " destination lattitude " + destLat
					+ " destination longitude " + destLon);
			e.printStackTrace();
		}
		logger.debug("End : UberEstmiateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		return uberPriceModel;
	}

	@Override
	public ListUberETAModel getETA(String originLat, String originLong) {
		logger.debug("Start : UberEstmiateServiceImpl => getETA  for origin lattitude" + originLat
				+ " origin longitude " + originLong);
		ListUberETAModel uberETAModel = null;
		try {
			String reqUrl = uberBaseUrl + UBER_PRC_ESMT_URL;
			Map<String, String> prmMap = new HashMap<String, String>();
			prmMap.put("start_latitude", "" + originLat);
			prmMap.put("start_longitude", "" + originLong);
			prmMap.put("server_token", "" + uberServerToken);

			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Accept-Language", "en_EN");

			uberETAModel = mapper.readValue(util.makeGetReuqest(reqUrl, prmMap, hdrMap), ListUberETAModel.class);

		} catch (Exception e) {
			logger.error("ERROR : UberEstmiateServiceImpl => getETA  for origin lattitude" + originLat
					+ " origin longitude " + originLong);
			e.printStackTrace();
		}
		logger.debug(
				"End : UberEstmiateServiceImpl => getETA  for origin lattitude" + originLat + " origin longitude ");
		return uberETAModel;
	}

}
