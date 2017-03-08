package com.cheapRide.service;

public interface RegisterDeviceService {

	String getRegisteredDevice(String deviceID);

	String addRegisteredDevice(String deviceID);

	String updateRegisteredDevice(String deviceID);

}
