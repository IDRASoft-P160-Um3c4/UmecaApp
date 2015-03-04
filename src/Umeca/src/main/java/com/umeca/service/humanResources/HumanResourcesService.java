package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.EmployeeDto;

public interface HumanResourcesService {

    ResponseMessage saveEmployee(EmployeeDto employeeDto);
}
