package com.cheapRide.model.uber;
/**
 * 
 * @author Amit
 *
 */
public class UberPriceModel {

	private String localized_display_name;
	private Double distance;
	private String display_name;
	private String product_id;
	private String estimate;
	private String currency_code;
	private Integer high_estimate; 
	private Integer low_estimate;
	private Integer duration;
	public String getLocalized_display_name() {
		return localized_display_name;
	}
	public void setLocalized_display_name(String localized_display_name) {
		this.localized_display_name = localized_display_name;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getEstimate() {
		return estimate;
	}
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	public String getCurrency_code() {
		return currency_code;
	}
	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}
	public int getHigh_estimate() {
		return high_estimate;
	}
	public void setHigh_estimate(int high_estimate) {
		this.high_estimate = high_estimate;
	}
	public int getLow_estimate() {
		return low_estimate;
	}
	public void setLow_estimate(int low_estimate) {
		this.low_estimate = low_estimate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}
