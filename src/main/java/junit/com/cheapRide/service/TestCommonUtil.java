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

	private String UBER_ETA_URL = "/estimates/time";
	@Test
	public void testMakeGetRequestUber() {

		String reqUrl = uberBaseUrl + UBER_ETA_URL;
		Map<String, String> prmMap = new HashMap<String, String>();
		prmMap.put("start_latitude", "" + PICKUP_LATITUDE);
		prmMap.put("start_longitude", "" + PICKUP_LONGITUDE);
		//prmMap.put("end_latitude", "" + DROPOFF_LATITUDE);
		//prmMap.put("end_longitude", "" + DROPOFF_LONGITUDE);
		prmMap.put("server_token", "" + uberServerToken);

		Map<String, String> hdrMap = new HashMap<String, String>();
		hdrMap.put("Content-Type", "application/json");
		hdrMap.put("Accept-Language", "en_EN");

		String responseStr = commonUtilObj.makeGetReuqest(reqUrl, prmMap, hdrMap);
		System.out.println(responseStr);

	}

	// @Test
	public void testMakeGetRequestLyft() {

		String reqUrl = lyftBaseUrl + LYFT_PRC_ESMT_URL;
		Map<String, String> prmMap = new HashMap<String, String>();
		prmMap.put("start_lat", "" + PICKUP_LATITUDE);
		prmMap.put("start_lng", "" + PICKUP_LONGITUDE);
		prmMap.put("end_lat", "" + DROPOFF_LATITUDE);
		prmMap.put("end_lng", "" + DROPOFF_LONGITUDE);

		Map<String, String> hdrMap = new HashMap<String, String>();
		hdrMap.put("Content-Type", "application/json");
		hdrMap.put("Accept-Language", "en_EN");
		hdrMap.put("Authorization", "bearer " + lyftSecretToken);

		String responseStr = commonUtilObj.makeGetReuqest(reqUrl, prmMap, hdrMap);
		System.out.println(responseStr);

	}
	//@Test
	public void testFinishRow() {
		for (int i = 0; i < 3; i++) {
			
			int offSet = ((i + 1) * 2) - 1;
			 printIndex(offSet, i);
		}
	}

	private void printIndex(int offSet , int rank) {
		int next_count = rank;
		String flag  = "F"; //F = forward R= Reverse
		System.out.println("FOR RANK"+rank);
		for (int i = 0; i < 72; i++) {

			if (next_count == i ) {
					System.out.println(" NEXT COUNT "+next_count);
				if (flag == "F") {
					flag = "R";
					next_count = next_count + (12 * 2) - offSet;
				}
				else if (flag == "R") {
					flag = "F";
					next_count = next_count + offSet;
				}
				
				//next_count = next_count + my_world;
			}

		}
		
		System.out.println("=================");
	}

	/*int finishRow(int numPartitions, int n, int rank) {
		int returnVal = 0;
		double tmp1 = (double)( rank + 1 )/ (double) numPartitions;
		 tmp1 = Math.sqrt(tmp1);
		tmp1 = tmp1 * n;
		returnVal = (int) Math.ceil(tmp1) - 1;
		return returnVal;
		//return (int)Math.ceil(n*Math.sqrt(((double)(rank + 1)) / (double)numPartitions)) - 1;
	}*/

}
