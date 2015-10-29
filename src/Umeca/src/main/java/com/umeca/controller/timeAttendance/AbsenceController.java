package com.umeca.controller.timeAttendance;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.AbsenceDto;
import com.umeca.model.entities.timeAttendance.AbsenceView;
import com.umeca.repository.humanResources.AbsenceRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.device.AbsenceService;
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
public class AbsenceController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private SharedUserService sharedUserService;

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/humanResources/absence/index", method = RequestMethod.GET)
    public String Index(){
        return "/humanResources/timeAttendance/absence/index";
    }

    @RequestMapping(value = "/humanResources/absence/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("date"));
                    add(r.get("type"));
                    add(r.get("reason"));
                    add(r.get("value"));
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
        }, AbsenceView.class, AbsenceDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/absence/upsertAbsence", method = RequestMethod.POST )
    public ModelAndView upsertAbsence(@RequestParam(required = true) Long id){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/absence/upsert");

        Gson gson = new Gson();
        AbsenceDto absenceDto = absenceRepository.getAbsence(id);

        if (absenceDto != null){

            model.addObject("Absence", gson.toJson(absenceDto));
        }
        return model;
    }


    @RequestMapping(value = "/humanResources/absence/addAbsence", method = RequestMethod.POST )
    public ModelAndView addAbsence(){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/absence/add");

        Gson gson = new Gson();
        List employees = employeeRepository.getAllNoObsoleteEmployees();

        if (employees != null){
            model.addObject("Employees", gson.toJson(employees));
        }
        return model;
    }

    @RequestMapping(value = "/humanResources/absence/doUpsertJustify", method = RequestMethod.POST)
     public ResponseMessage doUpsertJustify(@ModelAttribute AbsenceDto absenceDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long idUser = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(idUser, absenceDto.getPassword())) {
                absenceDto.setIdUser(idUser);
                response = absenceService.justifyAbsence(absenceDto);
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

    @RequestMapping(value = "/humanResources/absence/doAddAbsence", method = RequestMethod.POST)
    public ResponseMessage doAddAbsence(@ModelAttribute AbsenceDto absenceDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long idUser = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(idUser, absenceDto.getPassword())) {
                absenceDto.setIdUser(idUser);
                response = absenceService.addAbsence(absenceDto);
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
