package com.umeca.controller.supervisor;

import com.google.gson.Gson;
import com.umeca.model.ResponseMessage;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.supervisor.ArrangementView;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.repository.catalog.ArrangementRepository;
import com.umeca.service.reviewer.CaseService;
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

    @Autowired
    CaseService caseService;

    @Autowired
    HearingFormatService hearingFormatService;

    @RequestMapping(value = "/supervisor/hearingFormat", method = RequestMethod.GET)
    public String hearingFormat() {

        return "/supervisor/hearingFormat";
    }

    @RequestMapping(value = "/supervisor/searchCase", method = RequestMethod.POST)
    public
    @ResponseBody
    HearingFormatView searchCase(@RequestBody String idFolder) {

        HearingFormatView hearingFormatView;

        Case caseDet = caseService.findByIdFolder(idFolder);
        hearingFormatView = hearingFormatService.fillForView(caseDet, idFolder);

        return hearingFormatView;
    }

    @RequestMapping(value = "/supervisor/searchArrangements", method = RequestMethod.POST)
    public
    @ResponseBody
    String searchArrangement(@RequestBody String folderId) {

        Gson conv = new Gson();
        List<ArrangementView> lstArrangementView = hearingFormatService.getArrangmentLst(folderId);
        String jsonLst = conv.toJson(lstArrangementView);

        return jsonLst;
    }


    @RequestMapping(value = "/supervisor/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute HearingFormatView result) {

        ResponseMessage response = new ResponseMessage();
        try {

            Case caseDet = caseService.findByIdFolder(result.getIdFolderCode());

            HearingFormat hearingFormat;
            hearingFormat = hearingFormatService.fillHearingFormat(result);

            if (caseDet != null) {
                hearingFormat.setCaseDetention(caseDet);
                response = hearingFormatService.save(hearingFormat);
            } else {

                Imputed imp = new Imputed();
                imp.setName(result.getImputedName());
                imp.setLastNameP(result.getImputedFLastName());
                imp.setLastNameM(result.getImputedSLastName());
                imp.setCelPhone(result.getImputedTel());
                imp.setDateBirth(result.getImputedBirthDate());
                caseDet = caseService.generateNewCase(imp);
                caseDet.setIdFolder(result.getIdFolderCode());

                hearingFormat.setCaseDetention(caseDet);
                caseDet.setHearingFormat(hearingFormat);

                caseDet = caseService.save(caseDet);
            }

            response.setHasError(false);
            response.setMessage(caseDet.getIdFolder());

        } catch (Exception e) {

            System.out.println("Error al guardar el formato de audiencia!\n");
            System.out.println(e.getMessage());
            response.setHasError(true);
            response.setMessage(e.getMessage());

        } finally {
            return response;
        }
    }


}
