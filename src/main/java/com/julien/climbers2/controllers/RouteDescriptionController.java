package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Comment;
import com.julien.climbers2.entities.Length;
import com.julien.climbers2.entities.Route;
import com.julien.climbers2.entities.Usor;
import com.julien.climbers2.service.CommentService;
import com.julien.climbers2.service.LengthService;
import com.julien.climbers2.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RouteDescriptionController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LengthService lengthService;



    @GetMapping("/routedescription/{routeId}")
    public String routeDescription(@PathVariable String routeId, Model model, HttpSession session){


        try {
            Usor usor = (Usor)session.getAttribute("user");
            model.addAttribute("username", usor.getPseudo());
            model.addAttribute("useremail", usor.getEmail());
        }catch (NullPointerException e){
            System.out.println("Pas d'utilisateur identifié");
        }

        Route route = routeService.getRoutebyId(Integer.parseInt(routeId));

        List<Comment> comments = commentService.getCommentsperRoute(Integer.parseInt(routeId));

        model.addAttribute("lengths", lengthService.getAllLengthsByRouteId(Integer.parseInt(routeId)));
        model.addAttribute("comments", comments);
        model.addAttribute("route", route);

        return "routedescription";
    }

    @PostMapping(value = "/routedescription/{routeId}", params = "commentaire")
    public String addComment(@ModelAttribute Comment comment, @PathVariable String routeId, Model model, HttpSession session){

        comment.setRoute(routeService.getRoutebyId(Integer.parseInt(routeId)));
        commentService.addComment(comment);

        return routeDescription(routeId, model, session);
    }

    @PostMapping(value = "/routedescription/{id}", params = "lth")
    public String addLength(@ModelAttribute Length length, @PathVariable String id, Model model, HttpSession session){

        length.setRoute(routeService.getRoutebyId(Integer.parseInt(id)));
        lengthService.addLength(length);

        return routeDescription(id, model, session);
    }


}
