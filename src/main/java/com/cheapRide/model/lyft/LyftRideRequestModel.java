package com.cheapRide.model.lyft;

import java.io.Serializable;

import com.cheapRide.model.Destination;
import com.cheapRide.model.Origin;

/**
 * 
 * @author Amit
 *
 */
public class LyftRideRequestModel implements Serializable {
	
	private String ride_type;
    private Origin origin;
    private Destination destination;
	public String getRide_type() {
		return ride_type;
	}
	public void setRide_type(String ride_type) {
		this.ride_type = ride_type;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
    

}
