package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.uber.UberPriceModel;

/**
 * 
 * @author Amit Comaparator for min price Uber
 */
public class UberMinPriceComparator implements Comparator<UberPriceModel> {

	@Override
	public int compare(UberPriceModel o1, UberPriceModel o2) {
		if (o1.getLow_estimate() > o2.getLow_estimate()) {
			return 1;
		} else if (o1.getLow_estimate() < o2.getLow_estimate()) {
			return -1;
		} else {
			return 0;
		}
	}

}
