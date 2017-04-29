package com.cheapRide.service;

import com.cheapRide.model.lyft.RideResponseModel;
import org.springframework.stereotype.Service;

/**
 * Created by pshayegh on 4/28/2017.
 */
@Service
public interface RideRequestService {
    RideResponseModel getLyftRide(String originLat, String originLong, String destLat, String destLon);
}
