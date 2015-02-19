package com.umeca.controller.director;

import com.google.gson.Gson;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.agenda.ActivityAgendaRequest;
import com.umeca.model.shared.*;
import com.umeca.repository.catalog.PriorityRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.director.ActivityAgendaService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class TaskDiaryController {

    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    PriorityRepository priorityRepository;

    @RequestMapping(value = "/director/taskDiary/index", method = RequestMethod.GET)
    public ModelAndView index() {
        Gson gson = new Gson();
        ModelAndView model = new ModelAndView("/director/taskDiary/index");
        List<SelectList> priorities = priorityRepository.getPriorities();
        model.addObject("lstPriorities", new Gson().toJson(priorities));
        model.addObject("lstPrioritiesDt", priorities);
        return model;
    }

    @Autowired
    ActivityAgendaService agendaService;

    @RequestMapping(value = "/director/taskDiary/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsert(@RequestBody ActivityAgendaRequest model){
        ResponseMessage response = new ResponseMessage();
        response.setTitle("Agenda de actividades " + model.getLstActivitiesUpsert());

        try {
            User user = new User();
            if(sharedUserService.isValidUser(user, response) == false)
                return response;

            if(agendaService.doUpsertDelete(sharedUserService, model, user, response) == false)
                return response;

            if(model.getActsIns() == 0 && model.getActsUpd() == 0 && model.getActsDel() == 0){
                response.setMessage("No fue posible realizar la operaci&oacute;n, revise que su informaci&oacute;n est&eacute; correcta o que la(s)" +
                        " actividad(es) que desea modificar y/o eliminar a&uacute;n no est&eacute;(n) finalizada(s) o sean actividades actuales y/o futuras.");
                response.setHasError(true);

            }else{
                Gson gson = new Gson();
                response.setReturnData(gson.toJson(model.getLstActivitiesUpserted()));
                response.setHasError(false);
                response.setMessage("La operaci&oacute;n se realiz&oacute; de forma correcta." +
                        (model.getActsIns() == 0 ? "" : ("<br/>" + model.getActsIns() + " actividad(es) fue(ron) insertada(s)")) +
                        (model.getActsUpd() == 0 ? "" : ("<br/>" + model.getActsUpd() + " actividad(es) fue(ron) actualizada(s)")) +
                        (model.getActsDel() == 0 ? "" : ("<br/>" + model.getActsDel() + " actividad(es) fue(ron) eliminada(s)")));

            }
            return response;
        }catch (Exception ex){
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
        }

        response.setMessage("Se present&oacute; un error inesperado. Por favor revise la informaci&oacute;n e intente de nuevo");
        return response;

    }
}
