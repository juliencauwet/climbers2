package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LengthRepository extends CrudRepository<Length,Integer> {

    List<Length> findLengthsByRoute_Id(int id);
}
