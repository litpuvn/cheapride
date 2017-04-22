package com.cheapRide.dao.impl;

import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
/**
*@author: Agnes
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.model.HistoryModel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })

public class HistoryDaoImpl implements HistoryDao {
	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(HistoryDaoImpl.class);

	@Autowired
	 private MongoTemplate mongoTemplate;
	@Override
	public void addHistory(String username, String date,String pickup,String destination, String fee, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryDaoImpl => addHistory  for user "
				+ username);
		try{
			HistoryModel history = new HistoryModel(username, date, pickup, destination, fee,  provider);
			mongoTemplate.save(history);
		}catch (Exception e) {
			logger.error("ERROR => HistoryDaoImpl => addHistory  for user "
					+ username);
		}

		logger.debug("End => HistoryDaoImpl => addHistory  for user "
				+ username);
		
	}

	@Override
	public HistoryModel getHistoryByUsername(String username) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryDaoImpl => getHistoryByUsername  for user "
				+ username);
		HistoryModel userHistoryByUsername = null;
		Query searchUsername = new Query(Criteria.where("username").is(username));
		try{
			userHistoryByUsername = mongoTemplate.findOne(searchUsername, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByUsername  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByUsername  for user "
				+ username);
		return userHistoryByUsername;
	}
	
	@Override
	public HistoryModel getHistoryByProvider(String username,String provider) {
		logger.debug("Start => HistoryDaoImpl => getHistoryByProvider  for user "
				+ username);
		HistoryModel userHistoryByProvider = null;
		Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
				username).and("provider").is(provider));
		try{
			userHistoryByProvider = mongoTemplate.findOne(searchUserHistoryByProvider, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByProvider  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByProvider  for user "
				+ username);
		return userHistoryByProvider;
	}
	
	@Override
	public HistoryModel getHistoryByDate(String username,String fromDate, String toDate){
		logger.debug("Start => HistoryDaoImpl => getHistoryByDate  for user "
				+ username);
		HistoryModel userHistoryByDate = null;
		Query searchUserHistoryByDate = new Query();
		searchUserHistoryByDate.addCriteria(Criteria.where("date").gte(fromDate).lt(toDate).and("username").is(username));
		try{
			userHistoryByDate = (HistoryModel) mongoTemplate.find(searchUserHistoryByDate, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByDate  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByDate  for user "
				+ username);
		return userHistoryByDate;
	}
}
