package com.julien.climbers2.controllers;

import com.julien.climbers2.entities.Usor;
import com.julien.climbers2.service.UsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SignController {

    @Autowired
    private UsorService usorService;

    @GetMapping("/sign")
    public String sign() {

        return "sign";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST, params = "signin")
    public String signin(@RequestParam String emailIn, @RequestParam String passwordIn, HttpSession session, Model model) {

        String message = "";

        Usor usor = usorService.getUsorByEmail(emailIn);

        System.out.println(usor.getEmail());
        System.out.println(emailIn);
        if (usor == null)
            message = "Cet email n'est associé à aucun compte.";
        else {
            if (usor.getPassword().equals(passwordIn)) {
                message = "Vous êtes connecté!";
                session.setAttribute("user", usor);
                return "redirect:";
            } else {
                message = "Le mot de passe est incorrect!";
            }
        }
        model.addAttribute("message", message);

        return "sign";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST, params = "signup")
    public String signup(@RequestParam String pseudo, @RequestParam String emailUp, @RequestParam String passwordUp1, @RequestParam String passwordUp2, Model model) {
        Usor usor = new Usor(pseudo, emailUp, passwordUp1);
        String message = "";

        if (usorService.getUsorByEmail(emailUp) != null)
            message = "Cet email est déjà utilisé, mot de passe oublié?";
        else {
            if (checkUserValidity(pseudo, emailUp, passwordUp1, passwordUp2)) {
                usorService.addUsor(usor);
                message = "Nouvel utilisateur sauvegardé";
            } else
                message = "Veuillez recommencer. La saisie n'est pas valide.";
        }
        model.addAttribute("message", message);

        return "sign";
    }

    private boolean checkUserValidity(String pseudo, String email, String pw1, String pw2) {

        return !(pseudo.length() > 4 && pw1.length() > 8 && pw1 == pw2);
    }

}
