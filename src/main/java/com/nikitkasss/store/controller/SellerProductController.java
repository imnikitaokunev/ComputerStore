package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.ProductDto;
import com.nikitkasss.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value="/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model){
        ProductDto dto = new ProductDto();
        model.addAttribute("product", dto);
        return "seller/addProduct";
    }

    @RequestMapping(value="/addProduct", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute ProductDto dto, BindingResult errors, Model model) throws Exception {
        productService.add(dto);
        return "redirect:/all/products";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public String editProduct(@RequestParam(value = "id", required = true) Long id, Model model) {
        ProductDto dto = productService.getById(id);
        model.addAttribute("product", dto);
        return "seller/editProduct";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            ProductDto dto = productService.getById(id);
            productService.delete(dto);
        }
        List<ProductDto> products = productService.allProducts();
        model.addAttribute("products", products);
        return "seller/deleteProduct";
    }

    @RequestMapping(value="/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute ProductDto dto, BindingResult errors, Model model) throws Exception {
        productService.edit(dto);
        return "redirect:/all/products";
    }
}
