package com.umeca.controller.director;

import com.google.gson.Gson;
import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.infrastructure.jqgrid.model.JqGridResultModel;
import com.umeca.infrastructure.jqgrid.model.JqGridRulesModel;
import com.umeca.infrastructure.jqgrid.operation.GenericJqGridPageSortFilter;
import com.umeca.model.ResponseMessage;
import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.director.view.CaseView;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.CriminalProceedingView;
import com.umeca.model.entities.reviewer.View.MeetingView;
import com.umeca.model.entities.reviewer.View.PersonSocialNetworkView;
import com.umeca.model.entities.shared.Message;
import com.umeca.model.shared.Constants;
import com.umeca.repository.reviewer.CaseRequestRepository;
import com.umeca.repository.shared.SelectFilterFields;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.reviewer.MeetingService;
import com.umeca.service.reviewer.ScheduleService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DetailRequestController {


    @RequestMapping(value = "/director/caseRequest/show", method = RequestMethod.GET)
    public String index() {
        return "/director/caseRequest/show";
    }

    @Autowired
    SharedUserService userService;
    @Autowired
    private GenericJqGridPageSortFilter gridFilter;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Autowired
    CaseRequestRepository caseRequestRepository;

    @RequestMapping(value = "/director/caseRequest/list", method = RequestMethod.POST)
    public
    @ResponseBody
    JqGridResultModel list(@ModelAttribute JqGridFilterModel opts) {
        JqGridResultModel result = gridFilter.find(opts, new SelectFilterFields() {
            @Override
            public <T> List<Selection<?>> getFields(final Root<T> r) {
                final javax.persistence.criteria.Join<Message, Case> joinMeCa = r.join("requestMessage").join("caseDetention");
                final javax.persistence.criteria.Join<Case, Imputed> joinCaIm = joinMeCa.join("meeting").join("imputed");
                return new ArrayList<Selection<?>>() {{
                    add(joinMeCa.get("id"));
                    add(joinMeCa.get("idFolder"));
                    add(joinCaIm.get("name"));
                    add(joinCaIm.get("lastNameP"));
                    add(joinCaIm.get("lastNameM"));
                    add(joinCaIm.get("gender"));
                    add(joinMeCa.join("status").get("description").alias("statusString"));
                }};
            }
            @Override
            public <T> Expression<String> setFilterField(Root<T> r, String field) {
                if (field.equals("idFolder"))
                    return r.join("requestMessage").join("caseDetention").get("idFolder");
                else if (field.equals("fullname"))
                    return r.join("requestMessage").join("caseDetention").join("meeting").join("imputed").get("name");
                else if (field.equals("statusString")){
                    return r.join("requestMessage").join("caseDetention").join("status").get("description");
                }else
                    return null;
            }
        }, CaseRequest.class, CaseView.class);

        return result;

    }


    @RequestMapping(value = "/director/caseRequest/showDetail", method = RequestMethod.POST)
    public ModelAndView upsertSocialNetwork(@RequestParam(required = false) Long id, @RequestParam(required = true) Long idCase) {
        ModelAndView model = new ModelAndView("/reviewer/meeting/socialNetwork/upsert");
//        Gson gson = new Gson();
//        model.addObject("lstRelationship", gson.toJson(relationshipRepository.findNotObsolete()));
//        model.addObject("lstElection", gson.toJson(electionRepository.findAll()));
//        model.addObject("lstDocumentType", gson.toJson(documentTypeRepository.findNotObsolete()));
//        if (id != null) {
//            PersonSocialNetwork p = personSocialNetworkRepository.findOne(id);
//            PersonSocialNetwork pView = new PersonSocialNetwork();
//            pView.setId(p.getId());
//            pView.setName(p.getName());
//            pView.setAge(p.getAge());
//            pView.setPhone(p.getPhone());
//            pView.setAddress(p.getAddress());
//            pView.setSpecification(p.getSpecification());
//            model.addObject("p", gson.toJson(pView));
//            model.addObject("relId", gson.toJson(p.getRelationship().getId()));
//            model.addObject("docId", gson.toJson(p.getDocumentType().getId()));
//            model.addObject("depId", gson.toJson(p.getDependent().getId()));
//            model.addObject("livId", gson.toJson(p.getLivingWith().getId()));
//        }
//        model.addObject("idCase", idCase);
        return model;
    }

}
