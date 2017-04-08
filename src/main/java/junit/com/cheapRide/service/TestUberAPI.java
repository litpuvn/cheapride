package junit.com.cheapRide.service;

import static org.junit.Assert.fail;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.uber.ListUberETAModel;
import com.cheapRide.model.uber.ListUberPriceModel;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.uber.sdk.rides.client.services.RidesService;

/**
 *
 * @author Amit JUnit test class to test UBER API
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestUberAPI {

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;

	@Value("${UBER_CLIENT_ID}")
	private String uberClientId;

	@Value("${UBER_SERVER_TOKEN}")
	private String uberServerToken;

	@Value("${UBER_CLIENT_SECRET}")
	private String uberClietSecret;

	@Value("${UBER_BASE_URL}")
	private String uberBaseUrl;

	ObjectMapper mapper = new ObjectMapper();

	private RidesService service;

	private String UBER_PRC_ESMT_URL = "/estimates/price";

	private String UBER_TIME_ESMT_URL = "/estimates/time";

	private String Q_MARK = "?";



	//@Test
	public void TestGetEstimatedPrice() {
		String responseString =  getEstimatedPrice(PICKUP_LATITUDE, PICKUP_LONGITUDE, DROPOFF_LATITUDE, DROPOFF_LONGITUDE);
	}
	
	private String getEstimatedPrice(float start_lat, float start_lon, float dropOffLat, float dropOffLon){
		String returnStr = null;
		
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(UBER_PRC_ESMT_URL);
			urlBuilder.addQueryParameter("start_latitude", ""+start_lat);
			urlBuilder.addQueryParameter("start_longitude", ""+start_lon);
			urlBuilder.addQueryParameter("end_latitude", ""+dropOffLat);
			urlBuilder.addQueryParameter("end_longitude", ""+dropOffLon);
			urlBuilder.addQueryParameter("server_token", uberServerToken);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder()
					.header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN")
                    .url(url)
                    .build();

			Response response = client.newCall(request).execute();
			returnStr = new String(response.body().bytes());
			assert(returnStr!=null && response.code()==200);

		} catch (Exception exc) {
			fail(exc.getMessage());
		}
		return returnStr;
	}

	private HttpUrl.Builder getUberServiceUrl(String offSet){

		return HttpUrl.parse(uberBaseUrl+offSet+Q_MARK).newBuilder();

	}
	
	private String getEstimatedTime(float start_lat, float start_lon){
		String returnStr = null;
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(UBER_TIME_ESMT_URL);
			urlBuilder.addQueryParameter("start_latitude", ""+PICKUP_LATITUDE);
			urlBuilder.addQueryParameter("start_longitude", ""+PICKUP_LONGITUDE);

			urlBuilder.addQueryParameter("server_token", uberServerToken);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder()
					.header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN")
                    .url(url)
                    .build();

			Response response = client.newCall(request).execute();
			returnStr = new String(response.body().bytes());
			System.out.println(returnStr);
			assert(returnStr!=null && response.code()==200);

		} catch (Exception exc) {
			fail(exc.getMessage());
		}
		return returnStr;
		
	}


	//@Test
	public void testGetEstimatedTime() {
		
		getEstimatedPrice(PICKUP_LATITUDE, PICKUP_LONGITUDE, DROPOFF_LATITUDE, DROPOFF_LONGITUDE);
	}
	
	
	@Test
	public void uberPriceJsonToObject() {
		try {
			
			String etaEstStr =  getEstimatedTime(PICKUP_LATITUDE, PICKUP_LONGITUDE);
			
			ListUberETAModel uberETAEstModel = mapper.readValue(etaEstStr, ListUberETAModel.class);
			
			assert(uberETAEstModel.getTimes().get(0).getEstimate() == 60);
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}
	
	@Test
	public void uberETAJsonToObject() {
		try {
			
			String etatStr =  getEstimatedPrice(PICKUP_LATITUDE, PICKUP_LONGITUDE, DROPOFF_LATITUDE, DROPOFF_LONGITUDE);
			
			ListUberPriceModel uberPriceEstModel = mapper.readValue(etatStr, ListUberPriceModel.class);
			
			assert(uberPriceEstModel.getPrices().get(0).getDistance() == 1.4);
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}


}
