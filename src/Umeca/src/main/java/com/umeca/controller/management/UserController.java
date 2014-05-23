package com.umeca.controller.management;

import com.google.gson.Gson;
import com.umeca.infrastructure.PojoValidator;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.infrastructure.security.BcryptUtil;
import com.umeca.model.ResponseMessage;
import com.umeca.model.ResponseUniqueMessage;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.account.UserUnique;
import com.umeca.model.entities.account.UserView;
import com.umeca.repository.account.RoleRepository;
import com.umeca.repository.account.UserRepository;
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

        //opts.extraFilters = new ArrayList<>();
        //JqGridRulesModel extraFilter = new JqGridRulesModel("enabled", "1", JqGridFilterModel.COMPARE_EQUAL);
        //opts.extraFilters.add(extraFilter);

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>(){{
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
                if(field.equals("role"))
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
    public ModelAndView upsert(@RequestParam(required = false) Long id){
        ModelAndView modelView = new ModelAndView("/management/user/upsert");

        Gson gson = new Gson();
        String lstRoles = gson.toJson(repositoryRole.findSelectList());

        modelView.addObject("lstRoles", lstRoles);

        if(id != null)
        {
            User model = repositoryUser.findOne(id);
            modelView.addObject("model", model);

            if(model.getRoles().size() > 0 )
                modelView.addObject("roleId", model.getRoles().get(0).getId());
        }

        return modelView;
    }



    @Qualifier("qUserRepository")
    @Autowired
    UserRepository repositoryUser;

    @RequestMapping(value = "/management/user/doUpsert", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage doUpsert(@ModelAttribute User modelNew){

        ResponseMessage response = new ResponseMessage();

        try{
            User model;

            if(modelNew.getId() > 0) {
                model = repositoryUser.findOne(modelNew.getId());
                model.setUsername(modelNew.getUsername());
                model.setEmail(modelNew.getEmail());
                model.setFullname(modelNew.getFullname());
                model.getRoles().clear();
                model.setRoles(modelNew.getRoles());

                if(modelNew.getHasChangePass()){
                    model.setPassword(modelNew.getPassword());
                    model.setConfirm(modelNew.getConfirm());
                }
                else{
                    model.setConfirm(model.getPassword());
                }
            }
            else{
                model = modelNew;
                model.setEnabled(true);
            }

            ResponseMessage resp = PojoValidator.validate(model);
            if(resp != null)
                return resp;

            if(model.getId() <= 0 || modelNew.getHasChangePass())
                model.setPassword(BcryptUtil.encode(modelNew.getPassword()));

            Long idUser = repositoryUser.findIdByUsername(model.getUsername());

            if(idUser != null && idUser != model.getId()){
                response.setHasError(true);
                response.setMessage("El usuario ya existe, por favor elija otro usuario");
                return response;
            }

            repositoryUser.save(model);
            response.setHasError(false);
        }catch (Exception ex){
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }

        return response;
    }

    @RequestMapping(value = "/management/user/isUserAvailable", method = RequestMethod.POST)
    public @ResponseBody
    ResponseUniqueMessage isUserAvailable(@RequestBody UserUnique model){

        ResponseUniqueMessage response = new ResponseUniqueMessage();

        try{
            Long count = repositoryUser.countByUsername(model.getUsername(), model.getId());
            if(count != null && count > 0)
                response.setUnique(false);
            else
                response.setUnique(true);

        }catch (Exception ex){
            response.setUnique(false);
        }

        return response;
    }


    @RequestMapping(value = "/management/user/disable", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage disable(@RequestParam Long id){
        return EnableUser(id, false);
    }


    @RequestMapping(value = "/management/user/enable", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage enable(@RequestParam Long id){
        return EnableUser(id, true);
    }

    private ResponseMessage EnableUser(Long id, boolean bIsEnabled) {
        ResponseMessage response = new ResponseMessage();
        try{
            User model = repositoryUser.findOne(id);

            if(model == null){
                response.setHasError(true);
                response.setMessage("Por favor revise que el registro exista e intente de nuevo");
                return response;
            }

            model.setEnabled(bIsEnabled);
            repositoryUser.save(model);
            response.setHasError(false);

        }catch (Exception ex){
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que el registro exista e intente de nuevo");
        }
        return response;
    }
}