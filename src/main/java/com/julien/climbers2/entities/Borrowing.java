package com.julien.climbers2.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Borrowing {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Topo topo;

    Date booked;

    public Borrowing(Topo topo, Date booked) {
        this.topo = topo;
        this.booked = booked;
    }

    public Borrowing(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Date getBooked() {
        return booked;
    }

    public void setBooked(Date booked) {
        this.booked = booked;
    }
}
