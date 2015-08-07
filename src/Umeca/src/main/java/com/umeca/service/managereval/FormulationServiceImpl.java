package com.umeca.service.managereval;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.managereval.Formulation;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;




@Service("formulationService")
public class FormulationServiceImpl implements FormulationService{


    @Override
    public ModelAndView upsert(Long id) {
        ModelAndView model =  new ModelAndView("/managereval/formulationDate/upsert");
        return model;
    }

    @Override
    public ResponseMessage doUpsert(Formulation formulation) {
        return null;
    }
}
