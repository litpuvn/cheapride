package com.cheapRide.model.lyft;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Amit
 *
 */
public class ListLyftPriceModel {
	
	List<LyftPriceModel> cost_estimates;
	
	@JsonIgnore
	String errorMessage;
	@JsonIgnore
	String errorCode;

	public List<LyftPriceModel> getCost_estimates() {
		return cost_estimates;
	}

	public void setCost_estimates(List<LyftPriceModel> cost_estimates) {
		this.cost_estimates = cost_estimates;
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
