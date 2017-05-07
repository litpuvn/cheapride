package junit.com.cheapRide.dao;
/**
 * @author Agnes
 */
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.model.HistoryModel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
public class HistoryDaoImplTest {
	@Autowired
	 private MongoTemplate mongoTemplate;
	
	private String username  = "a";
	private String date  = "09/09/2017";
	private String pickup  = "HSC";
	private String destination  = "University";
	private String fee  = "$7";
	private String provider  = "uber";
	private Date from = new Date("1/1/2015");
	private Date to = new Date("2/1/2018");
	private int pageNumber = 1;
	private int size = 3;
//@Test
	public void addHistory() {
		// TODO Auto-generated method stub
		
		try{
			HistoryModel history = new HistoryModel(username, date, pickup, destination, fee, provider);
			mongoTemplate.save(history);
			System.out.println("inserted"+history);
				}catch(Exception e){
					fail(e.getMessage());
			System.out.println(e);
		}

	}
// @Test
public void getHistoryByUsername() {
	// TODO Auto-generated method stub
	try{
		//HistoryModel userHistoryByUsername = null;
		Query searchUsername = new Query(Criteria.where("username").is(username)).skip((pageNumber-1)*size).limit(size);
		ArrayList<HistoryModel> userHistoryByUsername = (ArrayList<HistoryModel>)mongoTemplate.find(searchUsername, HistoryModel.class);
		System.out.println("history by user name " +userHistoryByUsername);
	}catch(Exception e){
		fail(e.getMessage());
		System.out.println(e);
	}
}
 //@Test
public void getHistoryByProvider(){
	try{
		Query searchProvider = new Query(Criteria.where("provider").is(provider)).skip((pageNumber-1)*size).limit(size);
		ArrayList<HistoryModel> userHistoryByProvider = (ArrayList<HistoryModel>) mongoTemplate.find(searchProvider, HistoryModel.class);
	System.out.println("history by provider " +userHistoryByProvider);
	}catch(Exception e){
		fail(e.getMessage());
		System.out.println(e);
	}
}
@Test
public void getHistoryByDate(){
	try{
		ArrayList<HistoryModel> userHistoryByDate = null;
		Query searchUserHistoryByDate = new Query();
		searchUserHistoryByDate.addCriteria(Criteria.where("date").gte(from).lt(to).and("username").is(username)).skip((pageNumber-1)*size).limit(size);
		userHistoryByDate =(ArrayList<HistoryModel>) mongoTemplate.find(searchUserHistoryByDate, HistoryModel.class);
		System.out.println(userHistoryByDate);
	}catch(Exception e){
		fail(e.getMessage());
		System.out.println(e);
	}
}
}
