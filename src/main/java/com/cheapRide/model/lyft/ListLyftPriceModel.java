package com.cheapRide.model.lyft;

import java.util.List;

/**
 * 
 * @author Amit
 *
 */
public class ListLyftPriceModel {
	
	List<LyftPriceModel> cost_estimates;

	public List<LyftPriceModel> getCost_estimates() {
		return cost_estimates;
	}

	public void setCost_estimates(List<LyftPriceModel> cost_estimates) {
		this.cost_estimates = cost_estimates;
	}

}
