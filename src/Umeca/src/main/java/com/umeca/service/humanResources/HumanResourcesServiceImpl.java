package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.EmployeeDto;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.repository.humanResources.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service("humanResourcesService")
public class HumanResourcesServiceImpl implements HumanResourcesService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public ResponseMessage saveEmployee(EmployeeDto employeeDto, HttpServletRequest request) {
        ResponseMessage resp = new ResponseMessage();
        Long count = employeeRepository.findExistEmployee(employeeDto.getName(), employeeDto.getLastNameP(), employeeDto.getLastNameM(), employeeDto.getBirthDate());
        if (count > 0) {
            resp.setHasError(true);
            resp.setMessage("Ya existe un empleado con los mismos datos. Revise la información e intente de nuevo.");
            return resp;
        }

        Employee newEmp = new Employee(employeeDto);

        employeeRepository.save(newEmp);
        employeeRepository.flush();
        resp.setHasError(false);
        resp.setMessage("El empleado ha sido registrado.");
        resp.setUrlToGo(request.getContextPath() + "/humanResources/digitalRecord/index.html?id=" + newEmp.getId());

        return resp;
    }
}
