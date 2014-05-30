package com.umeca.service.reviewer;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Imputed;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MeetingService {
    ResponseMessage createMeeting(Imputed imputed);

    ModelAndView showMeeting(Long id);

    ModelAndView showLegalProcess(Long id);
}
