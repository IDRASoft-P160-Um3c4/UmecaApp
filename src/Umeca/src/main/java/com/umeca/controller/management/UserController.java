package com.umeca.controller.management;

import com.google.gson.Gson;
import com.umeca.controller.shared.ExcelConv;
import com.umeca.infrastructure.PojoValidator;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.model.ResponseMessage;
import com.umeca.model.ResponseUniqueMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.account.UserUnique;
import com.umeca.model.entities.account.UserView;
import com.umeca.model.entities.reviewer.FieldMeetingSource;
import com.umeca.model.entities.reviewer.View.Section;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 9:44 AM
 */

@Controller
@SuppressWarnings("unchecked")
public class UserController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/management/user/index", method = RequestMethod.GET)
    public String index() {
        return "/management/user/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/management/user/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("username"));
                    add(r.get("fullname"));
                    add(r.get("email"));
                    add(r.get("enabled"));
                    add(r.join("roles").get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("role"))
                    return r.join("roles").get("description");
                return null;
            }
        }, User.class, UserView.class);

        return result;

    }

    @Qualifier("qRoleRepository")
    @Autowired
    RoleRepository repositoryRole;

    @RequestMapping(value = "/management/user/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id) {
        ModelAndView modelView = new ModelAndView("/management/user/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(repositoryRole.findSelectList());

        modelView.addObject("lstRoles", lstRoles);

        if (id != null) {
            User model = repositoryUser.findOne(id);
            modelView.addObject("model", model);

            if (model.getRoles().size() > 0)
                modelView.addObject("roleId", model.getRoles().get(0).getId());
        }

        return modelView;
    }


    @Qualifier("qUserRepository")
    @Autowired
    UserRepository repositoryUser;

    @RequestMapping(value = "/management/user/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute User modelNew) {

        ResponseMessage response = new ResponseMessage();

        try {
            User model;

            if (modelNew.getId() > 0) {
                model = repositoryUser.findOne(modelNew.getId());
                model.setUsername(modelNew.getUsername());
                model.setEmail(modelNew.getEmail());
                model.setFullname(modelNew.getFullname());
                model.getRoles().clear();
                model.setRoles(modelNew.getRoles());

                if (modelNew.getHasChangePass()) {
                    model.setPassword(modelNew.getPassword());
                    model.setConfirm(modelNew.getConfirm());
                } else {
                    model.setConfirm(model.getPassword());
                }
            } else {
                model = modelNew;
                model.setEnabled(true);
            }

            ResponseMessage resp = PojoValidator.validate(model);
            if (resp != null)
                return resp;

            if (model.getId() <= 0 || modelNew.getHasChangePass())
                model.setPassword(BcryptUtil.encode(modelNew.getPassword()));

            Long idUser = repositoryUser.findIdByUsername(model.getUsername());

            if (idUser != null && idUser != model.getId()) {
                response.setHasError(true);
                response.setMessage("El usuario ya existe, por favor elija otro usuario");
                return response;
            }

            repositoryUser.save(model);
            response.setHasError(false);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }

        return response;
    }

    @RequestMapping(value = "/management/user/isUserAvailable", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseUniqueMessage isUserAvailable(@RequestBody UserUnique model) {

        ResponseUniqueMessage response = new ResponseUniqueMessage();

        try {
            Long count = repositoryUser.countByUsername(model.getUsername(), model.getId());
            if (count != null && count > 0)
                response.setUnique(false);
            else
                response.setUnique(true);

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "isUserAvailable", sharedUserService);
            response.setUnique(false);
        }

        return response;
    }


    @RequestMapping(value = "/management/user/disable", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage disable(@RequestParam Long id) {
        return enableUser(id, false);
    }


    @RequestMapping(value = "/management/user/enable", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage enable(@RequestParam Long id) {
        return enableUser(id, true);
    }

    private ResponseMessage enableUser(Long id, boolean bIsEnabled) {
        ResponseMessage response = new ResponseMessage();
        try {
            User model = repositoryUser.findOne(id);

            if (model == null) {
                response.setHasError(true);
                response.setMessage("Por favor revise que el registro exista e intente de nuevo");
                return response;
            }

            model.setEnabled(bIsEnabled);
            repositoryUser.save(model);
            response.setHasError(false);

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "enableUser", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que el registro exista e intente de nuevo");
        }
        return response;
    }


    @Autowired
    CaseRepository caseRepository;
    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;

    @RequestMapping(value = "/management/user/jxls", method = RequestMethod.GET)
    public
    @ResponseBody
    void jxlsMethod(HttpServletRequest request, HttpServletResponse response) {

        Map beans = new HashMap();

        XLSTransformer transformer = new XLSTransformer();

        try {

            //TODO agregar consulta de los reportes que obtenga los id's

            //TODO agreggar a la consulta de getInfoCases el parametro de la lista de id´s obtenida anteriormente
            List<ExcelCaseInfoDto> listCases = caseRepository.getInfoCases();

            List<Long> casesIds = new ArrayList<>();

            for (ExcelCaseInfoDto act : listCases) {
                casesIds.add(act.getIdCase());
            }

            //TODO sustituir casesIds por lista de ids obtenida en consulta
            List<ExcelActivitiesDto> lstActivities = caseRepository.getInfoImputedActivities(casesIds);
            List<ExcelImputedHomeDto> lstHomes = caseRepository.getInfoImputedHomes(casesIds);
            List<ExcelSocialNetworkDto> lstSN = caseRepository.getInfoSocialNetwork(casesIds);
            List<ExcelReferenceDto> lstRef = caseRepository.getInfoReference(casesIds);
            List<ExcelJobDto> lstJob = caseRepository.getInfoJobs(casesIds);
            List<ExcelDrugDto> lstDrug = caseRepository.getInfoDrugs(casesIds);
            List<ExcelCrimeDto> lstCrimes = caseRepository.getInfoCrimes(casesIds);
            List<ExcelCoDefDto> lstCoDef = caseRepository.getInfoCoDef(casesIds);
            List<ExcelTecRevSelQuestDto> lstSelQuest = caseRepository.getInfoTecRevSelQuest(casesIds);
            List<ExcelVerificationDto> lstVerif = caseRepository.getInfoVerification(casesIds);
            String template = "{0}: {1} \n";
            for(ExcelVerificationDto evdto : lstVerif){
                for(int i=0; i< Constants.NAMES_MEETING.length;i++) {
                    String finalString = "";
                    List<FieldMeetingSource> fieldMeetingSources = fieldMeetingSourceRepository.getFieldMeetingBySource(evdto.getIdCase(), evdto.getIdSource(), Constants.ST_FIELD_VERIF_UNABLE,(i+1));
                    if(fieldMeetingSources!=null && fieldMeetingSources.size()>0) {
                        for (FieldMeetingSource fms : fieldMeetingSources) {
                            String aux =  template.replace("{0}",fms.getFieldVerification().getFieldName());
                            aux = aux.replace("{1}",fms.getValue());
                            finalString = finalString + aux;
                        }
                    }
                    switch (i){
                        case 0:
                            evdto.setPersonalData(finalString);
                            break;
                        case 1:
                            evdto.setImputedHome(finalString);
                            break;
                        case 2:
                            evdto.setSocialNetwork(finalString);
                            break;
                        case 3:
                            evdto.setReference(finalString);
                            break;
                        case 4:
                            evdto.setJob(finalString);
                            break;
                        case 5:
                            evdto.setSchool(finalString);
                            break;
                        case 6:
                            evdto.setDrug(finalString);
                            break;
                        case 7:
                            evdto.setLeaveCountry(finalString);
                            break;
                    }
                }

            }

            for (ExcelCaseInfoDto cAct : listCases) {

                List<ExcelActivitiesDto> acts = new ArrayList<>();
                for (ExcelActivitiesDto aAct : lstActivities) {
                    if (aAct.getIdCase() == cAct.getIdCase()) {
                        acts.add(aAct);
                    }
                }
                cAct.setLstActivities(acts);

                List<ExcelImputedHomeDto> lstImHome = new ArrayList<>();
                for (ExcelImputedHomeDto hAct : lstHomes) {
                    if (hAct.getIdCase() == cAct.getIdCase()) {
                        lstImHome.add(hAct);
                    }
                }
                cAct.setLstHomes(lstImHome);

                List<ExcelSocialNetworkDto> lstCSN = new ArrayList<>();
                for (ExcelSocialNetworkDto snAct : lstSN) {
                    if (snAct.getIdCase() == cAct.getIdCase()) {
                        lstCSN.add(snAct);
                    }
                }
                cAct.setLstSN(lstCSN);

                List<ExcelReferenceDto> lstR = new ArrayList<>();
                for (ExcelReferenceDto rAct : lstRef) {
                    if (rAct.getIdCase() == cAct.getIdCase()) {
                        lstR.add(rAct);
                    }
                }
                cAct.setLstRef(lstR);

                List<ExcelJobDto> lstJ = new ArrayList<>();
                for (ExcelJobDto jAct : lstJob) {
                    if (jAct.getIdCase() == cAct.getIdCase()) {
                        lstJ.add(jAct);
                    }
                }
                cAct.setLstJob(lstJ);

                List<ExcelDrugDto> lstD = new ArrayList<>();
                for (ExcelDrugDto dAct : lstDrug) {
                    if (dAct.getIdCase() == cAct.getIdCase()) {
                        lstD.add(dAct);
                    }
                }
                cAct.setLstDrug(lstD);

                List<ExcelCrimeDto> lstCr = new ArrayList<>();
                for (ExcelCrimeDto crAct : lstCrimes) {
                    if (crAct.getIdCase() == cAct.getIdCase()) {
                        lstCr.add(crAct);
                    }
                }
                cAct.setLstCrimes(lstCr);

                List<ExcelCoDefDto> lstCo = new ArrayList<>();
                for (ExcelCoDefDto coAct : lstCoDef) {
                    if (coAct.getIdCase() == cAct.getIdCase()) {
                        lstCo.add(coAct);
                    }
                }
                cAct.setLstCoDef(lstCo);

                List<ExcelTecRevSelQuestDto> lstQu = new ArrayList<>();
                for (ExcelTecRevSelQuestDto quAct : lstSelQuest) {
                    if (quAct.getIdCase() == cAct.getIdCase()) {
                        lstQu.add(quAct);
                    }
                }
                cAct.setLstSelQuest(lstQu);
            }

            beans.put("listCases", listCases);

            beans.put("listCasesV", lstVerif);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            beans.put("dateFormat", dateFormat);

            ExcelConv excelConv = new ExcelConv();
            beans.put("excelConv", excelConv);

            UUID uid = UUID.randomUUID();

            File temp = File.createTempFile(uid.toString(), ".xls");
            String tempPath = temp.getAbsolutePath();

            ServletContext servletContext = request.getSession().getServletContext();
            String realContextPath = servletContext.getRealPath("/");
            realContextPath += "/WEB-INF/jxlsTemplate/reportCase.xls";

            transformer.transformXLS(realContextPath, beans, tempPath);

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=\"infoCase.xls\"");

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
        }
    }
}
