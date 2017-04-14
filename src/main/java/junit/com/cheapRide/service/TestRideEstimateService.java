package junit.com.cheapRide.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.service.RideEstimateService;

/**
 *
 * @author Amit JUnit test class to test UBER API
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestRideEstimateService {

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;

	@Autowired
	private RideEstimateService rideEstimateService;

	@Test
	public void TestGetEstimate() {
		String responseString =  rideEstimateService.getEstimates(PICKUP_LATITUDE, PICKUP_LONGITUDE, DROPOFF_LATITUDE, DROPOFF_LONGITUDE, null);
		System.out.println(responseString);
	}
	
	

}
