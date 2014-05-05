package com.umeca.controller.management;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.account.UserView;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.shared.SelectFilterFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * Date: 4/30/14
 * Time: 9:44 AM
 */

@Controller
@SuppressWarnings("unchecked")
public class UserController {

    @RequestMapping(value = "/management/user/index", method = RequestMethod.GET)
    public String index(){
        return "/management/user/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/management/user/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
                    add(r.get("id"));
                    add(r.get("username"));
                    add(r.get("fullname"));
                    add(r.get("email"));
                    add(r.get("enabled"));
                    add(r.join("roles").get("role"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("role"))
                    return r.join("roles").get("role");
                return null;
            }
        }, User.class, UserView.class);

        return result;

    }

    @Qualifier("qRoleRepository")
    @Autowired
    RoleRepository repositoryRole;

    @RequestMapping(value = "/management/user/upsert", method = RequestMethod.POST)
    public ModelAndView upsert(@RequestParam(required = false) Long id){
        ModelAndView model = new ModelAndView("/management/user/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(repositoryRole.findSelectList());

        model.addObject("lstRoles", lstRoles);

        return model;
    }
}
