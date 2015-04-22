package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.model.entities.supervisor.ManagerSupExcelReportInfo;
import com.umeca.model.entities.supervisor.ManagerSupReportParams;
import com.umeca.model.entities.supervisorManager.ManagerSupChartInfo;
import com.umeca.model.entities.supervisorManager.ManagerSupChartParams;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.LocationRepository;
import com.umeca.repository.catalog.MunicipalityRepository;
import com.umeca.repository.catalog.StateRepository;
import com.umeca.repository.shared.ReportExcelRepository;
import com.umeca.repository.supervisor.DistrictRepository;
import com.umeca.repository.supervisor.HearingFormatRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.ManagerSupReportService;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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


    private List<SelectList> doListOptionReport(String type) {

        List<SelectList> listOption = new ArrayList<>();

        if (type.equals("EXCEL")) {

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
        } else if (type.equals("CHART")) {

            SelectList obj = new SelectList();
            obj.setDescription("Casos nuevos en MC.");
            obj.setName("mcCases");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Incumplimiento total en MC.");
            obj.setName("totalMC");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Incumplimiento parcial MC.");
            obj.setName("partialMC");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Casos nuevos en SCPP.");
            obj.setName("scppCases");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Incumplimiento total en SCPP.");
            obj.setName("totalSCPP");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Incumplimiento parcial SCPP.");
            obj.setName("partialSCPP");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Casos de MC a SCPP.");
            obj.setName("arrangementChange");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Casos cerrados.");
            obj.setName("closedCases");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Sustraídos.");
            obj.setName("substractedCases");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Prision.");
            obj.setName("prisonCases");
            listOption.add(obj);

            obj = new SelectList();
            obj.setDescription("Visita domiciliar.");
            obj.setName("visits");
            listOption.add(obj);
        }

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
        model.addObject("lstOpts", gson.toJson(doListOptionReport("EXCEL")));
        model.addObject("lstStates", gson.toJson(stateRepository.findStatesByCountryAlpha2("MX")));
        model.addObject("lstDistrict", gson.toJson(districtRepository.findNoObsolete()));
        return model;
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

        //se selecciono el numero de casos por lugar de detencion
        if (params.getCountDetPlace() != null && params.getCountDetPlace().equals(true))
            infoObj = managerSupReportService.getCountCasesByDetentionPlace(params, infoObj);
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

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/supervisorManager/report/reportChart", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showReportChart(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/supervisorManager/report/indexCharts");
        Gson gson = new Gson();
        model.addObject("lstOpts", gson.toJson(doListOptionReport("CHART")));
        model.addObject("lstSupervisor", gson.toJson(userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR)));
        model.addObject("lstDistrict", gson.toJson(districtRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/supervisorManager/report/doReportChart", method = RequestMethod.GET)
    @ResponseBody
    public void doReportChart(HttpServletRequest request, HttpServletResponse response, ManagerSupChartParams params) {
        Map param = new HashMap<>();
        String outputFileName = "graficosSup";

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
            e.printStackTrace();
        }

        List<ManagerSupChartInfo> list = getInfoChart(params);
        param.put("title", params.getChartTitle());
        generateReportChart(outputFileName, list, param, request, response);
    }

    private List<ManagerSupChartInfo> getInfoChart(ManagerSupChartParams params) {
        List<ManagerSupChartInfo> list = new ArrayList<>();

        List<Long> lstIdsSup = new ArrayList<>();

        String title = "";

        //si se indica distrito
        if (params.getDistrictId() != null) {
            for (Object curr : reportExcelRepository.getSupervisorsByDistrict(params.getDistrictId())) {
                Object[] aux = (Object[]) curr;
                ManagerSupChartInfo newSup = new ManagerSupChartInfo(Long.parseLong(aux[0].toString()), aux[1].toString());
                list.add(newSup);
                lstIdsSup.add(newSup.getUserId());
            }
            title = "Estadísticos para el distrito de " + districtRepository.findOne(params.getDistrictId()).getName();
        } else {        //si se indica operador
            list = reportExcelRepository.getSupervisorsById(params.getUserId());
            lstIdsSup.add(params.getUserId());
            title = "Estadísticos para el operador " + userRepository.findOne(params.getUserId()).getFullname();
        }

        params.setChartTitle(title);

        List<String> closedStatus = new ArrayList<String>() {{
            add(Constants.CASE_STATUS_CLOSED);
            add(Constants.CASE_STATUS_PRISON_CLOSED);
            add(Constants.CASE_STATUS_CLOSE_FORGIVENESS);
            add(Constants.CASE_STATUS_CLOSE_AGREEMENT);
            add(Constants.CASE_STATUS_CLOSE_DESIST);
            add(Constants.CASE_STATUS_CLOSE_OTHER);
        }};


        List<Object> lstTotal = new ArrayList<>();

        if (list.size() > 0) {
            //casos en MC
            if (params.getMcCases() != null && params.getMcCases().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithMonitoringPlanResolution(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, HearingFormatConstants.HEARING_TYPE_MC);
                setTotalInList("CASES_MC", lstTotal, list);
            } else {
                setTotalInList("CASES_MC", null, list);
            }

            //incumplimiento parcial MC
            if (params.getPartialMC() != null && params.getPartialMC().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithFulfillmentReport(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, Constants.CODE_PARTIAL_FULFILLMENT, HearingFormatConstants.HEARING_TYPE_MC);
                setTotalInList("PARTIAL_MC", lstTotal, list);
            } else {
                setTotalInList("PARTIAL_MC", null, list);
            }

            //incumplimiento total MC
            if (params.getTotalMC() != null && params.getTotalMC().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithFulfillmentReport(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, Constants.CODE_TOTAL_FULFILLMENT, HearingFormatConstants.HEARING_TYPE_MC);
                setTotalInList("TOTAL_MC", lstTotal, list);
            } else {
                setTotalInList("TOTAL_MC", null, list);
            }

            //casos SCPP
            if (params.getScppCases() != null && params.getScppCases().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithMonitoringPlanResolution(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, HearingFormatConstants.HEARING_TYPE_SCP);
                setTotalInList("CASES_SCPP", lstTotal, list);
            } else {
                setTotalInList("CASES_SCPP", null, list);
            }

            //incumplimiento parcial SCPP
            if (params.getPartialSCPP() != null && params.getPartialSCPP().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithFulfillmentReport(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, Constants.CODE_PARTIAL_FULFILLMENT, HearingFormatConstants.HEARING_TYPE_SCP);
                setTotalInList("PARTIAL_SCPP", lstTotal, list);
            } else {
                setTotalInList("PARTIAL_SCPP", null, list);
            }

            //incumplimiento total SCPP
            if (params.getTotalSCPP() != null && params.getTotalSCPP().equals(true)) {
                lstTotal = reportExcelRepository.getCountCasesWithFulfillmentReport(params.getiDate(), params.geteDate(), closedStatus, lstIdsSup, Constants.CODE_TOTAL_FULFILLMENT, HearingFormatConstants.HEARING_TYPE_SCP);
                setTotalInList("TOTAL_SCPP", lstTotal, list);
            } else {
                setTotalInList("TOTAL_SCPP", null, list);
            }

            //TODO TERMINAR LOS REPORTES CON GRAFICO FALTANTES

        } else {
            list.add(new ManagerSupChartInfo(-1L, "Sin registros que coincidan"));
        }

        return list;
    }

    private void setTotalInList(String totalId, List<Object> totals, List<ManagerSupChartInfo> listInfo) {

        switch (totalId) {
            case "CASES_MC":
                if (totals != null) {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        for (Object act : totals) {
                            Object[] aux = (Object[]) act;
                            if (actSup.getUserId().equals(Long.parseLong(aux[1].toString()))) {
                                actSup.setMcCases(Long.parseLong(aux[0].toString()));
                                break;
                            }
                        }
                    }
                } else {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        actSup.setMcCases(0L);
                    }
                }
                break;

            case "PARTIAL_MC":
                if (totals != null) {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        for (Object act : totals) {
                            Object[] aux = (Object[]) act;
                            if (actSup.getUserId().equals(Long.parseLong(aux[1].toString()))) {
                                actSup.setPartialMC(Long.parseLong(aux[0].toString()));
                                break;
                            }
                        }
                    }
                } else {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        actSup.setPartialMC(0L);
                    }
                }
                break;
            case "TOTAL_MC":
                if (totals != null) {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        for (Object act : totals) {
                            Object[] aux = (Object[]) act;
                            if (actSup.getUserId().equals(Long.parseLong(aux[1].toString()))) {
                                actSup.setTotalMC(Long.parseLong(aux[0].toString()));
                                break;
                            }
                        }
                    }
                } else {
                    for (ManagerSupChartInfo actSup : listInfo) {
                        actSup.setTotalMC(0L);
                    }
                }
                break;
        }
    }


    private void generateReportChart(String name, Collection data, Map param, HttpServletRequest request, HttpServletResponse response) {
        try {

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jasperTemplate/chartReport.jasper";

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

