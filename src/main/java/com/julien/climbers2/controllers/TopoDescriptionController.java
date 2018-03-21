package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Borrowing;
import com.julien.climbers2.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TopoDescriptionController {

    @Autowired
    private BorrowingService borrowingService;

    @RequestMapping(value = "/topos/topodescription", method = RequestMethod.POST)
    public String topoBorrowing(@RequestParam("start") String start, @RequestParam("end") String end, Model model){
        String message = "L'emprunt du topo a été sauvegardé";
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(startDate.after(endDate)){
            message = " Veuillez entrer une date de fin ultérieure.";
            model.addAttribute("message", message);
            return "topodescription";
        }

        Calendar c0 = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c0.setTime(startDate);
        c1.setTime(endDate);

        while(!c0.equals(c1)){
            c0.add(Calendar.DATE, 1);
            System.out.println(c0);
            borrowingService.addBorrowing(new Borrowing());
        }



        if (startDate.after(endDate))
            message = "Veuillez entrer une date de retour antérieure.";


        model.addAttribute("message", message);

        model.addAttribute("booked","background = black;");


        return "topodescription";

    }


}
