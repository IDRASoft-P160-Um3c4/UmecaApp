package com.umeca.controller.humanResources;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.humanResources.EmployeeDto;
import com.umeca.model.dto.humanResources.EmployeeScheduleDto;
import com.umeca.model.dto.humanResources.ScheduleDayDto;
import com.umeca.model.entities.humanReources.EmployeeSchedule;
import com.umeca.model.entities.humanReources.ScheduleDay;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.humanResources.EmployeeScheduleRepository;
import com.umeca.repository.humanResources.ScheduleDayRepository;
import com.umeca.repository.shared.WeekDayRepository;
import com.umeca.service.account.SharedUserService;
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
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeScheduleController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private DigitalRecordService digitalRecordService;
    @Autowired
    private WeekDayRepository weekDayRepository;
    @Autowired
    private EmployeeScheduleRepository employeeScheduleRepository;
    @Autowired
    private ScheduleDayRepository scheduleDayRepository;


    @RequestMapping(value = "/humanResources/employeeSchedule/index", method = RequestMethod.GET)
    public String index() {
        return "/humanResources/employeeSchedule/index";
    }

    @RequestMapping(value = "/humanResources/employeeSchedule/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isObsolete", false, JqGridFilterModel.COMPARE_EQUAL
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("name"))
                    return r.get("name");
                if (field.equals("description"))
                    return r.get("description");
                if (field.equals("isObsolete"))
                    return r.get("isObsolete");
                return null;
            }
        }, EmployeeSchedule.class, EmployeeScheduleDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/employeeSchedule/upsertEmployeeSchedule", method = RequestMethod.POST)
    public ModelAndView upsertEmployeeSchedule(@RequestParam(required = false) Long id) {
        ModelAndView model = new ModelAndView("/humanResources/employeeSchedule/upsert");
        Gson gson = new Gson();

        SelectList employeeSchedule = new SelectList();
        List<ScheduleDayDto> lstSelDays = new ArrayList<>();

        if (id != null) {
            employeeSchedule = employeeScheduleRepository.findEmployeeScheduleDtoById(id);
            lstSelDays = scheduleDayRepository.findScheduleDaysByParentId(id);
        }

        model.addObject("empSch", gson.toJson(employeeSchedule));
        model.addObject("lstDays", gson.toJson(weekDayRepository.findAllNoObsolete()));
        model.addObject("lstSelDays", gson.toJson(lstSelDays));

        return model;
    }

    @RequestMapping(value = "/humanResources/employeeSchedule/doUpsertEmployeeSchedule", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertEmployeeSchedule(@ModelAttribute EmployeeScheduleDto scheduleDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.saveEmployeeSchedule(scheduleDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertEmployee", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/humanResources/employeeSchedule/deleteEmployeeSchedule", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doObsoleteEmployeeSchedule(@RequestParam(required = true) Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = digitalRecordService.deleteEmployeeSchedule(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertEmployee", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

}

