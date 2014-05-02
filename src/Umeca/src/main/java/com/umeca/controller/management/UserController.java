package com.umeca.controller.management;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRowsModel;
import com.umeca.infrastructure.jqgrid.operation.EntitySpecification;
import com.umeca.infrastructure.jqgrid.operation.JqGridPageSortFilter;
import com.umeca.model.entities.account.Role;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.account.UserView;
import com.umeca.model.entities.shared.EntityGrid;
import com.umeca.repository.Account.RoleRepository;
import com.umeca.repository.Account.UserRepository;
import com.umeca.repository.Account.UserViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 9:44 AM
 */

@Controller
public class UserController {

    @RequestMapping(value = "/management/user/index", method = RequestMethod.GET)
    public String index(){
        return "/management/user/index";
    }

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserViewRepository repository2;


    @RequestMapping(value = "/management/user/list", method = RequestMethod.POST)
    public @ResponseBody JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){
        //List<String> lstFields = new ArrayList<String>(){{add("id"); add("username");add("enabled");}};
        //return new JqGridPageSortFilter<User>().DoQuery(opts, repository, lstFields);

        JqGridResultModel result = new JqGridResultModel();
        /*EntitySpecification<User> specification = new EntitySpecification<>();
        Page<User> entities;

        if(StringExt.isNullOrWhiteSpace(opts.getSord()) == false && StringExt.isNullOrWhiteSpace(opts.getSidx())== false){
            Sort sort = specification.orderBy(opts);
            entities = repository.findAll(specification.byFilter(opts.filters, 1), new PageRequest(opts.getPage()-1, opts.getRows(), sort));
        }
        else{
            entities = repository.findAll(specification.byFilter(opts.filters, 1), new PageRequest(opts.getPage()-1, opts.getRows()));
        }

        result.setTotal(entities.getTotalPages());
        result.setPage(entities.getNumber()+1);
        result.setRecords(entities.getTotalElements());

        List<JqGridRowsModel> rows = new ArrayList<>();

        for(EntityGrid entity : entities){
            JqGridRowsModel row = new JqGridRowsModel();
            row.setId(entity.getId());
            row.setCell(entity);
            rows.add(row);
        }
        result.setRows(rows);
        */

        /*List<UserView> lst = repository2.findAll(opts);

        for(UserView usr : lst){
            System.out.println(usr.getUsername());
        }*/

        return repository2.findAll(opts);

    }
}
