package com.cheapRide.service.impl;
import java.awt.print.Pageable;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.dao.impl.HistoryDaoImpl;
import com.cheapRide.model.HistoryModel;
import com.cheapRide.service.HistoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })

public class HistoryServiceImpl implements HistoryService {
	@Autowired 
	public HistoryDao dao;
	String result  = null;
	private static ObjectMapper mapper = new ObjectMapper();
	
	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(HistoryDaoImpl.class);
	
	@Override
	public String checkAddHistory(String username,String date,String pickup, String destination, String fee, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkAddHistory  for user "
				+ username);
		HistoryModel model = null;
		try{
			//result = mapper.writeValueAsString(dao.addHistory(username, date, pickup, destination, fee, provider));
			model = dao.addHistory(username, date, pickup, destination, fee, provider);
			result = mapper.writeValueAsString(model);
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForUsername  for user "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkAddHistory  for user "
				+ username);
		return result;
		
	}

	@Override
	public String checkForUsername(String username) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForUsername  for user "
				+ username);
		String result  = null;
		
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUsername = new Query(Criteria.where("username").is(username));
			if(searchUsername != null){
				model = dao.getHistoryByUsername(username);
				result = mapper.writeValueAsString(model);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForUsername  for user "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkForUsername  for user "
				+ username);
		return result;
	}
	
	@Override
	public String checkForProvider(String username, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForProvider  for provider "
				+ provider);
		String result  = null;
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider));
			if(searchUserHistoryByProvider != null){
				model = dao.getHistoryByProvider(username, provider);
				result = mapper.writeValueAsString(model);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForProvider  for provider "
					+ provider);
		}
		logger.debug("End => HistoryServiceImpl => checkForProvider  for provider "
				+ provider);
		return result;
	}

	@Override
	public String checkDate(String username, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkDate  for username "
				+ username);
		String result  = null;
		ArrayList<HistoryModel> model = null;
		try{
			Query fDate = new Query(Criteria.where("username").is(
					username).and("date").is(fromDate));
			Query tDate = new Query(Criteria.where("username").is(
					username).and("date").is(toDate));
			if(fDate != null && tDate != null){
				model = dao.getHistoryByDate(username, fromDate, toDate);
				result = mapper.writeValueAsString(model);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkDate  for username "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkDate  for username "
				+ username);
		return result;
	}

}
