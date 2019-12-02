package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.product.ProductNameDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/all")
public class ProductController {
    private ProductService productService;
    //private AuthenticationService authenticationService;

    @Autowired
    public ProductController(ProductService productService){//
        this.productService = productService;
        //this.authenticationService = authenticationService;
    }

    @GetMapping("/allProducts")
    public List<AllProductInfoDto> getAllProducts(){
        return productService.allProducts();
    }

    @GetMapping("/productNames")
    public List<ProductNameDto> getProductNames(){
        return productService.allProductNames();
    }

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
