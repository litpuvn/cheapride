package junit.com.cheapRide.service;

import static org.junit.Assert.fail;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.RideRequestModel;
import com.cheapRide.model.RideResponseModel;
import com.cheapRide.model.lyft.LyftRideRequestModel;
import com.cheapRide.service.impl.LyftRideBookingServiceImpl;

/**
 * 
 * @author Amit
 * Junit test case for LYFT price estmiate API
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestLyftBookingService {

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	LyftRideBookingServiceImpl service;
	

	//@Test
	public void testGetToken() {

		try {
			String token = service.getLyftAuthToken();
			assert (token != null);
			
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}
	
	@Test
	public void testRequestLyftRide() {

		try {
			String requestJson = "{\"ride_type\" : \"lyft\", \"origin\" : {\"lat\" : 37.7763, \"lng\" : -122.3918 }, \"destination\" : {\"lat\" : 37.7972, \"lng\" : -122.4533 } }";
			LyftRideRequestModel model = mapper.readValue(requestJson, LyftRideRequestModel.class);
			RideRequestModel reideRqModel = new RideRequestModel();
			reideRqModel.setDestination(model.getDestination());
			reideRqModel.setOrigin(model.getOrigin());
			reideRqModel.setProvider("lyft");
			reideRqModel.setRide_type("lyft");
			RideResponseModel ride = service.requestLyftRide(reideRqModel);
			assert (ride.getRideId() != null);
			
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}

}
