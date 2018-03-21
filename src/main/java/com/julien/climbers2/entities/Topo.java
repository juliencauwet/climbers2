package com.julien.climbers2.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "topo_id")
    private Set<Borrowing> borrowing = new HashSet<>();

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

    public String getAuthor() {
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
