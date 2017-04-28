package junit.com.cheapRide.service;
/**
 * @author Agnes
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.model.HistoryModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })

public class HistoryServiceImplTest {
	@Autowired
	HistoryDao dao;
	
	private String username = "john";
	private String provider = "uber";
	private String fromDate = "09/11/2017";
	private String toDate = "09/13/2017";
	@Test
	public void checkForUsername() {
		// TODO Auto-generated method stub
		
		try{
			ArrayList<HistoryModel> model = null;
			Query searchUsername = new Query(Criteria.where("username").is(username));
			if(searchUsername != null){
				model = dao.getHistoryByUsername(username);
				System.out.println("check for username" +model);
			}
				}catch(Exception e){
					fail(e.getMessage());
					System.out.println(e);
		}

	}
	@Test
	public void checkForProvider(){
		try{
			ArrayList<HistoryModel> model = null;
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider));
			if(searchUserHistoryByProvider != null){
				model = dao.getHistoryByProvider(username, provider);
				System.out.println("check for provider" +model);
		}
	}catch(Exception e){
		fail(e.getMessage());
		System.out.println(e);
}
}
	@Test
	public void checkDate(){
		try{
			ArrayList<HistoryModel> model = null;
			Query fDate = new Query(Criteria.where("username").is(
					username).and("date").is(fromDate));
			Query tDate = new Query(Criteria.where("username").is(
					username).and("date").is(toDate));
			if(fDate != null && tDate != null){
				model = dao.getHistoryByDate(username, fromDate, toDate);
				System.out.println("from - to date" +model);
			}
			
		}catch(Exception e){
			fail(e.getMessage());
			System.out.println(e);
	}
	}
}
