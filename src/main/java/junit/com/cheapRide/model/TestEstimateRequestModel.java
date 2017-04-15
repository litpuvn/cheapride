package junit.com.cheapRide.model;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.EstimateRequestModel;
import com.cheapRide.model.LocationModel;

/**
 * 
 * @author Amit
 * Junit test case for LYFT EAT API
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class TestEstimateRequestModel{

	private static final float PICKUP_LATITUDE = 37.7753f;
	private static final float PICKUP_LONGITUDE = -122.418f;
	private static final float DROPOFF_LATITUDE = 37.787654f;
	private static final float DROPOFF_LONGITUDE = -122.40276f;
	ObjectMapper mapper = new ObjectMapper();
	//String estReqStr = "{\\"origin\\":{\\"lat\\":37.7753,\\"lon\\":-122.418},\\"destination\\":{\\"lat\\":37.7753,\\"lon\\":-122.418},\\"options\\":{\\"uber_car_type\\":\\"sedan\\",\\"lyft_car_type\\":\\"lyft_plus\\"}}";
	@Test
	public void objectToJson() {
		try {
			LocationModel origin = new LocationModel();
			origin.setLat(PICKUP_LATITUDE);
			origin.setLon(PICKUP_LONGITUDE);
			
			LocationModel destination = new LocationModel();
			destination.setLat(PICKUP_LATITUDE);
			destination.setLon(PICKUP_LONGITUDE);
			
			Map<String, String> options = new HashMap<String, String>();
			options.put("lyft_car_type", "lyft_plus");
			options.put("uber_car_type", "sedan");
			
			EstimateRequestModel estReqModel = new EstimateRequestModel();
			estReqModel.setDestination(destination);
			estReqModel.setOrigin(origin);
			estReqModel.setOptions(options);
			String estReqStr = mapper.writeValueAsString(estReqModel);
			System.out.println(estReqStr);
			assert(estReqStr.contains("origin"));
			
			EstimateRequestModel estReqModelConvert = mapper.readValue(estReqStr, EstimateRequestModel.class) ;
			assert(estReqModelConvert.getDestination().getLat()==estReqModel.getDestination().getLat());
			
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}




}
