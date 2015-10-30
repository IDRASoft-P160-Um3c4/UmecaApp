package com.umeca.service.device;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.IncidenceDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.timeAttendance.Incidence;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.humanResources.IncidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 10/30/2015.
 */
@Service
public class IncidenceServiceImpl implements IncidenceService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    IncidenceRepository incidenceRepository;

    @Override
    public ResponseMessage addIncidence(IncidenceDto incidenceDto) throws Exception {
        Employee employee = employeeRepository.findOne(incidenceDto.getIdEmployee());
        User user = userRepository.findOne(incidenceDto.getIdUser());
        Date date = Calendar.getInstance().getTime();
        Incidence incidence = new Incidence();

        incidence.setEventDate(date);
        incidence.setReason(incidenceDto.getReason());
        incidence.setCommentReason(incidence.getCommentReason());
        incidence.setComment(null);
        incidence.setClose(false);

        incidence.setResponsible(user);
        incidence.setEmployee(employee);

        incidenceRepository.save(incidence);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación de la falta ha sido registrado con éxito.");
        return resp;
    }

    @Override
    public ResponseMessage justifyIncidence(IncidenceDto incidenceDto) {

        User user = userRepository.findOne(incidenceDto.getIdUser());
        Date date = Calendar.getInstance().getTime();
        Incidence incidence = incidenceRepository.findOne(incidenceDto.getId());


        incidence.setComment(incidenceDto.getComment());
        incidence.setClose(true);
        incidence.setApproved(incidenceDto.isApproved());

        incidence.setResponsible(user);

        incidenceRepository.save(incidence);

        ResponseMessage resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La justificación de la falta ha sido registrado con éxito.");
        return resp;
    }
}