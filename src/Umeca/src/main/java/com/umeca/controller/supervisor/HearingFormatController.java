package com.umeca.controller.supervisor;

import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.Constants;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.service.supervisor.HearingFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HearingFormatController {

    @Qualifier("qArrangementRepository")
    @Autowired
    ArrangementRepository arrangementRepository;

    @Qualifier("qCaseRepository")
    @Autowired
    CaseRepository caseRepository;

    @Autowired
    HearingFormatService hearingFormatService;


    @RequestMapping(value = "/supervisor/hearingFormat", method = RequestMethod.GET)
    public ModelAndView hearingFormat() {
        ModelAndView model = new ModelAndView("/supervisor/hearingFormat");
        List<Arrangement> arrangements = arrangementRepository.findAll();

        Case caseDet = caseRepository.findByIdFolder("F-002");

        return model;
    }

    @RequestMapping(value = "/supervisor/searchCase", method = RequestMethod.POST)
    public @ResponseBody HearingFormatView searchCase(@RequestBody String idFolder) {

        Case caseDet = caseRepository.findByIdFolder("F-002");

        HearingFormatView hearingFormatView = new HearingFormatView();

        hearingFormatView.setArrangementType(Constants.CONDITIONAL_REPRIEVE_HEARING);

        if(caseDet!=null){
            hearingFormatView=hearingFormatService.fillForView(caseDet);
            hearingFormatView.setArrangementType(Constants.MEETING_HEARING);
        }

        return hearingFormatView;
    }

    @RequestMapping(value = "/supervisor/searchArrangement", method = RequestMethod.POST)
    public @ResponseBody List<Arrangement> searchArrangement(@RequestBody Integer idType) {

        List<Arrangement> lstArrangement= arrangementRepository.findByType(idType);
        return lstArrangement;
    }


    @RequestMapping(value = "/supervisor/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result) {

        ResponseMessage response = new ResponseMessage();
        try{

            Case caseDet = caseRepository.findByIdFolder(result.getIdFolderCode());

            HearingFormat hearingFormat;

            if(caseDet!=null)
                hearingFormat=hearingFormatService.fillHearingFormatMeeting(result,caseDet);
            else
                hearingFormat=hearingFormatService.fillHearingFormatConditional(result);

            hearingFormatService.save(hearingFormat);

            response.setHasError(false);
            response.setUrlToGo("/supervisor/index.html");

        }catch (Exception e){

            System.out.println("Error al guardar el formato de audiencia!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());

        }finally {
            return response;
        }

    }


}
