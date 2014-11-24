package com.umeca.service.shared;

import org.springframework.web.servlet.ModelAndView;

public interface LogCaseService {


    void fillgeneralDataLog(Long caseId, ModelAndView model);

}
