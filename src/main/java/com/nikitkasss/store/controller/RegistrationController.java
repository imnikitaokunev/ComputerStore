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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public String register(Model model){
        BuyerInfoDto dto = new BuyerInfoDto();
        model.addAttribute("usr", dto);
        return "main/registration";
    }

    //@RequestParam (value = "param", required = false) Error error,
    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute BuyerInfoDto dto, BindingResult errors, Model model) throws Exception {
        dto.setRoleName("ROLE_BUYER");
        if(service.getByUserName(dto.getUserName()) != null){
//            model.addAttribute("error", "Пользователь с таким логином уже существует.");
//            return "main/registration";
        }
        else {
            //service.add(dto);
            buyerService.add(dto);
        }
        return "redirect:/login";
    }

}
