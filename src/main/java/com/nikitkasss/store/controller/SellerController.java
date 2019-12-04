package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.GeneralUserDto;
import com.nikitkasss.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;


    @RequestMapping(value="/sellers", method = RequestMethod.GET)
    public String allSellers(Model model){
        List<GeneralUserDto> sellers = sellerService.getGeneralSellersInfo();
        model.addAttribute("sellers", sellers);
        return "seller/sellers";
    }

    @RequestMapping(value = "/findSeller", method = RequestMethod.GET)
    public String showSellers(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<GeneralUserDto> sellers =  sellerService.getSellersGeneralInfoByParam(name);
        model.addAttribute("result", sellers);
        model.addAttribute("search", name);
        return "seller/findSeller";
    }

}
