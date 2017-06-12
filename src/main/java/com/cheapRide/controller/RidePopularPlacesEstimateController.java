package com.cheapRide.controller;

import com.cheapRide.model.*;
import com.cheapRide.service.RideEstimateService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.asin;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

/**
 * Created by pshayegh on 6/8/2017.
 */

@RestController
public class RidePopularPlacesEstimateController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RideEstimateController.class);
    @Autowired
    public RideEstimateService rideService;
    private double pi = 3.14;


//    public String getEstimatedTime(float pickUpLat, float pickUpLong) {
//        String resString = null;
//        try {
//            resString = rideService.getEstimateTime(pickUpLat, pickUpLong, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return resString;
//    }


    @RequestMapping(value = "/bookRidePopular", method = RequestMethod.GET)
    public ResponseEntity<List<PopularPlaceInfo>> requestRide() {
        List<PopularPlaceInfo> popularPlaceInfoList = new ArrayList<>();
//        List<PopularResponseModel> responseModels=new ArrayList<>();
//
//        for (PopularPlaceInfo info : popularPlaceInfoList) {
//            PopularResponseModel model=new PopularResponseModel();
//            model.setCost(rideService.getEstimateCost(info.getLat().floatValue(), info.getLon().floatValue(), getDesGeo(info.getLat(), info.getLon()).getLat().floatValue(), getDesGeo(info.getLat(), info.getLon()).getLng().floatValue(), null, "4_seats"));
//            model.setTime(rideService.getEstimateTimeCorrect(info.getLat().floatValue(), info.getLon().floatValue(), getDesGeo(info.getLat(), info.getLon()).getLat().floatValue(), getDesGeo(info.getLat(), info.getLon()).getLng().floatValue(), null, "4_seats"));
//            responseModels.add(model);
//        }
        popularPlaceInfoList = rideService.getEstimatePopularInfo();
        ResponseEntity<List<PopularPlaceInfo>> responseEntity;
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(popularPlaceInfoList);
        return responseEntity;

    }


    @RequestMapping(value = "/popularEstimation", method = RequestMethod.GET)
    public ResponseEntity<List<OriginPopular>> popularEstimation() {
        List<OriginPopular> originPopulars = new ArrayList<>();
        List<PopularPlaceInfo> popularPlaceInfoList = new ArrayList<>();
        popularPlaceInfoList = rideService.getEstimatePopularInfo();


        for (String city : getPopularCities()) {
            OriginPopular originPopular = new OriginPopular();
            North north = new North();
            East east = new East();
            South south = new South();
            West west = new West();


            for (PopularPlaceInfo info : popularPlaceInfoList) {
                if(info.getName().equals(city)){
                if (info.getDirection().equals("north")) {
                    if (info.getType().equals("uber")) {
                        Uber uber = new Uber();
                        uber.setCost(info.getCost());
                        uber.setPickupTime(info.getTime());
                        north.setUber(uber);
                    } else {
                        Lyft lyft = new Lyft();
                        lyft.setCost(info.getCost());
                        lyft.setPickupTime(info.getCost());
                        north.setLyft(lyft);
                    }
                    originPopular.setNorth(north);


                } else if (info.getDirection().equals("east")) {
                    if (info.getType().equals("uber")) {
                        Uber uber = new Uber();
                        uber.setCost(info.getCost());
                        uber.setPickupTime(info.getTime());
                        east.setUber(uber);

                    } else {
                        Lyft lyft = new Lyft();
                        lyft.setCost(info.getCost());
                        lyft.setPickupTime(info.getCost());
                        east.setLyft(lyft);
                    }
                    originPopular.setEast(east);
                } else if (info.getDirection().equals("south")) {
                    if (info.getType().equals("uber")) {
                        Uber uber = new Uber();
                        uber.setCost(info.getCost());
                        uber.setPickupTime(info.getTime());
                        south.setUber(uber);
                    } else {
                        Lyft lyft = new Lyft();
                        lyft.setCost(info.getCost());
                        lyft.setPickupTime(info.getCost());
                        south.setLyft(lyft);
                    }
                    originPopular.setSouth(south);
                } else if (info.getDirection().equals("west")) {
                    if (info.getType().equals("uber")) {
                        Uber uber = new Uber();
                        uber.setCost(info.getCost());
                        uber.setPickupTime(info.getTime());
                        west.setUber(uber);
                    } else {
                        Lyft lyft = new Lyft();
                        lyft.setCost(info.getCost());
                        lyft.setPickupTime(info.getCost());
                        west.setLyft(lyft);
                    }
                    originPopular.setWest(west);
                }
                originPopular.setName(info.getName());
                originPopular.setLat(info.getLat());
                originPopular.setLon(info.getLon());
                }

            }
            originPopulars.add(originPopular);
        }
        ResponseEntity<List<OriginPopular>> responseEntity;


        responseEntity = ResponseEntity.status(HttpStatus.OK).body(originPopulars);
        return responseEntity;

    }

    private List<String> getPopularCities() {
        List<String> popularCities = new ArrayList<>();

        popularCities.add("Golden Gate Park");

        popularCities.add("Zoo");

        return popularCities;
    }


}
