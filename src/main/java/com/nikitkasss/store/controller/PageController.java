package com.nikitkasss.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/all")
public class PageController {

    @GetMapping("/")
    public String allProducts() {
        return "products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProductPage() {
        return "editProduct";
    }

    @GetMapping("/addProduct")
    public String addProductPage() {
        return "addProduct";
    }


    @GetMapping("/productInfo/{id}")
    public String productInfoPage() {
        return "productInfo";
    }
}
