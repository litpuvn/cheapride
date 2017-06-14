package com.cheapRide.controller;

import com.cheapRide.model.*;
import com.cheapRide.service.RideEstimateService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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



    @RequestMapping(value = "/popularEstimation", method = RequestMethod.GET)
    public ResponseEntity<List<OriginPopular>> popularEstimation(@RequestParam String date) {
        List<OriginPopular> originPopulars = new ArrayList<>();
        List<PopularPlaceInfo> popularPlaceInfoList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime dt = formatter.parseDateTime(date);
        popularPlaceInfoList = rideService.getEstimatePopularInfo(dt.toDate());


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
        popularCities.add("park");
        popularCities.add("college");
        popularCities.add("airport");
        popularCities.add("hospital");
        popularCities.add("church");

        return popularCities;
    }


}
