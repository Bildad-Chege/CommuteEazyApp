package com.example.commuteeazy.DO;

public class PlaceOnRoute {

    private String placeID;
    private String address;
    private double latitude;
    private double longitude;

    public PlaceOnRoute() {
    }

    public PlaceOnRoute(String placeID, String address, double latitude, double longitude) {
        this.placeID = placeID;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
