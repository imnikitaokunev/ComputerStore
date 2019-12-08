package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.SellerDto;
import com.nikitkasss.store.service.SellerService;
import com.nikitkasss.store.service.UserService;
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
@RequestMapping("/admin")
public class AdminSellerController {

    @Autowired
    private UserService service;

    @Autowired
    private SellerService sellerService;


    @RequestMapping(value="/addSeller", method = RequestMethod.GET)
    public String addSeller(Model model){
        SellerDto dto = new SellerDto();
        model.addAttribute("seller", dto);
        return "admin/addSeller";
    }

    @RequestMapping(value="/addSeller", method = RequestMethod.POST)
    public String addSeller(@ModelAttribute SellerDto dto, BindingResult errors, Model model) throws Exception {
        if(service.getByUserName(dto.getUserName()) == null){
            sellerService.add(dto);
        }
        return "redirect:/seller/sellers";
    }

    @RequestMapping(value="/sellers", method = RequestMethod.GET)
    public String allSellers(Model model){
        List<SellerDto> sellers = sellerService.allSellers();
        model.addAttribute("sellers", sellers);
        return "admin/sellers";
    }


    @RequestMapping(value = "/findSeller", method = RequestMethod.GET)
    public String showSellers(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<SellerDto> sellers =  sellerService.getSellersByParam(name);
        model.addAttribute("result", sellers);
        model.addAttribute("search", name);
        return "admin/findSeller";
    }

    @RequestMapping(value = "/editSeller", method = RequestMethod.GET)
    public String editSeller(@RequestParam(value = "id", required = true) Long id, Model model) {
        SellerDto dto = sellerService.getById(id);
        model.addAttribute("seller", dto);
        return "admin/editSeller";
    }

    @RequestMapping(value="/editSeller", method = RequestMethod.POST)
    public String editSeller(@ModelAttribute SellerDto dto, BindingResult errors, Model model) throws Exception {
        sellerService.edit(dto);
        return "redirect:/admin/sellers";
    }

    @RequestMapping(value = "/deleteSeller", method = RequestMethod.GET)
    public String deleteSeller(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            SellerDto dto = sellerService.getById(id);
            sellerService.delete(dto);
        }
        List<SellerDto> sellers = sellerService.allSellers();
        model.addAttribute("sellers", sellers);
        return "admin/deleteSeller";
    }

    @RequestMapping(value="/deleteSeller", method = RequestMethod.POST)
    public String deleteSeller(@ModelAttribute SellerDto dto, BindingResult errors, Model model) throws Exception {
        sellerService.delete(dto);
        return "redirect:/admin/sellers";
    }
}
