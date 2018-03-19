package com.julien.climbers2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class TopoDescriptionController {

    @RequestMapping(value = "/topos/topodescription", method = RequestMethod.POST)
    public String topoBorrowing(@RequestParam("start") String start, @RequestParam("end") String end, Model model){
        String message = "L'emprunt du topo a été sauvegardé";
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (startDate.after(endDate))
            message = "Veuillez entrer une date de retour antérieure.";


        model.addAttribute("message", message);

        model.addAttribute("booked","background = black;");


        return "topodescription";

    }


}
