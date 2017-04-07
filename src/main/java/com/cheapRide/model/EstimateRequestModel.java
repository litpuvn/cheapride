package com.cheapRide.model;

import java.util.Map;

/**
 * 
 * @author Amit
 *
 */
public class EstimateRequestModel {
	private LocationModel origin;
	private LocationModel destination;
	private Map<String, String> options;
	public LocationModel getOrigin() {
		return origin;
	}
	public void setOrigin(LocationModel origin) {
		this.origin = origin;
	}
	public LocationModel getDestination() {
		return destination;
	}
	public void setDestination(LocationModel destination) {
		this.destination = destination;
	}
	public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
	
	
}
