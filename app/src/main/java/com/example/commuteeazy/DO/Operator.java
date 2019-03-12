package com.example.commuteeazy.DO;

import java.util.HashSet;
import java.util.Set;

public class Operator {

    private Long operatorID;
    private Long operatorName;
    private String email;
    private String phone;
    private String accountPassword;
    private Set<PlaceOnRoute> places = new HashSet<>();
    private Set<Terminus> termini;

    public Operator() {
    }

    public Operator(Long operatorID, Long operatorName, String email, String phone, String accountPassword, Set<PlaceOnRoute> places, Set<Terminus> termini) {
        this.operatorID = operatorID;
        this.operatorName = operatorName;
        this.email = email;
        this.phone = phone;
        this.accountPassword = accountPassword;
        this.places = places;
        this.termini = termini;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public Long getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(Long operatorName) {
        this.operatorName = operatorName;
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

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
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
