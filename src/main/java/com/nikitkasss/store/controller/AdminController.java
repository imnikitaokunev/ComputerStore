package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.position.PositionInfoDto;
import com.nikitkasss.store.dto.user.SellerInfoDto;
import com.nikitkasss.store.service.PositionService;
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
public class AdminController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private UserService service;

    @Autowired
    private SellerService sellerService;

    //--------------Position------------------

    @RequestMapping(value="/addPosition", method = RequestMethod.GET)
    public String addPosition(Model model){
        PositionInfoDto dto = new PositionInfoDto();
        model.addAttribute("position", dto);
        return "admin/addPosition";
    }

    @RequestMapping(value="/addPosition", method = RequestMethod.POST)
    public String addPosition(@ModelAttribute PositionInfoDto dto, BindingResult errors, Model model) throws Exception {
        positionService.add(dto);
        return "redirect:/all/positions";
    }

    @RequestMapping(value = "/editPosition", method = RequestMethod.GET)
    public String editPosition(@RequestParam(value = "id", required = true) Long id, Model model) {
        PositionInfoDto dto = positionService.getById(id);
        model.addAttribute("position", dto);
        return "admin/editPosition";
    }

    @RequestMapping(value="/editPosition", method = RequestMethod.POST)
    public String editPosition(@ModelAttribute PositionInfoDto dto, BindingResult errors, Model model) throws Exception {
        positionService.edit(dto);
        return "redirect:/all/positions";
    }

    @RequestMapping(value = "/deletePosition", method = RequestMethod.GET)
    public String deletePosition(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            PositionInfoDto dto = positionService.getById(id);
            positionService.delete(dto);
        }
        List<PositionInfoDto> positions = positionService.allPositions();
        model.addAttribute("positions", positions);
        return "admin/deletePosition";
    }


    //-------------------Seller-------------------------

    @RequestMapping(value="/addSeller", method = RequestMethod.GET)
    public String addSeller(Model model){
        SellerInfoDto dto = new SellerInfoDto();
        model.addAttribute("seller", dto);
        return "admin/addSeller";
    }

    @RequestMapping(value="/addSeller", method = RequestMethod.POST)
    public String addSeller(@ModelAttribute SellerInfoDto dto, BindingResult errors, Model model) throws Exception {
        dto.setRoleName("ROLE_SELLER");
        if(service.getByUserName(dto.getUserName()) != null){
//            model.addAttribute("error", "Пользователь с таким логином уже существует.");
//            return "main/registration";
        }
        else {
            sellerService.add(dto);
        }
        return "redirect:/seller/sellers";
    }

    @RequestMapping(value="/sellers", method = RequestMethod.GET)
    public String allSellers(Model model){
        List<SellerInfoDto> sellers = sellerService.allSellers();
        model.addAttribute("sellers", sellers);
        return "admin/sellers";
    }


    @RequestMapping(value = "/findSeller", method = RequestMethod.GET)
    public String showSellers(@RequestParam (value = "search", required = false, defaultValue = "") String name, Model model) {
        List<SellerInfoDto> sellers =  sellerService.getSellersByParam(name);
        model.addAttribute("result", sellers);
        model.addAttribute("search", name);
        return "admin/findSeller";
    }

    @RequestMapping(value = "/editSeller", method = RequestMethod.GET)
    public String editSeller(@RequestParam(value = "id", required = true) Long id, Model model) {
        SellerInfoDto dto = sellerService.getById(id);
        model.addAttribute("seller", dto);
        return "admin/editSeller";
    }

    @RequestMapping(value="/editSeller", method = RequestMethod.POST)
    public String editSeller(@ModelAttribute SellerInfoDto dto, BindingResult errors, Model model) throws Exception {
        sellerService.edit(dto);
        return "redirect:/admin/sellers";
    }

    @RequestMapping(value = "/deleteSeller", method = RequestMethod.GET)
    public String deleteSeller(@RequestParam (value = "id", required = false) Long id,  Model model){
        if(id != null){
            SellerInfoDto dto = sellerService.getById(id);
            sellerService.delete(dto);
        }
        List<SellerInfoDto> sellers = sellerService.allSellers();
        model.addAttribute("sellers", sellers);
        return "admin/deleteSeller";
    }

    @RequestMapping(value="/deleteSeller", method = RequestMethod.POST)
    public String deleteSeller(@ModelAttribute SellerInfoDto dto, BindingResult errors, Model model) throws Exception {
        sellerService.delete(dto);
        return "redirect:/admin/sellers";
    }

}
