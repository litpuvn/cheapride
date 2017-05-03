
package com.cheapRide.dao.impl;
/**
 * @author Agnes
 */
import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.model.HistoryModel;

@Repository
public class HistoryDaoImpl implements HistoryDao {
	private final static org.slf4j.Logger logger = LoggerFactory
			.getLogger(HistoryDaoImpl.class);

	@Autowired
	 private MongoTemplate mongoTemplate;
	@Override
	public HistoryModel addHistory(String username,String date, String pickup,String destination,String fee, String provider) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryDaoImpl => addHistory  for user "
				+ username);
		HistoryModel history = null;
		try{
			history = new HistoryModel(username,date, pickup,destination,fee,provider);
			mongoTemplate.save(history);
		}catch (Exception e) {
			logger.error("ERROR => HistoryDaoImpl => addHistory  for user "
					+ username);
		}

		logger.debug("End => HistoryDaoImpl => addHistory  for user "
				+ username);
		return history;
		
	}

	@Override
	public ArrayList<HistoryModel> getHistoryByUsername(String username) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryDaoImpl => getHistoryByUsername  for user "
				+ username);
		ArrayList<HistoryModel> userHistoryByUsername = null;
		try{
			
			Query searchUsername = new Query(Criteria.where("username").is(username));
			userHistoryByUsername = (ArrayList<HistoryModel>) mongoTemplate.find(searchUsername, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByUsername  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByUsername  for user "
				+ username);
		return userHistoryByUsername;
	}
	
	@Override
	public ArrayList<HistoryModel> getHistoryByProvider(String username,String provider) {
		logger.debug("Start => HistoryDaoImpl => getHistoryByProvider  for user "
				+ username);
		ArrayList<HistoryModel> userHistoryByProvider = null;
		
		try{
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider));
			userHistoryByProvider = (ArrayList<HistoryModel>) mongoTemplate.find(searchUserHistoryByProvider, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByProvider  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByProvider  for user "
				+ username);
		return userHistoryByProvider;
	}
	
	@Override
	public ArrayList<HistoryModel> getHistoryByDate(String username,String fromDate, String toDate){
		logger.debug("Start => HistoryDaoImpl => getHistoryByDate  for user "
				+ username);
		//HistoryModel userHistoryByDate = null;
		ArrayList<HistoryModel> userHistoryByDate = null;
		try{
			
			Query searchUserHistoryByDate = new Query();
			searchUserHistoryByDate.addCriteria(Criteria.where("date").gte(fromDate).lt(toDate).and("username").is(username));
			userHistoryByDate =(ArrayList<HistoryModel>) mongoTemplate.find(searchUserHistoryByDate, HistoryModel.class);
		}catch(Exception e){
			logger.error("ERROR => HistoryDaoImpl => getHistoryByDate  for user "
					+ username);
		}
		
		logger.debug("End => HistoryDaoImpl => getHistoryByDate  for user "
				+ username);
		return userHistoryByDate;
	}
}
