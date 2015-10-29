package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.BonusTimeDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.timeAttendance.BonusTime;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.humanResources.BonusTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 10/22/2015.
 */
@Service
public class BonusTimeServiceImpl implements BonusTimeService {
    @Autowired
    BonusTimeRepository bonusTimeRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public ResponseMessage upsertBonusTime(BonusTimeDto bonusTimeDto) {
        BonusTime bonusTime = new BonusTime();

        if (bonusTimeDto.getId() == null || bonusTimeDto.getId() == 0)
            bonusTime = bonusTimeRepository.findOne(bonusTimeDto.getId());

        if (bonusTimeDto.getId() != null || bonusTimeDto.getId() > 0)
            bonusTime.setIdAttendanceLog(bonusTimeDto.getId());

        User user = userRepository.findOne(bonusTimeDto.getIdUser());

        bonusTime.setResponsible(user);
        bonusTime.setApproved(bonusTimeDto.isApproved());
        bonusTime.setComment(bonusTimeDto.getComment());

        bonusTimeRepository.save(bonusTime);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La aprobación/rechazo de las horas extra ha sido registrado con éxito.");
        return resp;
    }
}
