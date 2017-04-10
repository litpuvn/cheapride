package junit.com.cheapRide.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.util.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestCommonUtil {
	
	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;

	@Autowired
	CommonUtil commonUtilObj;
	
	@Value("${UBER_BASE_URL}")
	private String uberBaseUrl;
	
	@Value("${UBER_SERVER_TOKEN}")
	private String uberServerToken;
	
	@Value("${LYFT_CLIENT_TOKEN}")
	private String lyftSecretToken;

	private String LYFT_PRC_ESMT_URL = "/cost";
	
	@Value("${LYFT_BASE_URL}")
	private String lyftBaseUrl;

	private String UBER_PRC_ESMT_URL = "/estimates/price";
	
	//@Test
	public void testMakeGetRequestUber() {
				
		String reqUrl = uberBaseUrl+ UBER_PRC_ESMT_URL;
		Map<String,String> prmMap = new HashMap<String,String>();
		prmMap.put("start_latitude", ""+PICKUP_LATITUDE);
		prmMap.put("start_longitude", ""+PICKUP_LONGITUDE);
		prmMap.put("end_latitude", ""+DROPOFF_LATITUDE);
		prmMap.put("end_longitude", ""+DROPOFF_LONGITUDE);
		prmMap.put("server_token", ""+uberServerToken);
		
		Map<String,String> hdrMap = new HashMap<String,String>();
		hdrMap.put("Content-Type",  "application/json");
		hdrMap.put("Accept-Language",  "en_EN");
		
		String responseStr = commonUtilObj.makeGetReuqest(reqUrl, prmMap, hdrMap);
		System.out.println(responseStr);
		
	}
	
	@Test
	public void testMakeGetRequestLyft() {
		
		String reqUrl = lyftBaseUrl+ LYFT_PRC_ESMT_URL;
		Map<String,String> prmMap = new HashMap<String,String>();
		prmMap.put("start_lat", ""+PICKUP_LATITUDE);
		prmMap.put("start_lng", ""+PICKUP_LONGITUDE);
		prmMap.put("end_lat", ""+DROPOFF_LATITUDE);
		prmMap.put("end_lng", ""+DROPOFF_LONGITUDE);
		
		Map<String,String> hdrMap = new HashMap<String,String>();
		hdrMap.put("Content-Type",  "application/json");
		hdrMap.put("Accept-Language",  "en_EN");
		hdrMap.put("Authorization",  "bearer "+lyftSecretToken);
		
		String responseStr = commonUtilObj.makeGetReuqest(reqUrl, prmMap, hdrMap);
		System.out.println(responseStr);
		
	}
	
	
	
}
