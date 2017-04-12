package com.cheapRide.dao.impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cheapRide.dao.HistoryDao;
import com.cheapRide.model.historyModel;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml" })

public class HistoryDaoImpl implements HistoryDao {
	private String date = "02/02/2017";
	private String provider = "Uber";
	private String pickup = "III Pl";
	private String destination = "Texas Tech University";
	private String fee = "$3";
	private String type = "ride booking";
	@Autowired
	 private MongoTemplate mongoOperation;
	@Override
	public void setHistory() {
		// TODO Auto-generated method stub
		historyModel history = new historyModel();
		history.setDate(date);
		history.setDestination(destination);
		history.setFee(fee);
		history.setPickup(pickup);
		history.setProvider(provider);
		history.setType(type);
		
		mongoOperation.save(history);
	}

	@Override
	public void getHistory() {
		// TODO Auto-generated method stub
		Query searchType = new Query(Criteria.where("type").is(type));
		historyModel savedUserHistory = mongoOperation.findOne(searchType, historyModel.class);
		System.out.println("getData : " + savedUserHistory);

	}

}
