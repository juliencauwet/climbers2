package com.julien.climbers2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Borrowing {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Topo topo;
    @OneToOne
    private Usor borrower;
    @OneToOne
    private Usor owner;
    private Date startDate;
    private Date endDate;
    private Date returnDate;

    public Borrowing(Topo topo, Usor borrower, Usor owner, Date startDate, Date endDate, Date returnDate) {
        this.topo = topo;
        this.borrower = borrower;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.returnDate = returnDate;
    }

    private Borrowing(){

    }

    public int getId() {
        return id;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo(Topo topo) {
        this.topo = topo;
    }

    public Usor getBorrower() {
        return borrower;
    }

    public void setBorrower(Usor borrower) {
        this.borrower = borrower;
    }

    public Usor getOwner() {
        return owner;
    }

    public void setOwner(Usor owner) {
        this.owner = owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
