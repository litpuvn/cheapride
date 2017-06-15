package com.cheapRide.model;
/**
 * 
 * @author Amit
 *
 */
public class ResponseModel {

	private String time;
	private double cost;
	private String rideRequestId;	
	private String rideType;
	
	public String getRideType() {
		return rideType;
	}
	public void setRideType(String rideType) {
		this.rideType = rideType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getRideRequestId() {
		return rideRequestId;
	}
	public void setRideRequestId(String rideRequestId) {
		this.rideRequestId = rideRequestId;
	}
	
	
}
