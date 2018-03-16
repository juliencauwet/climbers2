package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Site {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name = "site_id")
    private Set<Area> areas = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Region region;

    public Site(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    private Site() {
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
