package com.umeca.service.reviewer;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.FieldVerified;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/06/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ValuesOfMeetingService {
    List<FieldMeetingSource> getValueOfMeetingByCode(String code, Meeting m, FieldMeetingSource template);


    List<FieldMeetingSource> getValueByCode(String code, Meeting m, FieldMeetingSource template, Long idList);

    void createMeetingVirified(Long idCase, Verification verification);
}
