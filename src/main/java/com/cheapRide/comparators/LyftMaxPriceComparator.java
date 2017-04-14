package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.lyft.LyftPriceModel;

/**
 * 
 * @author Amit Comaparator for max price Lyft
 */
public class LyftMaxPriceComparator implements Comparator<LyftPriceModel> {

	@Override
	public int compare(LyftPriceModel o1, LyftPriceModel o2) {
		if (o1.getEstimated_cost_cents_max() > o2.getEstimated_cost_cents_max()) {
			return 1;
		} else if (o1.getEstimated_cost_cents_max() < o2.getEstimated_cost_cents_max()) {
			return -1;
		} else {
			return 0;
		}
	}

}
