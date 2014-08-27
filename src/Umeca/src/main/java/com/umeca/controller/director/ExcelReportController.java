package com.umeca.controller.director;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.ForCasesHFGrid;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelReportController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;


    @RequestMapping(value = "/director/excelReport/listCases", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listCases(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{

                    add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
                    add(Constants.CASE_STATUS_HEARING_FORMAT_END);
                    add(Constants.CASE_STATUS_CONDITIONAL_REPRIEVE);
                    add(Constants.CASE_STATUS_FRAMING_INCOMPLETE);
                    add(Constants.CASE_STATUS_FRAMING_COMPLETE);

                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = r.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("status").get("name"));
                    add(r.join("status").get("description"));
                    add(r.get("idFolder"));
                    add(r.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("idMP"))
                    return r.get("idMP");
                else if (field.equals("statusName"))
                    return r.join("status").get("name");
                else if (field.equals("fullName"))
                    return r.join("meeting").join("imputed").get("name");
                return null;
            }
        }, Case.class, ForCasesHFGrid.class);

        return result;
    }


    @RequestMapping(value = "/director/excelReport/index", method = RequestMethod.GET)
    public String index() {
        return "/director/excelReport/index";
    }

}
