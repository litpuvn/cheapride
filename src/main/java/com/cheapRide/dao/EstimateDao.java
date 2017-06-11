package com.cheapRide.dao;

import com.cheapRide.model.PopularPlaceInfo;
import com.cheapRide.model.PopularResponseModel;

/**
 * Created by pshayegh on 6/11/2017.
 */
public interface EstimateDao {
    String storeEstimateTimeAndCost(PopularPlaceInfo popularPlaceInfo);
    PopularPlaceInfo restorePopularPlaceInfo(String name);


}
