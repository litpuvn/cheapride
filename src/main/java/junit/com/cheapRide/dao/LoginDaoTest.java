
package junit.com.cheapRide.dao;
/**
 * @author Agnes Priyadharshini
 */
import static org.junit.Assert.fail;

import javax.servlet.jsp.jstl.core.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.cheapRide.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class LoginDaoTest {
	@Autowired
	 private MongoTemplate mongoOperation;
	
	private String name = "john";
	private String username = "jo";
	private String password = "Test123";
	private String newpwd = "newpwd";
//method for saving the user details into the database
	@Test
	public void setUserDetails() {
		try {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);

			// save
			mongoOperation.save(user);

			// now user object got the created id.
			System.out.println("1. user : " + user);

		} catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}

	}
	//method for saving the getting the details from the database
	//@Test
	public void getdetails() {
		try {
			// query to search user
			Query searchUserQuery = new Query(Criteria.where("userName").is(username));

			// find the saved user again.
			User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
			System.out.println("getData : " + savedUser);
		} catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}

	}
	//method for updating the password into the database
	//@Test
	public void updatePassword() {
		try {
			Query q = new Query(Criteria.where("userName").is(username));
			// update password
			mongoOperation.updateFirst(q, Update.update(password, newpwd), User.class);

			// find the updated user object
			User updatedUser = mongoOperation.findOne(new Query(Criteria.where("userName").is(username)), User.class);

			System.out.println("details after the update : " + updatedUser);
		} catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}
		
	}

}
