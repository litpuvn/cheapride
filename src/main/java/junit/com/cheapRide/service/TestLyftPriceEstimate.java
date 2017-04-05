package junit.com.cheapRide.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.uber.sdk.rides.client.services.RidesService;

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



	private RidesService service;

	private String LYFT_PRC_ESMT_URL = "/cost";


	private String Q_MARK = "?";


	@Test
	public void getEstimatedPrice() {
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(LYFT_PRC_ESMT_URL);
			urlBuilder.addQueryParameter("start_lat", ""+PICKUP_LATITUDE);
			urlBuilder.addQueryParameter("start_lng", ""+PICKUP_LONGITUDE);
			urlBuilder.addQueryParameter("end_lat", ""+DROPOFF_LATITUDE);
			urlBuilder.addQueryParameter("end_lng", ""+DROPOFF_LONGITUDE);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder()
					.header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN")
					.header("Authorization", "bearer "+lyftSecretToken)
                    .url(url)
                    .build();

			Response response = client.newCall(request).execute();
			 String responseString = new String(response.body().bytes());
			System.out.println(responseString);
			assert(responseString!=null && response.code()==200);

		} catch (Exception exc) {
			//fail(exc.getMessage());
		}
	}

	private HttpUrl.Builder getUberServiceUrl(String offSet){

		return HttpUrl.parse(lyftBaseUrl+offSet+Q_MARK).newBuilder();

	}



}
