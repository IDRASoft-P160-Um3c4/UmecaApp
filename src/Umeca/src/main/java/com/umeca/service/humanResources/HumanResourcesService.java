package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.EmployeeDto;

import javax.servlet.http.HttpServletRequest;

public interface HumanResourcesService {

    ResponseMessage saveEmployee(EmployeeDto employeeDto, HttpServletRequest request);
}
