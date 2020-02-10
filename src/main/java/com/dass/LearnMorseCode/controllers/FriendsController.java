package com.dass.LearnMorseCode.controllers;


import com.dass.LearnMorseCode.models.ApplicationUser;
import com.dass.LearnMorseCode.models.ApplicationUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;



@Controller
public class FriendsController {

    @Autowired
    // Could remove private to improve usability
    private ApplicationUserRepo applicationUserRepo;

    @GetMapping("/friends")
    public String getCreate(Principal p, Model m){

        // Calling currently logged in username (me)
        // This could be a helper function
        if(p != null){
            m.addAttribute("username", p.getName());
            m.addAttribute("action","/logout");
        } else {
            m.addAttribute("action","/login");
        }

        // List to display all users in database
        m.addAttribute("allUsers", applicationUserRepo.findAll());

        return "friends";
    }

    @GetMapping("/friends/{id}")
    // good that this one is plural
    public String getAllFriends(@PathVariable long id, Principal p, Model d){
        ApplicationUser friendsList = applicationUserRepo.findById(id).get();
        ApplicationUser loggedInUser = applicationUserRepo.findByUsername(p.getName());
        d.addAttribute("friendsList", friendsList);
        d.addAttribute("loggedInUser", loggedInUser);
        return "/profile";
    }
}


