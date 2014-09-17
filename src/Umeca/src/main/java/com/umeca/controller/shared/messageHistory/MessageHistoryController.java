package com.umeca.controller.shared.messageHistory;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.RequestType;
import com.umeca.model.catalog.ResponseType;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.entities.shared.MessageHistoryView;
import com.umeca.model.managereval.ManagerevalView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.umeca.model.entities.account.User;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcortesr on 11/09/14.
 */
@Controller
public class MessageHistoryController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = {"/shared/messageHistory/index"}, method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("/shared/messageHistory/index");
    }

    @RequestMapping(value={"/shared/messageHistory/list"}, method = RequestMethod.POST)
    @ResponseBody
    public JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        /*
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCode",
                Constants.VERIFICATION_STATUS_NEW_SOURCE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCaseCode",
                Constants.CASE_STATUS_SOURCE_VALIDATION, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter2);
        */

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {


                final Join<CaseRequest, Message> messageRequest = r.join("requestMessage");
                final Join<Message, Case> messageRequestCase = messageRequest.join("caseDetention");
                final Join<CaseRequest, Message> messageResponse = r.join("responseMessage", JoinType.LEFT);
                final Join<CaseRequest, RequestType> requestType = r.join("requestType");
                final Join<CaseRequest, ResponseType> responseType = r.join("responseType");
                final Join<Message, User> sender = messageRequest.join("sender");


                ArrayList<Selection<?>> result = new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(messageRequestCase.get("idFolder"));
                    add(requestType.get("description").alias("requestType"));
                    add(responseType.get("description").alias("responseType"));
                    add(sender.get("fullname").alias("sender"));
                    add(messageRequest.get("text").alias("message"));
                }};

                return result;
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                /*
                if (field.equals("statusCode"))
                    return r.join("verification").join("status").get("name");
                if (field.equals("statusCaseCode"))
                    return r.join("status").get("name");
                */
                return null;
            }
        }, CaseRequest.class, MessageHistoryView.class);
        return result;
    }
}
