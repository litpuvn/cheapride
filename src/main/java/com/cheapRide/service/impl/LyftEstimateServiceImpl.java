package com.cheapRide.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cheapRide.model.lyft.ListLyftETAModel;
import com.cheapRide.model.lyft.ListLyftPriceModel;
import com.cheapRide.util.CommonUtil;
/**
 * 
 * @author Amit
 *
 */
@Service
public class LyftEstimateServiceImpl implements LyftEstimateService {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LyftEstimateServiceImpl.class);

	@Autowired
	CommonUtil util;

	@Value("${LYFT_CLIENT_ID}")
	private String lyftClientId;

	@Value("${LYFT_CLIENT_SECRET}")
	private String lyftClietSecret;

	@Value("${LYFT_CLIENT_TOKEN}")
	private String lyftSecretToken;

	@Value("${LYFT_BASE_URL}")
	private String lyftBaseUrl;

	private String LYFT_ETA_ESMT_URL = "/eta";
	
	private String LYFT_PRC_ESMT_URL = "/cost";

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public ListLyftPriceModel getPriceEstmiate(String originLat, String originLong, String destLat, String destLon) {
		logger.debug("Start : LyftEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		ListLyftPriceModel lyftPriceModel = null;
		try {
			String reqUrl = lyftBaseUrl + LYFT_PRC_ESMT_URL;
			Map<String,String> prmMap = new HashMap<String,String>();
			prmMap.put("start_lat", ""+originLat);
			prmMap.put("start_lng", ""+originLong);
			prmMap.put("end_lat", ""+destLat);
			prmMap.put("end_lng", ""+destLon);
			
			Map<String,String> hdrMap = new HashMap<String,String>();
			hdrMap.put("Content-Type",  "application/json");
			hdrMap.put("Accept-Language",  "en_EN");
			hdrMap.put("Authorization",  "bearer "+lyftSecretToken);
			

			lyftPriceModel = mapper.readValue(util.makeGetReuqest(reqUrl, prmMap, hdrMap), ListLyftPriceModel.class);

		} catch (Exception e) {
			logger.error("ERROR : LyftEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
					+ " origin longitude " + originLong + " destination lattitude " + destLat
					+ " destination longitude " + destLon);
			e.printStackTrace();
		}
		logger.debug("End : LyftEstimateServiceImpl => getPriceEstmiate  for origin lattitude" + originLat
				+ " origin longitude " + originLong + " destination lattitude " + destLat + " destination longitude "
				+ destLon);
		return lyftPriceModel;
	}

	@Override
	public ListLyftETAModel getETA(String originLat, String originLong) {
		logger.debug("Start : LyftEstimateServiceImpl => getETA  for origin lattitude" + originLat
				+ " origin longitude " + originLong);
		ListLyftETAModel lyftETAModel = null;
		try {
			String reqUrl = lyftBaseUrl + LYFT_ETA_ESMT_URL;
			Map<String, String> prmMap = new HashMap<String, String>();
			prmMap.put("lat", "" + originLat);
			prmMap.put("lng", "" + originLong);

			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Accept-Language", "en_EN");
			hdrMap.put("Authorization",  "bearer "+lyftSecretToken);

			lyftETAModel = mapper.readValue(util.makeGetReuqest(reqUrl, prmMap, hdrMap), ListLyftETAModel.class);

		} catch (Exception e) {
			logger.error("ERROR : LyftEstimateServiceImpl => getETA  for origin lattitude" + originLat
					+ " origin longitude " + originLong);
			e.printStackTrace();
		}
		logger.debug(
				"End : LyftEstimateServiceImpl => getETA  for origin lattitude" + originLat + " origin longitude ");
		return lyftETAModel;
	}

}
