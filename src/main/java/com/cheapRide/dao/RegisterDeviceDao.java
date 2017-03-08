package com.cheapRide.dao;

public interface RegisterDeviceDao {

	Object getBydeviceID(String deviceID);

	Object createBydeviceID(String deviceID);

	Object updateBydeviceID(String deviceID);

}
