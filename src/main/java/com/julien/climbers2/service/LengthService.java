package com.julien.climbers2.service;

import com.julien.climbers2.entities.Length;
import com.julien.climbers2.entities.LengthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LengthService {

    @Autowired
    private LengthRepository lengthRepository;

    public void addLength(Length length){
        lengthRepository.save(length);
    }

    public List<Length> getAllLengthsByRouteId(int id){
        List<Length> lengths = new ArrayList<>();
        lengthRepository.findLengthsByRoute_Id(id).forEach(lengths::add);
        return lengths;
    }
}
