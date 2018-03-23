package com.julien.climbers2.entities;



import javax.persistence.*;

@Entity
public class Length {

    @Id
    @GeneratedValue
    private int id;
    private  String name;
    private int DRating;
    private char CRating;

    @ManyToOne
    private Route route;

    public Length(String name, int DRating, char CRating, Route route) {
        this.name = name;
        this.DRating = DRating;
        this.CRating = CRating;
        this.route = route;
    }

    private Length() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDRating() {
        return DRating;
    }

    public void setDRating(int DRating) {
        this.DRating = DRating;
    }

    public char getCRating() {
        return CRating;
    }

    public void setCRating(char CRating) {
        this.CRating = CRating;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
