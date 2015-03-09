package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.dto.humanResources.EmployeeDto;
import com.umeca.model.dto.humanResources.EmployeeGeneralDataDto;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.model.entities.reviewer.Job;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.repository.catalog.DocumentTypeRepository;
import com.umeca.repository.catalog.MaritalStatusRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.reviewer.JobRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.humanResources.HumanResourcesService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private HumanResourcesService humanResourcesService;
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
            response = humanResourcesService.saveEmployee(employeeDto, request);
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
        model.addObject("idEmployee", id);
        model.addObject("listState", gson.toJson(stateRepository.getStatesByCountryAlpha2("MX")));
        model.addObject("lstMaritalSt", gson.toJson(maritalStatusRepository.findAll()));
        model.addObject("lstDocType", gson.toJson(documentTypeRepository.findNotObsolete()));
        EmployeeGeneralDataDto dto = humanResourcesService.fillGeneralDataDto(id);
        model.addObject("generalData", gson.toJson(dto));

        if (dto.getIdAddres() != null)
            addressService.fillModelAddress(model, dto.getIdAddres());

        return model;
    }


    @RequestMapping(value = "/humanResources/digitalRecord/doUpsertGeneralData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertGeneralData(@ModelAttribute EmployeeGeneralDataDto dataDto) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = humanResourcesService.saveGeneralData(dataDto);
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
            response = humanResourcesService.saveEmployeeJob(jobDto);
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
            response = humanResourcesService.deleteJob(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doDeleteJob", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

}
