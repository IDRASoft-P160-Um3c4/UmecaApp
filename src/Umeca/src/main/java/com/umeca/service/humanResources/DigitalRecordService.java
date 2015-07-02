package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface DigitalRecordService {

    ResponseMessage saveEmployee(EmployeeDto employeeDto, HttpServletRequest request);

    ResponseMessage saveGeneralData(EmployeeGeneralDataDto dataDto);

    EmployeeGeneralDataDto fillGeneralDataDto(Long idEmployee);

    ResponseMessage saveEmployeeJob(JobDto jobDto);

    ResponseMessage deleteJob(Long id);

    ResponseMessage saveSchoolHisotry(EmployeeSchoolHistoryDto employeeSchoolHistoryDto);

    ResponseMessage saveCourse(CourseAchievementDto courseAchievementDto);

    ResponseMessage deleteCourse(HttpServletRequest request, Long id);

    ResponseMessage saveReference(EmployeeReferenceDto referenceDto);

    ResponseMessage deleteReference(Long id);

    ResponseMessage saveUmecaJob(UmecaJobDto umecaJobDto);

    ResponseMessage deleteUmecaJob(Long id);

    ResponseMessage saveTraining(HttpServletRequest request, CourseAchievementDto trainingDto);

    ResponseMessage saveIncident(HttpServletRequest request, IncidentDto incidentDto);

    ResponseMessage deleteIncident(HttpServletRequest request, Long id);

    ResponseMessage saveVacation(VacationDto vacationDto);

    ResponseMessage deleteVacation(Long id);

    ResponseMessage saveIncapacity(HttpServletRequest request, IncapacityDto incapacityDto);

    ResponseMessage deleteIncapacity(HttpServletRequest request, Long id);

    ResponseMessage saveAttachment(HttpServletRequest request, AttachmentDto attachmentDto);

    ResponseMessage deleteAttachment(HttpServletRequest request, Long id);

    ResponseMessage doUploadGeneric(UploadFileRequest uploadRequest, MultipartHttpServletRequest request, SharedLogExceptionService logException);

    ResponseMessage doUploadGenericPhoto(UploadFileRequest uploadRequest, MultipartHttpServletRequest request, SharedLogExceptionService logException);

    ResponseMessage doObsoleteEmployee(Long id);

    DigitalRecordSummaryDto fillDigitalRecordSummary(Long idEmployee, String contextPath, SharedLogExceptionService logException);

    ResponseMessage saveEmployeeSchedule(EmployeeScheduleDto scheduleDto);

    ResponseMessage deleteEmployeeSchedule(Long employeeScheduleId);

}
