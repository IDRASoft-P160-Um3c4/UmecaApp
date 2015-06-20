package com.umeca.controller.supervisor;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.ChannelingCaseView;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.shared.Constants;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChannelingTrackController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/channelingTrack/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/channelingTrack/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/supervisor/channelingTrack/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusCase",
                new ArrayList<String>() {{add(Constants.CASE_STATUS_CLOSED);}}, JqGridFilterModel.COMPARE_NOT_IN);
        opts.extraFilters.add(extraFilter);
        extraFilter = new JqGridRulesModel("isTerminated", "1", JqGridFilterModel.COMPARE_EQUAL);
        extraFilter.setbData(true);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<Case, Meeting> joinMeVe = r.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<Case, District> joinDi = r.join("district");
                final Join<Case, FramingMeeting> joinFr = r.join("framingMeeting");
                final Join<FramingMeeting, User> joinFrUs = joinFr.join("supervisor");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("idMP"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinDi.get("name"));
                    add(joinFrUs.get("fullname"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCase"))
                    return r.join("status").get("name");
                if (field.equals("isTerminated"))
                    return r.join("framingMeeting").get("isTerminated");
                if (field.equals("imputed"))
                    return r.join("meeting").join("imputed").get("name");
                if (field.equals("district"))
                    return r.join("district").get("name");
                if (field.equals("supervisor"))
                    return r.join("framingMeeting").join("supervisor").get("fullname");
                return null;
            }
        }, Case.class, ChannelingCaseView.class);

        return result;
    }

}
