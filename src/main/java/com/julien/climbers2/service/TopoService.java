package com.julien.climbers2.service;

import com.julien.climbers2.entities.Topo;
import com.julien.climbers2.entities.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopoService {

    @Autowired
    private TopoRepository topoRepository;

    public List<Topo> getTopos(){

          List<Topo> topos = new ArrayList<>();
          topoRepository.findAll().forEach(topos::add);

        return topos;
    }

    public Topo getTopo(int id){

        return topoRepository.findOne(id);
    }

    public void addTopo(Topo topo) {
        topoRepository.save(topo);
    }

    public void updateTopo(String id, Topo topo) {
        topoRepository.save(topo);
    }

}
