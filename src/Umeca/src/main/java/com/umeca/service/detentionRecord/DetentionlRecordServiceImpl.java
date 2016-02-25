package com.umeca.service.detentionRecord;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.District;
import com.umeca.model.dto.detentionRecord.DetainedDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.detentionRecord.Detained;
import com.umeca.repository.detentionRecord.DetainedRepository;
import com.umeca.service.account.SharedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service("detentionlRecordService")
public class DetentionlRecordServiceImpl implements DetentionRecordService {

    @Autowired
    private DetainedRepository detainedRepository;
    @Autowired
    private SharedUserService sharedUserService;

    private Detained fillDetained(DetainedDto dto) {
        Detained detained = new Detained();

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(dto.getInitTime().getTime());

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(dto.getInitDate().getTime());

        Calendar summ = Calendar.getInstance();
        summ.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR),time.get(Calendar.MINUTE),time.get(Calendar.SECOND));

        detained.setInitDate(dto.getInitDate());
        detained.setInitTime(dto.getInitTime());
        detained.setInitTimestamp(summ);

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
        detained.setIsVisibleDetentionRecord(true);
        detained.setIsVisibleUmeca(true);
        return detained;
    }

    @Transactional
    @Override
    public ResponseMessage saveDetained(DetainedDto dto) {
        ResponseMessage resp;
        detainedRepository.save(fillDetained(dto));
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La información ha sido guardada con éxito.");
        return resp;
    }

    @Transactional
    @Override
    public ResponseMessage doProsecute(Long id) {
        ResponseMessage resp;
        Detained d = detainedRepository.findOne(id);

        d.setIsProsecute(true);
        d.setTimestampProsecute(Calendar.getInstance().getInstance());

        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        d.setUserProsecute(u);

        detainedRepository.save(d);
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La información ha sido guardada con éxito.");
        return resp;
    }


    @Transactional
    @Override
    public ResponseMessage doNotVisibleDetentionRecord(Long id) {
        ResponseMessage resp;
        Detained d = detainedRepository.findOne(id);
        d.setIsVisibleDetentionRecord(false);
        detainedRepository.save(d);
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("Se ha ocultado el caso de manera correcta.");
        return resp;
    }

    @Transactional
    @Override
    public ResponseMessage doNotVisibleUmeca(Long id) {
        ResponseMessage resp;
        Detained d = detainedRepository.findOne(id);
        d.setIsVisibleUmeca(false);
        detainedRepository.save(d);
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("Se ha ocultado el caso de manera correcta.");
        return resp;
    }
}

