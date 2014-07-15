package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Questionary;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.ForTechnicalReviewGrid;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileView;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.shared.QuestionaryRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.reviewer.TechnicalReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TechnicalReviewController {

    @RequestMapping(value = "/reviewer/technicalReview/index", method = RequestMethod.GET)
    public String index() {
        return "reviewer/technicalReview/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Qualifier("qQuestionaryRepository")
    @Autowired
    QuestionaryRepository questionaryRepository;

    @Qualifier("qTechnicalReviewRepository")
    @Autowired
    TechnicalReviewRepository technicalReviewRepository;

    @Qualifier("qVerificationRepository")
    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    TechnicalReviewService technicalReviewService;

    @RequestMapping(value = "/reviewer/technicalReview/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {

                final javax.persistence.criteria.Join<Verification, Case> joinCd = r.join("caseDetention");
                final javax.persistence.criteria.Join<Meeting, Imputed> joinIm = r.join("meetingVerified").join("imputed");

                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(joinCd.join("status").get("name"));
                    add(joinCd.get("idFolder"));
                    add(joinCd.get("idMP"));
                    add(joinIm.get("name"));
                    add(joinIm.get("lastNameP"));
                    add(joinIm.get("lastNameM"));
                }};
            }


            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");

                return null;
            }
        }, Verification.class, ForTechnicalReviewGrid.class);

        return result;
    }

    @RequestMapping(value = "/reviewer/technicalReview/technicalReview", method = RequestMethod.GET)
    public ModelAndView technicalReview(Long id) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/technicalReview");

        try {

            Gson gson = new Gson();
            List<QuestionarySectionView> listaSecciones = new ArrayList<>();

            Questionary quest = questionaryRepository.findByCode(Constants.TECHNICAL_REVIEW_QUESTIONARY_CODE);

            for (QuestionarySection padre : quest.getSections()) {
                listaSecciones.add(technicalReviewService.getSections(padre));
            }

            model.addObject("listaSecc", gson.toJson(listaSecciones));

            Verification verification = verificationRepository.findById(id);
            Case caseDet = verification.getCaseDetention();
            TechnicalReview tecRev_prev = caseDet.getTechnicalReview();
            Imputed imputed = caseDet.getMeeting().getImputed();
            String fullname = imputed.getName() + " " + imputed.getLastNameP() + " " + imputed.getLastNameM();

            System.out.println(caseDet.getIdFolder());
            model.addObject("idVerification", verification.getId());
            model.addObject("imputedFullName", fullname);
            model.addObject("foldId", verification.getCaseDetention().getIdFolder());

            if (tecRev_prev != null) {
                model.addObject("hasRevTec", true);
                model.addObject("showRisk", true);
                model.addObject("lstQuestSel_prev", technicalReviewService.genLstJsonQuesSel(tecRev_prev.getQuestionsSel()));
                model.addObject("totRisk_prev", tecRev_prev.getTotalRisk());
                model.addObject("lstSubtotTxt_prev", tecRev_prev.getSubtotalsTxt());
                model.addObject("comments", tecRev_prev.getComments());
            } else {
                model.addObject("hasRevTec", false);
                model.addObject("showRisk", false);
                model.addObject("lstQuestSel_prev", "[]");
                model.addObject("totRisk_prev", 0);
                model.addObject("lstSubtotTxt_prev", "[]");
                model.addObject("comments", "");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los datos para la vista technical review !!!!!\n\n");
            System.out.println(e.getMessage());
            return null;
        }

        return model;
    }

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @RequestMapping(value = "/reviewer/technicalReview/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute TechnicalReview result) {

        ResponseMessage response = new ResponseMessage();

        //validar que no exista la evaluacion para guardarla

        try {
            result.setQuestionsSel(technicalReviewService.generateQuesRevRel(result, result.getTxtListQuest()));
            Case caseDetention = verificationRepository.findById(result.getIdVerification()).getCaseDetention();
            caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_TECHNICAL_REVIEW));
            result.setCaseDetention(caseDetention);
            technicalReviewRepository.save(result);
            response.setHasError(false);
            response.setUrlToGo("index.html");
        } catch (Exception ex) {
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo.");
        }

        return response;
    }

    @RequestMapping(value = "/reviewer/technicalReview/generateFile", method = RequestMethod.GET)
    public ModelAndView generateFile(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/infoFile");

        TechnicalReviewInfoFileView dataFile = technicalReviewService.fillInfoFile(id);

        model.addObject("data",dataFile);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment; filename=\"datos_opinion_tecnica.doc\"");



            return model;
    }


}
