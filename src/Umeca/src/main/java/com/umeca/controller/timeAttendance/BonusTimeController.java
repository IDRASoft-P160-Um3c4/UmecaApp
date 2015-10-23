package com.umeca.controller.timeAttendance;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.BonusTimeDto;
import com.umeca.model.entities.timeAttendance.BonusTime;
import com.umeca.model.entities.timeAttendance.BonusTimeView;
import com.umeca.repository.humanResources.BonusTimeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.device.BonusTimeService;
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
 * Created by Administrator on 10/21/2015.
 */
@Controller
public class BonusTimeController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private BonusTimeRepository bonusTimeRepository;

    @Autowired
    BonusTimeService bonusTimeService;

    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/humanResources/bonustime/index", method = RequestMethod.GET)
    public String Index(){
        return "/humanResources/timeAttendance/bonustime/index";
    }

    @RequestMapping(value = "/humanResources/bonustime/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("eventDate"));
                    add(r.get("eventTime"));
                    add(r.get("bonusTime"));
                    add(r.get("approved"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("name"))
                    return r.get("name");
                if (field.equals("eventDate"))
                    return r.get("eventDate");
                return null;
            }
        }, BonusTimeView.class, BonusTimeDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/bonustime/upsertBonusTime", method = RequestMethod.POST )
    public ModelAndView upsertAssistence(@RequestParam(required = true) Long id){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/bonustime/upsert");

        Gson gson = new Gson();
        BonusTimeDto bonusTimeDto = bonusTimeRepository.getTimeAttendance(id);

        if (bonusTimeDto != null){
            BonusTime bonusTime = bonusTimeRepository.getBonusTime(id);

            if (bonusTime != null) {
                bonusTimeDto.setComment(bonusTime.getComment());
                bonusTimeDto.setIdAttendancelog(bonusTime.getIdAttendanceLog());
            }
            model.addObject("BonusTime", gson.toJson(bonusTimeDto));
        }

        return model;
    }

    @RequestMapping(value = "/humanResources/bonustime/doUpsertBonusTime.json", method = RequestMethod.POST)
    public ResponseMessage doUserBonusTime(@ModelAttribute BonusTimeDto bonusTimeDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long userId = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(userId, bonusTimeDto.getPassword()))
                response = bonusTimeService.upsertBonusTime(bonusTimeDto);
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