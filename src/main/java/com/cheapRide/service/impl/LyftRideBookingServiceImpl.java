package com.cheapRide.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cheapRide.model.lyft.AuthTokenModel;
import com.cheapRide.service.LyftRideBookingService;
import com.cheapRide.util.CommonUtil;
import com.squareup.okhttp.Credentials;

/**
 * 
 * @author Amit
 *
 */
@Service
public class LyftRideBookingServiceImpl implements LyftRideBookingService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(LyftRideBookingServiceImpl.class);

	private static ObjectMapper mapper = new ObjectMapper();

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

	private static String RIDE_URL = "/rides";

	public String getLyftAuthToken() {

		logger.debug("Start : LyftRideBookingServiceImpl => getLyftAuthToken  ");

		String returnToken = "";

		try {
			String reqUrl = "https://api.lyft.com/oauth/token";

			String credential = Credentials.basic(lyftClientId, "SANDBOX-" + lyftClietSecret);
			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Authorization", credential);
			try {
				String bodyStr = "{\"grant_type\": \"client_credentials\", \"scope\": \"public rides.read rides.request\"}";
				returnToken = CommonUtil.makePostReuqest(reqUrl, bodyStr, hdrMap);
				returnToken = mapper.readValue(returnToken, AuthTokenModel.class).getAccess_token();

			} catch (Exception e1) {

				logger.error("ERROR : LyftRideBookingServiceImpl => getLyftAuthToken => Reason => " + e1.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR : LyftRideBookingServiceImpl => getLyftAuthToken => Reason => " + e.getMessage());

			e.printStackTrace();
		}

		logger.debug("End : LyftRideBookingServiceImpl => getLyftAuthToken  ");
		return returnToken;
	}

	public String requestLyftRide(String requestJson) {

		logger.debug("Start : LyftRideBookingServiceImpl => requestLyftRide for object " + requestJson);

		String returnObj = "";
		String rideId = "";
		try {
			String reqUrl = lyftBaseUrl + RIDE_URL;
			String accessToken = getLyftAuthToken();
			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Authorization", "Bearer " + accessToken);
			try {
				returnObj = CommonUtil.makePostReuqest(reqUrl, requestJson, hdrMap);
				Map<String, Object> map = mapper.readValue(returnObj, new TypeReference<Map<String, Object>>() {
				});
				rideId = (String) map.get("ride_id");
				changeStatusToAccept(rideId, accessToken);
				returnObj = getRideDetails(rideId, accessToken);
			} catch (Exception e1) {

				logger.error("ERROR : LyftRideBookingServiceImpl => requestLyftRide => Reason => " + e1.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR : LyftRideBookingServiceImpl => requestLyftRide => Reason => " + e.getMessage());

			e.printStackTrace();
		}

		logger.debug("End : LyftRideBookingServiceImpl => requestLyftRide for object " + requestJson);
		return returnObj;
	}

	public String changeStatusToAccept(String rideId, String accessToken) {

		logger.debug("Start : LyftRideBookingServiceImpl => changeStatusToAccept for ride " + rideId);

		String returnObj = "";
		try {
			String reqUrl = lyftBaseUrl + "/sandbox/rides/" + rideId;
			String requestJson = "{\"status\": \"accepted\"}";
			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Authorization", "Bearer " + accessToken);
			try {
				returnObj = CommonUtil.makePutReuqest(reqUrl, requestJson, hdrMap);
			} catch (Exception e1) {

				logger.error(
						"ERROR : LyftRideBookingServiceImpl => changeStatusToAccept => Reason => " + e1.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR : LyftRideBookingServiceImpl => changeStatusToAccept => Reason => " + e.getMessage());

			e.printStackTrace();
		}

		logger.debug("End : LyftRideBookingServiceImpl => changeStatusToAccept for ride " + rideId);
		return returnObj;
	}

	public String getRideDetails(String rideId, String accessToken) {

		logger.debug("Start : LyftRideBookingServiceImpl => getRideDetails for ride " + rideId);

		String returnObj = "";
		try {
			String reqUrl = lyftBaseUrl + "/rides/" + rideId;
			Map<String, String> hdrMap = new HashMap<String, String>();
			hdrMap.put("Content-Type", "application/json");
			hdrMap.put("Authorization", "Bearer " + accessToken);
			try {
				returnObj = CommonUtil.makeGetReuqest(reqUrl, null, hdrMap);
			} catch (Exception e1) {

				logger.error("ERROR : LyftRideBookingServiceImpl => getRideDetails => Reason => " + e1.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR : LyftRideBookingServiceImpl => getRideDetails => Reason => " + e.getMessage());

			e.printStackTrace();
		}

		logger.debug("End : LyftRideBookingServiceImpl => getRideDetails for ride " + rideId);
		return returnObj;
	}

}
