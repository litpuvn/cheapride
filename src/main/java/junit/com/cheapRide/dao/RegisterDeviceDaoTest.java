package junit.com.cheapRide.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Amit
 * JUnit test class to test the DB operations
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class RegisterDeviceDaoTest {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private String deviceId = "Test123";
	
	@Test
	public void getDevice() {
		try {
			System.out.println("test class");
			//TODO - add test logic to save entries in mongoDB
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}

	
	//@Test
	public void addDevice() {
		try {

			//TODO - add test logic to save entries in mongoDB
		} catch (Exception exc) {
			fail(exc.getMessage());
		}
	}


}
