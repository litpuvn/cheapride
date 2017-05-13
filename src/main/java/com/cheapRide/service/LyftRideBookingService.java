package com.cheapRide.service;

import com.cheapRide.model.RideRequestModel;
import com.cheapRide.model.RideResponseModel;

public interface LyftRideBookingService {
	
	public String getLyftAuthToken();
	
	public RideResponseModel requestLyftRide(RideRequestModel requestJson); 
	
	public String changeStatusToAccept(String rideId, String accessToken);
	
	public String getRideDetails(String rideId, String accessToken);

}
