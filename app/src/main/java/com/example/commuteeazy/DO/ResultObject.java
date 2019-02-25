package com.example.commuteeazy.DO;

import java.util.List;

public class ResultObject {

    List<AddressComponents> addressComponents;
    String formattedAddress;
    GeometryObject geometry;
    String placeID;
    String [] types;

    public ResultObject() {
    }

    public ResultObject(List<AddressComponents> addressComponents, String formattedAddress, GeometryObject geometry, String placeID, String[] types) {
        this.addressComponents = addressComponents;
        this.formattedAddress = formattedAddress;
        this.geometry = geometry;
        this.placeID = placeID;
        this.types = types;
    }

    public List<AddressComponents> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponents> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeometryObject getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryObject geometry) {
        this.geometry = geometry;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
