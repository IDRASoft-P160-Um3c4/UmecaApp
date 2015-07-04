package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.dto.tablet.TabletCaseDto;
import com.umeca.model.dto.tablet.TabletMeetingDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.View.PersonSocialNetworkView;
import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.repository.CaseRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.MeetingService;
import com.umeca.service.reviewer.ScheduleService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.tablet.TabletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.security.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    TabletService tabletService;

    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = "/reviewer/meeting/index", method = RequestMethod.GET)
    public String index() {
        try {
//            TabletCaseDto tc = tabletService.getAllCaseByIdCase(1L);
//            String cadena = new Gson().toJson(tc);
//
//            TabletCaseDto ttC = new Gson().fromJson("{\"webId\":1,\"id\":1,\"idFolder\":\"CASOWEB\",\"recidivist\":false,\"dateCreate\":\"2015/07/03\",\"status\":{\"id\":3,\"name\":\"ST_CASE_MEETING\",\"description\":\"Entrevista de riesgos procesales\"},\"meeting\":{\"webId\":1,\"id\":1,\"meetingType\":1,\"commentReference\":\"COMENT REFE\",\"commentJob\":\"COMENT JOB\",\"commentSchool\":\"COMENT SCHOOL\",\"commentCountry\":\"COMENT ABANDONAR PAIS\",\"commentHome\":\"COMENT DOM\",\"commentDrug\":\"COMENT DROGAS\",\"dateCreate\":\"2015/07/03\",\"status\":{\"id\":1,\"name\":\"INCOMPLETE\",\"description\":\"Entrevista incompleta\"},\"reviewer\":{\"id\":2,\"fullname\":\"reviewersillo\"},\"imputed\":{\"webId\":1,\"id\":1,\"name\":\"NOMBRE WEB\",\"lastNameP\":\"AP WEB\",\"lastNameM\":\"AM WEB\",\"foneticString\":\"nombre webap webam web\",\"gender\":false,\"birthDate\":\"1997/07/03\",\"celPhone\":\"CELULAR\",\"yearsMaritalStatus\":25,\"boys\":2,\"dependentBoys\":1,\"birthMunicipality\":\"\",\"birthState\":\"\",\"birthLocation\":\"\",\"nickname\":\"APODO\",\"maritalStatus\":{\"id\":2,\"name\":\"Casado\"},\"birthCountry\":{\"id\":1,\"name\":\"Mexico\",\"alpha2\":\"MX\",\"alpha3\":\"MEX\",\"latitude\":23,\"longitude\":-102},\"location\":{\"id\":831,\"name\":\"Acueducto de Guadalupe\",\"abbreviation\":\"Acueducto de Guadalupe\",\"description\":\"Acueducto de Guadalupe\",\"zipCode\":\"07279\"}},\"socialNetwork\":{\"webId\":1,\"id\":1,\"comment\":\"COMENT RED\",\"peopleSocialNetwork\":[{\"webId\":1,\"id\":1,\"name\":\"PERSONA 1\",\"age\":50,\"phone\":\"TELEFONO1\",\"address\":\"\",\"specification\":\"FOLIOIFE\",\"isAccompaniment\":true,\"specificationRelationship\":\"VALEDOR\",\"block\":true,\"relationship\":{\"id\":19,\"name\":\"Otro\",\"isObsolete\":false,\"specification\":true},\"documentType\":{\"id\":1,\"name\":\"Credencial de Elector\",\"isObsolete\":false,\"specification\":true},\"dependent\":{\"id\":1,\"name\":\"Si\"},\"livingWith\":{\"id\":1,\"name\":\"Si\"}}]},\"school\":{\"webId\":1,\"id\":1,\"name\":\"ESCUELA\",\"phone\":\"TELEFONO\",\"address\":\"DIRECCIÓN\",\"specification\":\"\",\"block\":true,\"degree\":{\"id\":31,\"name\":\"Terminado\",\"isObsolete\":false},\"schedule\":[{\"id\":3,\"day\":\"DIA\",\"start\":\"16:22\",\"end\":\"16:22\"}]},\"socialEnvironment\":{\"webId\":1,\"id\":1,\"physicalCondition\":\"ENFERMEDAD\",\"comment\":\"OBS ABANDONAR PAIS\",\"relSocialEnvironmentActivities\":[{\"id\":1,\"activity\":{\"id\":1,\"name\":\"Laborales\",\"specification\":true,\"isObsolete\":false},\"specification\":\"LABORAL\"},{\"activity\":{\"id\":2,\"name\":\"Laborales\",\"specification\":true,\"isObsolete\":false},\"specification\":\"MIERDA\"}]},\"leaveCountry\":{\"webId\":1,\"id\":1,\"timeAgo\":\"\",\"reason\":\"\",\"state\":\"\",\"media\":\"\",\"address\":\"\",\"timeResidence\":\"\",\"specficationImmigranDoc\":\"\",\"specificationRelationship\":\"\",\"familyAnotherCountry\":{\"id\":2,\"name\":\"No\"},\"officialDocumentation\":{\"id\":2,\"name\":\"No\"},\"livedCountry\":{\"id\":2,\"name\":\"No\"},\"immigrationDocument\":{\"id\":1,\"name\":\"Pasaporte\",\"specification\":false,\"obsolete\":false},\"relationship\":{\"id\":18,\"name\":\"Abuelo\",\"isObsolete\":false,\"specification\":false}},\"references\":[{\"webId\":1,\"id\":1,\"fullName\":\"REFE\",\"age\":40,\"address\":\"DIRECCIONREF\",\"phone\":\"TELEFONOR\",\"specification\":\"FOLIOLIC\",\"isAccompaniment\":false,\"specificationRelationship\":\"\",\"block\":true,\"documentType\":{\"id\":2,\"name\":\"Licencia de Manejo\",\"isObsolete\":false,\"specification\":true},\"relationship\":{\"id\":12,\"name\":\"Empleado\",\"isObsolete\":false,\"specification\":false}}],\"imputedHomes\":[{\"webId\":1,\"id\":1,\"timeLive\":\"TIEMPO\",\"reasonChange\":\"\",\"description\":\"COMO LLEGAR\",\"phone\":\"TELEFONO\",\"specification\":\"\",\"reasonSecondary\":\"\",\"address\":{\"id\":1,\"street\":\"CALLE\",\"outNum\":\"EXT\",\"innNum\":\"INT\",\"lat\":\"\",\"lng\":\"\",\"addressString\":\"Calle: CALLE No Ext: EXT No Int:INT,15 de Agosto. CP: 07058. Gustavo A. Madero, Distrito Federal.\",\"location\":{\"id\":764,\"name\":\"15 de Agosto\",\"abbreviation\":\"15 de Agosto\",\"description\":\"15 de Agosto\",\"zipCode\":\"07058\"}},\"homeType\":{\"id\":1,\"name\":\"Propia\",\"specification\":false,\"obsolete\":false},\"registerType\":{\"id\":1,\"name\":\"Actual\"},\"schedule\":[{\"webId\":1,\"id\":1,\"day\":\"DIA\",\"start\":\"10:00\",\"end\":\"20:00\"}]}],\"jobs\":[{\"webId\":1,\"id\":1,\"post\":\"PUEST\",\"nameHead\":\"PATRON\",\"company\":\"EMPRESA\",\"phone\":\"TELEFONO\",\"startPrev\":\"2015/07/03\",\"start\":\"2015/07/03\",\"salaryWeek\":0.0,\"end\":\"2015/07/03\",\"reasonChange\":\"\",\"address\":\"DIRECCION\",\"block\":true,\"registerType\":{\"id\":1,\"name\":\"Actual\"},\"schedule\":[{\"webId\":2,\"id\":2,\"day\":\"DIAS\",\"start\":\"15:00\",\"end\":\"23:00\"},{\"id\":2,\"day\":\"DIAzzzz\",\"start\":\"01:00\",\"end\":\"02:00\"}]}],\"drugs\":[{\"webId\":1,\"id\":1,\"quantity\":\"2 LITROS\",\"lastUse\":\"2015/07/03\",\"block\":true,\"specificationType\":\"\",\"specificationPeriodicity\":\"\",\"onsetAge\":\"15\",\"drugType\":{\"id\":1,\"name\":\"Alcohol\",\"specification\":false,\"isObsolete\":false},\"periodicity\":{\"id\":7,\"name\":\"En reuniones sociales\",\"isObsolete\":false,\"specification\":false}}]}}", new TypeToken<TabletCaseDto>() {
//            }.getType());
//
//            tabletService.synchronizeMeeting(ttC);
            //tabletService.synchronizeVerification(tc);
            System.out.println("----");

        } catch (Exception e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return "/reviewer/meeting/index";
    }

    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/reviewer/meeting/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        Long userId = userService.GetLoggedUserId();

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("reviewerId", userId.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilter2 = new JqGridRulesModel("statusCode",
                new ArrayList<String>() {{
                    add(Constants.S_MEETING_INCOMPLETE);
                    add(Constants.S_MEETING_INCOMPLETE_LEGAL);
                }}
                , JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter2);
        JqGridRulesModel extraFilter3 = new JqGridRulesModel("statusCase", Constants.CASE_STATUS_MEETING, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter3);
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, Meeting> joinM = r.join("meeting");
                final javax.persistence.criteria.Join<Meeting, StatusMeeting> joinSM = joinM.join("status");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinImp = joinM.join("imputed");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinUsr = joinM.join("reviewer");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSM.get("name").alias("statusCode"));
                    add(r.get("idFolder"));
                    add(joinImp.get("name"));
                    add(joinImp.get("lastNameP"));
                    add(joinImp.get("lastNameM"));
                    add(joinImp.get("birthDate"));
                    add(joinImp.get("gender"));
                    add(joinSM.get("description"));
                    add(joinUsr.get("id").alias("reviewerId"));
                    add(r.join("status").get("name").alias("statusCase"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("statusCode"))
                    return r.join("meeting").join("status").get("name");
                else if (field.equals("reviewerId"))
                    return r.join("meeting").join("reviewer").get("id");
                if (field.equals("idFolder"))
                    return r.get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("meeting").join("imputed").get("name");
                else if (field.equals("statusCase")) {
                    return r.join("status").get("name");
                } else
                    return null;
            }
        }, Case.class, MeetingView.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listAddress", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAddress(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("address").get("addressString").alias("addressString"));
                    add(r.get("phone"));
                    add(r.join("registerType").get("name").alias("registerTypeString"));
                    add(r.join("homeType").get("name").alias("homeType"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("registerTypeString")) {
                    return r.join("registerType").get("name");
                } else if (field.equals("addressString")) {
                    return r.join("address").get("addressString");
                }
                return null;
            }
        }, ImputedHome.class, ImputedHome.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listSocialNetwork", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listSocialNetwork(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                    add(r.get("isAccompaniment"));
                    add(r.join("dependent").get("id").alias("dependent"));
                    add(r.get("specificationRelationship"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("socialNetwork").join("meeting").join("caseDetention").get("id");
                } else if (field.equals("relName")) {
                    return r.join("relationship").get("name");
                }

                return null;
            }
        }, PersonSocialNetwork.class, PersonSocialNetworkView.class);

        return result;

    }

    @RequestMapping(value = {"/reviewer/meeting/listReference"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReference(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("fullName"));
                    add(r.join("relationship").get("name").alias("relName"));
                    add(r.get("age"));
                    add(r.get("phone"));
                    add(r.get("isAccompaniment"));
                    add(r.get("specificationRelationship"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("relName")) {
                    return r.join("relationship").get("name");
                }

                return null;
            }
        }, Reference.class, Reference.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listDrug", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listDrug(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("periodicity").get("name").alias("perName"));
                    add(r.get("lastUse"));
                    add(r.join("drugType").get("name").alias("drugName"));
                    add(r.get("quantity"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("drugName")) {
                    return r.join("drugType").get("name");
                }
                return null;
            }
        }, Drug.class, Drug.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/listJob", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listJob(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<Meeting, Case> joinRT = r.join("registerType");
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("company"));
                    add(r.get("post"));
                    add(r.get("nameHead"));
                    add(r.get("phone"));
                    add(joinRT.get("name").alias("registerTypeString"));
                    add(joinRT.get("id").alias("registerTypeId"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("meeting").join("caseDetention").get("id");
                } else if (field.equals("registerTypeId")) {
                    return r.join("registerType").get("id");
                } else if (field.equals("registerTypeString")) {
                    return r.join("registerType").get("name");
                }
                return null;
            }
        }, Job.class, Job.class);

        return result;

    }

    @RequestMapping(value = "/reviewer/meeting/newMeeting", method = RequestMethod.POST)
    public String newMeeting() {
        return "/reviewer/meeting/newMeeting";
    }

    @RequestMapping(value = "/reviewer/meeting/doNewMeeting", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNewMeeting(@ModelAttribute Imputed imputed) {
        imputed.setFoneticString(sharedUserService.getFoneticByName(imputed.getName(), imputed.getLastNameP(), imputed.getLastNameM()));
        ResponseMessage validateCreate = meetingService.validateCreateMeeting(imputed);
        if (validateCreate != null)
            return validateCreate;
        Long idCase = meetingService.createMeeting(imputed);
        ResponseMessage result = new ResponseMessage(false, "Se ha guardado exitosamente");
        result.setUrlToGo("meeting.html?id=" + idCase);
        return result;
    }

    @RequestMapping(value = "/reviewer/meeting/meeting", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView meeting(@RequestParam(required = true) Long id) {
        return meetingService.showMeeting(id);
    }

    @RequestMapping(value = "/reviewer/meeting/legal/index", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView legal(@RequestParam(required = true) Long id, @RequestParam(required = false) Integer showCase) {
        return meetingService.showLegalProcess(id, showCase);
    }

    @RequestMapping(value = "/reviewer/meeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        return meetingService.upsertAddress(id, idCase);
    }


    @RequestMapping(value = "/reviewer/meeting/address/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertAddress(@ModelAttribute ImputedHome imputedHome, @RequestParam Long idCase, @RequestParam(required = false) String sch) {
        return meetingService.doUpsertAddress(imputedHome, idCase, sch);
    }


    @RequestMapping(value = "/reviewer/meeting/address/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteAddress(@RequestParam Long id) {
        return meetingService.deleteAddress(id);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/upsert", method = RequestMethod.POST)
    public ModelAndView upsertSocialNetwork(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        return meetingService.upsertSocialNetwork(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertSocialNewtork(@ModelAttribute PersonSocialNetwork person, @RequestParam Long idCase) {
        return meetingService.doUpsertSocialNetwork(person, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/socialNetwork/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteSocialNewtork(@RequestParam Long id) {
        return meetingService.deleteSocialNetwork(id);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/upsert", method = RequestMethod.POST)
    public ModelAndView upsertDrug(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        return meetingService.upsertDrug(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDrugNewtork(@ModelAttribute Drug drug, @RequestParam Long idCase) {
        return meetingService.doUpsertDrug(drug, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/drug/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteDrug(@RequestParam Long id) {
        return meetingService.deleteDrug(id);
    }

    @RequestMapping(value = "/reviewer/meeting/job/upsert", method = RequestMethod.POST)
    public ModelAndView upsertJob(@RequestParam(required = false) Long id, @RequestParam Long idCase) {
        return meetingService.upsertJob(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/job/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertJob(@ModelAttribute Job job, @RequestParam Long idCase, @RequestParam(required = false) String sch) {
        return meetingService.doUpsertJob(job, idCase, sch);
    }

    @RequestMapping(value = "/reviewer/meeting/job/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteJob(@RequestParam Long id) {
        return meetingService.deleteJob(id);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/upsert", method = RequestMethod.POST)
    public ModelAndView upsertReference(@RequestParam(required = false) Long id, @RequestParam Long idCase) {
        return meetingService.upsertReference(id, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertReference(@ModelAttribute Reference reference, @RequestParam Long idCase) {
        return meetingService.doUpsertReference(reference, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/reference/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteReference(@RequestParam Long id) {
        return meetingService.deleteReference(id);
    }

    @RequestMapping(value = "/reviewer/meeting/upsertPersonalData", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertPersonalData(@ModelAttribute Meeting meeting, String activities) {
        return meetingService.upsertPersonalData(meeting.getCaseDetention().getId(), meeting.getImputed(), meeting.getSocialEnvironment(), activities);
    }

    @RequestMapping(value = "/reviewer/meeting/upsertLeaveCountry", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertLeaveCountry(@ModelAttribute Meeting meeting) {
        return meetingService.upsertLeaveCountry(meeting.getCaseDetention().getId(), meeting.getLeaveCountry());
    }

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(value = "/reviewer/meeting/school/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertSchool(@ModelAttribute Meeting meeting, @RequestParam String sch) {
        try {
            ResponseMessage result = meetingService.doUpsertSchool(meeting.getCaseDetention().getId(), meeting.getSchool(), sch);
            return result;
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "upsertSchool", sharedUserService);
            return new ResponseMessage(true, "Ha ocurrido un error al guardar la historia escolar.");
        }

    }

    @RequestMapping(value = "/reviewer/meeting/terminateMeeting", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage terminateMeeting(@ModelAttribute Meeting meeting, @RequestParam String sch, String activities) {
        try {
            ResponseMessage result = meetingService.doTerminateMeeting(meeting, sch, activities);
            result.setUrlToGo("/index.html");
            return result;
        } catch (Exception e) {
            ResponseMessage result = new ResponseMessage();
            logException.Write(e, this.getClass(), "doTerminateMeeting", userService);
            result.setHasError(true);
            result.setMessage("Ha ocurrido un error al terminar la entrevista. Intente m&aacute;s tarde");
            return result;
        }
    }

    @RequestMapping(value = "/reviewer/meeting/saveProceedingLegal", method = RequestMethod.POST)

    public
    @ResponseBody
    ResponseMessage saveProceedingLegal(@ModelAttribute CriminalProceedingView cpv) {
        return meetingService.saveProceedingLegal(cpv);
    }


    @RequestMapping(value = "/reviewer/meeting/upsertSocialNetworkComment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertSocialNetworkComment(@RequestParam String comment, @RequestParam Long idCase) {
        return meetingService.upsertSocialNetworkComment(comment, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/findPreviousCase", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage findPreviousCase(@RequestParam String sName, @RequestParam String sLastNameP, @RequestParam String sLastNameM, @RequestParam Long idCase) {
        return meetingService.findPreviousCase(sName, sLastNameP, sLastNameM, idCase);
    }

    @RequestMapping(value = "/reviewer/meeting/savePartialPrevious", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage savePartialPrevious(@ModelAttribute CriminalProceedingView cpv) {
        return meetingService.savePartialPrevious(cpv);
    }

    @RequestMapping(value = "/reviewer/meeting/savePartialCurrent", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage savePartialCurrent(@ModelAttribute CriminalProceedingView cpv) {
        return meetingService.savePartialCurrent(cpv);
    }

    @RequestMapping(value = "/reviewer/meeting/upsertComment", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage upsertComment(@RequestParam String comment, @RequestParam Long idCase, @RequestParam Integer typeComment) {
        return meetingService.upsertComment(idCase, comment, typeComment);
    }

}
