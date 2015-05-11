package com.umeca.service.detentionRecord;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.District;
import com.umeca.model.dto.detentionRecord.DetainedDto;
import com.umeca.model.entities.detentionRecord.Detained;
import com.umeca.repository.detentionRecord.DetainedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service("detentionlRecordService")
public class DetentionlRecordServiceImpl implements DetentionRecordService{

    @Autowired
    private DetainedRepository detainedRepository;

    private Detained fillDetained(DetainedDto dto){
        Detained detained = new Detained();
        detained.setInitDate(dto.getInitDate());
        detained.setInitTime(dto.getInitTime());
        detained.setName(dto.getName());
        detained.setLastNameP(dto.getLastNameP());
        detained.setLastNameM(dto.getLastNameM());
        detained.setIdFolder(dto.getIdFolder());
        detained.setAge(dto.getAge());
        detained.setInvestigationUnit(dto.getInvestigationUnit());
        detained.setCrime(dto.getCrime());
        District district = new District();
        district.setId(dto.getDistrictId());
        detained.setDistrict(district);
        detained.setRegisterTimestamp(Calendar.getInstance());
        detained.setIsProsecute(false);
        return detained;
    }

    @Transactional
    @Override
    public ResponseMessage saveDetained(DetainedDto dto){
        ResponseMessage resp;
        detainedRepository.save(fillDetained(dto));
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La información ha sido guardada con éxito.");
        return resp;
    }
}
