package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.JustifyDto;
import com.umeca.model.entities.timeAttendance.DelayJustification;
import com.umeca.repository.humanResources.AssistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 10/13/2015.
 */
@Service
public class AssistenceServiceImpl implements AssistenceService {

    @Autowired
    AssistenceRepository assistenceRepository;

    @Override
    @Transactional
    public ResponseMessage upsertDevice(JustifyDto justifyDto) {

        DelayJustification delayJustification = new DelayJustification();

        if (justifyDto.getId() == null || justifyDto.getId() == 0)
            delayJustification = assistenceRepository.findOne(justifyDto.getId());

        if (justifyDto.getId() != null || justifyDto.getId() > 0)
            delayJustification.setIdAttendanceLog(justifyDto.getId());
        delayJustification.setApproved(justifyDto.getJustified());
        delayJustification.setComment(justifyDto.getComment());

        assistenceRepository.save(delayJustification);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación del retardo ha sido registrado con éxito.");
        return resp;
    }
}