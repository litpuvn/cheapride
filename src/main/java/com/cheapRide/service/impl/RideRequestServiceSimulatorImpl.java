package com.cheapRide.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cheapRide.model.Car;
import com.cheapRide.model.Driver;
import com.cheapRide.model.RideResponseModel;
import com.cheapRide.service.RideRequestSimulatorService;

/**
 * Created by pshayegh on 4/28/2017.
 */

@Service
public class RideRequestServiceSimulatorImpl implements RideRequestSimulatorService {
    @Override
    public RideResponseModel getLyftRide() {
        RideResponseModel rideResponseModel =new RideResponseModel();
        rideResponseModel.setStatus("accepted");
        rideResponseModel.setCar(getCar());
        rideResponseModel.setDriver(getDriver());
        return rideResponseModel;
    }

    private Driver getDriver() {
            Driver driver=new Driver();
            driver.setFirstName(getRandomFirstName());
            driver.setImageUrl(getRandomDriverImage());
            driver.setPhone_number(getRandomPhoneNumber());
            driver.setRating(getRandomRating());
            return driver;
    }

    private String getRandomRating() {
        return "5";
    }

    private String getRandomPhoneNumber() {
        Random r = new Random();
        List<String>   phones=new ArrayList<>();
        phones.add("8064567532");
        phones.add("7314567560");
        phones.add("6873445123");
        return phones.get(r.nextInt(3));

    }

    private String getRandomDriverImage() {
        Random r = new Random();
        String imagePath= "/images/avatar/"+r.nextInt(10)+".jpg";
       return imagePath;
    }

    private String getRandomFirstName() {
        Random r = new Random();
        List<String> randomNames=new ArrayList<>();
        randomNames.add("Sophia");
        randomNames.add("Jack");
        randomNames.add("David");
        randomNames.add("Mary");
        randomNames.add("Sarah");
        randomNames.add("Mia");
        randomNames.add("Noah");
        randomNames.add("Liam");
        randomNames.add("James");
        randomNames.add("Daniel");
        return randomNames.get(r.nextInt(10));
    }

    private Car getCar() {
        Car car=new Car();
        car.setColor(getRandomColor());
        car.setModel(getRandomCarModel());
        car.setImageUrl(getRandomCarImage());
        car.setLicense_plate(getRandomLicencePlate());
        car.setMake(getRandomCarMake());
        car.setYear(getRandomYear());
        car.setLicense_plate_state(getRandomLicencePlateState());
        return car;

    }

    private String getRandomLicencePlate() {
        List<String> plates=new ArrayList<>();
        Random r=new Random();
        plates.add("11BA44");
        plates.add("BC44CC");
        plates.add("23DS23");
        plates.add("67TY34");
        plates.add("AZX457");
        return plates.get(r.nextInt(5));    }

    private String getRandomLicencePlateState() {
        List<String> states=new ArrayList<>();
        Random r=new Random();
        states.add("TX");
        states.add("CA");
        states.add("AL");
        states.add("AZ");
        states.add("AR");
        return states.get(r.nextInt(5));
    }

    private String getRandomYear() {
        Random r=new Random();
        return String.valueOf(r.nextInt(10)+2005);
    }

    private String getRandomCarModel() {
        List<String> models=new ArrayList<>();
        Random r=new Random();
        models.add("Camero");
        models.add("Ford");
        models.add("Highlander");
        models.add("Maxima");
        models.add("Mustang");
        return models.get(r.nextInt(5));
    }

    private String getRandomCarImage() {
        Random r = new Random();
        String imagePath= "/resources/images/cars/"+r.nextInt(5)+".jpg";
        return imagePath;
    }

    private String getRandomColor() {
        List<String> colors=new ArrayList<>();
        colors.add("blue");
        colors.add("red");
        colors.add("black");
        colors.add("white");
        colors.add("silver");
        Random r = new Random();
        return colors.get(r.nextInt(5));
    }

    private String getRandomCarMake() {
        List<String> models=new ArrayList<>();
        models.add("Acura");
        models.add("Audi");
        models.add("Bmw");
        models.add("Bugatti");
        models.add("Buick");
        Random r = new Random();
        return models.get(r.nextInt(5));
    }
}
