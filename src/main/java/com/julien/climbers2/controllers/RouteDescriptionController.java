package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Comment;
import com.julien.climbers2.entities.Route;
import com.julien.climbers2.service.CommentService;
import com.julien.climbers2.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RouteDescriptionController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/routedescription/{routeId}")
    public String routeDescription(@PathVariable String routeId, Model model){

        Route route = routeService.getRoutebyId(Integer.parseInt(routeId));

        List<Comment> comments = commentService.getCommentsperRoute(Integer.parseInt(routeId));

        model.addAttribute("comments", comments);
        model.addAttribute("route", route);


        return "routedescription";
    }
}
