package com.julien.climbers2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Topo {

    @Id
    @GeneratedValue
    private int id;
    private String author;
    private String title;

    @ManyToOne
    private Usor usor;

    @ManyToOne
    private Region region;

    public Topo(String author, String title, Region region) {
        this.author = author;
        this.title = title;
        this.region = region;
    }

    public Topo() {

    }

    public int getId() {
        return id;
    }

    public String getAutor() {
        return author;
    }

    public void setAutor(String autor) {
        this.author = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


}
