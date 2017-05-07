package com.cheapRide.service.impl;
/**
 * @author Agnes
 */
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cheapRide.dao.HistoryDao;
import com.cheapRide.dao.impl.HistoryDaoImpl;
import com.cheapRide.model.HistoryModel;
import com.cheapRide.service.HistoryService;

@Service
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
	public ArrayList<HistoryModel> checkForUsername(String username, int pageNumber,int size) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForUsername  for user "
				+ username);
		 result  = null;
		
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUsername = new Query(Criteria.where("username").is(username)).skip((pageNumber-1)*size).limit(size);
			if(searchUsername != null){
				model = dao.getHistoryByUsername(username,pageNumber,size);
				//result = mapper.writeValueAsString(model);
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
	public ArrayList<HistoryModel> checkForProvider(String username, String provider, int pageNumber,int size) {
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkForProvider  for provider "
				+ provider);
		String result  = null;
		ArrayList<HistoryModel> model = null;
		try{
			Query searchUserHistoryByProvider = new Query(Criteria.where("username").is(
					username).and("provider").is(provider)).skip((pageNumber-1)*size).limit(size);
			if(searchUserHistoryByProvider != null){
				model = dao.getHistoryByProvider(username, provider, pageNumber, size);
				//result = mapper.writeValueAsString(model);
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
<<<<<<< HEAD
	public ArrayList<HistoryModel> checkDate(String username, Date from, Date to, int pageNumber,int size) {
=======
	public ArrayList<HistoryModel> checkDate(String username, String from, String to, int pageNumber,int size) {
>>>>>>> 8bd28f8ee19a561d5439025f552a8b024540c2bd
		// TODO Auto-generated method stub
		logger.debug("Start => HistoryServiceImpl => checkDate  for username "
				+ username);
		String result  = null;
		ArrayList<HistoryModel> model = null;
		try{
			if(from != null && to != null){
				model = dao.getHistoryByDate(username, from, to, pageNumber, size);
				//result = mapper.writeValueAsString(model);
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
