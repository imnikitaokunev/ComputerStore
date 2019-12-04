package com.nikitkasss.store.controller;

import com.nikitkasss.store.dto.ActDto;
import com.nikitkasss.store.service.ActService;
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
@RequestMapping(value = "/seller")
public class ActController {

    @Autowired
    private ActService actService;

    @RequestMapping(value="/acts", method = RequestMethod.GET)
    public String getAllActs(Model model){
        List<ActDto> acts = actService.allActs();
        model.addAttribute("acts", acts);
        return "/act/acts";
    }

    @RequestMapping(value = "/findAct", method = RequestMethod.GET)
    public String showActsByName(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<ActDto> acts =  actService.getActsByParam(name);
        model.addAttribute("result", acts);
        model.addAttribute("search", name);
        return "act/findAct";
    }

    @RequestMapping(value="/addAct", method = RequestMethod.GET)
    public String addAct(Model model){
        ActDto dto = new ActDto();
        model.addAttribute("act", dto);
        return "act/addAct";
    }

    @RequestMapping(value="/addAct", method = RequestMethod.POST)
    public String addAct(@ModelAttribute ActDto dto, BindingResult errors, Model model) throws Exception {
        actService.add(dto);
        return "redirect:/seller/acts";
    }

    @RequestMapping(value = "/editAct", method = RequestMethod.GET)
    public String editAct(@RequestParam(value = "id", required = true) Long id, Model model) {
        ActDto dto = actService.getById(id);
        model.addAttribute("act", dto);
        return "act/editAct";
    }

    @RequestMapping(value = "/deleteAct", method = RequestMethod.GET)
    public String deleteAct(@RequestParam (value = "id", required = false) Long id,  Model model) throws Exception {
        if(id != null){
            ActDto dto = actService.getById(id);
            System.out.println("Before del");
            actService.delete(dto);
            System.out.println("after del");
        }
        List<ActDto> acts = actService.allActs();
        model.addAttribute("acts", acts);
        return "act/deleteAct";
    }

    @RequestMapping(value="/editAct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute ActDto dto, BindingResult errors, Model model) throws Exception {
        actService.edit(dto);
        return "redirect:/seller/acts";
    }

}
