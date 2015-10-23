package com.umeca.service.managereval;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.managereval.Formulation;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


public interface FormulationService {
    ModelAndView upsert(Long id);
    ResponseMessage doUpsert(Formulation formulation);
    ResponseMessage confirmInformation(Long id);
    ModelAndView absenceReport(Long id);
    ModelAndView printAbsenceReport(Long id,HttpServletResponse response);
    ModelAndView showAttendanceRecord(Long id);
    ResponseMessage doAttendanceRecord(Long id, boolean attendance);

}
