package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.lyft.LyftPriceModel;

/**
 * 
 * @author Amit Comaparator for min price Lyft
 */
public class LyftMinPriceComparator implements Comparator<LyftPriceModel> {

	@Override
	public int compare(LyftPriceModel o1, LyftPriceModel o2) {
		if (o1.getEstimated_cost_cents_min() > o2.getEstimated_cost_cents_min()) {
			return 1;
		} else if (o1.getEstimated_cost_cents_min() < o2.getEstimated_cost_cents_min()) {
			return -1;
		} else {
			return 0;
		}
	}

}
