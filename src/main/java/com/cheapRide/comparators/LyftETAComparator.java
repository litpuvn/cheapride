package com.cheapRide.comparators;

import java.util.Comparator;

import com.cheapRide.model.lyft.LyftETAModel;

/**
 * 
 * @author Amit Comaparator for min ETA Lyft
 */
public class LyftETAComparator implements Comparator<LyftETAModel> {

	@Override
	public int compare(LyftETAModel o1, LyftETAModel o2) {
		if (o1.getEta_seconds() > o2.getEta_seconds()) {
			return 1;
		} else if (o1.getEta_seconds() < o2.getEta_seconds()) {
			return -1;
		} else {
			return 0;
		}
	}

}
