package com.umeca.controller.detentionRecord;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.detentionRecord.DetainedDto;
import com.umeca.model.entities.detentionRecord.Detained;
import com.umeca.model.shared.Constants;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.detentionRecord.DetentionRecordService;
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
public class DetentionRecordController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private DetentionRecordService detentionRecordService;
    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping(value = "/detentionRecord/detainedSheet", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/detentionRecord/detainedSheet");

        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        Boolean showAdd = false;
        Boolean showAction = false;
        Boolean showProsecute = false;

        if (roles.contains(Constants.ROLE_DETENTION_RECORD))
            showAdd = true;

        if (roles.contains(Constants.ROLE_EVALUATION_MANAGER))
            showAction = true;

        if (roles.contains(Constants.ROLE_EVALUATION_MANAGER) || roles.contains(Constants.ROLE_REVIEWER))
            showProsecute = true;

        model.addObject("showAdd", showAdd);
        model.addObject("showAction", showAction);
        model.addObject("showProsecute", showProsecute);
        return model;
    }

    @RequestMapping(value = "/detentionRecord/detainedSheet/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
//                public DetainedDto(Long id, String name, String lastNameP, String lastNameM, Date initDate, Time initTime,
//                        String idFolder, String age, String investigationUnit, String crime, String district
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("registerTimestamp"));
                    add(r.get("name"));
                    add(r.get("lastNameP"));
                    add(r.get("lastNameM"));
                    add(r.get("initDate"));
                    add(r.get("initTime"));
                    add(r.get("idFolder"));
                    add(r.get("age"));
                    add(r.get("investigationUnit"));
                    add(r.get("crime"));
                    add(r.join("district").get("name").alias("districtName"));
                    add(r.get("isProsecute"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
//                if (field.equals("fullName"))
//                    return r.get("name");
//                if (field.equals("district"))
//                    return r.join("district").get("name");
                return null;
            }
        }, Detained.class, DetainedDto.class);

        return result;
    }

    @RequestMapping(value = "/detentionRecord/upsertDetention", method = RequestMethod.POST)
    public ModelAndView upsertDetention() {
        ModelAndView model = new ModelAndView("/detentionRecord/upsert");
        model.addObject("lstDistrict", new Gson().toJson(districtRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/detentionRecord/doUpsertDetention", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertDetained(@ModelAttribute DetainedDto dto) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = detentionRecordService.saveDetained(dto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertDetained", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/detentionRecord/upsertProsecute", method = RequestMethod.POST)
    public ModelAndView upsertProsecute(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/detentionRecord/prosecute");
        model.addObject("detainedId", id);
        return model;
    }


    @RequestMapping(value = "/detentionRecord/doProsecute", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doProsecute(@RequestParam Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = detentionRecordService.doProsecute(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doProsecute", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

}

