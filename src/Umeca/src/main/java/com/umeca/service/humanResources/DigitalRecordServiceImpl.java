package com.umeca.service.humanResources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.*;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.*;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.Schedule;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.CourseType;
import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.DocumentTypeRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.MaritalStatusRepository;
import com.umeca.repository.catalog.RegisterTypeRepository;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.JobRepository;
import com.umeca.repository.shared.SystemSettingRepository;
import com.umeca.repository.shared.UploadFileGenericRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("digitalRecordService")
public class DigitalRecordServiceImpl implements DigitalRecordService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private LocationRepository locationRepository;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private EmployeeGeneralDataRepository employeeGeneralDataRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RegisterTypeRepository registerTypeRepository;
    @Autowired
    private EmployeeSchoolHistoryRepository employeeSchoolHistoryRepository;
    @Autowired
    private CourseAchievementRepository courseAchievementRepository;
    @Autowired
    private EmployeeReferenceRepository employeeReferenceRepository;
    @Autowired
    private UmecaJobRepository umecaJobRepository;
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private IncapacityRepository incapacityRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UpDwFileGenericService upDwFileGenericService;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UploadFileGenericRepository uploadFileGenericRepository;
    @Autowired
    private SystemSettingRepository systemSettingRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    @Transactional
    public ResponseMessage saveEmployee(EmployeeDto employeeDto, HttpServletRequest request) {
        ResponseMessage resp;

        resp = this.validateExistEmployee(employeeDto.getName(), employeeDto.getLastNameP(), employeeDto.getLastNameM(), employeeDto.getBirthDate(), null);
        if (resp != null) {
            return resp;
        }

        Employee newEmp = new Employee(employeeDto);

        employeeRepository.save(newEmp);
        employeeRepository.flush();
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("El empleado ha sido registrado con éxito.");
        resp.setUrlToGo(request.getContextPath() + "/humanResources/digitalRecord/index.html?id=" + newEmp.getId());
        return resp;
    }

    private ResponseMessage validateExistEmployee(String name, String lastNameP, String lastNameM, Date bthDate, Long idEmployee) {
        ResponseMessage resp = new ResponseMessage();
        Long count = -1L;

        if (idEmployee != null) {
            count = employeeRepository.findExistEmployeeWithId(name.toLowerCase(), lastNameP.toLowerCase(), lastNameM.toLowerCase(), bthDate, idEmployee);
        } else {
            count = employeeRepository.findExistEmployee(name.toLowerCase(), lastNameP.toLowerCase(), lastNameM.toLowerCase(), bthDate);
        }

        if (count > 0) {
            resp.setHasError(true);
            resp.setMessage("Ya existe un empleado con los mismos datos. Revise la información e intente de nuevo.");
            return resp;
        }
        return null;
    }

    @Transactional
    public ResponseMessage saveGeneralData(EmployeeGeneralDataDto dataDto) {
        ResponseMessage resp = null;
        Employee employee = employeeRepository.findOne(dataDto.getIdEmployee());
        Date bthDt = null;

        try {
            bthDt = sdf.parse(dataDto.getBirthDate());
            resp = this.validateExistEmployee(dataDto.getName(), dataDto.getLastNameP(), dataDto.getLastNameM(), bthDt, dataDto.getIdEmployee());
            if (resp != null) {
                return resp;
            }
        } catch (Exception e) {
        }

        employee.setName(dataDto.getName());
        employee.setLastNameP(dataDto.getLastNameP());
        employee.setLastNameM(dataDto.getLastNameM());
        employee.setEmployeeGeneralData(fillGeneralData(dataDto));
        employee.setGender(dataDto.getGender());

        EmployeeSchedule es = new EmployeeSchedule();
        es.setId(dataDto.getEmpSchId());
        employee.setEmployeeSchedule(es);

        List<User> usrs = new Gson().fromJson(dataDto.getAssignedUsr(), new TypeToken<List<User>>() {
        }.getType());
        employee.getUsers().clear();
        employee.getUsers().addAll(usrs);

        employeeRepository.save(employee);
        resp = new ResponseMessage();
        resp.setHasError(false);
        resp.setMessage("La información ha sido guardada con éxito.");

        return resp;
    }

    public EmployeeGeneralDataDto fillGeneralDataDto(Long idEmployee) {
        return employeeGeneralDataRepository.getDataDtoByEmployeeId(idEmployee);
    }

    private EmployeeGeneralData fillGeneralData(EmployeeGeneralDataDto dataDto) {

        EmployeeGeneralData employeeGeneralData = employeeGeneralDataRepository.getDataByEmployeeId(dataDto.getIdEmployee());

        if (employeeGeneralData == null)
            employeeGeneralData = new EmployeeGeneralData();

        employeeGeneralData.setIdentificationDesc(dataDto.getDocumentDesc());
        employeeGeneralData.setEmail(dataDto.getEmail());
        employeeGeneralData.setPhone(dataDto.getPhone());
        employeeGeneralData.setCertificate(dataDto.getCertificate());
        employeeGeneralData.setDependents(dataDto.getDependents());
        employeeGeneralData.setNoEmployee(dataDto.getNoEmployee());
        employeeGeneralData.setNoImss(dataDto.getNoImss());
        employeeGeneralData.setAppointment(dataDto.getAppointment());
        employeeGeneralData.setIsCommissioner(dataDto.getCommissioner());

        try {
            employeeGeneralData.setDatePublicServ(sdf.parse(dataDto.getDatePublicServ()));
            employeeGeneralData.setDateEntryUmeca(sdf.parse(dataDto.getDateEntryUmeca()));
        } catch (Exception e) {
        }

        employeeGeneralData.setMaritalStatus(maritalStatusRepository.findOne(dataDto.getMaritalStatusId()));
        employeeGeneralData.setIdentification(documentTypeRepository.findOne(dataDto.getDocumentId()));

        Address address = employeeGeneralData.getAddress();

        if (address == null)
            address = new Address();

        address.setStreet(dataDto.getStreet());
        address.setInnNum(dataDto.getInnNum());
        address.setOutNum(dataDto.getOutNum());
        address.setLat(dataDto.getLat());
        address.setLng(dataDto.getLng());
        address.setLocation(locationRepository.findOne(dataDto.getLocation().getId()));
        address.setAddressString(address.toString());
        employeeGeneralData.setAddress(address);

        return employeeGeneralData;
    }

    private Job fillJob(JobDto jobDto) {

        Job j;

        if (jobDto.getId() != null)
            j = jobRepository.findOne(jobDto.getId());
        else
            j = new Job();

        j.setCompany(jobDto.getCompany());
        j.setPost(jobDto.getPost());
        j.setNameHead(jobDto.getNameHead());
        j.setSalaryWeek(jobDto.getSalaryWeek());
        j.setPhone(jobDto.getPhone());

        try {
            j.setStart(sdf.parse(jobDto.getStart()));
            j.setEnd(sdf.parse(jobDto.getEnd()));
        } catch (Exception e) {

        }

        j.setRegisterType(registerTypeRepository.findOne(Constants.REGYSTER_TYPE_PREVIOUS));
        j.setEmployee(employeeRepository.findOne(jobDto.getIdEmployee()));

        return j;
    }

    @Transactional
    public ResponseMessage saveEmployeeJob(JobDto jobDto) {
        Job job = fillJob(jobDto);
        ResponseMessage resp = new ResponseMessage();
        jobRepository.save(job);
        resp.setHasError(false);
        resp.setMessage("El trabajo ha sido guardado con éxito");
        return resp;
    }

    @Transactional
    public ResponseMessage deleteJob(Long id) {
        ResponseMessage resp = new ResponseMessage();
        jobRepository.delete(id);
        resp.setHasError(false);
        resp.setMessage("Se ha eliminado el trabajo con éxito.");
        return resp;
    }


    @Transactional
    public ResponseMessage saveSchoolHisotry(EmployeeSchoolHistoryDto employeeSchoolHistoryDto) {
        ResponseMessage resp = new ResponseMessage();
        EmployeeSchoolHistory employeeSchoolHistory = fillSchoolHistory(employeeSchoolHistoryDto);
        employeeSchoolHistoryRepository.save(employeeSchoolHistory);
        resp.setHasError(false);
        resp.setMessage("El último grado de estudios ha sido guardado con éxito");
        return resp;
    }

    private EmployeeSchoolHistory fillSchoolHistory(EmployeeSchoolHistoryDto schoolDto) {

        if (schoolDto.getIdEmployee() == null)
            return null;

        EmployeeSchoolHistory schoolHistory = employeeSchoolHistoryRepository.getEmpSchoolHistByIdEmployee(schoolDto.getIdEmployee());

        if (schoolHistory == null) {
            schoolHistory = new EmployeeSchoolHistory();
            Employee e = new Employee();
            e.setId(schoolDto.getIdEmployee());
            schoolHistory.setEmployee(e);
        }

        schoolHistory.setSchool(schoolDto.getName());
        Degree d = new Degree();
        d.setId(schoolDto.getDegreeId());
        schoolHistory.setDegree(d);
        SchoolDocumentType doc = new SchoolDocumentType();
        doc.setId(schoolDto.getDocumentId());
        schoolHistory.setSchoolDocumentType(doc);
        schoolHistory.setSpecDocType(schoolDto.getDocSpec());

        return schoolHistory;
    }

    private CourseAchievement fillCourseAchievement(CourseAchievementDto courseDto) {
        CourseAchievement course = new CourseAchievement();

        if (courseDto.getId() != null)
            course = courseAchievementRepository.findCourseAchievmentByIds(courseDto.getIdEmployee(), courseDto.getId());
        else {
            Employee employee = new Employee();
            employee.setId(courseDto.getIdEmployee());
            course.setEmployee(employee);
        }

        course.setName(courseDto.getName());
        course.setPlace(courseDto.getPlace());
        try {
            course.setStart(sdf.parse(courseDto.getStart()));
            course.setEnd(sdf.parse(courseDto.getEnd()));
        } catch (Exception e) {
        }
        course.setIsTraining(false);

        CourseType ct = new CourseType();
        ct.setId(courseDto.getIdCourseType());
        course.setCourseType(ct);
        course.setSpecCourseType(courseDto.getSpecCourseType());

        SchoolDocumentType dc = new SchoolDocumentType();
        dc.setId(courseDto.getIdDocType());
        course.setSchoolDocumentType(dc);
        course.setSpecDocType(courseDto.getSpecDocType());

        return course;
    }

    @Transactional
    public ResponseMessage saveCourse(CourseAchievementDto courseDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        courseAchievementRepository.save(fillCourseAchievement(courseDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El curso ha sido guardado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage deleteCourse(HttpServletRequest request, Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        CourseAchievement c = courseAchievementRepository.findOne(id);
        UploadFileGeneric f = c.getFile();
        if (f != null) {
            String path = request.getSession().getServletContext().getRealPath("");
            upDwFileGenericService.deleteFile(path, f, userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }
        courseAchievementRepository.delete(c);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El curso ha sido eliminado con éxito");
        return responseMessage;
    }

    private EmployeeReference fillReference(EmployeeReferenceDto referenceDto) {
        EmployeeReference reference = new EmployeeReference();

        if (referenceDto.getId() != null)
            reference = employeeReferenceRepository.findReferenceByIds(referenceDto.getIdEmployee(), referenceDto.getId());
        else {
            Employee e = new Employee();
            e.setId(referenceDto.getIdEmployee());
            reference.setEmployee(e);
        }

        reference.setName(referenceDto.getName());
        reference.setAge(referenceDto.getAge());
        reference.setPhone(referenceDto.getPhone());
        Relationship r = new Relationship();
        r.setId(referenceDto.getIdRelationship());
        reference.setRelationship(r);
        reference.setSpecRelationship(referenceDto.getSpecRelationship());
        reference.setTimeAgo(referenceDto.getTimeAgo());

        return reference;
    }

    @Transactional
    public ResponseMessage saveReference(EmployeeReferenceDto referenceDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        employeeReferenceRepository.save(fillReference(referenceDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("La referencia ha sido guardada con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage deleteReference(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        employeeReferenceRepository.delete(id);
        responseMessage.setHasError(false);
        responseMessage.setMessage("La referencia ha sido eliminado con éxito");
        return responseMessage;
    }

    private UmecaJob fillUmecaJob(UmecaJobDto jobDto) {
        UmecaJob job = new UmecaJob();

        if (jobDto.getId() != null) {
            job = umecaJobRepository.findUmecaJobByIds(jobDto.getIdEmployee(), jobDto.getId());
        } else {
            Employee e = new Employee();
            e.setId(jobDto.getIdEmployee());
            job.setEmployee(e);
        }

        job.setNameHead(jobDto.getNameHead());
        job.setSalary(jobDto.getSalary());

        try {
            if (jobDto.getStartDate() != null && jobDto.getStartDate() != "")
                job.setStartDate(sdf.parse(jobDto.getStartDate()));

            if (jobDto.getEndDate() != null && jobDto.getEndDate() != "")
                job.setEndDate(sdf.parse(jobDto.getEndDate()));
            else
                job.setEndDate(null);

        } catch (Exception e) {

        }

        Role r = new Role();
        r.setId(jobDto.getIdRole());
        job.setRole(r);

        District d = new District();
        d.setId(jobDto.getIdDistrict());
        job.setDistrict(d);

        return job;
    }

    @Transactional
    public ResponseMessage saveUmecaJob(UmecaJobDto jobDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        umecaJobRepository.save(fillUmecaJob(jobDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El trabajo ha sido guardado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage deleteUmecaJob(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        umecaJobRepository.delete(id);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El trabajo ha sido eliminado con éxito");
        return responseMessage;
    }

    private CourseAchievement fillTraining(HttpServletRequest request, CourseAchievementDto trainingDto) {
        CourseAchievement training = new CourseAchievement();

        if (trainingDto.getId() != null)
            training = courseAchievementRepository.findCourseAchievmentByIds(trainingDto.getIdEmployee(), trainingDto.getId());
        else {
            Employee e = new Employee();
            e.setId(trainingDto.getIdEmployee());
            training.setEmployee(e);
        }

        if (training.getFile() != null && training.getFile().getId() != trainingDto.getFileId()) {//si ya tiene uno, verifico que no sea el mismo
            String path = request.getSession().getServletContext().getRealPath("");
            //si es difenrente elimino el anterior
            upDwFileGenericService.deleteFile(path, training.getFile(), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        UploadFileGeneric fileGeneric = uploadFileGenericRepository.findOne(trainingDto.getFileId());
        fileGeneric.setObsolete(false);

        training.setFile(fileGeneric);
        training.setName(trainingDto.getName());
        training.setPlace(trainingDto.getPlace());
        training.setDuration(trainingDto.getDuration());
        training.setIsTraining(true);

        try {
            training.setStart(sdf.parse(trainingDto.getStart()));
            training.setEnd(sdf.parse(trainingDto.getEnd()));
        } catch (Exception e) {
        }

        return training;
    }

    @Transactional
    public ResponseMessage saveTraining(HttpServletRequest request, CourseAchievementDto trainingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        courseAchievementRepository.save(fillTraining(request, trainingDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El curso ha sido guardado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage saveIncident(HttpServletRequest request, IncidentDto incidentDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        incidentRepository.save(fillIncident(request, incidentDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El incidente ha sido guardado con éxito");
        return responseMessage;
    }

    private Incident fillIncident(HttpServletRequest request, IncidentDto incidentDto) {
        Incident incident = new Incident();
        if (incidentDto.getId() != null)
            incident = incidentRepository.findIncidentByIds(incidentDto.getIdEmployee(), incidentDto.getId());
        else {
            Employee e = new Employee();
            e.setId(incidentDto.getIdEmployee());
            incident.setEmployee(e);
        }

        try {
            incident.setIncidentDate(sdf.parse(incidentDto.getIncidentDate()));
        } catch (Exception e) {
        }

        if (incident.getFile() != null && incident.getFile().getId() != incidentDto.getFileId()) {//si ya tiene uno, verifico que no sea el mismo
            String path = request.getSession().getServletContext().getRealPath("");
            //si es difenrente elimino el anterior
            upDwFileGenericService.deleteFile(path, incident.getFile(), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        UploadFileGeneric fileGeneric = uploadFileGenericRepository.findOne(incidentDto.getFileId());
        fileGeneric.setObsolete(false);
        incident.setFile(fileGeneric);

        IncidentType it = new IncidentType();
        it.setId(incidentDto.getIdIncidentType());

        incident.setIncidentType(it);
        incident.setSpecIncidentType(incidentDto.getSpecIncidentType());
        incident.setReason(incidentDto.getReason());
        incident.setComments(incidentDto.getComments());

        return incident;
    }

    @Transactional
    public ResponseMessage deleteIncident(HttpServletRequest request, Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        Incident i = incidentRepository.findOne(id);
        UploadFileGeneric f = i.getFile();
        if (f != null) {
            String path = request.getSession().getServletContext().getRealPath("");
            upDwFileGenericService.deleteFile(path, f, userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }
        incidentRepository.delete(i);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El incidente ha sido eliminado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage saveVacation(VacationDto vacationDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        vacationRepository.save(fillVacation(vacationDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El periodo vacacional ha sido guardado con éxito");
        return responseMessage;
    }

    private Vacation fillVacation(VacationDto vacationDto) {
        Vacation vacation = new Vacation();
        if (vacationDto.getId() != null)
            vacation = vacationRepository.findVacationByIds(vacationDto.getIdEmployee(), vacationDto.getId());
        else {
            Employee e = new Employee();
            e.setId(vacationDto.getIdEmployee());
            vacation.setEmployee(e);
        }

        try {
            vacation.setStart(sdf.parse(vacationDto.getStart()));
            vacation.setEnd(sdf.parse(vacationDto.getEnd()));
        } catch (Exception e) {
        }

        vacation.setName(vacationDto.getName());
        vacation.setComments(vacationDto.getComments());

        return vacation;
    }

    @Transactional
    public ResponseMessage deleteVacation(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        vacationRepository.delete(id);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El periodo vacacional ha sido eliminado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage saveIncapacity(HttpServletRequest request, IncapacityDto incapacityDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        incapacityRepository.save(fillIncapacity(request, incapacityDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("La incapacidad ha sido guardada con éxito");
        return responseMessage;
    }

    private Incapacity fillIncapacity(HttpServletRequest request, IncapacityDto incapacityDto) {
        Incapacity incapacity = new Incapacity();
        if (incapacityDto.getId() != null)
            incapacity = incapacityRepository.findIncapacityByIds(incapacityDto.getIdEmployee(), incapacityDto.getId());
        else {
            Employee e = new Employee();
            e.setId(incapacityDto.getIdEmployee());
            incapacity.setEmployee(e);
        }

        try {
            incapacity.setStart(sdf.parse(incapacityDto.getStart()));
            incapacity.setEnd(sdf.parse(incapacityDto.getEnd()));
        } catch (Exception e) {
        }

        if (incapacity.getFile() != null && incapacity.getFile().getId() != incapacityDto.getFileId()) {//si ya tiene uno, verifico que no sea el mismo
            String path = request.getSession().getServletContext().getRealPath("");
            //si es difenrente elimino el anterior
            upDwFileGenericService.deleteFile(path, incapacity.getFile(), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        UploadFileGeneric fileGeneric = uploadFileGenericRepository.findOne(incapacityDto.getFileId());
        fileGeneric.setObsolete(false);
        incapacity.setFile(fileGeneric);

        incapacity.setDescription(incapacityDto.getDescriptionIn());
        incapacity.setDocName(incapacityDto.getDocName());
        incapacity.setComments(incapacityDto.getComments());

        return incapacity;
    }


    @Transactional
    public ResponseMessage deleteIncapacity(HttpServletRequest request, Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        Incapacity in = incapacityRepository.findOne(id);
        UploadFileGeneric f = in.getFile();
        if (f != null) {
            String path = request.getSession().getServletContext().getRealPath("");
            upDwFileGenericService.deleteFile(path, f, userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }
        incapacityRepository.delete(in);
        responseMessage.setHasError(false);
        responseMessage.setMessage("La incapacidad ha sido eliminada con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage saveAttachment(HttpServletRequest request, AttachmentDto attachmentDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        attachmentRepository.save(fillAttachment(request, attachmentDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El archivo ha sido guardado con éxito");
        return responseMessage;
    }

    private Attachment fillAttachment(HttpServletRequest request, AttachmentDto attachmentDto) {
        Attachment att = new Attachment();

        if (attachmentDto.getId() != null) {
            att = attachmentRepository.findAttachmentByIds(attachmentDto.getIdEmployee(), attachmentDto.getId());
        } else {
            Employee e = new Employee();
            e.setId(attachmentDto.getIdEmployee());
            att.setEmployee(e);
        }

        if (att.getGenericFile() != null && att.getGenericFile().getId() != attachmentDto.getFileId()) {//si ya tiene uno, verifico que no se el mismo
            String path = request.getSession().getServletContext().getRealPath("");
            //si es difenrente elimino el anterior
            upDwFileGenericService.deleteFile(path, att.getGenericFile(), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        UploadFileGeneric newFile = uploadFileGenericRepository.findOne(attachmentDto.getFileId());
        newFile.setObsolete(false);
        att.setGenericFile(newFile);
        att.setAttachmentName(attachmentDto.getAttachmentName());
        att.setAttachmentDescription(attachmentDto.getAttachmentDescription());

        return att;
    }

    @Transactional
    public ResponseMessage deleteAttachment(HttpServletRequest request, Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        Attachment att = attachmentRepository.findOne(id);
        String path = request.getSession().getServletContext().getRealPath("");
        upDwFileGenericService.deleteFile(path, att.getGenericFile(), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        attachmentRepository.delete(att);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El archivo ha sido eliminado con éxito");
        return responseMessage;
    }

    @Transactional
    public ResponseMessage doUploadGeneric(UploadFileRequest uploadRequest,
                                           MultipartHttpServletRequest request, SharedLogExceptionService logException) {
        ResponseMessage resMsg = new ResponseMessage();

        Long userId = sharedUserService.GetLoggedUserId();

        Iterator<String> itr = request.getFileNames();

        if (upDwFileGenericService.isValidRequestFile(itr, resMsg) == false) {
            return resMsg;
        }

        UploadFileGeneric file = new UploadFileGeneric();

        MultipartFile mpf = request.getFile(itr.next());
        if (upDwFileGenericService.isValidExtension(mpf, file, resMsg) == false)
            return resMsg;

        User user = new User();
        user.setId(userId);
        upDwFileGenericService.fillUploadFileGeneric(mpf, file, uploadRequest, user);

        //valida nombre archivo
        if (upDwFileGenericService.hasAvailability(file, resMsg, userId) == false)
            return resMsg;

        String path = request.getSession().getServletContext().getRealPath("");
        path = new File(path, file.getPath()).toString();

        if (upDwFileGenericService.saveOnDiskUploadFile(mpf, path, file, resMsg, logException, sharedUserService) == false)
            return resMsg;

        upDwFileGenericService.save(file);

        resMsg.setMessage("El archivo " + file.getFileName() + " fue subido de forma correcta. Por favor presione el botón guardar para finalizar el proceso.");
        resMsg.setHasError(false);
        if (uploadRequest.getCloseUploadFile() != null && uploadRequest.getCloseUploadFile()) {

            resMsg.setUrlToGo("close");
            resMsg.setReturnData(file.getPath() + "/" + file.getRealFileName());
        } else {
            resMsg.setReturnData(file.getId());
        }

        return resMsg;
    }

    @Transactional
    public ResponseMessage doUploadGenericPhoto(UploadFileRequest uploadRequest, MultipartHttpServletRequest request, SharedLogExceptionService logException) {

        ResponseMessage resMsg = new ResponseMessage();
        Long userId = sharedUserService.GetLoggedUserId();

        Iterator<String> itr = request.getFileNames();

        if (upDwFileGenericService.isValidRequestFile(itr, resMsg) == false) {
            return resMsg;
        }

        UploadFileGeneric file = new UploadFileGeneric();

        MultipartFile mpf = request.getFile(itr.next());
        if (upDwFileGenericService.isValidExtensionByCode(mpf, file, resMsg, Constants.CODE_FILE_TYPE_IMG) == false)
            return resMsg;

        User user = new User();
        user.setId(userId);
        upDwFileGenericService.fillUploadFileGeneric(mpf, file, uploadRequest, user);

        //valida nombre archivo
        if (upDwFileGenericService.hasPhotoAvailability(file, resMsg, userId, uploadRequest.getIdEmployee()) == false)
            return resMsg;

        String path = request.getSession().getServletContext().getRealPath("");
        path = new File(path, file.getPath()).toString();

        if (upDwFileGenericService.saveOnDiskUploadFile(mpf, path, file, resMsg, logException, sharedUserService) == false)
            return resMsg;

        file.setObsolete(false);
        upDwFileGenericService.save(file);

        Long idOldPhoto = employeeRepository.getIdPhotoByIdEmployee(uploadRequest.getIdEmployee());

        if (idOldPhoto != null) {
            path = request.getSession().getServletContext().getRealPath("");
            upDwFileGenericService.deleteFile(path, uploadFileGenericRepository.findOne(idOldPhoto), userRepository.findOne(sharedUserService.GetLoggedUserId()));
        }

        Employee e = employeeRepository.findOne(uploadRequest.getIdEmployee());
        e.setPhoto(file);
        employeeRepository.save(e);

        resMsg.setHasError(false);
        resMsg.setUrlToGo("close");
        resMsg.setReturnData(file.getPath() + "/" + file.getRealFileName());
        return resMsg;
    }

    @Transactional
    public ResponseMessage doObsoleteEmployee(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        Employee e = employeeRepository.findOne(id);
        e.setIsObsolete(true);
        e.setDateObsolete(new Date());
        User u = new User();
        u.setId(sharedUserService.GetLoggedUserId());
        e.setUserObsolete(u);
        employeeRepository.saveAndFlush(e);
        responseMessage.setHasError(false);
        responseMessage.setMessage("El empleado ha sido eliminado con éxito");
        return responseMessage;
    }

    public DigitalRecordSummaryDto fillDigitalRecordSummary(Long idEmployee, String contextPath, SharedLogExceptionService logException) {
        DigitalRecordSummaryDto summary = new DigitalRecordSummaryDto();
        EmployeeGeneralDataDto gd = employeeGeneralDataRepository.getSummaryDataByEmployeeId(idEmployee);
        summary.setGeneralData(gd);
        List<JobDto> jobs = jobRepository.getJobsDtoByIdEmployee(idEmployee);
        summary.setJobs(jobs);
        List<CourseAchievementDto> courses = courseAchievementRepository.findCoursesDtoByIdEmployee(idEmployee);
        summary.setCourses(courses);

        UploadFileGeneric photoFile = uploadFileGenericRepository.getPathAndFilenamePhotoByIdEmployee(idEmployee);
        String photoStr = "";

        if (photoFile != null) {
            String tempImgPath = systemSettingRepository.findOneValue(Constants.SYSTEM_SETTINGS_ARCHIVE, Constants.SYSTEM_SETTINGS_ARCHIVE_EMPLOYEE_PHOTO_TEMPORAL_PATH_TO_SAVE);
            String uuid = UUID.randomUUID().toString();
            String tempFilePath = new File(contextPath, tempImgPath + "\\" + uuid).toString();
            if (upDwFileGenericService.doTemporalPhoto(tempFilePath, contextPath, photoFile, logException, sharedUserService) != false) {
                summary.setPhoto(tempImgPath + "\\" + uuid + "\\" + photoFile.getRealFileName());
            }

        }
        return summary;
    }

    @Transactional
    public ResponseMessage saveEmployeeSchedule(EmployeeScheduleDto scheduleDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        employeeScheduleRepository.save(fillEmployeeSchedule(scheduleDto));
        responseMessage.setHasError(false);
        responseMessage.setMessage("El horario de trabajo ha sido guardado con éxito");
        return responseMessage;
    }

    private EmployeeSchedule fillEmployeeSchedule(EmployeeScheduleDto scheduleDto) {
        EmployeeSchedule schedule = new EmployeeSchedule();
        Gson gson = new Gson();

        if (scheduleDto.getId() != null) {
            schedule = employeeScheduleRepository.findOne(scheduleDto.getId());

            for (ScheduleDay day : schedule.getDays()) {
                scheduleDayRepository.delete(day);
            }
            schedule.getDays().clear();
        }

        List<ScheduleDayDto> scheduleDtos = gson.fromJson(scheduleDto.getScheduleDays(), new TypeToken<List<ScheduleDayDto>>() {
        }.getType());

        List<ScheduleDay> finalDays = new ArrayList<>();

        for (ScheduleDayDto dto : scheduleDtos) {
            if (dto.getIsSel() == true) {
                dto.setStartI(dto.convStrToInt(dto.getStart()));
                dto.setEndI(dto.convStrToInt(dto.getEnd()));
                ScheduleDay day = new ScheduleDay();

                day.setDayId(dto.getDayId());
                day.setName(dto.getName());
                day.setStart(dto.getStartI());
                day.setEnd(dto.getEndI());
                day.setEmployeeSchedule(schedule);
                finalDays.add(day);
            }
        }

        schedule.setName(scheduleDto.getName());
        schedule.setDescription(scheduleDto.getDescription());
        schedule.setIsObsolete(false);
        schedule.setDays(finalDays);
        return schedule;
    }

    @Transactional
    public ResponseMessage deleteEmployeeSchedule(Long id) {
        ResponseMessage resp = new ResponseMessage();

        if (id != null) {
            EmployeeSchedule schedule = employeeScheduleRepository.findOne(id);
            schedule.setIsObsolete(true);
//            for (ScheduleDay day : schedule.getDays()) {
//                scheduleDayRepository.delete(day);
//            }
//            schedule.getDays().clear();
            employeeScheduleRepository.save(schedule);
            resp.setHasError(false);
            resp.setMessage("El horario ha sido eliminado con éxito.");
        } else {
            resp.setHasError(true);
            resp.setMessage("Ha ocurrido un error, intente nuevamente.");
        }

        return resp;
    }
}
