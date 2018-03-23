package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BorrowingRepository extends CrudRepository<Borrowing,Integer> {

    Borrowing findBorrowingByBooked(Date date);

    List<Borrowing> findBorrowingsByTopo_IdOrderByBooked(int id);
}
