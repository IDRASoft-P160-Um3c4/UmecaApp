package com.umeca.controller.reviewer;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.Questionary;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.QuestionarySectionView;
import com.umeca.model.entities.reviewer.TechnicalReview;
import com.umeca.model.entities.reviewer.Verification;
import com.umeca.model.entities.reviewer.View.ForTechnicalReviewView;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
import com.umeca.repository.shared.QuestionaryRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.reviewer.TechnicalReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vmware on 06/05/2014.
 */
@Controller
public class TechnicalReviewController {

    @RequestMapping(value = "/reviewer/technicalReview/index", method = RequestMethod.GET)
    public String index() {
        return "/reviewer/technicalReview/index";
    }

    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Qualifier("qQuestionaryRepository")
    @Autowired
    QuestionaryRepository questionaryRepository;

    @Qualifier("qTechnicalReviewRepository")
    @Autowired
    TechnicalReviewRepository technicalReviewRepository;

    @Autowired
    TechnicalReviewService technicalReviewService;

    @RequestMapping(value = "/reviewer/technicalReview/list", method = RequestMethod.POST)
    public @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts){

        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                return new ArrayList<Selection<?>>() {{
                    add(r.get("id"));
                    add(r.join("caseDetention").get("status"));
                    add(r.join("caseDetention").get("idFolder"));
                    add(r.join("caseDetention").get("idMP"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("name"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("lastNameP"));
                    add(r.join("caseDetention").join("meeting").join("imputed").get("lastNameM"));
                }};
            }

            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if(field.equals("idFolder"))
                    return r.join("caseDetention").get("idFolder");

                return null;
            }
        }, Verification.class, ForTechnicalReviewView.class);

        return result;
    }

    @RequestMapping(value = "/reviewer/technicalReview/technicalReview", method = RequestMethod.GET)
    public ModelAndView technicalReview(Long id) {

        ModelAndView model = new ModelAndView("/reviewer/technicalReview/technicalReview");

        Gson gson = new Gson();

        List<QuestionarySectionView> listaSecciones= new ArrayList<>();

        Questionary quest = questionaryRepository.findByCode("OP_TEC");
        if(quest!=null) {

            for(QuestionarySection padre : quest.getSections()){
                listaSecciones.add(technicalReviewService.getSections(padre));
            }
        }



        model.addObject("listaSecc",gson.toJson(listaSecciones));

        model.addObject("idMeeting",id);
        model.addObject("intHasRevTec",0);
        model.addObject("lstQuestSel_prev","[]");
        model.addObject("totRisk_prev",0);

        return model;
    }



    @RequestMapping(value = "/reviewer/technicalReview/doUpsert", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage doUpsert(@ModelAttribute TechnicalReview result){

        ResponseMessage response = new ResponseMessage();

        try{
            result.setAnswersSel(technicalReviewService.generateQuesRevRel(result,result.getTxtListQuest()));
            technicalReviewRepository.save(result);
            System.out.println(result.getId());

        }catch (Exception ex){
            response.setHasError(true);
            response.setMessage("Se presentó un error inesperado. Por favor revise que la información e intente de nuevo");
        }

        return response;
    }
}
