package com.nikitkasss.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/all")
public class PageController {

//    @GetMapping("/products")
//    public String productsPage(){
//        return "product/products";
//    }


//    @GetMapping("")

//    @GetMapping("/editProduct/{id}")
//    public String editProductPage() {
//        return "editProduct";
//    }
//
//    @GetMapping("/addProduct")
//    public String addProductPage() {
//        return "addProduct";
//    }
//
//    @GetMapping("/productInfo/{id}")
//    public String productInfoPage() {
//        return "productInfo";
//    }

//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(AllUserInfoDto user, Map<String, Object> model){
//        AbstractUser u = repository.getByUserName(user.getUserName());
//        if(u != null){
//            model.put("message", "Пользователь с таким логином уже существует.");
//            return "registration";
//        }
//        return "redirect:/login";
//    }
}
