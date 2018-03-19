package com.julien.climbers2.service;

import com.julien.climbers2.entities.Route;
import com.julien.climbers2.entities.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes(){
        List <Route> routes = new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);

        return routes;
    }

    public void addRoute(Route route){
        routeRepository.save(route);
    }

    public Route getRoutebyId(int id){
        return routeRepository.findOne(id);
    }

    public List<Route> getRoutesPerArea(int id){
        return routeRepository.findRoutesByAreaId(id);
    }
}
