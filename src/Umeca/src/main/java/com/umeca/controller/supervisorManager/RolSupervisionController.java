package com.umeca.controller.supervisorManager;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.supervisorManager.RolActivityRequest;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.Meeting;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.account.UserRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.repository.supervisor.*;
import com.umeca.repository.supervisorManager.RolActivityRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import com.umeca.service.supervisiorManager.RolActivityService;
import com.umeca.service.supervisor.MonitoringPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 12:03 PM
 */

@Controller
public class RolSupervisionController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/supervisorManager/rolSupervision/index", method = RequestMethod.GET)
    public @ResponseBody ModelAndView index(){
        ModelAndView model = new ModelAndView("/supervisorManager/rolSupervision/index");
        Gson gson = new Gson();
        List<SelectList> lstSupervisor = userRepository.getLstValidUsersByRole(Constants.ROLE_SUPERVISOR);
        String sLstGeneric = gson.toJson(lstSupervisor);
        model.addObject("lstSupervisor", sLstGeneric);
        return model;
    }


    @Autowired
    RolActivityService rolActivityService;


    @RequestMapping(value = "/supervisorManager/rolSupervision/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsert(@RequestBody RolActivityRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Rol de supervisión");

        try {
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(rolActivityService.doUpsertDelete(model, user, response) == false)
                return response;

            if(model.getActsIns() == 0 && model.getActsUpd() == 0 && model.getActsDel() == 0){
                response.setMessage("No fue posible realizar la operación, revise que su información esté correcta o reinicie su navegador.");
                response.setHasError(true);
            }else{
                Gson gson = new Gson();
                response.setReturnData(gson.toJson(model.getLstActivitiesUpserted()));
                response.setHasError(false);
                response.setMessage("La operación se realizó de forma correcta." +
                        (model.getActsIns() == 0 ? "" : ("<br/>" + model.getActsIns() + " actividad(es) fue(ron) insertada(s)")) +
                        (model.getActsUpd() == 0 ? "" : ("<br/>" + model.getActsUpd() + " actividad(es) fue(ron) actualizada(s)")) +
                        (model.getActsDel() == 0 ? "" : ("<br/>" + model.getActsDel() + " actividad(es) fue(ron) eliminada(s)")));
            }

            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
        }
        response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        return response;
    }

}

