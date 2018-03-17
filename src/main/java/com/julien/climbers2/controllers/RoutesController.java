package com.julien.climbers2.controllers;


import com.julien.climbers2.entities.Area;
import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.Route;
import com.julien.climbers2.entities.Site;
import com.julien.climbers2.service.AreaService;
import com.julien.climbers2.service.RegionService;
import com.julien.climbers2.service.RouteService;
import com.julien.climbers2.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RoutesController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private RouteService routeService;



    /**
     *
     * @param model
     * @return view routes en mode Get
     */
    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public String routes(Model model){

        List<Region> regions = regionService.getRegions();

        model.addAttribute("regList", regions);

    return "routes";
    }

    /**
     *
     * @param region
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/routes")
    public String displaySites(@RequestParam String region, Model model){

        List<Region> regions = regionService.getRegions();

        List<Site> sites = siteService.getSitesByRegionId(Integer.parseInt(region));

        model.addAttribute("regList", regions);
        model.addAttribute("sites",sites);


        return "routes";
    }

    @GetMapping("/routes/{siteId}")
    public String displayAreas (@PathVariable String siteId, Model model){

       List<Area> areas = areaService.getAreasperSites(Integer.parseInt(siteId));

       model.addAttribute("areaList", areas );

        return "routes";
    }

    @GetMapping("/routes/{siteId}/{areaId}")
    public String displayRoutes (@PathVariable String siteId, @PathVariable String areaId, Model model){
        System.out.println("Entrée dans displayRoutes");
        List<Route> routes = routeService.getRoutesPerArea(Integer.parseInt(areaId));

        model.addAttribute("routes", routes);

        return "routes";
    }


}
