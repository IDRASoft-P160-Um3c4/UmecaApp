package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Degree;
import com.umeca.model.dto.humanResources.*;
import com.umeca.model.entities.humanReources.CourseAchievement;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.humanReources.EmployeeReference;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.Reference;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.reviewer.dto.ReferenceDto;
import com.umeca.repository.catalog.*;
import com.umeca.repository.humanResources.*;
import com.umeca.repository.reviewer.JobRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.humanResources.DigitalRecordService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/humanResources/digitalRecord/deleteCourse", method = RequestMethod.POST)
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
//                    add(r.get("id"));
//                    add(r.join("courseType").get("name").alias("ct"));
//                    add(r.get("name"));
//                    add(r.get("place"));
//                    add(r.get("schoolDocumentType").get("name").alias("dt"));
                    //todo
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idEmployee"))
                    return r.join("employee").get("id");
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

}
