package com.umeca.controller.shared;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.shared.UploadFileView;
import com.umeca.model.entities.supervisor.MonitoringPlan;
import com.umeca.model.entities.supervisor.MonitoringPlanView;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.LoginService;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.util.locale.LocaleUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadFileController {

    @Autowired
    SharedLogExceptionService logException;

    @RequestMapping(value = "/shared/uploadFile/index", method = RequestMethod.GET)
    public @ResponseBody ModelAndView index(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/shared/uploadFile/index");
        model.addObject("caseId",id);
        return model;
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private SharedUserService userService;


    @RequestMapping(value = "/shared/uploadFile/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long caseId){

        Long userId = userService.GetLoggedUserId();

        if(userId == null)
            return null;

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseId", caseId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("filename"));
                    add(r.get("description"));
                    add(r.get("size"));
                    add(r.join("creationUser").get("fullname"));
                    add(r.get("creationTime"));
                    add(r.join("caseDetention").get("id"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("fullname"))
                    return r.join("creationUser").get("fullname");
                return null;
            }
        }, UploadFile.class, UploadFileView.class);
        return result;
    }

}
