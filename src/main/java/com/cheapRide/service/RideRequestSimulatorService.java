package com.cheapRide.service;

import com.cheapRide.model.RideResponseModel;
import org.springframework.stereotype.Service;

/**
 * Created by pshayegh on 4/28/2017.
 */
@Service
public interface RideRequestSimulatorService {
    RideResponseModel getLyftRide();
}
