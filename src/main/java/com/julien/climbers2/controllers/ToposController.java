package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Borrowing;
import com.julien.climbers2.entities.Region;
import com.julien.climbers2.entities.Topo;
import com.julien.climbers2.entities.Usor;
import com.julien.climbers2.service.BorrowingService;
import com.julien.climbers2.service.RegionService;
import com.julien.climbers2.service.TopoService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NonUniqueResultException;
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
    public String getTopo(@PathVariable String id, Model model, HttpSession session) {
        String message = "";
        Topo topo = topoService.getTopo(Integer.parseInt(id));

        model.addAttribute("title", topo.getTitle());
        model.addAttribute("author", topo.getAuthor());
        model.addAttribute("region", topo.getRegion().getName());
        model.addAttribute("message", message);
        model.addAttribute("borrowings", getAllBooked(id));

        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        return "topodescription";
    }

    @RequestMapping(value = "/topos", method = RequestMethod.POST)
    public void addTopo(@ModelAttribute @Valid Topo topo, Model model, HttpSession session) {

        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        topoService.addTopo(topo);

        displayTopos(model, session);
    }

    @RequestMapping(value = "/topos/{id}", method = RequestMethod.POST)
    public String topoDescription(@PathVariable String id, @RequestParam String start, @RequestParam String end, Model model, HttpSession session) {

        String message = "";
        Topo topo = topoService.getTopo(Integer.parseInt(id));

        model.addAttribute("title", topo.getTitle());
        model.addAttribute("author", topo.getAuthor());
        model.addAttribute("region", topo.getRegion().getName());

        int result = borrow(start, end, topo);

        if (result == 1) {
            message = "Veuillez entrer une date ultérieure";
        } else if (result == 2) {
            message = "Certaines ou toutes de ces dates sont déjà prises.";
        } else {
            message = "Les dates de réservations sont enregistrées.";
        }

        model.addAttribute("regions", regionService.getRegions());
        model.addAttribute("borrowings", getAllBooked(id));
        model.addAttribute("message", message);

        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        return "topoDescription";
    }


    @RequestMapping("/topos")
    public String displayTopos(Model model, HttpSession session) {

        model.addAttribute("list", topoService.getTopos());
        model.addAttribute("regions", regionService.getRegions());

        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        return "topos";
    }


    public int borrow(String start, String end, Topo topo){
        Date startDate = null;
        Date endDate = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = sdf.parse(start);
            endDate = sdf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (startDate.after(endDate)) {
            return 1;
        }

        Calendar c0 = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        c0.setTime(startDate);
        c1.setTime(endDate);
        c1.add(Calendar.DATE, 1);

        List<Borrowing> borrowing;

        do {

            borrowing = borrowingService.getBorrowing(c0.getTime(), topo.getId());

            if (borrowing.size() > 0 )
                return 2;
            else
                c0.add(Calendar.DATE, 1);
        } while (!c0.equals(c1));

        c0.setTime(startDate);

        do {
            borrowingService.addBorrowing(new Borrowing(topo, c0.getTime()));
            c0.add(Calendar.DATE, 1);
        } while (!c0.equals(c1));

        return 3;

    }


    private int daysPerMonth(int month, int year) {
        int nbDays = 0;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                nbDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                nbDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) &&
                        !(year % 100 == 0))
                        || (year % 400 == 0))
                    nbDays = 29;
                else
                    nbDays = 28;
                break;
        }

        return nbDays;
    }


    private List<Date> generateGridperMonth() throws ParseException {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        List<Date> dates = new ArrayList<>();
        int nbWeeks = 5;
        if (daysPerMonth(month ,year) == 28)
            nbWeeks = 4;

        System.out.println(cal.get(Calendar.DAY_OF_WEEK));

        return dates;
    }


    private List<String> getAllBooked(String topoId) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        for (Borrowing bor : borrowingService.getAllBorrowingsByTopoId(Integer.parseInt(topoId)))
            dates.add(sdf.format(bor.getBooked()));
        return dates;
    }
}
