package com.julien.climbers2.service;

import com.julien.climbers2.entities.Area;
import com.julien.climbers2.entities.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public Area getAreaPerId(int id){
        return areaRepository.findOne(id);
    }

    public List<Area> getAreasperSites(int id){
        return areaRepository.findAllBySiteId(id);
    }
}
