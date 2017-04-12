package junit.com.cheapRide.dao;
/**
 * @author Agnes
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.historyModel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class HistoryDaoImplTest {
	private String date = "02/02/2017";
	private String provider = "Uber";
	private String pickup = "III Pl";
	private String destination = "Texas Tech University";
	private String fee = "$3";
	private String type = "ride booking";

	@Autowired
	 private MongoTemplate mongoTemplate;
	@Test
public void setHistory(){
		try{
			historyModel history = new historyModel();
			history.setDate(date);
			history.setDestination(destination);
			history.setFee(fee);
			history.setPickup(pickup);
			history.setProvider(provider);
			history.setType(type);
			
			mongoTemplate.save(history);
			System.out.println(history);
		}catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}
		
	}
	@Test
	public void getHistory() {
		try{
			Query searchType = new Query(Criteria.where("type").is(type));
			historyModel savedUserHistory = mongoTemplate.findOne(searchType, historyModel.class);
			System.out.println("getData : " + savedUserHistory);
		}catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}
		
	}



}
