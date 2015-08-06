package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.Channeling;
import com.umeca.model.entities.supervisor.ChannelingDropInfo;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisorManager.ChannelingInfoDropModel;
import com.umeca.model.entities.supervisorManager.ChannelingInfoDropView;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ChannelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChannelingInfoController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisorManager/channelingInfo/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisorManager/channelingInfo/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/supervisorManager/channelingInfo/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isAuthorized", "", JqGridFilterModel.IS_NULL);
        opts.extraFilters.add(extraFilter);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<ChannelingDropInfo, Channeling> joinChdrCa = r.join("channeling");
                final Join<Channeling, CatChannelingType> joinChChtp = joinChdrCa.join("channelingType");
                final Join<Channeling, Case> joinChCa = joinChdrCa.join("caseDetention");
                final Join<Case, Meeting> joinMeVe = joinChCa.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Channeling, District> joinDi = joinChdrCa.join("district");
                final Join<Case, FramingMeeting> joinFr = joinChCa.join("framingMeeting");
                final Join<FramingMeeting, User> joinFrUs = joinFr.join("supervisor");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("creationDate"));
                    add(joinChdrCa.get("name"));
                    add(joinChChtp.get("name"));
                    add(joinChCa.get("idMP"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinDi.get("name"));
                    add(joinFrUs.get("fullname"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("creationDateTx"))
                    return r.get("creationDate");
                if (field.equals("channelingName"))
                    return r.join("channeling").get("name");
                if (field.equals("channelingTypeName"))
                    return r.join("channeling").join("channelingType").get("name");
                if (field.equals("idMp"))
                    return r.join("channeling").join("caseDetention").get("idMP");
                if (field.equals("imputed"))
                    return r.join("channeling").join("caseDetention").join("meeting").join("imputed").get("name");
                if (field.equals("district"))
                    return r.join("channeling").join("district").get("name");
                if (field.equals("supervisor"))
                    return r.join("channeling").join("caseDetention").join("framingMeeting").join("supervisor").get("fullname");
                return null;
            }
        }, ChannelingDropInfo.class, ChannelingInfoDropView.class);

        return result;
    }


    @Autowired
    ChannelingService channelingService;

    @RequestMapping(value = "/supervisorManager/channelingInfo/authRejChannelingDrop", method = RequestMethod.POST)
    public
    @ResponseBody
    ModelAndView authRejChannelingDrop(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/supervisorManager/channelingInfo/authRejChannelingDrop");

        ChannelingInfoDropModel channelingInfo;
        channelingInfo = channelingService.getAuthRejChannelingDropInfoById(id);

        Gson gson = new Gson();
        String sObject = gson.toJson(channelingInfo);
        model.addObject("channelingInfo", sObject);

        return model;
    }


    @RequestMapping(value = "/supervisorManager/channelingInfo/doAuthRejChannelingDrop", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAuthRejChannelingDrop(@ModelAttribute final ChannelingInfoDropModel model) {
        ResponseMessage response = new ResponseMessage();

        try {

            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;

            if (userService.isValidPasswordForUser(user.getId(), model.getPassword()) == false){
                response.setMessage("La contrase&ntilde;a es incorrecta, verifique los datos.");
                response.setHasError(true);
                return response;
            }

            channelingService.doAuthRejChannelingDrop(model, user, userService, response);

            return response;

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthRejChannelingDrop", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }

        return response;
    }
}
