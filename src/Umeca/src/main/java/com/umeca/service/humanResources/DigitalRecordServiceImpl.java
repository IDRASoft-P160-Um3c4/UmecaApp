package com.umeca.service.humanResources;

import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Degree;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.humanReources.*;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.CourseType;
import com.umeca.model.entities.shared.SchoolDocumentType;
import com.umeca.model.shared.Constants;
import com.umeca.repository.catalog.DocumentTypeRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.MaritalStatusRepository;
import com.umeca.repository.catalog.RegisterTypeRepository;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

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

    @Transactional
    public ResponseMessage saveGeneralData(EmployeeGeneralDataDto dataDto) {
        ResponseMessage resp = new ResponseMessage();
        Employee employee = employeeRepository.findOne(dataDto.getIdEmployee());


        employee.setName(dataDto.getName());
        employee.setLastNameP(dataDto.getLastNameP());
        employee.setLastNameM(dataDto.getLastNameM());

        try {
            employee.setBirthDate(sdf.parse(dataDto.getBirthDate()));
        } catch (Exception e) {

        }

        employee.setEmployeeGeneralData(fillGeneralData(dataDto));

        employee.setGender(dataDto.getGender());

        employeeRepository.save(employee);

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
        course.setIsTraining(courseDto.getIsTraining());

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
    public ResponseMessage deleteCourse(Long id) {
        ResponseMessage responseMessage = new ResponseMessage();
        courseAchievementRepository.delete(id);
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

}
