package com.umeca.controller.detentionRecordManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.detentionRecord.DetainedDto;
import com.umeca.model.entities.detentionRecord.Detained;
import com.umeca.model.shared.Constants;
import com.umeca.repository.detentionRecord.DetainedRepository;
import com.umeca.repository.shared.SystemSettingRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.detentionRecord.DetentionRecordService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class DetentionRecordManagerController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DetainedRepository detainedRepository;

    @Autowired
    SystemSettingRepository systemSettingRepository;


    @RequestMapping(value = "/detentionRecordManager/detainedSheet", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/detentionRecordManager/detainedSheet");


        String rol = Constants.ROLE_DETENTION_RECORD_MANAGER;

        model.addObject("rol", rol);
        return model;
    }

    @RequestMapping(value = "/detentionRecordManager/detainedSheet/list", method = RequestMethod.POST)
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
                if (field.equals("idsRecords"))
                    return r.get("id");
                return null;

            }
        }, Detained.class, DetainedDto.class);

        return result;
    }

    @RequestMapping(value = {"/detentionRecordManager/excelDetentionRecordReport/jxls", "/managereval/excelDetentionRecordReport/jxls"}, method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();

        List<DetainedDto> lstDetained = detainedRepository.getDetainedInfo();


        try {
            beans.put("lstDetained", lstDetained);

            UUID uid = UUID.randomUUID();
            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");


            List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

            if (roles.contains(Constants.ROLE_DETENTION_RECORD_MANAGER)) {
                realContextPath += "/WEB-INF/jxlsTemplate/DetainedExcelDetentionRecord.xls";
            }

            transformer.transformXLS(realContextPath, beans, tempPath);
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"DetainedExcel.xls\"");

            FileInputStream istr = new FileInputStream(tempPath);
            OutputStream ostr = response.getOutputStream();

            int curByte = -1;

            while ((curByte = istr.read()) != -1)
                ostr.write(curByte);

            ostr.flush();
            ostr.close();
            istr.close();
            temp.delete();

        } catch (Exception ex) {
            ex.printStackTrace();
            logException.Write(ex, this.getClass(), "jxlsMethod", sharedUserService);
        }
    }

    @RequestMapping(value = "/detentionRecordManager/detainedSheet/findRecords", method = RequestMethod.GET)
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

            List<Long> idsRecords = detainedRepository.getDetainedByPeriod(initDate, endDate);

            return new ResponseMessage(false, gson.toJson(idsRecords));

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "findRecords", sharedUserService);
            return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }

    }




}
