package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.Municipality;
import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import com.umeca.model.entities.supervisor.ManagerSupReportInfoDto;
import com.umeca.model.entities.supervisor.ManagerSupReportParams;
import com.umeca.model.entities.supervisorManager.ManagerSupChartDto;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.MunicipalityRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.ManagerSupReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ManagerSupReportController {

    @Autowired
    private ReportExcelRepository reportExcelRepository;
    @Autowired
    private HearingFormatRepository hearingFormatRepository;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;


    private List<SelectList> doListOptionReport() {
        List<SelectList> listOption = new ArrayList<>();
        SelectList obj = new SelectList();
        obj.setDescription("Número de imposiciones por obligación procesal.");
        obj.setName("countArrangement");
        listOption.add(obj);

        obj = new SelectList();
        obj.setDescription("Número de personas que consumen sustancias.");
        obj.setName("countDrug");
        listOption.add(obj);

        obj = new SelectList();
        obj.setDescription("Número de canalizaciones a organizaciones de la sociedad civil.");
        obj.setName("countCivOrg");
        listOption.add(obj);

        obj = new SelectList();
        obj.setDescription("Número de personas con empleo.");
        obj.setName("countJob");
        listOption.add(obj);

        obj = new SelectList();
        obj.setDescription("Número de casos cerrados.");
        obj.setName("countClosed");
        listOption.add(obj);

        obj = new SelectList();
        obj.setDescription("Número de detenidos por lugar de detención.");
        obj.setName("countDetPlace");
        listOption.add(obj);

        return listOption;
    }

    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MunicipalityRepository municipalityRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @RequestMapping(value = "/supervisorManager/report/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/supervisorManager/report/index");
        Gson gson = new Gson();
        model.addObject("lstOpts", gson.toJson(doListOptionReport()));
        model.addObject("lstStates", gson.toJson(stateRepository.findStatesByCountryAlpha2("MX")));
        model.addObject("lstMun", gson.toJson(municipalityRepository.findByIdState(1L)));
        model.addObject("lstDistrict", gson.toJson(districtRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/supervisorManager/report/getMun", method = RequestMethod.POST)
    @ResponseBody
    public String getMunBySt(@RequestParam Long idState) {
        Gson gson = new Gson();
        return gson.toJson(municipalityRepository.findMunByState(idState));
    }

    @RequestMapping(value = "/supervisorManager/report/getLoc", method = RequestMethod.POST)
    @ResponseBody
    public String getLocByMun(@RequestParam Long idMun) {
        Gson gson = new Gson();
        return gson.toJson(locationRepository.findLocByMunId(idMun));
    }

    @RequestMapping(value = "/supervisorManager/report/doReport", method = RequestMethod.GET)
    @ResponseBody
    public void deReport(HttpServletRequest request, HttpServletResponse response, @ModelAttribute ManagerSupReportParams params) {
        this.doXls(request, response, this.getInfo(params));
    }

    @Autowired
    ManagerSupReportService managerSupReportService;

    private ManagerSupExcelReportInfo getInfo(ManagerSupReportParams params) {

        ManagerSupExcelReportInfo infoObj = new ManagerSupExcelReportInfo();
        Date initDate = null;
        Date endDate = null;
        String initTime = " 00:00:00";
        String endTime = " 23:59:59";

        try {
            initDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(params.getInitDate() + initTime);

            endDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                    .parse(params.getEndDate() + endTime);

            params.setiDate(initDate);
            params.seteDate(endDate);

        } catch (Exception e) {
            return null;
        }

        infoObj.setStrInitDate(params.getInitDate());
        infoObj.setStrEndDate(params.getEndDate());

        if (params.getDistrictId() != null)
            infoObj.setDistrictName(districtRepository.findOne(params.getDistrictId()).getName());

        //REVISO LOS INDICADORES SELECCIONADOS

        //se selecciono el numero de imposiciones por obligacion procesal
        if (params.getCountArrangement() != null && params.getCountArrangement().equals(true))
            infoObj = managerSupReportService.getCountByArrangements(params, infoObj);
        else {
            infoObj.setLstCasesByArrangement(new ArrayList<SelectList>());
        }

        //se selecciono el numero de personas que consumen alguna droga
        if (params.getCountDrug() != null && params.getCountDrug().equals(true))
            infoObj = managerSupReportService.getCountByDrugs(params, infoObj);
        else {
            infoObj.setLstCasesByDrugs(new ArrayList<SelectList>());
        }

        //se selecciono el numero de personas con empleo
        if (params.getCountJob() != null && params.getCountJob().equals(true))
            infoObj = managerSupReportService.getCountByJob(params, infoObj);
        else {
            infoObj.setLstCasesByJob(new ArrayList<SelectList>());
        }

        //se selecciono el numero de casos cerrados
        if (params.getCountClosed() != null && params.getCountClosed().equals(true))
            infoObj = managerSupReportService.getCountClosedCases(params, infoObj);
        else {
            infoObj.setLstClosedCases(new ArrayList<SelectList>());
        }


        return infoObj;
    }

    private void doXls(HttpServletRequest request, HttpServletResponse response, ManagerSupExcelReportInfo info) {
        Map beans = new HashMap();
        XLSTransformer transformer = new XLSTransformer();

        File temp;
        UUID uid = UUID.randomUUID();
        try {
            temp = File.createTempFile(uid.toString(), ".xls");
            Gson conv = new Gson();

            String tempPath = temp.getAbsolutePath();

            beans.put("infoObj", info);

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/supervisionInfo.xls";

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"infoSup.xls\"");

            FileInputStream istr = new FileInputStream(tempPath);
            OutputStream ostr = response.getOutputStream();

            int curByte = -1;

            while ((curByte = istr.read()) != -1)
                ostr.write(curByte);

            ostr.flush();
            ostr.close();
            istr.close();
            temp.delete();

        } catch (Exception e) {
            e.printStackTrace();
            logException.Write(e, this.getClass(), "save", sharedUserService);
            //return new ResponseMessage(true, "Error de red, intente mas tarde.");
        }
    }

    @RequestMapping(value = "/supervisorManager/report/jasperTest", method = RequestMethod.GET)
    @ResponseBody
    public void doReport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<ManagerSupChartDto> list = new ArrayList();

        ManagerSupChartDto a = new ManagerSupChartDto("axel", 5L, 10L, 15L);
        ManagerSupChartDto b = new ManagerSupChartDto("isra", 20L, 25L, 30L);
        ManagerSupChartDto c = new ManagerSupChartDto("rolex", 35L, 40L, 45L);
        ManagerSupChartDto d = new ManagerSupChartDto("yoni", 50L, 55L, 60L);
        ManagerSupChartDto e = new ManagerSupChartDto("nano", 65L, 70L, 75L);

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        String outputFileName = "algodon";

        generateReports(outputFileName, list, param, request, response);
    }

    private void generateReports(String name, Collection data, Map param, HttpServletRequest request, HttpServletResponse response) {
        try {

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jasperTemplate/test5.jasper";

            JRBeanCollectionDataSource jrbc = new JRBeanCollectionDataSource(data);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(realContextPath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, jrbc);

            UUID uid = UUID.randomUUID();
            File temp = File.createTempFile(uid.toString(), ".docx");
            String tempPath = temp.getAbsolutePath();

            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(temp));
            exporter.exportReport();

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".docx\"");

            FileInputStream istr = new FileInputStream(tempPath);
            OutputStream ostr = response.getOutputStream();

            int curByte = -1;

            while ((curByte = istr.read()) != -1)
                ostr.write(curByte);

            ostr.flush();
            ostr.close();
            istr.close();
            temp.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


