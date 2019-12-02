package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.AllUserInfoDto;
import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import com.nikitkasss.store.service.BuyerService;
import com.nikitkasss.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class RegistrationController {
    @Autowired
    private UserService service;


    @Autowired
    private BuyerService buyerService;

    @Autowired
    private AuthenticationManagerBuilder auth;


//    public void register(AbstractUser user) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser(user.getUserName())
//                .password(user.getUserPassword())
//                .roles(user.getUserRole().getValue());
//    }

//    @RequestMapping(value="/registration", method = RequestMethod.GET)
//    public void registratot(Model model) throws Exception {
//        //model.addAttribute("user", new BuyerInfoDto());
//        model.addAttribute("serverTime", "gfhfhfghfg");
//
//    }
//

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String register(Model model){
        BuyerInfoDto dto = new BuyerInfoDto();
        model.addAttribute("usr", dto);
        return "main/registration";
    }

    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute BuyerInfoDto dto, BindingResult errors, Model model) throws Exception {
        dto.setRoleName("BUYER");
        System.out.println(dto.getUserName());
        System.out.println(dto.getUserPassword());
        System.out.println(dto.getRoleName());
        service.add(dto);
        return "redirect:/login";
    }

//    @PostMapping("/registration")
//    public void addUser(@RequestBody BuyerInfoDto dto) throws Exception {
//        service.add(dto);
//    }
}
