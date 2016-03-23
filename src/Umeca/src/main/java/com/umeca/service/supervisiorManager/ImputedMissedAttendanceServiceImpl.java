package com.umeca.service.supervisiorManager;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.supervisorManager.ImputedMissedAttendanceLog;
import com.umeca.repository.supervisorManager.ImputedMissedAttendanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImputedMissedAttendanceServiceImpl implements  ImputedMissedAttendanceService {

    @Autowired
    ImputedMissedAttendanceLogRepository imputedMissedAttendanceLogRepository;

    @Override
    public ResponseMessage doObsoleteImputedMissed(Long id) {
        ResponseMessage resp = new ResponseMessage();

        if (id != null) {
            ImputedMissedAttendanceLog imputed = imputedMissedAttendanceLogRepository.findOne(id);
            imputed.setIsObsolete(true);
            imputedMissedAttendanceLogRepository.save(imputed);
            resp.setHasError(false);
            resp.setMessage("El registro ha sido eliminado.");
        } else {
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error, intente nuevamente.");
        }

        return resp;

    }
}
