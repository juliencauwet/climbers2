package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Usor usor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Route route;

    private String email;
    private String text;
    private Date postDate;

    public Comment(Usor usor, Route route, String text, Date postDate, String email) {
        this.usor = usor;
        this.route = route;
        this.text = text;
        this.postDate = postDate;
        this.email = email;
    }

    private Comment() {

    }

    public int getId() {
        return id;
    }

    public Usor getUsor() {
        return usor;
    }

    public void setUsor(Usor usor) {
        this.usor = usor;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
