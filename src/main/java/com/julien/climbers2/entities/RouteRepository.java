package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Integer>{

    List<Route> findRoutesByAreaId(int id);
}
