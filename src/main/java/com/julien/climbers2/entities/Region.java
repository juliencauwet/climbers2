package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name = "region_id")
    private Set<Topo> topo = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "region_id")
    private Set<Site> sites = new HashSet<>();


    public Region(String name) {
        this.name = name;
        this.getClass().getMethods();

    }

    private Region(){

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
}
