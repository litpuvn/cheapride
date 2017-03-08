package com.cheapRide.dao.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.cheapRide.dao.RegisterDeviceDao;

/**
 * 
 * @author Amit 
 *  saving device data to database
 */

@Repository
public class RegisterDeviceDaoImpl implements RegisterDeviceDao {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RegisterDeviceDaoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public Object getBydeviceID(String deviceID) {
		logger.debug("Start => RegisterDeviceDaoImpl => getBydeviceID  for device ID "+ deviceID);
		//TODO : Add logic to get the data from mongoDB
		
		logger.debug("End => RegisterDeviceDaoImpl => getBydeviceID  for device ID "+ deviceID);
		return null;
	
	}

	@Override
	public Object createBydeviceID(String deviceID) {
		logger.debug("Start => RegisterDeviceDaoImpl => createBydeviceID  for device ID "+ deviceID);
		//TODO : Add logic to insert the data from mongoDB
		logger.debug("End => RegisterDeviceDaoImpl => createBydeviceID  for device ID "+ deviceID);
		return null;
	}

	@Override
	public Object updateBydeviceID(String deviceID) {
		logger.debug("Start => RegisterDeviceDaoImpl => updateBydeviceID  for device ID "+ deviceID);
		//TODO : Add logic to update the data from mongoDB
		logger.debug("End => RegisterDeviceDaoImpl => updateBydeviceID  for device ID "+ deviceID);
		return null;
	}

}
