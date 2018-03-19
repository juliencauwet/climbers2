package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.Topo;
import com.julien.climbers2.service.RegionService;
import com.julien.climbers2.service.TopoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ToposController {

    @Autowired
    private TopoService topoService;
    @Autowired
    private RegionService regionService;

  // @RequestMapping("/entities/{id}")
  // public Topo getTopo(@PathVariable String id){
  //     return topoService.getTopo(id);
  // }
    @RequestMapping("/topos/{id}")
    public String getTopo(@PathVariable String id, Model model){
        int t = Integer.parseInt(id);
        Topo topo = topoService.getTopo(t);
/* début


        model.addAttribute("monthweeks", weeks);
        model.addAttribute("weekdays", days);
fin */
        model.addAttribute("title", topo.getTitle());
        model.addAttribute("author", topo.getAutor());
        model.addAttribute("region", topo.getRegion().getName());

        model.addAttribute("booked","black");

        return "topodescription";
    }

    @RequestMapping(value = "/topos", method = RequestMethod.POST)
    public void addTopo(@ModelAttribute @Valid Topo topo, Model model){

        topoService.addTopo(topo);
        displayTopos(model);
    }

    @RequestMapping(value = "/topos/{id}", method = RequestMethod.PUT)
    public void updateTopo(@PathVariable String id, @RequestBody Topo topo){
        topoService.updateTopo(id, topo);
    }

    /*@RequestMapping(value = "/entities/{id}", method = RequestMethod.DELETE)
    public void deleteTopo(@PathVariable String id){
        topoService.deleteTopo(id);
    }*/

    @RequestMapping("/topos")
    public String displayTopos(Model model){

        List<Topo> topoList = topoService.getTopos();
        model.addAttribute("list", topoList);

        List<Region> regionList = regionService.getRegions();
        model.addAttribute("regions", regionList);

        return "topos";
    }

    private int daysPerMonth(int month, int year){
        int nbDays = 0;

        switch (month){
            case 1 : case 3 : case 5 :case 7 :case 8 :case 10 : case 12 : nbDays =31;
        break;
            case 4: case 6 :
            case 9: case 11:
                nbDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) &&
                        !(year % 100 == 0))
                        || (year % 400 == 0))
                    nbDays = 29;
                else
                    nbDays = 28;
                break;}

                return nbDays;
    }

    private List<Date> generateMonth(int month, int year) throws ParseException {
        List<Date> dates = null;
        String date = "01/" + Integer.toString(month) + "/" + Integer.toString(year);
        String end = Integer.toString(daysPerMonth(month,year));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");

        Date firstDay = sdf.parse(date);
        Date lastDay = sdf.parse(end);

        Calendar c = Calendar.getInstance();
        c.setTime(firstDay);

        ArrayList<Date> days = new ArrayList<Date>();
        for(Date d = firstDay ; !d.equals(lastDay)  ;){
            dates.add(d);
        }

        System.out.println(days.size());

        ArrayList<String> weeks = new ArrayList<String>();
        for(int i =1 ; i < 5 ; i++){
            weeks.add(Integer.toString(i));
        }



        return dates;
    }



}
