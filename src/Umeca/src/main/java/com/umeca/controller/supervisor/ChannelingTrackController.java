package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.repository.supervisor.ActivityMonitoringPlanRepository;
import com.umeca.repository.supervisor.ChannelingRepository;
import com.umeca.repository.supervisor.ChannelingTrackingRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisor.ChannelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ChannelingTrackController {

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/supervisor/channelingTrack/index", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/channelingTrack/index";
    }

    @Autowired
    SharedUserService userService;

    @RequestMapping(value = {"/supervisor/channelingTrack/list"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        //Traer actividades que no tienen asistencia en la canalización
        //No se añade la parte de que sea un activityGoalId == Constants.CHANNELING_NOTIFICATION_GOAL_TRACK
        //porque está implicito en la asistencia del mismo
        JqGridRulesModel extraFilter = new JqGridRulesModel("channelingAssistance", "0", JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        /*extraFilter = new JqGridRulesModel("isTerminated", "1", JqGridFilterModel.COMPARE_EQUAL);
        extraFilter.setbData(true);
        opts.extraFilters.add(extraFilter);*/

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final Join<ActivityMonitoringPlan, Case> joinCa = r.join("caseDetention");
                final Join<Case, Meeting> joinMeVe = joinCa.join("meeting");
                final Join<Meeting, Imputed> joinMee = joinMeVe.join("imputed");
                final Join<ActivityMonitoringPlan, Channeling> joinCh = r.join("channeling");
                final Join<Channeling, CatChannelingType> joinChTp = joinCh.join("channelingType");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinCa.get("idMP"));
                    add(joinMee.get("name"));
                    add(joinMee.get("lastNameP"));
                    add(joinMee.get("lastNameM"));
                    add(joinCh.get("name"));
                    add(joinChTp.get("name"));
                    add(r.get("start"));
                    add(r.get("end"));
                    add(r.get("isJustified"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idMP"))
                    return r.join("caseDetention").get("idMP");
                if (field.equals("imputed"))
                    return r.join("caseDetention").get("meeting").get("imputed").get("name");
                if (field.equals("absenceDate"))
                    return r.get("start");
                return null;
            }
        }, ActivityMonitoringPlan.class, ChannelingTrackView.class);

        return result;
    }

    @Autowired
    ChannelingTrackingRepository channelingTrackingRepository;

    @RequestMapping(value = "/supervisor/channelingTrack/upsert", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView upsert(@RequestParam(required = true) Long id) {
        ModelAndView model = new ModelAndView("/supervisor/channelingTrack/upsert");

        ChannelingTrackModel inModel;
        inModel = channelingTrackingRepository.getChannelingTrackByActMonPlanId(id);
        Gson gson = new Gson();
        String sObject = gson.toJson(inModel);
        model.addObject("model", sObject);
        return model;
    }

    @Autowired
    ActivityMonitoringPlanRepository activityMonitoringPlanRepository;

    @RequestMapping(value = "/supervisor/channelingTrack/doUpsert", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute ChannelingTrackModel model) {
        ResponseMessage response = new ResponseMessage();
        try {

            if (!userService.isValidPasswordForUser(userService.GetLoggedUserId(), model.getPassword())) {
                return new ResponseMessage(true, "La contrase&ntilde;a es incorrecta, verifique los datos.");
            }
            if(model.getComment().equals("")){
                return new ResponseMessage(true, "Debes ingresar una raz&oacute;n por la cu&aacute;l quieres realizar la solicitud");
            }

            ActivityMonitoringPlan amp = activityMonitoringPlanRepository.findOne(model.getActMonPlanId());

            if(amp == null){
                return new ResponseMessage(true, "No existe una actividad para justificar.");
            }

            if(amp.getChannelingAssistance() != 0){
                return new ResponseMessage(true, "La actividad si fue realizada, por ello, no es necesario justificarla.");
            }

            amp.setIsJustified(model.getIsJustified());
            amp.setCommentsJustification(model.getComment());
            activityMonitoringPlanRepository.save(amp);
            activityMonitoringPlanRepository.flush();

        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsert", userService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise la información e intente de nuevo");
        }
        return response;
    }
}
