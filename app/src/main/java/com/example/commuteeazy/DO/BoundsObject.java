package com.example.commuteeazy.DO;

public class BoundsObject {

    NorthEastObject nortEast;
    SouthWestObject southWest;

    public BoundsObject() {
    }

    public BoundsObject(NorthEastObject nortEast, SouthWestObject southWest) {
        this.nortEast = nortEast;
        this.southWest = southWest;
    }

    public NorthEastObject getNortEast() {
        return nortEast;
    }

    public void setNortEast(NorthEastObject nortEast) {
        this.nortEast = nortEast;
    }

    public SouthWestObject getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWestObject southWest) {
        this.southWest = southWest;
    }
}
