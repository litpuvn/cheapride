package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.uber.UberETAModel;

/**
 * 
 * @author Amit Comaparator for min price Uber
 */
public class UberETAComparator implements Comparator<UberETAModel> {

	@Override
	public int compare(UberETAModel o1, UberETAModel o2) {
		if (o1.getEstimate() > o2.getEstimate()) {
			return 1;
		} else if (o1.getEstimate() < o2.getEstimate()) {
			return -1;
		} else {
			return 0;
		}
	}

}
