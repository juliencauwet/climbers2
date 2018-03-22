package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Borrowing;
import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.Topo;
import com.julien.climbers2.service.BorrowingService;
import com.julien.climbers2.service.RegionService;
import com.julien.climbers2.service.TopoService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private BorrowingService borrowingService;


    @RequestMapping("/topos/{id}")
    public String getTopo(@PathVariable String id, HttpSession session, Model model){

        Topo topo = topoService.getTopo(Integer.parseInt(id));

        model.addAttribute("title", topo.getTitle());
        model.addAttribute("author", topo.getAuthor());
        model.addAttribute("region", topo.getRegion().getName());

        return "topodescription";
    }

    @RequestMapping(value = "/topos", method = RequestMethod.POST)
    public void addTopo(@ModelAttribute @Valid Topo topo, Model model){

        topoService.addTopo(topo);
        displayTopos(model);
    }

    @RequestMapping(value = "/topos/{id}", method = RequestMethod.POST)
    public String topoDescription(@PathVariable String id, @RequestParam String start, @RequestParam String end, Model model){

        String message ="";
        Topo topo = topoService.getTopo(Integer.parseInt(id));

        model.addAttribute("title", topo.getTitle());
        model.addAttribute("author", topo.getAuthor());
        model.addAttribute("region", topo.getRegion().getName());

        int result = borrow(start, end, topo);
        if( result == 1) {
            message = "Veuillez entrer une date ultérieure";
        }else if(result == 2){
            message = "Les dates de réservations sont enregistrées.";
        }else{
            message = "Certaines ou toutes de ces dates sont déjà prises.";
        }

        model.addAttribute("message", message);

        return "topoDescription";
    }


    @RequestMapping("/topos")
    public String displayTopos(Model model){

        List<Topo> topoList = topoService.getTopos();
        model.addAttribute("list", topoList);

        List<Region> regionList = regionService.getRegions();
        model.addAttribute("regions", regionList);

        return "topos";
    }



    public int borrow(String start, String end, Topo topo) {
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
            System.out.println("startDate: " + startDate);
            System.out.println("endDate: " + endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate.after(endDate)) {
            return 1;
        }

        DateTime dt0 = new DateTime(startDate);
        DateTime dt1 = new DateTime(endDate);


      Calendar c0 = Calendar.getInstance();
      Calendar c1 = Calendar.getInstance();
      c0.setTime(startDate);
      c1.setTime(endDate);


      do  {
          if (borrowingService.getBorrowing(dt0.toDate()) != null)
          return 3;
          else
              c0.add(Calendar.DATE, 1);
          } while (!c0.equals(c1));

      c0.setTime(startDate);

           do  {
               System.out.println(c0.get(Calendar.DATE));
               borrowingService.addBorrowing(new Borrowing(topo, c0.getTime()));
               c0.add(Calendar.DATE, 1);
               System.out.println(c0.equals(c1));
           } while (!c0.equals(c1));

        return 3;

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
