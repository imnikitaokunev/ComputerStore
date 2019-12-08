package com.nikitkasss.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "main/main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "main/login";
    }

    @RequestMapping(value = "/all/home", method = RequestMethod.GET)
    public String homePage(){
        return "/main/home";
    }

    @RequestMapping(value = "/all/map", method = RequestMethod.GET)
    public String map(){
        return "/main/map";
    }

}
