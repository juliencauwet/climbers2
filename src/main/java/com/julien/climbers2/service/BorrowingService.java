package com.julien.climbers2.service;

import com.julien.climbers2.entities.Borrowing;
import com.julien.climbers2.entities.BorrowingRepository;
import com.julien.climbers2.entities.Topo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowingService{

    @Autowired
    private BorrowingRepository borrowingRepository;

    public List<Borrowing> getAllBorrowingsByTopoId(int topoId){
        List<Borrowing> borrowings = new ArrayList<>();
        borrowingRepository.findBorrowingsByTopo_IdOrderByBooked(topoId).forEach(borrowings::add);
        return borrowings;
    }

    public void addBorrowing(Borrowing borrowing){
        borrowingRepository.save(borrowing);
    }

    public List <Borrowing> getBorrowing(Date date, int topoId){
        return borrowingRepository.findBorrowingByBookedAndTopo_Id(date, topoId);
    }
}
