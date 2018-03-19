package com.julien.climbers2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Topo {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 4, max = 30)
    private String author;

    @NotNull
    @Size(min = 4, max = 100)
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

    public void setAuthor(String author) {
        this.author = author;
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
