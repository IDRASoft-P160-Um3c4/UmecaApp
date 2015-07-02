package com.umeca.controller.shared.messageHistory;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.RequestType;
import com.umeca.model.catalog.ResponseType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.CaseRequest;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.shared.CommentRequest;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.MessageHistoryDetailView;
import com.umeca.model.entities.shared.MessageHistoryView;
import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.MainPageService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageHistoryController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedUserService userService;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    MainPageService mainPageService;

    @RequestMapping(value = {"/shared/messageHistory/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("/shared/messageHistory/index");
    }

    private void setFiltersGridUser(JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();
        if (!userService.isUserInRole(userId, Constants.ROLE_DIRECTOR)) {
            Boolean isManagerEval = userService.isUserInRole(userId, Constants.ROLE_EVALUATION_MANAGER);
            Boolean isManagerSup = userService.isUserInRole(userId, Constants.ROLE_SUPERVISOR_MANAGER);
            if (isManagerSup || isManagerEval) {
                String role;
                if (isManagerEval) {
                    role = Constants.ROLE_REVIEWER;
                } else {
                    role = Constants.ROLE_SUPERVISOR;
                }
                JqGridRulesModel extraFilter = new JqGridRulesModel("role",
                        role, JqGridFilterModel.COMPARE_EQUAL);
                opts.extraFilters.add(extraFilter);
            } else {
                JqGridRulesModel extraFilter = new JqGridRulesModel("user",
                        userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
                opts.extraFilters.add(extraFilter);
            }
        }
    }

    @RequestMapping(value = {"/shared/messageHistory/list"}, method = RequestMethod.POST)
    @ResponseBody
    public JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        opts.extraFilters = new ArrayList<>();
        setFiltersGridUser(opts);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {


                final Join<CaseRequest, Message> messageRequest = r.join("requestMessage");
                final Join<Message, Case> messageRequestCase = messageRequest.join("caseDetention");
                final Join<Case, Meeting> meetingCase = messageRequestCase.join("meeting");
                final Join<Meeting, Imputed> imputed = meetingCase.join("imputed");

                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(messageRequestCase.get("id"));
                    add(messageRequestCase.get("idFolder"));
                    add(imputed.get("name"));
                    add(imputed.get("lastNameP"));
                    add(imputed.get("lastNameM"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("user"))
                    return r.join("requestMessage").join("sender").get("id");
                if (field.equals("role"))
                    return r.join("requestMessage").join("sender").join("roles").get("role");
                if (field.equals("fullName"))
                    return r.join("requestMessage").join("caseDetention").join("meeting").join("imputed").get("name");
                if (field.equals("idFolder"))
                    return r.join("requestMessage").join("caseDetention").get("idFolder");
                return null;
            }

        }, true, CaseRequest.class, MessageHistoryView.class);
        return result;
    }

    @RequestMapping(value = {"/shared/messageHistory/detail"}, method = RequestMethod.POST)
    @ResponseBody
    public JqGridResultModel detail(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("caseDetention", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        setFiltersGridUser(opts);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final Join<CaseRequest, Message> messageRequest = r.join("requestMessage");
                final Join<Message, User> sender = messageRequest.join("sender");
                final Join<CaseRequest, RequestType> requestType = r.join("requestType");
                final Join<CaseRequest, ResponseType> responseType = r.join("responseType", JoinType.LEFT);
                final Join<CaseRequest, Message> messageResponse = r.join("responseMessage", JoinType.LEFT);


                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    //add(messageRequestCase.get("id"));
                    add(r.get("id"));
                    add(sender.get("fullname"));
                    add(requestType.get("description"));
                    add(messageRequest.get("body"));
                    add(responseType.get("description"));
                    add(messageResponse.get("body"));
                    add(messageResponse.join("sender", JoinType.LEFT).get("fullname"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("user"))
                    return r.join("requestMessage").join("sender").get("id");
                if (field.equals("role"))
                    return r.join("requestMessage").join("sender").join("roles").get("role");
                if (field.equals("caseDetention"))
                    return r.join("requestMessage").join("caseDetention").get("id");

                return null;
            }
        }, true, CaseRequest.class, MessageHistoryDetailView.class);
        return result;
    }


    @RequestMapping(value = {"/shared/messageHistory/deleteNotification"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseMessage deleteNotification(@RequestParam(required = true) Long id) {
        ResponseMessage response = new ResponseMessage();

        try {
            User user = new User();
            if (userService.isValidUser(user, response) == false)
                return response;


            if (mainPageService.deleteNotification(id, user.getId(), response) == false)
                return response;

            response.setReturnData(id);
            response.setHasError(false);
            return response;
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "deleteNotification", userService);
            response.setHasError(true);
        }
        response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        return response;
    }


}
