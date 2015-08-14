package com.umeca.service.managereval;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.managereval.Formulation;
import org.springframework.web.servlet.ModelAndView;


public interface FormulationService {
    ModelAndView upsert(Long id);
    ResponseMessage doUpsert(Formulation formulation);
    ResponseMessage confirmInformation(Long id);
    ModelAndView absenceReport(Long id);
}
