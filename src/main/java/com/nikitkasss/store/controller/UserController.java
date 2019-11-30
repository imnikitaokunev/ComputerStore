package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.AllUserInfoDto;
import com.nikitkasss.store.dto.user.GeneralUserInfoDto;
import com.nikitkasss.store.dto.user.UserLoginPasswordDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<AllUserInfoDto> getAllUsers(){
        return userService.allUsers();
    }

    @GetMapping("/generalUsers")
    public List<GeneralUserInfoDto> getAllUserGeneralInfo(){
        return userService.allUserGeneralInfo();
    }

    @GetMapping("/accounts")
    public List<UserLoginPasswordDto> getAllLoginAndPassword(){
        return userService.allLoginAndPassword();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody AllUserInfoDto dto) throws ConvertingException, IllegalArgumentException{
        userService.add(dto);
    }

    @PostMapping("/editUser")
    public void editUser(@RequestBody AllUserInfoDto dto) throws ConvertingException, IllegalArgumentException{
        userService.edit(dto);
    }

//    @DeleteMapping("/deleteUser/{id}")
//    public void deleteUser(@PathVariable("id") Long id) throws ConvertingException, NoSuchEntityException{
//        AllUserInfoDto dto = userService.getById(id);
//        userService.delete(dto);
//    }

    @GetMapping("/findUser/{id}")
    public AllUserInfoDto findUser(@PathVariable("id") Long id) throws NoSuchEntityException{
        return userService.getById(id);
    }



}
