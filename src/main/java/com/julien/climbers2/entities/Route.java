package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @OneToMany
    @JoinColumn(name = "route_id")
    private Set<Length> lengths =new HashSet<>();

    @OneToMany
    @JoinColumn(name = "comment_id")
    private Set<Comment> comments = new HashSet<>();

    public Route(String name, Area area, Set<Length> lengths) {
        this.name = name;
        this.area = area;
        this.lengths = lengths;
    }

    public Route(){

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @OneToMany
    @JoinColumn(name = "route_id")
    public Set<Length> getLengths() {
        return lengths;
    }

    public void setLengths(Set<Length> lengths) {
        this.lengths = lengths;
    }
}
