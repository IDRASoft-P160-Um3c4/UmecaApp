package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.ImputedMissedAttendanceLogDto;
import com.umeca.model.entities.supervisorManager.ImputedMissedAttendanceLog;
import com.umeca.repository.supervisorManager.ImputedMissedAttendanceLogRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class imputedMissedAttendanceController {


    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;
    @Autowired
    ImputedMissedAttendanceLogRepository imputedMissedAttendanceLogRepository;

    @RequestMapping(value = {"/supervisorManager/imputedMissedAttendance/index", "/supervisor/imputedMissedAttendance/index"}, method = RequestMethod.GET)
    public String index() {
        return "/supervisorManager/imputedMissedAttendance/index";
    }

    @RequestMapping(value = {"/supervisorManager/imputedMissedAttendance/list", "/supervisor/imputedMissedAttendance/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts, String ids) {


        opts.extraFilters = new ArrayList<>();


        Gson gson = new Gson();
        List<String> idsRecords = gson.fromJson(ids, new TypeToken<List<String>>() {
        }.getType());

        if (idsRecords == null || !(idsRecords.size() > 0)) {
            idsRecords = new ArrayList<>();
            idsRecords.add("-1");
        }

        JqGridRulesModel extraFilter = new JqGridRulesModel("idsRecords", idsRecords, JqGridFilterModel.COMPARE_IN);
        opts.extraFilters.add(extraFilter);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("imputed").get("name"));
                    add(r.join("imputed").get("lastNameP"));
                    add(r.join("imputed").get("lastNameM"));
                    add(r.get("date"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idsRecords"))
                    return r.get("id");
                else if (field.equals("name"))
                    return r.join("imputed").get("name");
                else if (field.equals("lastNameP"))
                    return r.join("imputed").get("lastNameP");
                else if (field.equals("lastNameM"))
                    return r.join("imputed").get("lastNameM");
                else if (field.equals("dateStr"))
                    return  r.get("date");
                return null;

            }
        }, ImputedMissedAttendanceLog.class, ImputedMissedAttendanceLogDto.class);

        return result;
    }


    @RequestMapping(value = {"/supervisorManager/imputedMissedAttendance/findRecords", "/supervisor/imputedMissedAttendance/findRecords"}, method = RequestMethod.GET)
    public ResponseMessage findRecords(String initDateStr, String endDateStr) {

        Date initDate = null;
        Date endDate = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";
        Gson gson = new Gson();

        try {
            initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(initDateStr + initTime);

            endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(endDateStr + endTime);

            List<Long> idsRecords = imputedMissedAttendanceLogRepository.getDetainedByPeriod(initDate, endDate);

            return new ResponseMessage(false, gson.toJson(idsRecords));

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "findRecords", sharedUserService);
            return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }

    }
}
