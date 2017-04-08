package com.cheapRide.model.lyft;

import java.util.List;

public class ListLyftETAModel {

	List<LyftETAModel> eta_estimates;
	
	private String timezone;

	public List<LyftETAModel> getEta_estimates() {
		return eta_estimates;
	}

	public void setEta_estimates(List<LyftETAModel> eta_estimates) {
		this.eta_estimates = eta_estimates;
	}


	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	
}
