package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.*;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.entities.shared.UploadFileRequest;
import com.umeca.model.shared.Constants;
import com.umeca.repository.catalog.*;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.JobRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.humanResources.DigitalRecordService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
                    add(r.get("post"));
                    add(r.join("district").get("name"));
                    add(r.get("isObsolete"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("fullName"))
                    return r.get("name");
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

    @RequestMapping(value = "/humanResources/digitalRecord/index", method = RequestMethod.GET)
    public ModelAndView digitalRecordIndex(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/index");
        Gson gson = new Gson();

        //objetos para datos generales
        model.addObject("idEmployee", id);
        model.addObject("listState", gson.toJson(stateRepository.getStatesByCountryAlpha2("MX")));
        model.addObject("lstMaritalSt", gson.toJson(maritalStatusRepository.findAll()));
        model.addObject("lstDocType", gson.toJson(documentTypeRepository.findNotObsolete()));
        model.addObject("employeeName", employeeRepository.getEmployeeNameById(id));

        Long idPhoto = employeeRepository.getIdPhotoByIdEmployee(id);

        if (idPhoto != null && idPhoto > 0) {
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
    public ResponseMessage deleteCourse(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteCourse(id);
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
                    add(r.join("umecaPost").get("name").alias("postName"));
                    add(r.join("registerType").get("name").alias("regName"));
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
                else if (field.equals("umecaPost"))
                    return r.join("umecaPost").get("name");
                else if (field.equals("registerType"))
                    return r.join("registerType").get("name");
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
        model.addObject("lstUmecaPost", gson.toJson(umecaPostRepository.findNoObsolete()));
        model.addObject("lstDistrict", gson.toJson(districtRepository.findNoObsolete()));
        model.addObject("lstRegisterType", gson.toJson(registerTypeRepository.getAllRegisterType()));
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
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
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

        CourseAchievementDto t = new CourseAchievementDto();
        if (id != null)
            t = courseAchievementRepository.findTrainingDtoByIds(idEmployee, id);
        else {
            t.setIdEmployee(idEmployee);
        }

        model.addObject("training", gson.toJson(t));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertTraining", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertTraining(@ModelAttribute CourseAchievementDto courseDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveTraining(courseDto);
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

        IncidentDto i = new IncidentDto();
        if (id != null)
            i = incidentRepository.findIncidentDtoByIds(idEmployee, id);
        else {
            i.setIdEmployee(idEmployee);
        }

        model.addObject("incident", gson.toJson(i));
        model.addObject("lstIncidentType", gson.toJson(incidentTypeRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertIncident", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertIncident(@ModelAttribute IncidentDto incidentDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveIncident(incidentDto);
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
    public ResponseMessage deleteIncident(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteIncident(id);
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

        IncapacityDto in = new IncapacityDto();
        if (id != null)
            in = incapacityRepository.findIncapacityDtoByIds(idEmployee, id);
        else {
            in.setIdEmployee(idEmployee);
        }

        model.addObject("incapacity", gson.toJson(in));
        return model;
    }

    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertIncapacity", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage doUpsertIncapacity(@ModelAttribute IncapacityDto incapacityDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveIncapacity(incapacityDto);
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
    public ResponseMessage deleteIncapaciity(@RequestParam Long id) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteIncapacity(id);
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
            logException.Write(ex, this.getClass(), "doUploadFileGeneric", sharedUserService);
            resMsg.setHasError(true);
            resMsg.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        }

        return resMsg;
    }

}

