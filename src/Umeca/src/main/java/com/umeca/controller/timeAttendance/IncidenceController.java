package com.umeca.controller.timeAttendance;

import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 10/23/2015.
 */
@Controller
public class IncidenceController {

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @RequestMapping(value = "/humanResources/incidence/index", method = RequestMethod.GET)
    public String Index(){
        return "/humanResources/timeAttendance/incidence/index";
    }
}
