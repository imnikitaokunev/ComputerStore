package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.user.SellerInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.model.RoleEnum;
import com.nikitkasss.store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class SellerController {
    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("/addProduct")
    public String addProductPage(){
        return "addProduct";
    }

    @GetMapping("/sellers")
    public List<SellerInfoDto> getAllSellers(){
        return sellerService.allSellers();
    }

    @PostMapping("/addSeller")
    public void addSeller(@RequestBody SellerInfoDto dto) throws ConvertingException, IllegalArgumentException{
        if(!(dto.getRoleName().equals(RoleEnum.SELLER.getValue())) && !(dto.getRoleName().equals(RoleEnum.ADMIN.getValue()))){
            throw new IllegalArgumentException(dto.getRoleName() + " not supported.");
        }
        sellerService.add(dto);
    }

    @PostMapping("/editSeller")
    public void editSeller(@RequestBody SellerInfoDto dto) throws ConvertingException{
        sellerService.edit(dto);
    }

    @DeleteMapping("/deleteSeller/{id}")
    public void deleteSeller(@PathVariable("id") Long id) throws ConvertingException, NoSuchEntityException{
        SellerInfoDto dto = sellerService.getById(id);
        sellerService.delete(dto);
    }

    @GetMapping("/findSeller/{id}")
    public SellerInfoDto findSeller(@PathVariable("id") Long id) throws NoSuchEntityException{
        return sellerService.getById(id);
    }
}
