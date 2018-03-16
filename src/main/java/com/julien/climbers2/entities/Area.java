package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Area {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Site site;

    @OneToMany
    @JoinColumn(name = "area_id")
    private Set<Route> routes = new HashSet<>();

    public Area(String name, Site site) {
        this.name = name;
        this.site = site;
    }

    private Area(){

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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
}
