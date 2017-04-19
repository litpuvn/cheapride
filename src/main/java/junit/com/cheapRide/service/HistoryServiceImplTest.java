package junit.com.cheapRide.service;
/**
 * @author Agnes
 */
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cheapRide.dao.impl.HistoryDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })

public class HistoryServiceImplTest {
	@Autowired 
	private HistoryDaoImpl dao;
	
	@Test
	public void getHistoryService() {
		// TODO Auto-generated method stub
		try{
			dao.getHistory();

		}catch (Exception exc) {
			fail(exc.getMessage());
			System.out.println(exc);
		}
		
	}

}
