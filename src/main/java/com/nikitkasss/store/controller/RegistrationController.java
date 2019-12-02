package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.AllUserInfoDto;
import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.model.AbstractUser;
import com.nikitkasss.store.repository.UserRepository;
import com.nikitkasss.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManagerBuilder auth;


    public void register(AbstractUser user) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user.getUserName())
                .password(user.getUserPassword())
                .roles(user.getUserRole().getValue());
    }


    @PostMapping("/registration")
    public void addUser(@RequestBody BuyerInfoDto dto) throws Exception {
        service.add(dto);
    }
}
