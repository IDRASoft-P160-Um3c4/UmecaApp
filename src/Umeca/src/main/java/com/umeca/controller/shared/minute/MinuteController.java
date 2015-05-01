package com.umeca.controller.shared.minute;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.director.MinuteDto;
import com.umeca.model.dto.shared.AgreementDto;
import com.umeca.model.dto.shared.ObservationDto;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;
import com.umeca.model.entities.director.minutes.Minute;
import com.umeca.model.entities.humanReources.RequestAgreement;
import com.umeca.model.entities.humanReources.RequestAgreementDto;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.AreaRepository;
import com.umeca.repository.director.AssistantRepository;
import com.umeca.repository.humanResources.EmployeeRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.director.MinuteService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MinuteController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private SharedLogExceptionService logException;
    @Autowired
    private SharedUserService sharedUserService;
    @Autowired
    private MinuteService minuteService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/shared/minute/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("/shared/minute/index");
        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        Boolean isRH = false;
        Boolean isDir = false;

        if (roles.contains(Constants.ROLE_HUMAN_RESOURCES))
            isRH = true;
        if (roles.contains(Constants.ROLE_DIRECTOR))
            isDir = true;

        model.addObject("isRH", isRH);
        model.addObject("isDir", isDir);
        return model;
    }

    @RequestMapping(value = "/shared/minute/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listOpenMinutes(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isFinished", false, JqGridFilterModel.COMPARE_EQUAL
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("minuteDate"));
                    add(r.get("startTime"));
                    add(r.get("place"));
                    add(r.join("attendant").get("name"));
                    add(r.join("attendant").get("lastNameP"));
                    add(r.join("attendant").get("lastNameM"));
                    add(r.get("isFinished"));
                    add(r.get("stCode"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("isFinished"))
                    return r.get("isFinished");
                if (field.equals("minuteDate"))
                    return r.get("minuteDate");
                if (field.equals("startTime"))
                    return r.get("startTime");
                if (field.equals("attendantName"))
                    return r.join("attendant").get("name");
                return null;
            }
        }, Minute.class, MinuteDto.class);

        return result;
    }

    @RequestMapping(value = "/shared/minute/finishedIndex", method = RequestMethod.GET)
    public ModelAndView finishedIndex() {
        ModelAndView model = new ModelAndView("/shared/minute/finishedIndex");
        return model;
    }

    @RequestMapping(value = "/shared/minute/finishedList", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listFinishedMinutes(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("isFinished", true, JqGridFilterModel.COMPARE_EQUAL
        );
        opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("minuteDate"));
                    add(r.get("startTime"));
                    add(r.get("place"));
                    add(r.join("attendant").get("name"));
                    add(r.join("attendant").get("lastNameP"));
                    add(r.join("attendant").get("lastNameM"));
                    add(r.get("isFinished"));
                    add(r.get("stCode"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("isFinished"))
                    return r.get("isFinished");
                if (field.equals("minuteDate"))
                    return r.get("minuteDate");
                if (field.equals("startTime"))
                    return r.get("startTime");
                if (field.equals("attendantName"))
                    return r.join("attendant").get("name");
                return null;
            }
        }, Minute.class, MinuteDto.class);

        return result;
    }

    @RequestMapping(value = "/shared/minute/upsertMinute", method = RequestMethod.GET)
    public ModelAndView upsertMinute(@RequestParam(required = false) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/upsert");
        Gson gson = new Gson();
        MinuteDto dto = null;

        if (id != null) {
            dto = minuteService.getMinuteDtoById(id);
//            dto.setAssistantsIds(gson.toJson(assistantRepository.getAssistantsIdsByMinuteIds(id)));
            model.addObject("lstAssistantSel", gson.toJson(employeeRepository.getAllNoObsoleteEmployees()));
        } else {
            dto = new MinuteDto();
            dto.setIsFinished(false);
        }

        model.addObject("minute", gson.toJson(dto));

        List<String> roles = sharedUserService.getLstRolesByUserId(sharedUserService.GetLoggedUserId());

        Boolean isRH = false;
        Boolean isDir = false;

        if (roles.contains(Constants.ROLE_HUMAN_RESOURCES))
            isRH = true;

        if (roles.contains(Constants.ROLE_DIRECTOR))
            isDir = true;

        model.addObject("isRH", isRH);
        model.addObject("isDir", isDir);

        return model;
    }

    @RequestMapping(value = "/shared/minute/doUpsertMinute", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertMinute(@ModelAttribute MinuteDto minuteDto) {
        ResponseMessage response = new ResponseMessage();
        try {
            response = minuteService.doUpsertMinute(minuteDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertMinute", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }


    @RequestMapping(value = "/shared/agreement/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listAgreement(@RequestParam(required = true) final Long id, @ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel minuteIdFilter = new JqGridRulesModel("minuteId",
                new ArrayList<String>() {{
                    add(id.toString());
                }}, JqGridFilterModel.COMPARE_IN
        );

        opts.extraFilters.add(minuteIdFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("title"));
                    add(r.get("isDone"));
                    add(r.get("isFinished"));
                    add(r.get("stCode"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("title"))
                    return r.get("title");
                if (field.equals("theme"))
                    return r.get("theme");
                if (field.equals("minuteId"))
                    return r.join("minute").get("id");
                return null;
            }
        }, Agreement.class, AgreementDto.class);

        return result;
    }

    @RequestMapping(value = "/shared/agreement/upsertAgreement", method = RequestMethod.POST)
    public ModelAndView upsertAgreement(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idMinute) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/upsert");
        Gson gson = new Gson();
        model.addObject("minuteId", idMinute);
        model.addObject("lstArea", gson.toJson(areaRepository.findNoObsolete()));
        return model;
    }

    @RequestMapping(value = "/shared/agreement/doUpsertAgreement", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertAgreement(@ModelAttribute AgreementDto agreementDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), agreementDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        try {
            response = minuteService.doUpsertAgreement(agreementDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertAgreement", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }


    @RequestMapping(value = "/shared/observation/upsertObservation", method = RequestMethod.POST)
    public ModelAndView upsertObservation(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/observation/upsert");
        model.addObject("agreementId", id);
        return model;
    }

    @RequestMapping(value = "/shared/observation/doUpsertObservation", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertObservation(@ModelAttribute ObservationDto observationDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), observationDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        try {
            response = minuteService.doUpsertObservation(observationDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsertObservation", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/shared/observation/obsHistory", method = RequestMethod.GET)
    public ModelAndView obsHistory(@RequestParam Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/observation/obsHistory");
        Gson gson = new Gson();
        model.addObject("agreementData", gson.toJson(minuteService.getGrlAgreementInfoById(id)));
        model.addObject("lstObs", gson.toJson(minuteService.getAllObsDtoByAgreementId(id)));
        return model;
    }

    @RequestMapping(value = "/shared/agreement/finishRequestAgreement", method = RequestMethod.POST)
    public ModelAndView showRequestFinishAgreement(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/finishRequest");
        model.addObject("agreementId", id);
        return model;
    }

    @RequestMapping(value = "/shared/agreement/doFinishRequestAgreement", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doRequestFinishAgreement(@ModelAttribute AgreementDto agreementDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), agreementDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        if (minuteService.countPendingRequestByAgreementId(agreementDto.getId()) > 0) {
            response.setHasError(true);
            response.setMessage("No es posible registrar la solicitud. El acuerdo tiene una solicitud pendiente.");
            return response;
        }

        try {
            response = minuteService.doRequestFinishAgreement(agreementDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doRequestFinishAgreement", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/shared/agreement/authRejFinishRequest", method = RequestMethod.POST)
    public ModelAndView showAuthRejectFinishRequest(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/authRejFinishRequest");
        Gson gson = new Gson();
        model.addObject("agreementId", id);
        model.addObject("agreementData", gson.toJson(minuteService.getGrlAgreementInfoById(id)));
        model.addObject("requestData", gson.toJson(minuteService.getLastRequestInfoByIdAgreementIdType(id, Constants.REQUEST_AGREEMENT_TYPE_FINISH)));
        return model;
    }

    @RequestMapping(value = "/shared/agreement/doAuthRejFinishRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAuthRejFinishAgreementRequest(@ModelAttribute RequestAgreementDto requestAgreementDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), requestAgreementDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        try {
            response = minuteService.doAuthRejFinishAgreementRequest(requestAgreementDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthRejFinishAgreementRequest", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/shared/agreement/responseFinishRequest", method = RequestMethod.POST)
    public ModelAndView showResponseFinishAgreementRequest(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/agreement/responseRequestAgreement");
        Gson gson = new Gson();
        model.addObject("agreementData", gson.toJson(minuteService.getGrlAgreementInfoById(id)));
        model.addObject("responseData", gson.toJson(minuteService.getLastResponseInfoByIdAgreementIdType(id, Constants.REQUEST_AGREEMENT_TYPE_FINISH)));
        return model;
    }

    @RequestMapping(value = "/shared/minute/finishRequestMinute", method = RequestMethod.POST)
    public ModelAndView showMinuteFinishRequest(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/finishRequest");
        model.addObject("minuteId", id);
        return model;
    }

    @RequestMapping(value = "/shared/minute/doFinishRequestMinute", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doRequestFinishMinute(@ModelAttribute RequestAgreementDto requestDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), requestDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        if (minuteService.countPendingRequestByMinuteId(requestDto.getId()) > 0) {
            response.setHasError(true);
            response.setMessage("No es posible registrar la solicitud. La minuta tiene una solicitud pendiente.");
            return response;
        }

        try {
            response = minuteService.doRequestFinishMinute(requestDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doRequestFinishAgreement", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/shared/minute/authRejFinishRequest", method = RequestMethod.POST)
    public ModelAndView showAuthRejectFinishRequestMinute(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/authRejFinishRequest");
        Gson gson = new Gson();
        model.addObject("minuteId", id);
        model.addObject("minuteData", gson.toJson(minuteService.getMinuteGrlDataById(id)));
        model.addObject("requestData", gson.toJson(minuteService.getLastRequestInfoByMinuteIdType(id, Constants.REQUEST_AGREEMENT_TYPE_FINISH)));
        return model;
    }

    @RequestMapping(value = "/shared/minute/doAuthRejFinishRequest", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doAuthRejFinishMinuteRequest(@ModelAttribute RequestAgreementDto requestAgreementDto) {
        ResponseMessage response = new ResponseMessage();

        User user = userRepository.findOne(sharedUserService.GetLoggedUserId());

        if (sharedUserService.isValidPasswordForUser(user.getId(), requestAgreementDto.getPassword()) == false) {
            response.setHasError(true);
            response.setMessage("La contraseña no corresponde al usuario en sesión");
            return response;
        }

        try {
            response = minuteService.doAuthRejFinishMinuteRequest(requestAgreementDto);
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doAuthRejFinishAgreementRequest", sharedUserService);
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error, intente nuevamente.");
        } finally {
            return response;
        }
    }

    @RequestMapping(value = "/shared/minute/responseFinishRequest", method = RequestMethod.POST)
    public ModelAndView showResponseMinuteFinishRequest(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/shared/minute/responseRequestMinute");
        Gson gson = new Gson();
        model.addObject("minuteData", gson.toJson(minuteService.getMinuteGrlDataById(id)));
        model.addObject("responseData", gson.toJson(minuteService.getLastResponseInfoByMinuteIdType(id, Constants.REQUEST_AGREEMENT_TYPE_FINISH)));
        return model;
    }

    @RequestMapping(value = "/shared/minute/getEmployees", method = RequestMethod.GET)
    public
    @ResponseBody
    String searchUsr(@RequestParam(required = true) String str) {
        String param = str + "%";
        List<SelectList> lst = sharedUserService.getUserRoles(param, null, null);
        return new Gson().toJson(lst);
    }

}

