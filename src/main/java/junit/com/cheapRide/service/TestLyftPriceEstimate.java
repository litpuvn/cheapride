package junit.com.cheapRide.service;

import static org.junit.Assert.fail;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.lyft.ListLyftPriceModel;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * 
 * @author Amit
 * Junit test case for LYFT price estmiate API
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestLyftPriceEstimate {

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;

	@Value("${LYFT_CLIENT_ID}")
	private String lyftClientId;

	@Value("${LYFT_CLIENT_SECRET}")
	private String lyftClietSecret;

	@Value("${LYFT_CLIENT_TOKEN}")
	private String lyftSecretToken;

	@Value("${LYFT_BASE_URL}")
	private String lyftBaseUrl;

	private String LYFT_PRC_ESMT_URL = "/cost";

	private String Q_MARK = "?";
	
	ObjectMapper mapper = new ObjectMapper();
	

	@Test
	public void TestConvertJsonToObject() {

		try {

			String etaStr = getEstimatedPrice(PICKUP_LATITUDE, PICKUP_LONGITUDE,DROPOFF_LATITUDE,DROPOFF_LONGITUDE );

			ListLyftPriceModel lyftPriceEstModel = mapper.readValue(etaStr, ListLyftPriceModel.class);

			assert (lyftPriceEstModel.getCost_estimates().size() > 0);
			
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}


	public String getEstimatedPrice(float start_lat, float start_lon, float dropOffLat, float dropOffLon) {
		String returnStr = null;
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(LYFT_PRC_ESMT_URL);
			urlBuilder.addQueryParameter("start_lat", ""+start_lat);
			urlBuilder.addQueryParameter("start_lng", ""+start_lon);
			urlBuilder.addQueryParameter("end_lat", ""+dropOffLat);
			urlBuilder.addQueryParameter("end_lng", ""+dropOffLon);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder()
					.header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN")
					.header("Authorization", "bearer "+lyftSecretToken)
                    .url(url)
                    .build();

			Response response = client.newCall(request).execute();
			returnStr = new String(response.body().bytes());
			System.out.println(returnStr);
			assert(returnStr!=null && response.code()==200);

		} catch (Exception exc) {
			//fail(exc.getMessage());
		}
		return returnStr;
	}

	//@Test
	public void testGetEstimatedPrice(){
		getEstimatedPrice(DROPOFF_LATITUDE, PICKUP_LONGITUDE, PICKUP_LATITUDE, PICKUP_LONGITUDE);
	}
	private HttpUrl.Builder getUberServiceUrl(String offSet){

		return HttpUrl.parse(lyftBaseUrl+offSet+Q_MARK).newBuilder();

	}
	
	
	



}
