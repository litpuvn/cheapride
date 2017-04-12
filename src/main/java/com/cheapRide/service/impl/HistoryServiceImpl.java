package com.cheapRide.service.impl;
import static org.junit.Assert.fail;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.dao.impl.HistoryDaoImpl;
/**
 * @author Agnes
 */
import com.cheapRide.service.HistoryService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })
@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired 
	private HistoryDaoImpl dao;
	@Override
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
