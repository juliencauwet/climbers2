package com.julien.climbers2.service;

import com.julien.climbers2.entities.Site;
import com.julien.climbers2.entities.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<Site> getSitesByRegionName(String name){

        return siteRepository.findSitesByRegionName(name);
    }

    public List<Site> getSitesByRegionId(int id){
        return siteRepository.findSitesByRegion_Id(id);
    }

    public Site getSite(Integer id){
        return siteRepository.findSiteById(id);
    }

    public void addSite(Site site){
        siteRepository.save(site);
    }

    public void changeRegion(int i ){
        siteRepository.findSitesByRegion_Id(i);
    }
}
