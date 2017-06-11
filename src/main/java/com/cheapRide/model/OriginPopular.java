package com.cheapRide.model;

/**
 * Created by pshayegh on 6/11/2017.
 */
public class OriginPopular {
    private Double lat;
    private Double lon;
    private String name;
    private East east;
    private North north;
    private West west;
    private South south;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public East getEast() {
        return east;
    }

    public void setEast(East east) {
        this.east = east;
    }

    public North getNorth() {
        return north;
    }

    public void setNorth(North north) {
        this.north = north;
    }

    public West getWest() {
        return west;
    }

    public void setWest(West west) {
        this.west = west;
    }

    public South getSouth() {
        return south;
    }

    public void setSouth(South south) {
        this.south = south;
    }
}
