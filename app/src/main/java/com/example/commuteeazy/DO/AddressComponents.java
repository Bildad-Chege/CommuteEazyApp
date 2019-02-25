package com.example.commuteeazy.DO;

public class AddressComponents {

    String longName;
    String shortName;
    String [] types;

    public AddressComponents() {
    }

    public AddressComponents(String longName, String shortName, String[] types) {
        this.longName = longName;
        this.shortName = shortName;
        this.types = types;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
