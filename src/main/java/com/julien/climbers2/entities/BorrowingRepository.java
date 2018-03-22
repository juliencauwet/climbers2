package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface BorrowingRepository extends CrudRepository<Borrowing,Integer> {

    public Borrowing findBorrowingByBooked(Date date);
}
