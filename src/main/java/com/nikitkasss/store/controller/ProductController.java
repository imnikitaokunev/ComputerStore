package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.product.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/all")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public String getAllProducts(Model model){
        List<AllProductInfoDto> products = productService.allProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    @RequestMapping(value="/productNames", method = RequestMethod.GET)
    public String getProductNames(Model model){
        List<ProductNameDto> products = productService.allProductNames();
        model.addAttribute("products", products);
        return "product/productNames";
    }

    @RequestMapping(value="/findProduct", method = RequestMethod.GET)
    public String getProductsByName(Model model){
        String name = "";
        model.addAttribute("name", name);
        return "product/findProduct";
    }

    @RequestMapping(value="/findProduct", method = RequestMethod.POST)
    public String register(@ModelAttribute String name, BindingResult errors, Model model) throws Exception {
        List<AllProductInfoDto> products = productService.getProductsByName(name);
        System.out.println(name);
        return "redirect:/login";
    }


//    @GetMapping("/productNames")
//    public List<ProductNameDto> getProductNames(){
//        return productService.allProductNames();
//    }

    @GetMapping("/findProduct/{id}")
    public AllProductInfoDto findProduct(@PathVariable("id") Long id) throws NoSuchEntityException {
        return productService.getById(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ConvertingException, NoSuchEntityException{
        AllProductInfoDto dto = productService.getById(id);
        productService.delete(dto);
    }

    @PostMapping("/addProduct")
    public void addProduct(@RequestBody AllProductInfoDto dto) throws ConvertingException{
        productService.add(dto);
    }
}
