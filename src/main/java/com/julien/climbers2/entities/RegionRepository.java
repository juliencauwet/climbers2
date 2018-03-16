package com.julien.climbers2.entities;


import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Integer>{

    Region findRegionById(Integer id);

}
