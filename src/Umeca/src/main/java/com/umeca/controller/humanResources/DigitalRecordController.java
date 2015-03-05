package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.EmployeeDto;
import com.umeca.model.entities.humanReources.Employee;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.humanResources.HumanResourcesService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView digitalRecordIndex() {
        ModelAndView model = new ModelAndView("/humanResources/digitalRecord/index");
        Gson gson = new Gson();
        model.addObject("listState",gson.toJson(stateRepository.getStatesByCountryAlpha2("MX")));
        return model;
    }

    @RequestMapping(value = "/humanResources/employees/doUpsertGeneralData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertGeneralData(@ModelAttribute EmployeeDto employeeDto) {

        ResponseMessage response = new ResponseMessage();
        try {
            //response = humanResourcesService.saveEmployee(employeeDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertPersonalData", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }


}
