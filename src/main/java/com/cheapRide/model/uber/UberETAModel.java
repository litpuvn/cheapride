package com.cheapRide.model.uber;

/**
 * 
 * @author Amit
 *
 */
public class UberETAModel {
	private String localized_display_name;
	private Double estimate;
	private String display_name;
	private String product_id;

	public String getLocalized_display_name() {
		return localized_display_name;
	}

	public void setLocalized_display_name(String localized_display_name) {
		this.localized_display_name = localized_display_name;
	}

	public Double getEstimate() {
		return estimate;
	}

	public void setEstimate(Double estimate) {
		this.estimate = estimate;
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

}
