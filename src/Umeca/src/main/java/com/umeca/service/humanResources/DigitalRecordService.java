package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.humanReources.CourseAchievement;
import com.umeca.model.entities.humanReources.EmployeeReference;
import com.umeca.model.entities.humanReources.EmployeeSchoolHistory;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.shared.Constants;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface DigitalRecordService {

    ResponseMessage saveEmployee(EmployeeDto employeeDto, HttpServletRequest request);

    ResponseMessage saveGeneralData(EmployeeGeneralDataDto dataDto);

    EmployeeGeneralDataDto fillGeneralDataDto(Long idEmployee);

    ResponseMessage saveEmployeeJob(JobDto jobDto);

    ResponseMessage deleteJob(Long id);

    ResponseMessage saveSchoolHisotry(EmployeeSchoolHistoryDto employeeSchoolHistoryDto);

    ResponseMessage saveCourse(CourseAchievementDto courseAchievementDto);

    ResponseMessage deleteCourse(Long id);

    ResponseMessage saveReference(EmployeeReferenceDto referenceDto);

    ResponseMessage deleteReference(Long id);

}
