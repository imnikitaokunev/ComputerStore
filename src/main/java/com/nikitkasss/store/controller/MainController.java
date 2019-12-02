package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.BuyerInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "main/home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "main/login";
    }

}
