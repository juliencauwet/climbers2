package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AreaRepository extends CrudRepository<Area,Integer> {

    List<Area> findAllBySiteId(int i);
}
