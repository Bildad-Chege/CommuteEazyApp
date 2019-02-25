package com.example.commuteeazy.DO;

public class GeometryObject {

    BoundsObject bounds;
    LocationObject location;
    String locationType;
    ViewportObject viewPort;

    public GeometryObject() {
    }

    public GeometryObject(BoundsObject bounds, LocationObject location, String locationType, ViewportObject viewPort) {
        this.bounds = bounds;
        this.location = location;
        this.locationType = locationType;
        this.viewPort = viewPort;
    }

    public BoundsObject getBounds() {
        return bounds;
    }

    public void setBounds(BoundsObject bounds) {
        this.bounds = bounds;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public ViewportObject getViewPort() {
        return viewPort;
    }

    public void setViewPort(ViewportObject viewPort) {
        this.viewPort = viewPort;
    }
}
