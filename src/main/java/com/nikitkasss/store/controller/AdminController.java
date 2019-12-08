package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.BuyerDto;
import com.nikitkasss.store.dto.SellerDto;
import com.nikitkasss.store.service.BuyerService;
import com.nikitkasss.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BuyerService buyerService;

    @RequestMapping(value = "/blockBuyer", method = RequestMethod.GET)
    public String blockBuyer(@RequestParam(value = "id", required = false) Long id, Model model){
        if(id != null){
            BuyerDto dto = buyerService.getById(id);
            dto.setRoleName("ROLE_BLOCKED");
            buyerService.edit(dto);
        }
        List<BuyerDto> buyers = buyerService.allBuyers();
        model.addAttribute("buyers", buyers);
        return "admin/blockBuyer";
    }

    @RequestMapping(value = "/blockSeller", method = RequestMethod.GET)
    public String blockSeller(@RequestParam(value = "id", required = false) Long id, Model model){
        if(id != null){
            SellerDto dto = sellerService.getById(id);
            dto.setRoleName("ROLE_BLOCKED");
            sellerService.edit(dto);
        }
        List<SellerDto> sellers = sellerService.allSellers();
        model.addAttribute("sellers", sellers);
        return "admin/blockSeller";
    }
}
