package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.uber.UberPriceModel;

/**
 * 
 * @author Amit Comaparator for min price Uber
 */
public class UberMaxPriceComparator implements Comparator<UberPriceModel> {

	@Override
	public int compare(UberPriceModel o1, UberPriceModel o2) {
		if (o1.getHigh_estimate() > o2.getHigh_estimate()) {
			return 1;
		} else if (o1.getHigh_estimate() < o2.getHigh_estimate()) {
			return -1;
		} else {
			return 0;
		}
	}

}
