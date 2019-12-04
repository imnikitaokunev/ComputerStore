package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.BuyerDto;
import com.nikitkasss.store.service.BuyerService;
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
public class SellerBuyerController {

    @Autowired
    private BuyerService buyerService;


    @RequestMapping(value="/buyers", method = RequestMethod.GET)
    public String allBuyers(Model model){
        List<BuyerDto> buyers = buyerService.allBuyers();
        model.addAttribute("buyers", buyers);
        return "seller/buyers";
    }

    @RequestMapping(value="/addBuyer", method = RequestMethod.GET)
    public String addBuyer(Model model){
        return "redirect:/registration";
    }

    @RequestMapping(value = "/editBuyer", method = RequestMethod.GET)
    public String editBuyer(@RequestParam(value = "id", required = true) Long id, Model model) {
        BuyerDto dto = buyerService.getById(id);
        model.addAttribute("buyer", dto);
        return "seller/editBuyer";
    }

    @RequestMapping(value="/editBuyer", method = RequestMethod.POST)
    public String editBuyer(@ModelAttribute BuyerDto dto, BindingResult errors, Model model) throws Exception {
        buyerService.edit(dto);
        return "redirect:/seller/buyers";
    }

    @RequestMapping(value = "/findBuyer", method = RequestMethod.GET)
    public String showBuyers(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<BuyerDto> buyers =  buyerService.getBuyersByParam(name);
        model.addAttribute("result", buyers);
        model.addAttribute("search", name);
        return "seller/findBuyer";
    }

    @RequestMapping(value = "/deleteBuyer", method = RequestMethod.GET)
    public String deleteBuyer(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            BuyerDto dto = buyerService.getById(id);
            buyerService.delete(dto);
        }
        List<BuyerDto> buyers = buyerService.allBuyers();
        model.addAttribute("buyers", buyers);
        return "seller/deleteBuyer";
    }

    @RequestMapping(value="/deleteBuyer", method = RequestMethod.POST)
    public String deleteBuyer(@ModelAttribute BuyerDto dto, BindingResult errors, Model model) throws Exception {
        buyerService.delete(dto);
        return "redirect:/seller/buyers";
    }
}
