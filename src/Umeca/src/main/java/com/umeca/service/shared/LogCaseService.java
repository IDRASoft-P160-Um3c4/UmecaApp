package com.umeca.service.shared;

import org.springframework.web.servlet.ModelAndView;

public interface LogCaseService {


    void fillgeneralDataLog(Long caseId, ModelAndView model);

    void fillLogCase(Long id, ModelAndView model);

    /**
     *Add register in LogCase
     * @param activityCode code of activity to register in logCase, use ConstantsLogCase
     * @param idCase id of case to add register
     * @param detail Long - id of entity modified, id to add details of logCase
     *               String - text to put in detail information of logCase
     */
    void addLog(String activityCode, Long idCase, Object detail);

    String getLogCase(Long caseId);
}