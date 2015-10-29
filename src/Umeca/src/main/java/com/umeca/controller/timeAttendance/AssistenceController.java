package com.umeca.controller.timeAttendance;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.SelectFilterFields;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.model.ResponseMessage;
import com.umeca.model.dto.timeAttendance.AttendanceLogDto;
import com.umeca.model.dto.timeAttendance.JustifyDto;
import com.umeca.model.entities.timeAttendance.DelayJustification;
import com.umeca.model.entities.timeAttendance.DelayJustificationView;
import com.umeca.repository.humanResources.AssistenceRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.device.AssistenceService;
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
public class AssistenceController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    private AssistenceRepository assistenceRepository;

    @Autowired
    private AssistenceService assistenceService;

    @Autowired
    SharedUserService sharedUserService;

//    @Autowired
//    private SharedLogExceptionService logException;

    @RequestMapping(value = "/humanResources/assistence/index", method = RequestMethod.GET)
    public String Index(){
        return "/humanResources/timeAttendance/assistence/index";
    }

    @RequestMapping(value = "/humanResources/assistence/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                 return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.get("eventDate"));
                    add(r.get("justified"));
                    add(r.get("name"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("name"))
                    return r.get("name");
                if (field.equals("eventDate"))
                    return r.get("eventDate");
                return null;
            }
        }, DelayJustificationView.class, AttendanceLogDto.class);

        return result;
    }

    @RequestMapping(value = "/humanResources/assistence/upsertAssistence", method = RequestMethod.POST )
    public ModelAndView upsertAssistence(@RequestParam(required = true) Long id){
        ModelAndView model = new ModelAndView("/humanResources/timeAttendance/assistence/upsert");

        Gson gson = new Gson();
        JustifyDto justifyDto = assistenceRepository.getJustify(id);

        if (justifyDto != null){
            DelayJustification delayJustification = assistenceRepository.getDelayJustification(id);

            if (delayJustification != null) {
                justifyDto.setComment(delayJustification.getComment());
                justifyDto.setIdAttendanceLog(delayJustification.getIdAttendanceLog());
            }
            model.addObject("AttendanceLog", gson.toJson(justifyDto));
        }


        return model;
    }

    @RequestMapping(value = "/humanResources/assistence/doUpsertJustify", method = RequestMethod.POST)
    public ResponseMessage doUpsertJustify(@ModelAttribute JustifyDto justifyDto){
        ResponseMessage response = new ResponseMessage();
        try {
            Long idUser = sharedUserService.GetLoggedUserId();

            if (sharedUserService.isValidPasswordForUser(idUser, justifyDto.getPassword())) {
                justifyDto.setIdUser(idUser);
                response = assistenceService.upsertDevice(justifyDto);
            }
            else
                throw new SecurityException("La constraseña no es válida");
        }
        catch(Exception ex) {
            response.setHasError(true);
            response.setMessage("Ha ocurrido un error: " + ex.getMessage());
        }
        finally {
            return response;
        }
    }
}