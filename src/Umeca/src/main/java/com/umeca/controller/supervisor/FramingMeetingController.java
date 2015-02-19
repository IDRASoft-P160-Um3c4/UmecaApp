
package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.catalog.*;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.catalog.dto.CountryDto;
import com.umeca.model.catalog.dto.StateDto;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.dto.JobDto;
import com.umeca.model.entities.reviewer.dto.RelActivityObjectDto;
import com.umeca.model.entities.shared.UploadFile;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.*;
import com.umeca.repository.reviewer.DrugRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.repository.supervisor.*;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.catalog.AddressService;
import com.umeca.service.shared.CrimeService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.shared.UpDwFileService;
import com.umeca.service.supervisor.FramingMeetingService;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Controller
public class FramingMeetingController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private FramingMeetingService framingMeetingService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FramingReferenceRepository framingReferenceRepository;

    @Autowired
    private RelationshipRepository relationshipRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private HearingFormatService hearingFormatService;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private AcademicLevelRepository academicLevelRepository;

    @Autowired
    private DrugTypeRepository drugTypeRepository;

    @Autowired
    private PeriodicityRepository periodicityRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private StatusCaseRepository statusCaseRepository;

    @Autowired
    private SharedUserService sharedUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FramingMeetingRepository framingMeetingRepository;

    @Autowired
    private FramingMeetingLogRepository framingMeetingLogRepository;

    @RequestMapping(value = "/supervisor/framingMeeting/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/framingMeeting/index";
    }

    @RequestMapping(value = "/supervisor/framingMeeting/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_HEARING_FORMAT_END);
                    add(Constants.CASE_STATUS_FRAMING_COMPLETE);
                    add(Constants.CASE_STATUS_FRAMING_INCOMPLETE);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {

            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Case, StatusCase> joinSt = r.join("status");
                final javax.persistence.criteria.Join<Case, StatusCase> joinM = r.join("meeting").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinSt.get("name"));
                    add(joinSt.get("description"));
                    add(r.get("idMP"));
                    add(joinM.get("name"));
                    add(joinM.get("lastNameP"));
                    add(joinM.get("lastNameM"));
                    add(joinM.get("birthDate"));
                }};

//                Long id, String codeStatus, String descStatus, String idMP, String name, String lastNameP, String lastNameM, Date
//                brthDate,
//                        Long idTR, Long framingMeetingId)
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idMP"))
                    return r.get("idMP");

                if (field.equals("statusName"))
                    return r.join("status").get("name");

                return null;
            }
        }, Case.class, ForFramingMeetingGrid.class);

        return result;
    }

    @Autowired
    private HearingFormatRepository hearingFormatRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    CrimeService crimeService;
    @Autowired
    UpDwFileService upDwFileService;
    @Autowired
    private TechnicalReviewRepository technicalReviewRepository;

    @RequestMapping(value = "/supervisor/framingMeeting/framingMeeting", method = RequestMethod.GET)
    public ModelAndView framingMeeting(@RequestParam(required = true) Long id, Integer returnId) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingMeeting");

        try {
            Case caseDet = caseRepository.findOne(id);

            List<HearingFormat> lstHF = hearingFormatRepository.findLastHearingFormatByCaseId(id, new PageRequest(0, 1));
            if (lstHF != null && lstHF.size() > 0) {
                HearingFormatSpecs hfs = lstHF.get(0).getHearingFormatSpecs();
                if (hfs != null) {
                    if (hfs.getArrangementType().equals(HearingFormatConstants.HEARING_TYPE_MC)) {
                        model.addObject("resolution", "MC");
                    } else {
                        model.addObject("resolution", "SCCP");
                    }
                }
            }

            if (caseDet.getFramingMeeting() == null) {
                FramingMeeting framingMeeting = new FramingMeeting();
                framingMeeting.setIsTerminated(false);
                framingMeeting.setSupervisor(userRepository.findOne(sharedUserService.GetLoggedUserId()));

                Verification existVer = caseDet.getVerification();

                caseDet.setFramingMeeting(framingMeeting);

                caseDet.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_FRAMING_INCOMPLETE));
                framingMeeting.setCaseDetention(caseDet);
                framingMeetingService.save(framingMeeting);

                if (existVer != null && existVer.getStatus().getName().equals(Constants.VERIFICATION_STATUS_COMPLETE)) {
                    framingMeetingService.fillSaveVerifiedInfo(framingMeeting, existVer.getMeetingVerified());
                }

                HearingFormat lastFormat = lstHF.get(0); //busca si existe un formato y trae la ultima direccion

                if (lastFormat != null) {
                    //valida que no exista la direccion en la entrevista
                    Boolean existAddress = false;
                    Address formatAddress = lastFormat.getHearingImputed().getAddress();

                    List<FramingAddress> lstExistFramingAddress = framingMeeting.getFramingAddresses();

                    if (lstExistFramingAddress != null && lstExistFramingAddress.size() > 0) {
                        for (FramingAddress fAdd : framingMeeting.getFramingAddresses()) {
                            if (fAdd.getAddress().equals(formatAddress)) {
                                existAddress = true;
                                break;
                            }
                        }
                    }

                    if (existAddress == false) {
                        FramingAddress newFA = new FramingAddress();
                        Address newAddr = new Address();
                        newAddr.setStreet(formatAddress.getStreet());
                        newAddr.setOutNum(formatAddress.getOutNum());
                        newAddr.setInnNum(formatAddress.getInnNum());
                        newAddr.setLocation(formatAddress.getLocation());
                        newAddr.setAddressString(newAddr.toString());
                        newFA.setAddress(newAddr);
                        newFA.setFramingMeeting(framingMeeting);
                        framingAddressRepository.save(newFA);
                    }
                }
            }

            FramingMeetingView framingMeetingView = framingMeetingService.fillForView(caseDet);

            Gson conv = new Gson();
            List<CatalogDto> listActivity = new ArrayList<>();
            for (Activity a : activityRepository.findNotObsolete()) {
                CatalogDto c = new CatalogDto();
                c.setName(a.getName());
                c.setId(a.getId());
                c.setSpecification(a.getSpecification());
                listActivity.add(c);
            }

            model.addObject("lstActivity", conv.toJson(listActivity));
            FramingMeeting fm = caseDet.getFramingMeeting();
            if (fm != null && fm.getRelFramingMeetingActivities() != null) {
                List<RelFramingMeetingActivity> listRelData = caseDet.getFramingMeeting().getRelFramingMeetingActivities();
                if (listRelData != null && listRelData.size() > 0) {
                    List<RelActivityObjectDto> listRel = new ArrayList<>();
                    for (RelFramingMeetingActivity r : listRelData) {
                        RelActivityObjectDto rNew = new RelActivityObjectDto();
                        listRel.add(rNew.relDto(r));
                    }
                    model.addObject("activity", conv.toJson(listRel));
                }
            }
            model.addObject("objView", conv.toJson(framingMeetingView));
            addressService.fillCatalogAddress(model);

            List<CountryDto> countrys = new ArrayList<>();
            for (Country act : countryRepository.findAllOrderByName()) {

                countrys.add(new CountryDto().dtoCountry(act));
            }

            model.addObject("lstCountry", conv.toJson(countrys));

            List<StateDto> states = new ArrayList<>();
            for (State act : stateRepository.findStatesByCountryAlpha2("MX")) {

                states.add(new StateDto().stateDto(act));
            }

            model.addObject("listState", conv.toJson(states));

            List<CatalogDto> relationships = new ArrayList<>();
            for (Relationship act : relationshipRepository.findNotObsolete()) {

                CatalogDto rel = new CatalogDto();
                rel.setId(act.getId());
                rel.setName(act.getName());

                relationships.add(rel);
            }

            model.addObject("lstRelationship", conv.toJson(relationships));
            model.addObject("lstAcademicLvl", new Gson().toJson(academicLevelRepository.findNoObsolete()));

            if (returnId == null)
                returnId = -1;

            model.addObject("returnId", returnId);
            Long idFileTR = upDwFileService.getIdFileByCodeType(Constants.CODE_FILE_TECH_REVIEW, id);
            model.addObject("fileIdTR", idFileTR);
            Long idFilePhoto = upDwFileService.getIdFileByCodeType(Constants.CODE_FILE_IMPUTED_PHOTO, id);
            model.addObject("fileIdPhoto", idFilePhoto);
            if (idFilePhoto != null) {
                UploadFile file = upDwFileService.getPathAndFilename(idFilePhoto);
                String path = new File(file.getPath(), file.getRealFileName()).toString();
                model.addObject("pathPhoto", path);
            }

            String[] propsArr = new String[]{"idFolder", "meeting.imputed.name", "meeting.imputed.lastNameP", "meeting.imputed.lastNameM"};
            caseDet = (Case) StringEscape.escapeAttrs(caseDet, propsArr);

            model.addObject("idFolder", caseDet.getIdFolder());
            Imputed i = caseDet.getMeeting().getImputed();
            String fullName = i.getName() + " " + i.getLastNameP() + " " + i.getLastNameM();
            model.addObject("fullNameImputed", fullName);
            model.addObject("idCase", id);
            model.addObject("age", sharedUserService.calculateAge(i.getBirthDate()));
            model.addObject("imputedId", i.getId());
            model.addObject("hasMeeting", caseDet.getMeeting().getSchool() != null);

            Boolean isSubstracted = caseDet.getIsSubstracted();
            if (isSubstracted == null)
                isSubstracted = false;

            model.addObject("isSubstracted", isSubstracted);

            model.addObject("hasTR", caseDet.getTechnicalReview() != null);
            model.addObject("listCrime", crimeService.getListCrimeHearingformatByCase(id));

            model.addObject("lstCL", new Gson().toJson(technicalReviewRepository.getCommunityLinksByCaseId(id)));
            model.addObject("lstPR", new Gson().toJson(technicalReviewRepository.getProceduralRiskByCaseId(id)));
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doNewCase", sharedUserService);
            return null;
        }
        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listHousemate", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listHousemate(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilterA = new JqGridRulesModel("personType", FramingMeetingConstants.PERSON_TYPE_HOUSEMATE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilterA);


        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("age"));
                    add(r.join("relationship").get("name"));
                    add(r.get("occupation"));
                    add(r.get("personType"));
                    add(r.get("specificationRelationship"));
                    add(r.get("isAccompaniment"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("personType"))
                    return r.get("personType");

                return null;
            }
        }, FramingReference.class, FramingReference.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listAddress", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAddress(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("address").get("addressString"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("fullAddress"))
                    return r.join("address").get("addressString");

                return null;
            }
        }, FramingAddress.class, FramingAddressForGrid.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listReferences", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReferences(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilterA = new JqGridRulesModel("personType", FramingMeetingConstants.PERSON_TYPE_REFERENCE, JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilterA);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("phone"));
                    add(r.join("relationship").get("name"));
                    add(r.join("accompanimentInfo", JoinType.LEFT).join("address", JoinType.LEFT).get("addressString"));
                    add(r.get("address"));
                    add(r.get("personType"));
                    add(r.get("specificationRelationship"));
                    add(r.get("isAccompaniment"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("personType"))
                    return r.get("personType");

                return null;
            }
        }, FramingReference.class, FramingReference.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listDrug", method = RequestMethod.POST)
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
                    return r.join("framingMeeting").join("caseDetention").get("id");
                } else if (field.equals("drugName")) {
                    return r.join("drugType").get("name");
                }
                return null;
            }
        }, Drug.class, Drug.class);

        return result;

    }

    @RequestMapping(value = "/supervisor/framingMeeting/listActivities", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listActivities(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("activity").get("name"));
                    add(r.get("description"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idCase")) {
                    return r.join("framingMeeting").join("caseDetention").get("id");
                }
                if (field.equals("name")) {
                    return r.join("activity").get("name");
                }
                return null;
            }
        }, FramingActivity.class, FramingActivityView.class);

        return result;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/listJob", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listJob(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<Job, RegisterType> joinRT = r.join("registerType");
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
                    return r.join("framingMeeting").join("caseDetention").get("id");
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

    @RequestMapping(value = "/supervisor/framingMeeting/listVictim", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listVictims(@RequestParam final Long idCase, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);

        JqGridRulesModel extraFilterA = new JqGridRulesModel("personType",
                new ArrayList<String>() {{
                    add(FramingMeetingConstants.PERSON_TYPE_VICTIM);
                    add(FramingMeetingConstants.PERSON_TYPE_WITNESS);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);
        opts.extraFilters.add(extraFilterA);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("name"));
                    add(r.get("age"));
                    add(r.join("relationship").get("name"));
                    add(r.get("phone"));
                    add(r.join("accompanimentInfo", JoinType.LEFT).join("address", JoinType.LEFT).get("addressString"));
                    add(r.get("personType"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {

                if (field.equals("idCase"))
                    return r.join("framingMeeting").join("caseDetention").get("id");

                if (field.equals("personType"))
                    return r.get("personType");

                return null;
            }
        }, FramingReference.class, FramingReference.class);

        return result;
    }


    @Autowired
    FramingAddressRepository framingAddressRepository;
    @Autowired
    HomeTypeRepository homeTypeRepository;


    @RequestMapping(value = "/supervisor/framingMeeting/address/upsert", method = RequestMethod.POST)
    public ModelAndView showAddressUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingAddress/upsert");

        Gson conv = new Gson();

        Address address;

        FramingAddress existFramingAddress = null;
        if (id != null) {
            existFramingAddress = framingAddressRepository.findOne(id);
            addressService.fillModelAddress(model, existFramingAddress.getAddress().getId());
        } else {
            addressService.fillCatalogAddress(model);
        }

        FramingAddressDto addDto = framingMeetingService.fillFramingAddressForView(existFramingAddress);

        addDto.setIdCase(idCase);

        model.addObject("addObj", conv.toJson(addDto));

        model.addObject("lstHomeType", conv.toJson(homeTypeRepository.getAllHomeType()));
        model.addObject("lstRegisterType", conv.toJson(registerTypeRepository.getAllRegisterType()));

        List<StateDto> states = new ArrayList<>();
        for (State act : stateRepository.findStatesByCountryAlpha2("MX")) {

            states.add(new StateDto().stateDto(act));
        }

        model.addObject("listState", conv.toJson(states));

        model.addObject("idCaseAdd", idCase);

        return model;
    }


    @RequestMapping(value = "/supervisor/framingMeeting/drugs/upsert", method = RequestMethod.POST)
    public ModelAndView showDrugUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/drugs/upsert");
        Gson gson = new Gson();
        model.addObject("lstDrugType", gson.toJson(drugTypeRepository.findNotObsolete()));
        model.addObject("lstPeriodicity", gson.toJson(periodicityRepository.findNotObsolete()));
        if (id != null && id != 0) {
            Drug d = drugRepository.findOne(id);
            model.addObject("d", d);
            model.addObject("typeId", d.getDrugType().getId());
            model.addObject("perId", d.getPeriodicity().getId());
        }
        model.addObject("idCase", idCase);
        return model;

    }

    @RequestMapping(value = "/supervisor/framingMeeting/drugs/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertDrug(@ModelAttribute Drug drug, @RequestParam Long idCase) {
        return framingMeetingService.doUpsertDrug(drug, idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/drugs/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteDrug(@RequestParam Long id) {
        return framingMeetingService.deleteDrug(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/housemate/upsert", method = RequestMethod.POST)
    public ModelAndView showHousemateUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/housemate/upsert");

        FramingReferenceDto housemate;
        Gson conv = new Gson();

        if (id != null)
            housemate = new FramingReferenceDto(framingReferenceRepository.findOne(id));
        else
            housemate = new FramingReferenceDto();

        housemate.setIdCase(idCase);

        if (housemate.getAddress() == null || housemate.getAddress().trim().equals("")) {
            List<String> fmAddress = framingMeetingRepository.firstFramingHouseByCase(idCase, new PageRequest(0, 1));
            if (fmAddress != null && fmAddress.size() > 0) {
                housemate.setAddress(fmAddress.get(0));
            }
        }

        model.addObject("housemate", conv.toJson(housemate));

        List<CatalogDto> relationships = new ArrayList<>();
        for (Relationship act : relationshipRepository.findNotObsolete()) {

            CatalogDto rel = new CatalogDto();
            rel.setId(act.getId());
            rel.setName(act.getName());
            rel.setSpecification(act.getSpecification());
            relationships.add(rel);
        }

        model.addObject("lstRelationship", conv.toJson(relationships));

        List<CatalogDto> academicLvls = new ArrayList<>();
        for (AcademicLevel act : academicLevelRepository.findNotObsolete()) {

            CatalogDto rel = new CatalogDto();
            rel.setId(act.getId());
            rel.setName(act.getName());

            academicLvls.add(rel);
        }

        model.addObject("lstAcademicLevel", conv.toJson(academicLvls));

        return model;
    }


    @RequestMapping(value = "/supervisor/framingMeeting/address/doAddressUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAddressUpsert(@RequestParam Long idCase, @ModelAttribute FramingAddressDto view) {
        return framingMeetingService.saveFramingAddress(idCase, view);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/references/upsert", method = RequestMethod.POST)
    public ModelAndView showReferencesUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/references/upsert");

        FramingReferenceDto housemate;
        Gson conv = new Gson();

        if (id != null)
            housemate = new FramingReferenceDto(framingReferenceRepository.findOne(id));
        else {
            housemate = new FramingReferenceDto();
            housemate.setIsAccompaniment(false);
        }

        housemate.setIdCase(idCase);

        model.addObject("reference", conv.toJson(housemate));

        List<CatalogDto> relationships = new ArrayList<>();
        for (Relationship act : relationshipRepository.findNotObsolete()) {

            CatalogDto rel = new CatalogDto();
            rel.setId(act.getId());
            rel.setName(act.getName());
            rel.setSpecification(act.getSpecification());
            relationships.add(rel);
        }

        model.addObject("lstRelationship", conv.toJson(relationships));

        List<StateDto> states = new ArrayList<>();
        for (State act : stateRepository.findStatesByCountryAlpha2("MX")) {
            states.add(new StateDto().stateDto(act));
        }

        model.addObject("listState", conv.toJson(states));

        if (housemate.getAddressId() != null)
            addressService.fillModelAddress(model, housemate.getAddressId());

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/references/doReferenceUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doReferenceUpsert(@RequestParam Long idCase, @ModelAttribute FramingReference framingReference) {

        Case existCase = caseRepository.findOne(idCase);

        if (existCase == null)
            return new ResponseMessage(true, "Ocurrio un error al guardar la información. Intente mas tarde.");

        return framingMeetingService.saveReference(existCase, framingReference);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/address/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteAddress(@RequestParam Long id) {
        return framingMeetingService.deleteFramingAddress(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/reference/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage deleteReference(@RequestParam Long id) {
        return framingMeetingService.deleteReference(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/loadEnvironmentAnalysis", method = RequestMethod.POST)
    public
    @ResponseBody
    FramingEnvironmentAnalysisForView loadExistSources(@RequestParam(required = true) Long idCase) {
        FramingEnvironmentAnalysisForView view = framingMeetingService.loadEnvironmentAnalysis(idCase);
        return view;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/loadArrangements", method = RequestMethod.GET)
    public
    @ResponseBody
    String loadExistArrangements(@RequestParam(required = true) Long idCase) {
        Gson conv = new Gson();
        List<HearingFormat> lstHearing = caseRepository.findOne(idCase).getHearingFormats();
        return conv.toJson(hearingFormatService.assignedArrangementForView(lstHearing.get(lstHearing.size() - 1).getAssignedArrangements()));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/environmentAnalysis/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage environmentAnalysisDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingEnvironmentAnalysisForView view) {
        return framingMeetingService.saveSelectedItems(idCase, view);
    }

//    @RequestMapping(value = "/supervisor/framingMeeting/processAccompaniment/loadProcessAccompaniment", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    ProcessAccompanimentForView loadProcessAccompaniment(@RequestParam(required = true) Long idCase) {
//
//        return framingMeetingService.fillProcessAccompanimentForView(idCase);
//    }
//
//    @RequestMapping(value = "/supervisor/framingMeeting/processAccompaniment/doUpsert", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    ResponseMessage processAccompanimentDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute ProcessAccompanimentForView view) {
//
//        ProcessAccompaniment processAccompaniment = framingMeetingService.fillProcessAccompaniment(idCase, view);
//        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
//        existFraming.setProcessAccompaniment(processAccompaniment);
//
//        return framingMeetingService.save(existFraming);
//    }

    @RequestMapping(value = "/supervisor/framingMeeting/additionalQuestions/loadAdditionalQuestion", method = RequestMethod.GET)
    public
    @ResponseBody
    String loadRelativeAbroad(@RequestParam(required = true) Long idCase) {
        Gson conv = new Gson();

        AdditionalQuestionsForView addQuest = framingMeetingService.fillAddtionalQuestionsForView(idCase);
        return conv.toJson(addQuest);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/additionalQuestions/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage additionalQuestions(@RequestParam(required = true) Long idCase, @ModelAttribute AdditionalQuestionsForView view) {
        return framingMeetingService.saveAddQuest(idCase, view);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/personalData/loadPersonalData", method = RequestMethod.POST)
    public
    @ResponseBody
    FramingPersonalDataView loadImputedPersonalData(@RequestParam(required = true) Long idCase) {
        return framingMeetingService.fillPersonalDataForView(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/personalData/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage personalDataDoUpsert(@RequestParam(required = true) Long idCase, @ModelAttribute FramingPersonalDataView view) {

        FramingImputedPersonalData personalData = framingMeetingService.fillPersonalData(idCase, view);
        FramingMeeting existFraming = caseRepository.findOne(idCase).getFramingMeeting();
        existFraming.setPersonalData(personalData);

        if (existFraming.getIsTerminated() == true)
            framingMeetingLogRepository.save(framingMeetingService.getFramingPersonalDataLog(existFraming, view, FramingMeetingConstants.LOG_TYPE_MODIFIED));

        return framingMeetingService.save(existFraming);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/personalData/getStates", method = RequestMethod.POST)
    ResponseMessage getMunicipality(Long idSt) {
        ResponseMessage response = new ResponseMessage();
        List<CatalogDto> lstSt = new ArrayList<>();
        List<State> states = stateRepository.findStatesByCountryAlpha2("MX");

        for (State act : states) {
            CatalogDto dto = new CatalogDto();
            dto.setId(act.getId());
            dto.setName(act.getName());
            lstSt.add(dto);
        }

        Gson conv = new Gson();

        response.setHasError(false);
        response.setMessage(conv.toJson(lstSt));

        return response;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/additionalQuestion/loadAddtionalQuestion", method = RequestMethod.POST)
    public AdditionalQuestionsForView loadAdditionalQuestion(@RequestParam(required = false) Long idCase) {
        return framingMeetingService.fillAddtionalQuestionsForView(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/doTerminate", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseMessage doTerminate(@RequestParam(required = true) Long idCase) {

        return framingMeetingService.doTerminate(idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertAddressComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage addressComments(@RequestParam(required = true) Long idCase, @RequestParam String addressComments) {
        return framingMeetingService.upsertComments(idCase, 1, addressComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertHousemateComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage housemateComments(@RequestParam(required = true) Long idCase, @RequestParam String housemateComments) {
        return framingMeetingService.upsertComments(idCase, 2, housemateComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertReferencesComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage referencesComments(@RequestParam(required = true) Long idCase, @RequestParam String referencesComments) {
        return framingMeetingService.upsertComments(idCase, 3, referencesComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertDrugsComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage drugsComments(@RequestParam(required = true) Long idCase, @RequestParam String drugsComments) {
        return framingMeetingService.upsertComments(idCase, 4, drugsComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertActivitiesComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage activitiesComments(@RequestParam(required = true) Long idCase, @RequestParam String activitiesComments) {
        return framingMeetingService.upsertComments(idCase, 5, activitiesComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertJobComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage jobComments(@RequestParam(required = true) Long idCase, @RequestParam String jobComments) {
        return framingMeetingService.upsertComments(idCase, 6, jobComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/upsertVictimComments", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage victimComments(@RequestParam(required = true) Long idCase, @RequestParam String victimComments) {
        return framingMeetingService.upsertComments(idCase, 7, victimComments);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/activities/upsert", method = RequestMethod.POST)
    public ModelAndView showActivitiesUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/activities/upsert");
        model.addObject("activity", new Gson().toJson(framingMeetingService.fillActivityForView(id, idCase)));
        model.addObject("lstActivities", new Gson().toJson(activityRepository.getActivitiesNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/activities/doUpsert", method = RequestMethod.POST)
    public ResponseMessage activityDoUpsert(@RequestParam(required = false) Long idCase, @ModelAttribute FramingActivityView view) {
        return framingMeetingService.saveFramingActivity(view, idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/activities/delete", method = RequestMethod.POST)
    public ResponseMessage activityDelete(@RequestParam(required = false) Long id) {
        return framingMeetingService.deleteFramingActivity(id);
    }

    @Autowired
    private RegisterTypeRepository registerTypeRepository;

    @RequestMapping(value = "/supervisor/framingMeeting/job/upsert", method = RequestMethod.POST)
    public ModelAndView showJobUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/job/upsert");
        model.addObject("job", new Gson().toJson(framingMeetingService.fillJobForView(id, idCase)));
        model.addObject("lstRegisterType", new Gson().toJson(registerTypeRepository.getAllRegisterType()));
        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/job/doUpsert", method = RequestMethod.POST)
    public ResponseMessage jobDoUpsert(@RequestParam(required = false) Long idCase, @ModelAttribute JobDto view) {
        return framingMeetingService.saveFramingJob(view, idCase);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/job/delete", method = RequestMethod.POST)
    public ResponseMessage jobDelete(@RequestParam(required = false) Long id) {
        return framingMeetingService.deleteFramingJob(id);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/school/getAcademicLvl", method = RequestMethod.POST)
    public ResponseMessage getAcademicLvls() {
        return new ResponseMessage(false, new Gson().toJson(academicLevelRepository.findNoObsolete()));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/school/getDegree", method = RequestMethod.POST)
    public ResponseMessage getDegrees(@RequestParam(required = false) Long id) {
        return new ResponseMessage(false, new Gson().toJson(degreeRepository.findNoObsoleteByAcademicLvlId(id)));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/school/loadSchool", method = RequestMethod.POST)
    public ResponseMessage showSchool(@RequestParam(required = true) Long idCase) {
        return new ResponseMessage(false, new Gson().toJson(framingMeetingService.fillSchoolForView(idCase)));
    }

    @RequestMapping(value = "/supervisor/framingMeeting/school/doUpsert", method = RequestMethod.POST)
    public ResponseMessage upsertSchool(@ModelAttribute SchoolDto view) {
        return framingMeetingService.saveSchool(view);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/victim/upsert", method = RequestMethod.POST)
    public ModelAndView showVictimUpsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {

        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/victim/upsert");

        FramingReferenceDto victim;
        Gson conv = new Gson();

        if (id != null)
            victim = new FramingReferenceDto(framingReferenceRepository.findOne(id));
        else
            victim = new FramingReferenceDto();

        victim.setIdCase(idCase);
        model.addObject("victim", conv.toJson(victim));

        List<CatalogDto> relationships = new ArrayList<>();
        for (Relationship act : relationshipRepository.findNotObsolete()) {

            CatalogDto rel = new CatalogDto();
            rel.setId(act.getId());
            rel.setName(act.getName());
            rel.setSpecification(act.getSpecification());
            relationships.add(rel);
        }

        model.addObject("lstRelationship", conv.toJson(relationships));

        List<StateDto> states = new ArrayList<>();
        for (State act : stateRepository.findStatesByCountryAlpha2("MX")) {
            states.add(new StateDto().stateDto(act));
        }

        model.addObject("listState", conv.toJson(states));

        if (victim.getAddressId() != null)
            addressService.fillModelAddress(model, victim.getAddressId());

        return model;
    }

    @RequestMapping(value = "/supervisor/framingMeeting/victim/doVictimUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doVictimUpsert(@RequestParam Long idCase, @ModelAttribute FramingReference framingReference) {

        Case existCase = caseRepository.findOne(idCase);

        if (existCase == null)
            return new ResponseMessage(true, "Ocurrio un error al guardar la información. Intente mas tarde.");

        return framingMeetingService.saveVictim(existCase, framingReference);
    }

    @RequestMapping(value = "/supervisor/framingMeeting/framingMeetingLog", method = RequestMethod.GET)
    public ModelAndView showLog(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/supervisor/framingMeeting/framingMeetingLog");
        Long framingId = caseRepository.findOne(id).getFramingMeeting().getId();


        List<SelectList> lstLog = framingMeetingLogRepository.getLogByIdFraming(framingId, new ArrayList<String>() {{
            add(FramingMeetingConstants.LOG_TYPE_ADDED);
            add(FramingMeetingConstants.LOG_TYPE_MODIFIED);
            add(FramingMeetingConstants.LOG_TYPE_DELETED);
        }});
        model.addObject("lstAlterLog", new Gson().toJson(lstLog));
        model.addObject("lstDayChanges", new Gson().toJson(framingMeetingLogRepository.getChangeLogDatesByIdFraming(framingId,
                new ArrayList<String>() {{
                    add(FramingMeetingConstants.LOG_TYPE_ADDED);
                    add(FramingMeetingConstants.LOG_TYPE_MODIFIED);
                    add(FramingMeetingConstants.LOG_TYPE_DELETED);
                }})));

        List<SelectList> lstTerminateLog = framingMeetingLogRepository.getTerminatedLogByIdFraming(framingId, new ArrayList<String>() {{
            add(FramingMeetingConstants.LOG_TYPE_FINISHED);
        }});
        model.addObject("lstTerminateLog", new Gson().toJson(lstTerminateLog));

        return model;
    }


//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(String.class, new StringEscapeEditor(false, false, true));
//    }
}
