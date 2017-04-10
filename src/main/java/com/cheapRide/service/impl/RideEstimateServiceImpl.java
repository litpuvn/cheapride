package com.cheapRide.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cheapRide.service.RideEstimateService;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.uber.sdk.rides.client.services.RidesService;

@Service
public class RideEstimateServiceImpl implements RideEstimateService {


	@Value("${UBER_CLIENT_ID}")
	private String uberClientId;

	@Value("${UBER_SERVER_TOKEN}")
	private String uberServerToken;

	@Value("${UBER_CLIENT_SECRET}")
	private String uberClietSecret;

	@Value("${UBER_BASE_URL}")
	private String uberBaseUrl;

	private RidesService service;

	private String UBER_PRC_ESMT_URL = "/estimates/price";

	private String UBER_TIME_ESMT_URL = "/estimates/time";

	private String Q_MARK = "?";

	public String getEstimatedPrice(float pickUpLat, float pickUpLong, float dropOffLat, float dropOffLong) {
		String returnVal = null;
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(UBER_PRC_ESMT_URL);
			urlBuilder.addQueryParameter("start_latitude", "" + pickUpLat);
			urlBuilder.addQueryParameter("start_longitude", "" + pickUpLong);
			urlBuilder.addQueryParameter("end_latitude", "" + dropOffLat);
			urlBuilder.addQueryParameter("end_longitude", "" + dropOffLong);
			urlBuilder.addQueryParameter("server_token", uberServerToken);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder().header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN").url(url).build();

			Response response = client.newCall(request).execute();
			String responseString = new String(response.body().bytes());
			System.out.println(responseString);
			returnVal =  responseString;

		} catch (Exception exc) {
			fail(exc.getMessage());
		}
		return returnVal;
	}

	private HttpUrl.Builder getUberServiceUrl(String offSet) {

		return HttpUrl.parse(uberBaseUrl + offSet + Q_MARK).newBuilder();

	}

	@Test
	public String getEstimatedTime(float pickUpLat, float pickUpLong) {
		String returnVal = null;
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(UBER_TIME_ESMT_URL);
			urlBuilder.addQueryParameter("start_latitude", "" + pickUpLat);
			urlBuilder.addQueryParameter("start_longitude", "" + pickUpLong);

			urlBuilder.addQueryParameter("server_token", uberServerToken);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder().header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN").url(url).build();

			Response response = client.newCall(request).execute();
			String responseString = new String(response.body().bytes());
			System.out.println(responseString);
			returnVal =  responseString;

		} catch (Exception exc) {
			fail(exc.getMessage());
		}
		return returnVal;
	}

}
