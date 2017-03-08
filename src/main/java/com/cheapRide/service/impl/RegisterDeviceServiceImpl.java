package com.cheapRide.service.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheapRide.dao.RegisterDeviceDao;
import com.cheapRide.service.RegisterDeviceService;

/**
 * 
 * @author Amit 
 * Service layer for device manipulation
 */

@Service
public class RegisterDeviceServiceImpl implements RegisterDeviceService {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RegisterDeviceServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RegisterDeviceDao registerDeviceDao;


	@Override
	public String getRegisteredDevice(String deviceID) {

		logger.debug("Start => RegisterDeviceServiceImpl => getRegisteredDevice  for device ID " + deviceID);
		String returnString = null;
		try {
			returnString = mapper.writeValueAsString(registerDeviceDao.getBydeviceID(deviceID));
		} catch (Exception e) {
			logger.error("ERROR : RegisterDeviceServiceImpl => getRegisteredDevice  for device ID " + deviceID);
			e.printStackTrace();
		}
		logger.debug("End => RegisterDeviceServiceImpl => getRegisteredDevice  for device ID " + deviceID);
		return returnString;
	
	}

	@Override
	public String addRegisteredDevice(String deviceID) {

		logger.debug("Start => RegisterDeviceServiceImpl => addRegisteredDevice  for device ID " + deviceID);
		String returnString = null;
		try {
			returnString = mapper.writeValueAsString(registerDeviceDao.createBydeviceID(deviceID));
		} catch (Exception e) {
			logger.error("ERROR : RegisterDeviceServiceImpl => addRegisteredDevice  for device ID " + deviceID);
			e.printStackTrace();
		}
		logger.debug("End => RegisterDeviceServiceImpl => addRegisteredDevice  for device ID " + deviceID);
		return returnString;
	
	}

	@Override
	public String updateRegisteredDevice(String deviceID) {

		logger.debug("Start => RegisterDeviceServiceImpl => updateRegisteredDevice  for device ID " + deviceID);
		String returnString = null;
		try {
			returnString = mapper.writeValueAsString(registerDeviceDao.updateBydeviceID(deviceID));
		} catch (Exception e) {
			logger.error("ERROR : RegisterDeviceServiceImpl => updateRegisteredDevice  for device ID " + deviceID);
			e.printStackTrace();
		}
		logger.debug("End => RegisterDeviceServiceImpl => updateRegisteredDevice  for device ID " + deviceID);
		return returnString;
	
	}

}
