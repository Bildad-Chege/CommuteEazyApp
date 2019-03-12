package com.example.commuteeazy.DO;

public class Terminus {

    private String placeID;
    private String name;
    private double latitude;
    private double longitude;

    public Terminus() {
    }

    public Terminus(String placeID, String name, double latitude, double longitude) {
        this.placeID = placeID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
