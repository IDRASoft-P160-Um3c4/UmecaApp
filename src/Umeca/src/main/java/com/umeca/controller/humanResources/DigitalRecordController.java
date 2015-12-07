package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.humanReources.*;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.ReportList;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.JobRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.humanResources.DigitalRecordService;
import com.umeca.service.humanResources.StatisticHumanResourcesReportService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DigitalRecordController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DigitalRecordService digitalRecordService;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeSchoolHistoryRepository employeeSchoolHistoryRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private SchoolDocumentTypeRepository schoolDocumentTypeRepository;
    @Autowired
    private CourseAchievementRepository courseAchievementRepository;
    @Autowired
    private AcademicLevelRepository academicLevelRepository;
    @Autowired
    private EmployeeReferenceRepository employeeReferenceRepository;
    @Autowired
    private RelationshipRepository relationshipRepository;
    @Autowired
    private UmecaJobRepository umecaJobRepository;
    @Autowired
    private UmecaPostRepository umecaPostRepository;
    @Autowired
    private RegisterTypeRepository registerTypeRepository;
    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private IncidentTypeRepository incidentTypeRepository;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private IncapacityRepository incapacityRepository;
    @Autowired
    private UpDwFileGenericService upDwFileGenericService;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    StatisticHumanResourcesReportService statisticHumanResourcesReportService;

    @RequestMapping(value = "/humanResources/employees/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("lastNameP"));
                    add(r.get("lastNameM"));
                    add(r.join("district").get("name").alias("districtName"));
                    add(r.get("isObsolete"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("fullName"))
                    return r.get("name");
                if (field.equals("district"))
                    return r.join("district").get("name");
                return null;
            }
        }, Employee.class, EmployeeDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/employees/upsertEmployee", method = RequestMethod.POST)
    public ModelAndView upsertEmployee() {
        ModelAndView model = new ModelAndView("/humanResources/employees/upsert");
        model.addObject("lstDistrict", new Gson().toJson(districtRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/humanResources/employees/index", method = RequestMethod.GET)
    public String index() {
        return "/humanResources/employees/index";
    }

    @RequestMapping(value = "/humanResources/employees/doUpsertEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertEmployee(@ModelAttribute EmployeeDto employeeDto, HttpServletRequest request) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveEmployee(employeeDto, request);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertEmployee", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/employees/deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsoleteEmployee(@RequestParam(required = true) Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.doObsoleteEmployee(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertEmployee", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/index", method = RequestMethod.GET)
    public ModelAndView digitalRecordIndex(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/index");
        Gson gson = new Gson();

        //objetos para datos generales
        model.addObject("idEmployee", id);
        model.addObject("listState", gson.toJson(stateRepository.getStatesByCountryAlpha2("MX")));
        model.addObject("lstMaritalSt", gson.toJson(maritalStatusRepository.findAll()));
        model.addObject("lstDocType", gson.toJson(documentTypeRepository.findNotObsolete()));
        model.addObject("lstEmployeeSchedule", gson.toJson(employeeScheduleRepository.findAllEmployeeSchedule()));
        model.addObject("lstAssignedUsr", gson.toJson(sharedUserService.getUserRoles(null, null, id)));

        String employeeName = employeeRepository.getEmployeeNameById((id));

        model.addObject("employeeName", employeeName);

        Long idPhoto = employeeRepository.getIdPhotoByIdEmployee(id);

        if (idPhoto != null && idPhoto.longValue() > 0L) {
            UploadFileGeneric photo = upDwFileGenericService.getPathAndFilename(idPhoto);
            String path = new File(photo.getPath(), photo.getRealFileName()).toString();
            model.addObject("pathPhoto", path);
        }

        EmployeeGeneralDataDto dto = digitalRecordService.fillGeneralDataDto(id);
        model.addObject("generalData", gson.toJson(dto));
        if (dto.getIdAddres() != null)
            addressService.fillModelAddress(model, dto.getIdAddres());


        //objetos para historia escolar
        EmployeeSchoolHistoryDto schoolDto = employeeSchoolHistoryRepository.getEmpSchoolHistDtoByIdEmployee(id);
        if (schoolDto != null)
            model.addObject("schoolHistory", gson.toJson(schoolDto));
        else {
            schoolDto = new EmployeeSchoolHistoryDto();
            schoolDto.setIdEmployee(id);
            model.addObject("schoolHistory", gson.toJson(schoolDto));
        }
        model.addObject("lstAcLevel", gson.toJson(academicLevelRepository.findNoObsolete()));
        model.addObject("lstCourseType", gson.toJson(courseTypeRepository.findNoObsolete()));
        model.addObject("lstSchoolDocType", gson.toJson(schoolDocumentTypeRepository.findNoObsolete()));

        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertGeneralData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertGeneralData(@ModelAttribute EmployeeGeneralDataDto dataDto) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveGeneralData(dataDto);
        } catch (DataIntegrityViolationException e) {
            response.setHasError(true);
            response.setMessage("Algun(os) usuario(s) seleccionado(s) ya ha(n) sido asociado(s) a otro empleado, intente nuevamente.");
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertGeneralData", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listJob", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listJob(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("company"));
                    add(r.get("post"));
                    add(r.get("nameHead"));
                    add(r.get("start"));
                    add(r.get("end"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("company"))
                    return r.get("company");
                else if (field.equals("idEmployee"))
                    return r.join("employee").get("id");

                return null;
            }
        }, Job.class, JobDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertJob", method = RequestMethod.POST)
    public ModelAndView showUpsertJob(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/job/upsert");
        Gson gson = new Gson();
        JobDto j = new JobDto();
        if (id != null)
            j = jobRepository.getDtoJobByIds(idEmployee, id);
        else {
            j.setIdEmployee(idEmployee);
        }

        model.addObject("job", gson.toJson(j));

        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertJob(@ModelAttribute JobDto jobDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveEmployeeJob(jobDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertJob", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doDeleteJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doDeleteJob(@RequestParam(required = true) Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteJob(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doDeleteJob", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listSchoolCourses", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSchoolCourses(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);
        opts.extraFilters.add(new JqGridRulesModel("training", false, JqGridFilterModel.COMPARE_EQUAL));

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("courseType").get("name").alias("ct"));
                    add(r.get("name"));
                    add(r.get("place"));
                    add(r.get("schoolDocumentType").get("name").alias("dt"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("courseType"))
                    return r.join("courseType").get("name");
                else if (field.equals("documentType"))
                    return r.join("documentType").get("name");
                else if (field.equals("training"))
                    return r.get("isTraining");
                return null;
            }
        }, CourseAchievement.class, CourseAchievementDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertSchool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertSchool(@ModelAttribute EmployeeSchoolHistoryDto schoolDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveSchoolHisotry(schoolDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertSchool", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertCourse", method = RequestMethod.POST)
    public ModelAndView showUpsertCourse(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/school/upsert");
        Gson gson = new Gson();

        CourseAchievementDto c = new CourseAchievementDto();
        if (id != null)
            c = courseAchievementRepository.findCourseAchievmentDtoByIds(idEmployee, id);
        else {
            c.setIdEmployee(idEmployee);
        }

        model.addObject("course", gson.toJson(c));
        model.addObject("lstCourseType", gson.toJson(courseTypeRepository.findNoObsolete()));
        model.addObject("lstSchoolDocType", gson.toJson(schoolDocumentTypeRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertCourse(@ModelAttribute CourseAchievementDto courseDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveCourse(courseDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertSchool", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = {"/humanResources/digitalRecord/deleteCourse", "/humanResources/digitalRecord/deleteTraining"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteCourse(HttpServletRequest request, @RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteCourse(request, id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertSchool", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listReferences", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReferences(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                    add(r.get("timeAgo"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("name"))
                    return r.get("name");
                else if (field.equals("relationship"))
                    return r.join("relationship").get("name");
                return null;
            }
        }, EmployeeReference.class, EmployeeReferenceDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertReference", method = RequestMethod.POST)
    public ModelAndView showUpsertReference(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/references/upsert");
        Gson gson = new Gson();

        EmployeeReferenceDto r = new EmployeeReferenceDto();
        if (id != null)
            r = employeeReferenceRepository.findReferenceDtoByIds(idEmployee, id);
        else {
            r.setIdEmployee(idEmployee);
        }

        model.addObject("reference", gson.toJson(r));
        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertReference", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertReference(@ModelAttribute EmployeeReferenceDto referenceDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveReference(referenceDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertReference", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/deleteReference", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteReference(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteReference(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteReference", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listUmecaJob", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listUmecaJobs(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("nameHead"));
                    add(r.get("salary"));
                    add(r.get("startDate"));
                    add(r.get("endDate"));
                    add(r.join("district").get("name").alias("disName"));
                    add(r.join("role").get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("nameHead"))
                    return r.get("nameHead");
                else if (field.equals("salary"))
                    return r.get("salary");
                else if (field.equals("startDate"))
                    return r.get("startDate");
                else if (field.equals("endDate"))
                    return r.get("endDate");
                else if (field.equals("district"))
                    return r.join("district").get("name");
                else if (field.equals("role"))
                    return r.join("role").get("description");
                return null;
            }
        }, UmecaJob.class, UmecaJobDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertUmecaJob", method = RequestMethod.POST)
    public ModelAndView showUpsertUmecaJob(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/umecaHistory/umecaJob/upsertUmecaJob");
        Gson gson = new Gson();

        UmecaJobDto uj = new UmecaJobDto();
        if (id != null)
            uj = umecaJobRepository.findUmecaJobDtoByIds(idEmployee, id);
        else {
            uj.setIdEmployee(idEmployee);
        }

        model.addObject("umecaJob", gson.toJson(uj));
        model.addObject("lstRole", gson.toJson(roleRepository.findByExcludeCode(new ArrayList<String>() {{
            add(Constants.ROLE_ADMIN);
        }})));
        model.addObject("lstDistrict", gson.toJson(districtRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertUmecaJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertUmecaJob(@ModelAttribute UmecaJobDto umecaJobDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveUmecaJob(umecaJobDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertUmecaJob", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doDeleteUmecaJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteUmecaJob(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteUmecaJob(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteReference", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listTraining", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listTraining(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);
        opts.extraFilters.add(new JqGridRulesModel("training", true, JqGridFilterModel.COMPARE_EQUAL));

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("place"));
                    add(r.get("duration"));
                    add(r.get("start"));
                    add(r.get("end"));
                    add(r.join("file").get("id").alias("fileId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                if (field.equals("training"))
                    return r.get("isTraining");
                else if (field.equals("name"))
                    return r.get("name");
                else if (field.equals("place"))
                    return r.get("place");
                else if (field.equals("duration"))
                    return r.get("duration");
                else if (field.equals("start"))
                    return r.get("start");
                else if (field.equals("end"))
                    return r.get("end");
                return null;
            }
        }, CourseAchievement.class, CourseAchievementDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertTraining", method = RequestMethod.POST)
    public ModelAndView showUpsertTraining(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/umecaHistory/training//upsertTraining");
        Gson gson = new Gson();
        Boolean canSave = false;
        CourseAchievementDto t = new CourseAchievementDto();
        if (id != null) {
            t = courseAchievementRepository.findTrainingDtoByIds(idEmployee, id);
            canSave = true;
        } else {
            t.setIdEmployee(idEmployee);
        }

        model.addObject("training", gson.toJson(t));
        model.addObject("canSaveT", canSave);
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertTraining", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertTraining(HttpServletRequest request, @ModelAttribute CourseAchievementDto courseDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveTraining(request, courseDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertTraining", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listIncident", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listIncident(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("incidentType").get("name"));
                    add(r.get("reason"));
                    add(r.get("incidentDate"));
                    add(r.get("comments"));
                    add(r.join("file").get("id").alias("fileId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("incidentType"))
                    return r.join("incidentType").get("name");
                else if (field.equals("reason"))
                    return r.get("reason");
                else if (field.equals("incidentDate"))
                    return r.get("incidentDate");
                return null;
            }
        }, Incident.class, IncidentDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertIncident", method = RequestMethod.POST)
    public ModelAndView showUpsertIncident(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/umecaHistory/incidents/upsertIncident");
        Gson gson = new Gson();
        Boolean canSave = false;

        IncidentDto i = new IncidentDto();
        if (id != null) {
            i = incidentRepository.findIncidentDtoByIds(idEmployee, id);
            canSave = true;
        } else {
            i.setIdEmployee(idEmployee);
        }

        model.addObject("incident", gson.toJson(i));
        model.addObject("lstIncidentType", gson.toJson(incidentTypeRepository.findNoObsolete()));
        model.addObject("canSaveI", canSave);
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertIncident", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertIncident(HttpServletRequest request, @ModelAttribute IncidentDto incidentDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveIncident(request, incidentDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertIncident", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/deleteIncident", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteIncident(HttpServletRequest request, @RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteIncident(request, id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteIncident", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listVacation", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listVacation(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("start"));
                    add(r.get("end"));
                    add(r.get("comments"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("name"))
                    return r.get("name");
                else if (field.equals("start"))
                    return r.get("start");
                else if (field.equals("end"))
                    return r.get("end");
                return null;
            }
        }, Vacation.class, VacationDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertVacation", method = RequestMethod.POST)
    public ModelAndView showUpsertVacation(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/vacation/upsert");
        Gson gson = new Gson();

        VacationDto v = new VacationDto();
        if (id != null)
            v = vacationRepository.findVacationDtoByIds(idEmployee, id);
        else {
            v.setIdEmployee(idEmployee);
        }

        model.addObject("vacation", gson.toJson(v));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertVacation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertVacation(@ModelAttribute VacationDto vacationDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveVacation(vacationDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertVacation", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/deleteVacation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteVacation(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteVacation(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteVacation", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listIncapacity", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listIncapacity(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("description"));
                    add(r.get("start"));
                    add(r.get("end"));
                    add(r.get("comments"));
                    add(r.join("file").get("id").alias("fileId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
                else if (field.equals("description"))
                    return r.get("description");
                else if (field.equals("start"))
                    return r.get("start");
                else if (field.equals("end"))
                    return r.get("end");
                return null;
            }
        }, Incapacity.class, IncapacityDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertIncapacity", method = RequestMethod.POST)
    public ModelAndView showUpsertIncapacity(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/incapacity/upsert");
        Gson gson = new Gson();
        Boolean canSave = false;

        IncapacityDto in = new IncapacityDto();
        if (id != null) {
            in = incapacityRepository.findIncapacityDtoByIds(idEmployee, id);
            canSave = true;
        } else {
            in.setIdEmployee(idEmployee);
        }

        model.addObject("incapacity", gson.toJson(in));
        model.addObject("canSaveIn", canSave);
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertIncapacity", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertIncapacity(HttpServletRequest request, @ModelAttribute IncapacityDto incapacityDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveIncapacity(request, incapacityDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertIncapacity", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/deleteIncapacity", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteIncapaciity(HttpServletRequest request, @RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteIncapacity(request, id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteIncapaciity", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/listAttachment", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAttachments(@RequestParam(required = true) final String id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idEmployee",
                new ArrayList<String>() {{
                    add(id);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Attachment, UploadFileGeneric> joinFile = r.join("genericFile");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinFile.get("id").alias("fileId"));
                    add(r.get("attachmentName"));
                    add(joinFile.get("creationTime"));
                    add(r.get("attachmentDescription"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                final javax.persistence.criteria.Join<Attachment, Employee> joinEmpl = r.join("employee");
                final javax.persistence.criteria.Join<Attachment, UploadFileGeneric> joinFile = r.join("genericFile");

                if (field.equals("idEmployee"))
                    return joinEmpl.get("id");
                else if (field.equals("attachmentName"))
                    return r.get("attachmentName");
                else if (field.equals("creationTime"))
                    return joinFile.get("creationTime");
                else if (field.equals("attachmentDescription"))
                    return r.get("attachmentDescription");
                return null;
            }
        }, Attachment.class, AttachmentDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/upsertAttachment", method = RequestMethod.POST)
    public ModelAndView showUpsertAttachment(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/attachment/upsert");
        Gson gson = new Gson();
        Boolean canSave;
        AttachmentDto att = new AttachmentDto();
        if (id != null) {
            att = attachmentRepository.findAttachmentDtoByIds(idEmployee, id);
            canSave = true;
        } else {
            att.setIdEmployee(idEmployee);
            canSave = false;
        }

        model.addObject("attachment", gson.toJson(att));
        model.addObject("canSave", gson.toJson(canSave));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/uploadAttachment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUploadFileGeneric(@ModelAttribute UploadFileRequest uploadRequest,
                                        MultipartHttpServletRequest request) {
        ResponseMessage resMsg = new ResponseMessage();
        try {
            resMsg = digitalRecordService.doUploadGeneric(uploadRequest, request, logException);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUploadFileGeneric", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertAttachment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertAttachment(@ModelAttribute AttachmentDto attachmentDto, HttpServletRequest request) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveAttachment(request, attachmentDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertAttachment", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/deleteAttachment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteAttachment(HttpServletRequest request, @RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteAttachment(request, id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteAttachment", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/digitalRecord/uploadPhoto", method = RequestMethod.POST)
    public ModelAndView showUpsertPhoto(@RequestParam(required = true) Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/upsertPhoto");
        model.addObject("idEmployee", idEmployee);
        model.addObject("employeeName", employeeRepository.getEmployeeNameById(idEmployee));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUploadPhoto", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUploadFileGenericPhoto(@ModelAttribute UploadFileRequest uploadRequest,
                                             MultipartHttpServletRequest request) {
        ResponseMessage resMsg = new ResponseMessage();
        try {
            resMsg = digitalRecordService.doUploadGenericPhoto(uploadRequest, request, logException);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUploadFileGenericPhoto", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/digitalRecordSummary", method = RequestMethod.GET)
    public ModelAndView generateFileAllSources(@RequestParam(required = true) Long id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/digitalRecordSummary");
        String contextPath = request.getSession().getServletContext().getRealPath("");
        DigitalRecordSummaryDto summary = digitalRecordService.fillDigitalRecordSummary(id, contextPath, logException);
        model.addObject("summary", summary);
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/getUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    String searchUsr(@RequestParam(required = true) String str) {
        String param = str + "%";
        List<SelectList> lst = sharedUserService.getUserRoles(null, param, null);
        return new Gson().toJson(lst);

    }




    @RequestMapping(value = "/humanResources/digitalRecord/getOverTime", method = RequestMethod.GET)
    public ModelAndView showReport(String initDate, String endDate, Long idEmployee) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/overtime/showOverTimeReport");
        Gson gson = new Gson();
        String measure = "x";
        String extraData = null;
        Long total = Long.valueOf(0);
        try {

            List<ReportList> data;
            data = statisticHumanResourcesReportService.getData(initDate, endDate, "STHRR_3", Long.valueOf(3), Long.valueOf(1), idEmployee);

            measure = "Horas";
            extraData = employeeRepository.getEmployeeNameById(idEmployee);

            model.addObject("idEmployee", idEmployee);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", gson.toJson(data));
            model.addObject("extraData", extraData);
            model.addObject("measure", measure);

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "HumanResourcesReport", sharedUserService);
            model.addObject("initDate", initDate.toString());
            model.addObject("endDate", endDate.toString());
            model.addObject("total", total);
            model.addObject("data", null);
            model.addObject("measure", measure);
        }
        return model;
    }






}

