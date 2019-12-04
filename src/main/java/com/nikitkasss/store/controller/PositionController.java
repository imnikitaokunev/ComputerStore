package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.PositionDto;
import com.nikitkasss.store.dto.PositionNameDto;
import com.nikitkasss.store.exception.NoSuchEntityException;
import com.nikitkasss.store.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/all")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value="/positions", method = RequestMethod.GET)
    public String getAllPositions(Model model){
        List<PositionDto> positions = positionService.allPositions();
        model.addAttribute("positions", positions);
        return "/position/positions";
    }

    @RequestMapping(value="/positionNames", method = RequestMethod.GET)
    public String getAllPositionNames(Model model){
        List<PositionNameDto> positions = positionService.allPositionNames();
        model.addAttribute("positions", positions);
        return "/position/positionNames";
    }

    @RequestMapping(value = "/findPosition", method = RequestMethod.GET)
    public String showPositionsByName(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<PositionDto> positions =  positionService.getPositionsByName(name);
        model.addAttribute("result", positions);
        model.addAttribute("search", name);
        return "position/findPosition";
    }
}
