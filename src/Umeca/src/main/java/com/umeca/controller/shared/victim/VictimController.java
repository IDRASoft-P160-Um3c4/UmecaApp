package com.umeca.controller.shared.victim;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.dto.victim.VictimDto;
import com.umeca.model.entities.shared.Victim;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 29/10/14
 * Time: 04:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class VictimController {

    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;
    @Autowired
    private VictimService victimService;


    @RequestMapping(value = {"/shared/victim/listVictim"}, method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel listReference(@ModelAttribute JqGridFilterModel opts, @RequestParam(required = true) Long idCase) {
        List<String> roles = userService.getLstRolesByUserId(userService.GetLoggedUserId());
        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("idCase", idCase.toString(), JqGridFilterModel.COMPARE_EQUAL);
        opts.extraFilters.add(extraFilter);
        JqGridResultModel result = null;
        if(roles.contains(Constants.ROLE_EVALUATION_MANAGER) || roles.contains(Constants.ROLE_REVIEWER)){
            result = gridFilter.find(opts, new SelectFilterFields() {
                @Override
                public <T> List<Selection<?>> getFields(final Root<T> r) {
                    return new ArrayList<Selection<?>>() {{
                        add(r.get("id"));
                        add(r.get("fullname"));
                        add(r.join("relationship").get("name").alias("relName"));
                        add(r.get("age"));
                        add(r.get("phone"));
                        add(r.join("address").get("addressString"));
                        add(r.get("specification"));
                    }};
                }

                @Override
                public <T> Expression<String> setFilterField(Root<T> r, String field) {
                    if (field.equals("idCase")) {
                        return r.join("criminalProceeding").join("meeting").join("caseDetention").get("id");
                    } else if (field.equals("relName")) {
                        return r.join("relationship").get("name");
                    }

                    return null;
                }
            }, Victim.class, VictimDto.class);
        }else if(roles.contains(Constants.ROLE_SUPERVISOR)|| roles.contains(Constants.ROLE_SUPERVISOR_MANAGER)){

        }
        return result;

    }

    @RequestMapping(value = "/victim/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        return victimService.upsertVictim(id, idCase);
    }

    @RequestMapping(value = "/victim/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsertAddress(@ModelAttribute Victim victim, @RequestParam Long idCase, @RequestParam(required = false) String type) {
        return victimService.doUpsertVictim(victim, idCase,type);
    }

    @RequestMapping(value = "/victim/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doDeleteAddress(@RequestParam Long id) {
        return victimService.deleteVictim(id);
    }
}
