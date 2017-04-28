package com.cheapRide.service.impl;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(HistoryDaoImpl.class);
	
	@Override
	public HistoryModel checkAddHistory(String username,String date,String pickup, String destination, String fee, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkAddHistory  for user "
				+ username);
		HistoryModel model = null;
		try{
			model = dao.addHistory(username, date, pickup, destination, fee, provider);
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForUsername  for user "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkAddHistory  for user "
				+ username);
		return model;
		
	}

	@Override
	public ArrayList<HistoryModel> checkForUsername(String username) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForUsername  for user "
				+ username);
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUsername = new Query(Criteria.where("username").is(username));
			if(searchUsername != null){
				model = dao.getHistoryByUsername(username);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForUsername  for user "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkForUsername  for user "
				+ username);
		return model;
	}
	
	@Override
	public ArrayList<HistoryModel> checkForProvider(String username, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForProvider  for provider "
				+ provider);
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider));
			if(searchUserHistoryByProvider != null){
				model = dao.getHistoryByProvider(username, provider);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkForProvider  for provider "
					+ provider);
		}
		logger.debug("End => HistoryServiceImpl => checkForProvider  for provider "
				+ provider);
		return model;
	}

	@Override
	public ArrayList<HistoryModel> checkDate(String username, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkDate  for username "
				+ username);
		ArrayList<HistoryModel> model = null;
		try{
			Query fDate = new Query(Criteria.where("username").is(
					username).and("date").is(fromDate));
			Query tDate = new Query(Criteria.where("username").is(
					username).and("date").is(toDate));
			if(fDate != null && tDate != null){
				model = dao.getHistoryByDate(username, fromDate, toDate);
			}
		}catch(Exception e){
			logger.error("ERROR => HistoryServiceImpl => checkDate  for username "
					+ username);
		}
		logger.debug("End => HistoryServiceImpl => checkDate  for username "
				+ username);
		return model;
	}

	


}
