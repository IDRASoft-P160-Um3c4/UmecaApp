package com.umeca.controller.timeAttendance;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.IncidenceDto;
import com.umeca.model.entities.timeAttendance.IncidenceView;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.repository.humanResources.IncidenceRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.device.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/23/2015.
 */
@Controller
public class IncidenceController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SharedUserService sharedUserService;

    @Autowired
    private IncidenceService incidenceService;

    @Autowired
    IncidenceRepository incidenceRepository;

    @RequestMapping(value = "/humanResources/incidence/index", method = RequestMethod.GET)
    public String Index(){
        return "/humanResources/timeAttendance/incidence/index";
    }

    @RequestMapping(value = "/humanResources/incidence/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("date"));
                    add(r.get("reason"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("name"))
                    return r.get("name");
                if (field.equals("date"))
                    return r.get("date");
                return null;
            }
        }, IncidenceView.class, IncidenceDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/incidence/addIncidence", method = RequestMethod.POST )
    public ModelAndView addAbsence(){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/incidence/add");

        Gson gson = new Gson();
        List employees = employeeRepository.getAllNoObsoleteEmployees();

        if (employees != null){
            model.addObject("Employees", gson.toJson(employees));
        }
        return model;
    }

    @RequestMapping(value = "/humanResources/incidence/doAddIncidence", method = RequestMethod.POST)
    public ResponseMessage doAddIncidence(@ModelAttribute IncidenceDto incidenceDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long idUser = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(idUser, incidenceDto.getPassword())) {
                incidenceDto.setIdUser(idUser);
                response = incidenceService.addIncidence(incidenceDto);
            }
            else
                throw new SecurityException("La constraseña no es válida");
        }
        catch(Exception ex) {
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error: " + ex.getMessage());
        }
        finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/incidence/upsertIncidence", method = RequestMethod.POST )
    public ModelAndView upsertIncidence(@RequestParam(required = true) Long id){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/incidence/upsert");

        Gson gson = new Gson();
        IncidenceDto incidenceDto = incidenceRepository.getIncidence(id);

        if (incidenceDto != null){

            model.addObject("Incidence", gson.toJson(incidenceDto));
        }
        return model;
    }

    @RequestMapping(value = "/humanResources/incidence/doUpsertIncidence", method = RequestMethod.POST)
    public ResponseMessage doUpsertJustify(@ModelAttribute IncidenceDto incidenceDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long idUser = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(idUser, incidenceDto.getPassword())) {
                incidenceDto.setIdUser(idUser);
                response = incidenceService.justifyIncidence(incidenceDto);
            }
            else
                throw new SecurityException("La constraseña no es válida");
        }
        catch(Exception ex) {
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error: " + ex.getMessage());
        }
        finally {
            return response;
        }
    }
}
