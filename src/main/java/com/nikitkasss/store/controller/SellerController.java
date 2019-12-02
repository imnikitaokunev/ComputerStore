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
    public String register(@ModelAttribute AllProductInfoDto dto, BindingResult errors, Model model) throws Exception {
        productService.add(dto);
        return "redirect:/all/products";
    }

//    @GetMapping("/sellers")
//    public List<SellerInfoDto> getAllSellers(){
//        return sellerService.allSellers();
//    }
//
//    @PostMapping("/addSeller")
//    public void addSeller(@RequestBody SellerInfoDto dto) throws ConvertingException, IllegalArgumentException{
//        if(!(dto.getRoleName().equals(RoleEnum.ROLE_SELLER.getValue())) && !(dto.getRoleName().equals(RoleEnum.ROLE_ADMIN.getValue()))){
//            throw new IllegalArgumentException(dto.getRoleName() + " not supported.");
//        }
//        sellerService.add(dto);
//    }
//
//    @PostMapping("/editSeller")
//    public void editSeller(@RequestBody SellerInfoDto dto) throws ConvertingException{
//        sellerService.edit(dto);
//    }
//
//    @DeleteMapping("/deleteSeller/{id}")
//    public void deleteSeller(@PathVariable("id") Long id) throws ConvertingException, NoSuchEntityException{
//        SellerInfoDto dto = sellerService.getById(id);
//        sellerService.delete(dto);
//    }
//
//    @GetMapping("/findSeller/{id}")
//    public SellerInfoDto findSeller(@PathVariable("id") Long id) throws NoSuchEntityException{
//        return sellerService.getById(id);
//    }
}
