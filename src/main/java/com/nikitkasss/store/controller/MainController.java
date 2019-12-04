package com.nikitkasss.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main/main";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "main/login";
    }

    @GetMapping("/all/home")
    public String homePage(){
        return "/main/home";
    }

}
