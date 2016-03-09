package com.umeca.controller.detentionRecord;

import com.google.gson.Gson;
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
import org.springframework.web.bind.annotation.*;
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
import java.util.*;

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

    @Autowired
    private DetainedRepository detainedRepository;

    @Autowired
    SystemSettingRepository systemSettingRepository;

    @RequestMapping(value = "/detentionRecord/detainedSheet", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/detentionRecord/detainedSheet");

        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        Boolean showAdd = false;
        Boolean showAction = false;
        Boolean showProsecute = false;
        String rol = "";

        if (roles.contains(Constants.ROLE_DETENTION_RECORD)) {
            showAdd = true;
            showAction = true;
            rol = Constants.ROLE_DETENTION_RECORD;
        }

        if (roles.contains(Constants.ROLE_EVALUATION_MANAGER)) {
            showAction = true;
            rol = Constants.ROLE_EVALUATION_MANAGER;
        }

        if (roles.contains(Constants.ROLE_EVALUATION_MANAGER) || roles.contains(Constants.ROLE_REVIEWER)) {
            showProsecute = true;
        }

        String period = systemSettingRepository.findOneValue("DETENTION", "OutOfTimePeriod");
        Long OutOfTimePeriod = Long.parseLong(period);

        model.addObject("rol", rol);
        model.addObject("showAdd", showAdd);
        model.addObject("showAction", showAction);
        model.addObject("showProsecute", showProsecute);
        model.addObject("OutOfTimePeriod", OutOfTimePeriod);
        return model;
    }

    @RequestMapping(value = "/detentionRecord/detainedSheet/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        opts.extraFilters = new ArrayList<>();
        if (roles.contains(Constants.ROLE_DETENTION_RECORD)) {
            JqGridRulesModel extraFilter = new JqGridRulesModel("isVisibleDetentionRecord", "1", JqGridFilterModel.COMPARE_EQUAL);
            opts.extraFilters.add(extraFilter);

        } else if (roles.contains(Constants.ROLE_EVALUATION_MANAGER) || roles.contains(Constants.ROLE_REVIEWER)) {
            JqGridRulesModel extraFilter = new JqGridRulesModel("isVisibleUmeca", "1", JqGridFilterModel.COMPARE_EQUAL);
            opts.extraFilters.add(extraFilter);
        }

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
//                        return r.join("district").get("name");
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


    @RequestMapping(value = "/detentionRecord/doNotVisibleDetentionRecord", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNotVisibleDetentionRecord(@RequestParam Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = detentionRecordService.doNotVisibleDetentionRecord(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doNotVisibleDetentionRecord", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }


    @RequestMapping(value = "/managereval/doNotVisibleUmeca", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNotVisibleUmeca(@RequestParam Long id) {

        ResponseMessage response = new ResponseMessage();
        try {
            response = detentionRecordService.doNotVisibleUmeca(id);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doNotVisibleDetentionRecord", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }


    @RequestMapping(value = {"/detentionRecord/excelDetentionRecordReport/jxls", "/managereval/excelDetentionRecordReport/jxls"}, method = RequestMethod.GET)
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

            if (roles.contains(Constants.ROLE_DETENTION_RECORD)) {
                realContextPath += "/WEB-INF/jxlsTemplate/DetainedExcelDetentionRecord.xls";
            }


            if (roles.contains(Constants.ROLE_EVALUATION_MANAGER) || roles.contains(Constants.ROLE_REVIEWER)) {
                realContextPath += "/WEB-INF/jxlsTemplate/DetainedExcelMangEval.xls";
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


    @RequestMapping(value = "/detentionRecord/detainedCarousel", method = RequestMethod.GET)
    public ModelAndView detainedCarousel() {
        ModelAndView model = new ModelAndView("/detentionRecord/detainedCarousel");

        String period = systemSettingRepository.findOneValue("DETENTION", "OutOfTimePeriod");
        Long OutOfTimePeriod = Long.parseLong(period);

        model.addObject("OutOfTimePeriod", OutOfTimePeriod);
        return model;
    }

    @RequestMapping(value = "/detentionRecord/detainedCarousel/getDetainedList", method = RequestMethod.GET)
    public
    @ResponseBody
    String getDetainedList(){
        List<DetainedDto> list = detainedRepository.getDetainedVisibleInfo();
        return new Gson().toJson(list);
    }




}
