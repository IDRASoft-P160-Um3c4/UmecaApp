package com.umeca.service.shared;

import com.umeca.model.entities.shared.LogCase;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    List<LogCase> addLog(String activityCode, Long idCase, Object detail);

    List<LogCase> addLog(String activityCode, Long idCase, Object detail, Long userId);

    String getLogCase(Long caseId);

    void fillModelLogCaseFile(Long id, ModelAndView model);

    void fillModelAccomplishmentFile(Long caseId, ModelAndView model);

    String generateResumeOfHearingFormat(Long idHearingFormat);
}