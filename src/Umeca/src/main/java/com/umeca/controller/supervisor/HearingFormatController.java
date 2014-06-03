package com.umeca.controller.supervisor;

import com.umeca.model.catalog.Arrangement;
import com.umeca.repository.catalog.ArrangementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HearingFormatController  {

    @Qualifier("qArrangementRepository")
    @Autowired
    ArrangementRepository arrangementRepository;



    @RequestMapping(value = "/supervisor/hearingFormat", method = RequestMethod.GET)
    public ModelAndView hearingFormat() {
        ModelAndView model = new ModelAndView("/supervisor/hearingFormat");
        List<Arrangement> arrangements = arrangementRepository.findAll();

        return model;
    }
}
