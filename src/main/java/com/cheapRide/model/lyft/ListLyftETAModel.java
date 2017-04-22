package com.cheapRide.model.lyft;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ListLyftETAModel {

	List<LyftETAModel> eta_estimates;
	
	@JsonIgnore
	String errorMessage;
	@JsonIgnore
	String errorCode;
	
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	
}
