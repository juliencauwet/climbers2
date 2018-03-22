package com.julien.climbers2.service;

import com.julien.climbers2.entities.Borrowing;
import com.julien.climbers2.entities.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    public void addBorrowing(Borrowing borrowing){
        borrowingRepository.save(borrowing);
    }

    public Borrowing getBorrowing(Date date){
        return borrowingRepository.findBorrowingByBooked(date);
    }
}
