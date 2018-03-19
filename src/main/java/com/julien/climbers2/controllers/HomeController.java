package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.Site;
import com.julien.climbers2.entities.Usor;
import com.julien.climbers2.service.RegionService;
import com.julien.climbers2.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private SiteService siteService;

    @RequestMapping("/")
    public String index(Model model, HttpSession session){

        try {
            Usor usor = (Usor)session.getAttribute("user");

            System.out.println("user = " + usor.getPseudo());
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        List<Region> regionsList = regionService.getRegions();
        model.addAttribute("list", regionsList);


        return "index";

    }
}
