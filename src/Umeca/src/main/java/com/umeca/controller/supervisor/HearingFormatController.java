package com.umeca.controller.supervisor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HearingFormatController  {

    @RequestMapping(value = "/supervisor/hearingFormat", method = RequestMethod.GET)
    public String index() {
        return "/supervisor/hearingFormat";
    }

}
