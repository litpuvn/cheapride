package com.cheapRide.model;
/**
 * 
 * @author Amit
 *
 */
public class ResponseModel {

	private String time;
	private long cost;
	
	private String rideRequestId;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public String getRideRequestId() {
		return rideRequestId;
	}
	public void setRideRequestId(String rideRequestId) {
		this.rideRequestId = rideRequestId;
	}
	
	
}
