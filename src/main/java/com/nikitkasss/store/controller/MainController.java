package com.nikitkasss.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {



    @GetMapping("/")
    public String homePage() {
        return "main/home";
    }

//    @GetMapping("/registration")
//    public String addUserPage(){
//        return "main/registration";
//    }

    @GetMapping("/login")
    public String loginPage(){
        return "main/login";
    }
}
