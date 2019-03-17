package com.example.commuteeazy.DO;

import android.support.annotation.Nullable;

import java.util.Date;

public class Feed {

    private Long id;
    private String feed;
    private Date dateCreated;
    private Date dateMidified;

    @Nullable
    private User user;

    @Nullable
    private Operator operator;

    public Feed() {

    }

    public Feed(Long id, String feed, Date dateCreated, Date dateMidified, @Nullable User user, @Nullable Operator operator) {
        this.id = id;
        this.feed = feed;
        this.dateCreated = dateCreated;
        this.dateMidified = dateMidified;
        this.user = user;
        this.operator = operator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateMidified() {
        return dateMidified;
    }

    public void setDateMidified(Date dateMidified) {
        this.dateMidified = dateMidified;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    public Operator getOperator() {
        return operator;
    }

    public void setOperator(@Nullable Operator operator) {
        this.operator = operator;
    }
}
