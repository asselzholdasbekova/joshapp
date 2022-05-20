package kz.assel.joshapp.controllers;

import kz.assel.joshapp.dao.Dao;
import kz.assel.joshapp.dao.DaoFactory;
import kz.assel.joshapp.models.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private Dao profileDao = DaoFactory.createDao("profile");

    @GetMapping("/login")
    public String form(@ModelAttribute("profile") Profile profile){
        return "login/form";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("profile") Profile profile){
        if(profileDao.check(profile)){
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }
}
