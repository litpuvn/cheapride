package com.cheapRide.model.lyft;

/**
 * 
 * @author Amit
 *
 */
public class LyftETAModel {
	private String display_name;
	private int eta_seconds;
	private String ride_type;
	private boolean is_valid_estimate;
	

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public int getEta_seconds() {
		return eta_seconds;
	}

	public void setEta_seconds(int eta_seconds) {
		this.eta_seconds = eta_seconds;
	}

	public String getRide_type() {
		return ride_type;
	}

	public void setRide_type(String ride_type) {
		this.ride_type = ride_type;
	}

	public boolean isIs_valid_estimate() {
		return is_valid_estimate;
	}

	public void setIs_valid_estimate(boolean is_valid_estimate) {
		this.is_valid_estimate = is_valid_estimate;
	}


}
