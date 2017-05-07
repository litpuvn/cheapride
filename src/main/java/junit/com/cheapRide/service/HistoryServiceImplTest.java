package junit.com.cheapRide.service;
/**
 * @author Agnes
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
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
	ObjectMapper mapper = new ObjectMapper();
	private String username = "john";
	private String provider = "uber";
<<<<<<< HEAD
	private Date from = new Date("1/1/2016");
	private Date to = new Date("2/1/2018");
=======
	private String from = "09/09/2017";
	private String to = "09/13/2017";
>>>>>>> 8bd28f8ee19a561d5439025f552a8b024540c2bd
	private int pageNumber = 1;
	private int size = 3;
	//@Test
	public void checkForUsername() {
		// TODO Auto-generated method stub
		String result = null;
		
		try{
			ArrayList<HistoryModel> model = null;
			Query searchUsername = new Query(Criteria.where("username").is(username)).skip((pageNumber-1)*size).limit(size);
			if(searchUsername != null){
				model = dao.getHistoryByUsername(username, pageNumber, size);
				result = mapper.writeValueAsString(model);
				System.out.println(result);
			}
				}catch(Exception e){
					fail(e.getMessage());
					System.out.println(e);
		}

	}
	//@Test
	public void checkForProvider(){
		try{
			ArrayList<HistoryModel> model = null;
			String result = null;
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider)).skip((pageNumber-1)*size).limit(size);
			if(searchUserHistoryByProvider != null){
				model = dao.getHistoryByProvider(username, provider,pageNumber,size);
				result = mapper.writeValueAsString(model);
				System.out.println("check for provider" +result);
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
			String result = null;
			if(from != null && to != null){
				model = dao.getHistoryByDate(username, from, to,pageNumber,size);
				result = mapper.writeValueAsString(model);
				System.out.println("from - to date" +result);
			}
			
		}catch(Exception e){
			fail(e.getMessage());
			System.out.println(e);
	}
	}
}
