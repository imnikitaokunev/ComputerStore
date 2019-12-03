package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.service.ProductService;
import com.nikitkasss.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @RequestMapping(value="/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model){
        AllProductInfoDto dto = new AllProductInfoDto();
        model.addAttribute("product", dto);
        return "seller/addProduct";
    }

    @RequestMapping(value="/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute AllProductInfoDto dto, BindingResult errors, Model model) throws Exception {
        productService.add(dto);
        return "redirect:/all/products";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public String editProduct(@RequestParam (value = "id", required = true) Long id, Model model) {
        AllProductInfoDto dto = productService.getById(id);
        model.addAttribute("product", dto);
        return "seller/editProduct";
    }

    @RequestMapping(value="/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute AllProductInfoDto dto, BindingResult errors, Model model) throws Exception {
        productService.edit(dto);
        return "redirect:/all/products";
    }

//    @RequestMapping(value="/deleteProduct", method = RequestMethod.GET)
//    public String deleteProduct(Model model){
//        List<AllProductInfoDto> products = productService.allProducts();
//        model.addAttribute("products", products);
//        return "seller/deleteProduct";
//    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            AllProductInfoDto dto = productService.getById(id);
            productService.delete(dto);
        }
        List<AllProductInfoDto> products = productService.allProducts();
        model.addAttribute("products", products);
        return "seller/deleteProduct";
    }

//    @RequestMapping(value="/deleteProduct", method = RequestMethod.POST)
//    public String deleteProduct(@ModelAttribute AllProductInfoDto dto, BindingResult errors, Model model) throws Exception{
//        productService.delete(dto);
//        return "seller/deleteProduct";
//    }
}
