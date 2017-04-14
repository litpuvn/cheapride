package com.cheapRide.model.lyft;

/**
 * 
 * @author Amit
 *
 */
public class LyftPriceModel {

	private String ride_type;
	private int estimated_duration_seconds;
	private double estimated_distance_miles;
	private int estimated_cost_cents_max;
	private String primetime_percentage;
	private boolean is_valid_estimate;
	private String currency;
	private int estimated_cost_cents_min;
	private String display_name;
	private String primetime_confirmation_token;
	private boolean can_request_ride;
	private String cost_token;
	
	
	
	
	public String getCost_token() {
		return cost_token;
	}

	public void setCost_token(String cost_token) {
		this.cost_token = cost_token;
	}

	public String getRide_type() {
		return ride_type;
	}

	public void setRide_type(String ride_type) {
		this.ride_type = ride_type;
	}

	public int getEstimated_duration_seconds() {
		return estimated_duration_seconds;
	}

	public void setEstimated_duration_seconds(int estimated_duration_seconds) {
		this.estimated_duration_seconds = estimated_duration_seconds;
	}

	public double getEstimated_distance_miles() {
		return estimated_distance_miles;
	}

	public void setEstimated_distance_miles(double estimated_distance_miles) {
		this.estimated_distance_miles = estimated_distance_miles;
	}

	public int getEstimated_cost_cents_max() {
		return estimated_cost_cents_max;
	}

	public void setEstimated_cost_cents_max(int estimated_cost_cents_max) {
		this.estimated_cost_cents_max = estimated_cost_cents_max;
	}

	public String getPrimetime_percentage() {
		return primetime_percentage;
	}

	public void setPrimetime_percentage(String primetime_percentage) {
		this.primetime_percentage = primetime_percentage;
	}

	public boolean isIs_valid_estimate() {
		return is_valid_estimate;
	}

	public void setIs_valid_estimate(boolean is_valid_estimate) {
		this.is_valid_estimate = is_valid_estimate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getEstimated_cost_cents_min() {
		return estimated_cost_cents_min;
	}

	public void setEstimated_cost_cents_min(int estimated_cost_cents_min) {
		this.estimated_cost_cents_min = estimated_cost_cents_min;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getPrimetime_confirmation_token() {
		return primetime_confirmation_token;
	}

	public void setPrimetime_confirmation_token(String primetime_confirmation_token) {
		this.primetime_confirmation_token = primetime_confirmation_token;
	}

	public boolean isCan_request_ride() {
		return can_request_ride;
	}

	public void setCan_request_ride(boolean can_request_ride) {
		this.can_request_ride = can_request_ride;
	}

}
