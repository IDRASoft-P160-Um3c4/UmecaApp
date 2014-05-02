package com.umeca.controller.management;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;
import com.umeca.infrastructure.jqgrid.operation.JqGridPageSortFilter;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.shared.EntityGrid;
import com.umeca.repository.Account.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 9:44 AM
 */

@Controller
public class RoleController {

    @RequestMapping(value = "/management/role/index", method = RequestMethod.GET)
    public String index(){
        return "/management/role/index";
    }

    @Autowired
    private RoleRepository repository;

    @RequestMapping(value = "/management/role/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        return new JqGridPageSortFilter<Role>().DoQuery(opts, repository, null);
    }
}
