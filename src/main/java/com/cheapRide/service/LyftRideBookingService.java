package com.cheapRide.service;

public interface LyftRideBookingService {
	
	public String getLyftAuthToken();
	
	public String requestLyftRide(String requestJson);
	
	public String changeStatusToAccept(String rideId, String accessToken);
	
	public String getRideDetails(String rideId, String accessToken);

}
