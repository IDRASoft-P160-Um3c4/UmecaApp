 package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Questionary;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.ForTechnicalReviewGrid;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileAllSourcesView;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileView;
import com.umeca.model.entities.supervisor.HearingFormatView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.CaseRepository;
import com.umeca.repository.StatusCaseRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.repository.shared.QuestionaryRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.TechnicalReviewService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @RequestMapping(value = "/reviewer/technicalReview/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {

        opts.extraFilters = new ArrayList<>();
        JqGridRulesModel extraFilter = new JqGridRulesModel("statusName",
                new ArrayList<String>() {{
                    add(Constants.CASE_STATUS_VERIFICATION_COMPLETE);
                    add(Constants.CASE_STATUS_TECHNICAL_REVIEW);
                    add(Constants.CASE_STATUS_EDIT_TEC_REV);
                }}, JqGridFilterModel.COMPARE_IN
        );
        opts.extraFilters.add(extraFilter);

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
                else if (field.equals("statusName"))
                    return r.join("caseDetention").join("status").get("name");
                else
                    return null;
            }
        }, Verification.class, ForTechnicalReviewGrid.class);


        return result;
    }

    @RequestMapping(value = "/reviewer/technicalReview/technicalReview", method = RequestMethod.GET)
    public ModelAndView technicalReview(Long id, Integer returnId) {

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
            Imputed imputed = caseDet.getVerification().getMeetingVerified().getImputed();
            String fullname = imputed.getName() + " " + imputed.getLastNameP() + " " + imputed.getLastNameM();

            System.out.println(caseDet.getIdFolder());
            model.addObject("idVerification", verification.getId());
            model.addObject("imputedFullName", fullname);
            model.addObject("foldId", verification.getCaseDetention().getIdFolder());
            model.addObject("idCase", verification.getCaseDetention().getId());

            if (tecRev_prev != null) {
                model.addObject("hasRevTec", true);

                if (caseDet.getStatus().getName().equals(Constants.CASE_STATUS_EDIT_TEC_REV) && caseDet.getMeeting().getReviewer().getId().equals(sharedUserService.GetLoggedUserId()))
                    model.addObject("canEdit", true);
                else
                    model.addObject("canEdit", false);

                if (returnId == null)
                    model.addObject("returnId", -1);
                else
                    model.addObject("returnId", returnId);

                model.addObject("showRisk", true);
                model.addObject("lstQuestSel_prev", technicalReviewService.genLstJsonQuesSel(tecRev_prev.getQuestionsSel()));
                model.addObject("totRisk_prev", tecRev_prev.getTotalRisk());
                model.addObject("lstSubtotTxt_prev", tecRev_prev.getSubtotalsTxt());
                model.addObject("comments", tecRev_prev.getComments());
            } else {
                model.addObject("hasRevTec", false);
                model.addObject("returnId", -1);
                model.addObject("canEdit", false);
                model.addObject("showRisk", false);
                model.addObject("lstQuestSel_prev", "[]");
                model.addObject("totRisk_prev", 0);
                model.addObject("lstSubtotTxt_prev", "[]");
                model.addObject("comments", "");
            }
        } catch (Exception e) {
            logException.Write(e, this.getClass(), "technicalReview", sharedUserService);
            System.out.println("Error al cargar los datos para la vista technical review !!!!!\n\n");
            e.printStackTrace();
            return null;
        }

        return model;
    }

    @Autowired
    StatusCaseRepository statusCaseRepository;

    @Autowired
    CaseRepository caseRepository;

    @RequestMapping(value = "/reviewer/technicalReview/doUpsert", method = RequestMethod.POST)
    public
    @ResponseBody
    @Transactional
    ResponseMessage doUpsert(@ModelAttribute TechnicalReview result) {

        ResponseMessage response = new ResponseMessage();

        try {

            Case caseDetention = verificationRepository.findById(result.getIdVerification()).getCaseDetention();
            TechnicalReview prevTecRev = caseDetention.getTechnicalReview();

            if (prevTecRev != null) {

                prevTecRev.setCaseDetention(null);
                caseDetention.setTechnicalReview(null);
                technicalReviewRepository.delete(prevTecRev);
                caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_VERIFICATION_COMPLETE));
                caseRepository.save(caseDetention);
            }

            result.setLevelRisk(technicalReviewService.calculateLevelRisk(result.getTotalRisk()));
            result.setQuestionsSel(technicalReviewService.generateQuesRevRel(result, result.getTxtListQuest()));
            caseDetention.setStatus(statusCaseRepository.findByCode(Constants.CASE_STATUS_TECHNICAL_REVIEW));
            result.setCaseDetention(caseDetention);
            technicalReviewRepository.save(result);
            response.setHasError(false);
            response.setUrlToGo("index.html");
        } catch (Exception ex) {
            logException.Write(ex, this.getClass(), "doUpsert", sharedUserService);
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo.");
        }

        return response;
    }

    @RequestMapping(value = "/reviewer/technicalReview/generateFile", method = RequestMethod.GET)
    public ModelAndView generateFile(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/infoFile");

        TechnicalReviewInfoFileView dataFile = technicalReviewService.fillInfoFile(id);

        model.addObject("data", dataFile);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"datos_opinion_tecnica.doc\"");


        return model;
    }

    @RequestMapping(value = "/reviewer/technicalReview/generateFileCase", method = RequestMethod.GET)
    public ModelAndView generateFileByCase(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/infoFile");
        Case c = caseRepository.findOne(id);
        TechnicalReviewInfoFileView dataFile = technicalReviewService.fillInfoFile(c.getVerification().getId());
        model.addObject("data", dataFile);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"datos_opinion_tecnica.doc\"");


        return model;
    }

    @RequestMapping(value = "/reviewer/technicalReview/generateFileAllSources", method = RequestMethod.GET)
    public ModelAndView generateFileAllSources(@RequestParam(required = true) Long id, HttpServletResponse response) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/infoAllSources");

        TechnicalReviewInfoFileAllSourcesView dataFile = technicalReviewService.fillInfoFileAllSources(id);

        model.addObject("data", dataFile);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"datos_opinion_tecnica.doc\"");


        return model;
    }


}
