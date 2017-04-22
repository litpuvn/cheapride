package com.cheapRide.model.uber;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Amit
 *
 */
public class ListUberPriceModel {
	
	List<UberPriceModel> prices;
	@JsonIgnore
	String errorMessage;
	@JsonIgnore
	String errorCode;

	public List<UberPriceModel> getPrices() {
		return prices;
	}

	public void setPrices(List<UberPriceModel> prices) {
		this.prices = prices;
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
