package com.cheapRide.controller;
/**
 * @author Amit
 * controller for device registration process
 */
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheapRide.service.RegisterDeviceService;
import com.cheapRide.service.impl.RegisterDeviceServiceImpl;

@Controller
public class RegisterDeviceController {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RegisterDeviceController.class);
	
	@Autowired
	private RegisterDeviceService registerDeviceService;

	@RequestMapping(value = "/registerdDevice", method = RequestMethod.GET)
	public @ResponseBody String getRegisteredDevice(@RequestParam(value = "deviceID") String deviceID ) {

		logger.debug("Start => RegisterDeviceController => getRegisteredDevice  for device ID "+ deviceID);
		
		
		String returnString = registerDeviceService.getRegisteredDevice(deviceID);
		
		logger.debug("End => RegisterDeviceController => getRegisteredDevice  for device ID "+ deviceID);
		return "logResults("+returnString+")";

	}

	@RequestMapping(value = "/registerdDevice", method = RequestMethod.POST)
	public @ResponseBody String postRegisteredDevice(@RequestParam(value = "deviceID") String deviceID ) {

		logger.debug("Start => RegisterDeviceController => postRegisteredDevice  for device ID "+ deviceID);
		
		String returnString = registerDeviceService.addRegisteredDevice(deviceID);
		
		logger.debug("End => RegisterDeviceController => postRegisteredDevice  for device ID "+ deviceID);
		return "logResults("+returnString+")";

	}
	
	@RequestMapping(value = "/registerdDevice", method = RequestMethod.PUT)
	public @ResponseBody String putRegisteredDevice(@RequestParam(value = "deviceID") String deviceID ) {

		logger.debug("Start => BaseController => putRegisteredDevice  for device ID "+ deviceID);
		
		String returnString = registerDeviceService.updateRegisteredDevice(deviceID);
		
		logger.debug("End => BaseController => putRegisteredDevice  for device ID "+ deviceID);
		return "logResults("+returnString+")";

	}

}