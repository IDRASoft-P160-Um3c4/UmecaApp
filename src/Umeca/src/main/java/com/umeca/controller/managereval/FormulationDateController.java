package com.umeca.controller.managereval;


import com.umeca.service.managereval.FormulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormulationDateController {

    @Autowired
    FormulationService formulationService;

    @RequestMapping(value = {"/managereval/formulationDate/index"}, method = RequestMethod.GET)
    public ModelAndView formulationDate() {
        ModelAndView mv = new ModelAndView("/managereval/formulationDate/index");
        return mv;
    }

    @RequestMapping(value = {"/managereval/formulationDate/upsert"}, method = RequestMethod.POST)
    public ModelAndView upsert(Long id){
        return formulationService.upsert(id);
    }

    @RequestMapping(value = {"/managereval/formulationDate/doupsert"}, method = RequestMethod.POST)
    public ModelAndView doupsert(){
        ModelAndView vm = new ModelAndView("/managereval/formulationDate/upsert");
        return vm;
    }




}
