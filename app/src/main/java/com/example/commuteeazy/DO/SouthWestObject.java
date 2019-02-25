package com.example.commuteeazy.DO;

public class SouthWestObject {

    Double latitude;
    Double longitude;

    public SouthWestObject() {
    }

    public SouthWestObject(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
