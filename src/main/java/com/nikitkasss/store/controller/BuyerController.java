package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.BuyerInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.RoleEnum;
import com.nikitkasss.store.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class BuyerController {
    private BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/buyers")
    public List<BuyerInfoDto> getAllBuyers(){
        return buyerService.allBuyers();
    }

    @PostMapping("/addBuyer")
    public void addBuyer(@RequestBody BuyerInfoDto dto) throws ConvertingException{
        System.out.println("gdfgdfg");
        buyerService.add(dto);
    }

    @PostMapping("/editBuyer")
    public void editBuyer(@RequestBody BuyerInfoDto dto) throws ConvertingException{
        buyerService.edit(dto);
    }

    @DeleteMapping("/deleteBuyer/{id}")
    public void deleteBuyer(@PathVariable("id") Long id) throws ConvertingException{
        BuyerInfoDto dto = buyerService.getById(id);
        buyerService.delete(dto);
    }

    @GetMapping("/findBuyer/{id}")
    public BuyerInfoDto findBuyer(@PathVariable("id") Long id) throws NoSuchEntityException{
        return buyerService.getById(id);
    }
}
