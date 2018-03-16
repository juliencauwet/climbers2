package com.julien.climbers2.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SiteRepository  extends CrudRepository<Site,Integer>{

    List<Site> findSitesByRegionName(String name);

    Site findSiteById(Integer id);

    List <Site> findSitesByRegion_Id(int i);
}
