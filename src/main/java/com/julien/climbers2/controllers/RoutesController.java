package com.julien.climbers2.controllers;


import com.julien.climbers2.entities.*;
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
    public String routes(Model model, HttpSession session){

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
    public String displaySites(@RequestParam String region, Model model, HttpSession session){

        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        model.addAttribute("regList", regionService.getRegions());
        model.addAttribute("sites", siteService.getSitesByRegionId(Integer.parseInt(region)));

        return "routes";
    }

    @GetMapping("/routes/{siteId}")
    public String displayAreas (@PathVariable String siteId, Model model, HttpSession session){

       model.addAttribute("regList", regionService.getRegions());
       model.addAttribute("areas", areaService.getAreasperSites(Integer.parseInt(siteId)));

        return "routes";
    }

    @GetMapping("/routes/{siteId}/{areaId}")
    public String displayRoutes (@PathVariable String siteId, @PathVariable String areaId, Model model, HttpSession session){

        model.addAttribute("regList", regionService.getRegions());
        model.addAttribute("routes", routeService.getRoutesPerArea(Integer.parseInt(areaId)));
        model.addAttribute("selectedArea", areaService.getAreaPerId(Integer.parseInt(areaId)).getName());

        Boolean form = true;

        return "routes";
    }

    @PostMapping(value = "/routes/{siteId}/{areaId}", params = "Ajouter")
    public String addRoute(@PathVariable String siteId, @PathVariable String areaId, @RequestParam String name, Model model){

        Area selectedArea = areaService.getAreaPerId(Integer.parseInt(areaId));

        Route route = new Route();
        route.setArea(selectedArea);
        route.setName(name);
        routeService.addRoute(route);

        model.addAttribute("selectedArea", selectedArea.getName());

        return "routes";
    }


}
