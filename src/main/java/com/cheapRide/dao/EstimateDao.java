package com.cheapRide.dao;

import com.cheapRide.model.PopularPlaceInfo;
import com.cheapRide.model.PopularResponseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by pshayegh on 6/11/2017.
 */
public interface EstimateDao {
    String storeEstimateTimeAndCost(PopularPlaceInfo popularPlaceInfo);
   List<PopularPlaceInfo> restorePopularPlaceInfo(Date date);
   String dropTable();


}
