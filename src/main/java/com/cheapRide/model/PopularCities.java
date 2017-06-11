package com.cheapRide.model;

/**
 * Created by pshayegh on 6/8/2017.
 */
public enum PopularCities {
    NewYork("New York"),
    Austin("Austin"),
    SF("San Francisco"),
    Washington("Washington, D.C."),
    Boston("Boston");

    private final String name;
    PopularCities(String s) {

        name = s;
    }
}
