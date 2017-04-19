package com.cheapRide.model.uber;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ListUberETAModel {
	
	List<UberETAModel> times;
	
	@JsonIgnore
	String errorMessage;
	@JsonIgnore
	String errorCode;

	public List<UberETAModel> getTimes() {
		return times;
	}

	public void setTimes(List<UberETAModel> times) {
		this.times = times;
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
