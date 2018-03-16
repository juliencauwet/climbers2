package com.julien.climbers2.service;

import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegions(){
        List<Region> regions = new ArrayList<>();
        regionRepository.findAll().forEach(regions::add);
        return regions;
    }

    public Region getRegionById(Integer id){
        return regionRepository.findRegionById(id);
    }
}
