package com.cheapRide.service;

import com.cheapRide.model.RideRequestModel;

public interface LyftRideBookingService {
	
	public String getLyftAuthToken();
	
	public String requestLyftRide(RideRequestModel requestJson);
	
	public String changeStatusToAccept(String rideId, String accessToken);
	
	public String getRideDetails(String rideId, String accessToken);

}
