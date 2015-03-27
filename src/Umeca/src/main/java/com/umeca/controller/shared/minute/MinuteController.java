package com.umeca.controller.shared.minute;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.shared.Constants;
import com.umeca.repository.director.AssistantRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.director.MinuteService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MinuteController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private MinuteService minuteService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssistantRepository assistantRepository;

    @RequestMapping(value = "/shared/minute/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/shared/minute/index");
        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        if (roles.contains(Constants.ROLE_HUMAN_RESOURCES))
            model.addObject("canAdd", true);
        else
            model.addObject("canAdd", false);
        return model;
    }

    @RequestMapping(value = "/shared/minute/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("minuteDate"));
                    add(r.get("startTime"));
                    add(r.get("place"));
                    add(r.join("attendant").get("name"));
                    add(r.join("attendant").get("lastNameP"));
                    add(r.join("attendant").get("lastNameM"));
                    add(r.get("isObsolete"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("fullName"))
                    return r.get("minuteDate");
                if (field.equals("minuteDate"))
                    return r.get("name");
                if (field.equals("attendantName"))
                    return r.join("attendant").get("name");
                return null;
            }
        }, Minute.class, MinuteDto.class);

        return result;
    }

    @RequestMapping(value = "/shared/minute/upsertMinute", method = RequestMethod.GET)
    public ModelAndView upsertMinute(@RequestParam(required = false) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/upsert");
        Gson gson = new Gson();
        MinuteDto dto = null;

        if (id != null) {
            dto = minuteService.getMinuteDtoById(id);
            dto.setAssistantsIds(gson.toJson(assistantRepository.getAssistantsIdsByMinuteIds(id)));
        } else {
            dto = new MinuteDto();
        }

        model.addObject("minute", gson.toJson(dto));
        model.addObject("lstEmployee", gson.toJson(employeeRepository.getAllNoObsoleteEmployees()));
        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());
        if (roles.contains(Constants.ROLE_HUMAN_RESOURCES))
            model.addObject("canEdit", true);
        else
            model.addObject("canEdit", false);
        return model;
    }

    @RequestMapping(value = "/shared/minute/doUpsertMinute", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertMinute(@ModelAttribute MinuteDto minuteDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = minuteService.doUpsertMinute(minuteDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertMinute", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }
}

