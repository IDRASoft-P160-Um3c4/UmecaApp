package com.umeca.service.shared;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.shared.Victim;
import org.springframework.web.servlet.ModelAndView;

public interface VictimService {
    ModelAndView upsertVictim(Long id, Long idCase);

    ResponseMessage doUpsertVictim(Victim victim, Long idCase, String type);

    ResponseMessage deleteVictim(Long id);
}
