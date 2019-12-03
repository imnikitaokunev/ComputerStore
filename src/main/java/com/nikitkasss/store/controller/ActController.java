package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.act.AllActInfoDto;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ActController {
    private ActService actService;

    @Autowired
    public ActController(ActService actService) {
        this.actService = actService;
    }

    @GetMapping("/acts")
    public List<AllActInfoDto> getAllActs(){
        return actService.allActs();
    }

    @PostMapping("/addAct")
    public void addAct(@RequestBody AllActInfoDto dto) throws ConvertingException, ParseException {
        actService.add(dto);
    }

    @PostMapping("/editAct")
    public void editAct(@RequestBody AllActInfoDto dto) throws ConvertingException, ParseException {
        actService.edit(dto);
    }

    @DeleteMapping("/deleteAct/{id}")
    public void deleteAct(@PathVariable("id") Long id) throws ConvertingException, NoSuchEntityException, ParseException {
        AllActInfoDto dto = actService.getById(id);
        actService.delete(dto);
    }

    @GetMapping("/findAct/{id}")
    public AllActInfoDto findAct(@PathVariable("id") Long id) throws NoSuchEntityException{
        return actService.getById(id);
    }

}
