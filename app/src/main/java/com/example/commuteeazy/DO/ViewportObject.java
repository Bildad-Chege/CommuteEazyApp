package com.example.commuteeazy.DO;

public class ViewportObject {

    NorthEastObject northEast;
    SouthWestObject southWest;

    public ViewportObject() {
    }

    public ViewportObject(NorthEastObject northEast, SouthWestObject southWest) {
        this.northEast = northEast;
        this.southWest = southWest;
    }

    public NorthEastObject getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEastObject northEast) {
        this.northEast = northEast;
    }

    public SouthWestObject getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWestObject southWest) {
        this.southWest = southWest;
    }
}
