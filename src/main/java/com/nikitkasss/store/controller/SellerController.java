package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.product.AllProductInfoDto;
import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.dto.user.GeneralUserInfoDto;
import com.nikitkasss.store.dto.user.SellerInfoDto;
import com.nikitkasss.store.service.BuyerService;
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
    private BuyerService buyerService;

    //------------------Product---------------------

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

    @RequestMapping(value="/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute AllProductInfoDto dto, BindingResult errors, Model model) throws Exception {
        productService.edit(dto);
        return "redirect:/all/products";
    }

    //----------------Buyer--------------------

    @RequestMapping(value="/buyers", method = RequestMethod.GET)
    public String allBuyers(Model model){
        List<BuyerInfoDto> buyers = buyerService.allBuyers();
        model.addAttribute("buyers", buyers);
        return "seller/buyers";
    }

    @RequestMapping(value="/addBuyer", method = RequestMethod.GET)
    public String addBuyer(Model model){
        return "redirect:/registration";
    }

    @RequestMapping(value = "/editBuyer", method = RequestMethod.GET)
    public String editBuyer(@RequestParam (value = "id", required = true) Long id, Model model) {
        BuyerInfoDto dto = buyerService.getById(id);
        model.addAttribute("buyer", dto);
        return "seller/editBuyer";
    }

    @RequestMapping(value="/editBuyer", method = RequestMethod.POST)
    public String editBuyer(@ModelAttribute BuyerInfoDto dto, BindingResult errors, Model model) throws Exception {
        buyerService.edit(dto);
        return "redirect:/seller/buyers";
    }

    @RequestMapping(value = "/findBuyer", method = RequestMethod.GET)
    public String showBuyers(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<BuyerInfoDto> buyers =  buyerService.getBuyersByParam(name);
        model.addAttribute("result", buyers);
        model.addAttribute("search", name);
        return "seller/findBuyer";
    }

//    @GetMapping("/findBuyer/{id}")
//    public BuyerInfoDto findProduct(@PathVariable("id") Long id) throws NoSuchEntityException {
//        return buyerService.getById(id);
//    }

    @RequestMapping(value = "/deleteBuyer", method = RequestMethod.GET)
    public String deleteBuyer(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            BuyerInfoDto dto = buyerService.getById(id);
            buyerService.delete(dto);
        }
        List<BuyerInfoDto> buyers = buyerService.allBuyers();
        model.addAttribute("buyers", buyers);
        return "seller/deleteBuyer";
    }

    @RequestMapping(value="/deleteBuyer", method = RequestMethod.POST)
    public String deleteBuyer(@ModelAttribute BuyerInfoDto dto, BindingResult errors, Model model) throws Exception {
        buyerService.delete(dto);
        return "redirect:/seller/buyers";
    }


    //--------------------Seller---------------------

    @RequestMapping(value="/sellers", method = RequestMethod.GET)
    public String allSellers(Model model){
        List<GeneralUserInfoDto> sellers = sellerService.getGeneralSellersInfo();
        model.addAttribute("sellers", sellers);
        return "seller/sellers";
    }


    @RequestMapping(value = "/findSeller", method = RequestMethod.GET)
    public String showSellers(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<GeneralUserInfoDto> sellers =  sellerService.getSellersGeneralInfoByParam(name);
        model.addAttribute("result", sellers);
        model.addAttribute("search", name);
        return "seller/findSeller";
    }

}
