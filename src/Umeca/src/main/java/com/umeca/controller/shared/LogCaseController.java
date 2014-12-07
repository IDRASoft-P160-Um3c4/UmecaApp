package com.umeca.controller.shared;

import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.shared.LogCase;
import com.umeca.model.shared.ConstantsLogCase;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.LogCaseService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 20/11/14
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogCaseController {
    @Autowired
    SharedUserService userService;
    @Autowired
    LogCaseService logCaseService;
    @Autowired
    SharedLogExceptionService logException;
    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/shared/logCase/index", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView index(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/shared/logCase/index");
        logCaseService.fillgeneralDataLog(id,model);
        logCaseService.fillLogCase(id, model);
        model.addObject("titleDoc","Bit&aacute;cora del caso");
        return model;
    }

    @RequestMapping(value = "/shared/logCase/addActivity", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView addActivity(@RequestParam Long id){
        ModelAndView model = new ModelAndView("/shared/logCase/addActivity");
        model.addObject("caseId",id);
        return model;
    }

    @RequestMapping(value = "/shared/logCase/doNewLogSpontaneous", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doNewLogSpontaneous(@ModelAttribute LogCase logCase, @RequestParam Long id) {
        try{
            logCaseService.addLog(ConstantsLogCase.SPONTANEOUS_ACTIVITY,id,logCase);
            String message= logCaseService.getLogCase(id);
            return new ResponseMessage(false,message);
        }catch (Exception e){
            logException.Write(e, this.getClass(), "addLog", sharedUserService);
            return new ResponseMessage(true,"Ha ocurrido un error al guardar la actividad.");
        }
    }

    @RequestMapping(value = "/shared/logCase/generateFile", method = RequestMethod.GET)
    public ModelAndView generateFile(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/shared/logCase/logCaseFile");
        logCaseService.fillModelLogCaseFile(id, model);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"bitacora.doc\"");


        return model;
    }
}
