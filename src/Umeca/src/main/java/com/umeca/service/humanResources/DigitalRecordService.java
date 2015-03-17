package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.reviewer.dto.JobDto;

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

    ResponseMessage saveUmecaJob(UmecaJobDto umecaJobDto);

    ResponseMessage deleteUmecaJob(Long id);

    ResponseMessage saveTraining(CourseAchievementDto trainingDto);

    ResponseMessage saveIncident(IncidentDto incidentDto);

    ResponseMessage deleteIncident(Long id);

    ResponseMessage saveVacation(VacationDto vacationDto);

    ResponseMessage deleteVacation(Long id);

    ResponseMessage saveIncapacity(IncapacityDto incapacityDto);

    ResponseMessage deleteIncapacity(Long id);

}
