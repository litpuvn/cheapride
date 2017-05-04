package com.cheapRide.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cheapRide.model.Car;
import com.cheapRide.model.Driver;
import com.cheapRide.model.RideRequestModel;
import com.cheapRide.model.RideResponseModel;
import com.cheapRide.model.lyft.AuthTokenModel;
import com.cheapRide.model.lyft.LyftRideRequestModel;
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
	
	@Value("${4_seats}")
	private String seats4;

	@Value("${6_or_more_seats}")
	private String seats6;

	@Value("${luxury_4_seats}")
	private String lux4;
	
	@Value("${share}")
	private String share;

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

	public RideResponseModel requestLyftRide(RideRequestModel requestObj) {
		String requestJson = null;
		String returnObj = "";
		String rideId = "";
		RideResponseModel rideResponseModel  = null;
		try {
			
			String uberCarType = null;
			String lyftCarType = null;
			String carType = requestObj.getRide_type();
			if(carType != null){
				String[] carTypeArr = null;;
				if("4_seats".equalsIgnoreCase(carType))
					carTypeArr = seats4.split(":");
				if("6_or_more_seats".equalsIgnoreCase(carType))
					carTypeArr = seats6.split(":");
				if("luxury_4_seats".equalsIgnoreCase(carType))
					carTypeArr = lux4.split(":");
				if("share".equalsIgnoreCase(carType))
					carTypeArr = share.split(":");
				uberCarType = carTypeArr[1];
				lyftCarType = carTypeArr[0];
			}
			if("lyft".equalsIgnoreCase(requestObj.getProvider())){
				requestObj.setRide_type(lyftCarType);
			}
			LyftRideRequestModel lyftRideRequestModel = convertToLyftRequestModel(requestObj);
			requestJson = mapper.writeValueAsString(lyftRideRequestModel);
			logger.debug("Start : LyftRideBookingServiceImpl => requestLyftRide for object " + requestJson);
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
				rideResponseModel = convertIntoRideResponseModel(returnObj);
			} catch (Exception e1) {

				logger.error("ERROR : LyftRideBookingServiceImpl => requestLyftRide => Reason => " + e1.getMessage());
			}

		} catch (Exception e) {
			logger.error("ERROR : LyftRideBookingServiceImpl => requestLyftRide => Reason => " + e.getMessage());

			e.printStackTrace();
		}

		logger.debug("End : LyftRideBookingServiceImpl => requestLyftRide for object " + requestJson);
		return rideResponseModel;
	}

	private RideResponseModel convertIntoRideResponseModel(String returnObj) {
		RideResponseModel rideResModel = new RideResponseModel();
		Car carObj = new Car();
		Driver driverObj = new Driver();
		try{
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			Map<String, Object> map = mapper.readValue(returnObj, new TypeReference<Map<String, Object>>() {});
			carObj.setColor(((Map)map.get("vehicle")).get("color").toString());
			carObj.setMake(((Map)map.get("vehicle")).get("make").toString());
			carObj.setLicense_plate(((Map)map.get("vehicle")).get("license_plate").toString());
			carObj.setImageUrl(((Map)map.get("vehicle")).get("image_url").toString());
			carObj.setLicense_plate_state(((Map)map.get("vehicle")).get("license_plate_state").toString());
			carObj.setYear(((Map)map.get("vehicle")).get("year").toString());
			carObj.setModel(((Map)map.get("vehicle")).get("model").toString());
			rideResModel.setCar(carObj);
			
			driverObj.setFirstName(((Map)map.get("driver")).get("first_name").toString());
			driverObj.setImageUrl(((Map)map.get("driver")).get("image_url").toString());
			driverObj.setPhone_number(((Map)map.get("driver")).get("phone_number").toString());
			driverObj.setRating(((Map)map.get("driver")).get("rating").toString());
			rideResModel.setDriver(driverObj);
			rideResModel.setRideId(map.get("status").toString());
			rideResModel.setStatus(map.get("ride_id").toString());
		}catch(Exception e){
			System.out.println(e.getMessage()); 
			e.printStackTrace();
		}
		
		return rideResModel;
	}

	private LyftRideRequestModel convertToLyftRequestModel(RideRequestModel requestObj) {
		LyftRideRequestModel lyftRideRequestModel = new LyftRideRequestModel();
		if(requestObj!=null){
			lyftRideRequestModel.setOrigin(requestObj.getOrigin());
			lyftRideRequestModel.setDestination(requestObj.getDestination());
			lyftRideRequestModel.setRide_type(requestObj.getRide_type());
			
		}
		return lyftRideRequestModel;
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
