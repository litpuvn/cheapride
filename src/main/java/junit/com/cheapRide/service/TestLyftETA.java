package junit.com.cheapRide.service;

import static org.junit.Assert.fail;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.lyft.ListLyftETAModel;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * 
 * @author Amit Junit test case for LYFT EAT API
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestLyftETA {

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;

	@Value("${LYFT_CLIENT_ID}")
	private String lyftClientId;

	@Value("${LYFT_CLIENT_SECRET}")
	private String lyftClietSecret;

	@Value("${LYFT_CLIENT_TOKEN}")
	private String lyftSecretToken;

	@Value("${LYFT_BASE_URL}")
	private String lyftBaseUrl;

	private String LYFT_PRC_ESMT_URL = "/eta";

	private String Q_MARK = "?";

	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void TestConvertJsonToObject() {

		try {

			String etaStr = getETA(PICKUP_LATITUDE, PICKUP_LONGITUDE);

			ListLyftETAModel lyftEtaEstModel = mapper.readValue(etaStr, ListLyftETAModel.class);

			assert (lyftEtaEstModel.getEta_estimates().get(0).getEta_seconds() == 60);
			
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}

	public String getETA(float pickupLat, float pickupLon) {
		String returnStr = null;
		try {
			OkHttpClient client = new OkHttpClient();

			HttpUrl.Builder urlBuilder = getUberServiceUrl(LYFT_PRC_ESMT_URL);
			urlBuilder.addQueryParameter("lat", "" + pickupLat);
			urlBuilder.addQueryParameter("lng", "" + pickupLon);
			String url = urlBuilder.build().toString();

			Request request = new Request.Builder().header("Content-Type", "application/json")
					.header("Accept-Language", "en_EN").header("Authorization", "bearer " + lyftSecretToken).url(url)
					.build();

			Response response = client.newCall(request).execute();
			returnStr = new String(response.body().bytes());
			assert (returnStr != null && response.code() == 200);

		} catch (Exception exc) {
			// fail(exc.getMessage());
		}
		return returnStr;
	}

	private HttpUrl.Builder getUberServiceUrl(String offSet) {

		return HttpUrl.parse(lyftBaseUrl + offSet + Q_MARK).newBuilder();

	}

	// @Test
	public void testGetETA() {

		getETA(PICKUP_LATITUDE, PICKUP_LONGITUDE);

	}



}
