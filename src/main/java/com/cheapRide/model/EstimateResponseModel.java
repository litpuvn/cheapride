package com.cheapRide.model;

/**
 * 
 * @author Amit
 *
 */
public class EstimateResponseModel {

	private ResponseModel uber;

	private ResponseModel lyft;

	public ResponseModel getUber() {
		return uber;
	}

	public void setUber(ResponseModel uber) {
		this.uber = uber;
	}

	public ResponseModel getLyft() {
		return lyft;
	}

	public void setLyft(ResponseModel lyft) {
		this.lyft = lyft;
	}

}
