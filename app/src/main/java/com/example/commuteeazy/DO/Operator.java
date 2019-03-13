package com.example.commuteeazy.DO;

import java.util.HashSet;
import java.util.Set;

public class Operator {

    private Long operatorID;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Set<PlaceOnRoute> places = new HashSet<>();
    private Set<Terminus> termini;

    public Operator() {
    }

    public Operator(String name, String email, String phone, String password, Set<PlaceOnRoute> places, Set<Terminus> termini) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.places = places;
        this.termini = termini;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PlaceOnRoute> getPlaces() {
        return places;
    }

    public void setPlaces(Set<PlaceOnRoute> places) {
        this.places = places;
    }

    public Set<Terminus> getTermini() {
        return termini;
    }

    public void setTermini(Set<Terminus> termini) {
        this.termini = termini;
    }
}
