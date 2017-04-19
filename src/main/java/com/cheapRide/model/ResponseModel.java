package com.cheapRide.model;
/**
 * 
 * @author Amit
 *
 */
public class ResponseModel {

	private String time;
	private long minVal;
	private long maxVal;
	private String rideRequestId;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getMinVal() {
		return minVal;
	}
	public void setMinVal(long minVal) {
		this.minVal = minVal;
	}
	public long getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(long maxVal) {
		this.maxVal = maxVal;
	}
	public String getRideRequestId() {
		return rideRequestId;
	}
	public void setRideRequestId(String rideRequestId) {
		this.rideRequestId = rideRequestId;
	}
	
	
}
