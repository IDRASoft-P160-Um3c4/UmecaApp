package com.umeca.service.reviewer;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.Question;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.Section;
import com.umeca.model.entities.reviewer.View.TechnicalReviewInfoFileView;
import com.umeca.model.shared.Constants;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.VerificationRepository;
import com.umeca.service.account.SharedUserService;
import com.umeca.service.shared.SharedLogExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("technicalReviewService")
public class TechnicalReviewServiceImpl implements TechnicalReviewService {

    @Autowired
    VerificationRepository verificationRepository;

    @Autowired
    SharedLogExceptionService logException;

    @Autowired
    SharedUserService sharedUserService;

    @Override
    public QuestionarySectionView getSections(QuestionarySection obj) {

        QuestionarySectionView sectionView = new QuestionarySectionView();
        List<QuestionarySectionView> listChilds = new ArrayList<>();
        List<QuestionView> listQuestions = new ArrayList<>();

        if (obj.getQuestions() != null && obj.getQuestions().size() > 0) {

            Collections.sort(obj.getQuestions(), Question.questComparator);

            for (Question quest : obj.getQuestions()) {
                QuestionView questionView = new QuestionView();
                questionView.setQuestionId(Objects.toString(quest.getId()));
                questionView.setQuestionText(quest.getQuestion());
                questionView.setPtsValue(Objects.toString(quest.getValue()));
                questionView.setType(quest.getQuestionType().getName());
                listQuestions.add(questionView);
            }
        }

        if (obj.getChilds() != null && obj.getChilds().size() > 0) {

            for (QuestionarySection child : obj.getChilds()) {
                listChilds.add(getSections(child));
            }
        }

        sectionView.setChilds(listChilds);
        sectionView.setSectionName(obj.getName());
        sectionView.setTabId(obj.getCode());
        sectionView.setExtras(obj.getExtras());
        sectionView.setQuestions(listQuestions);

        return sectionView;
    }

    public List<QuestionReviewRel> generateQuesRevRel(TechnicalReview technicalReview, String txtListSel) {

        Gson conv = new Gson();

        List<QuestionReviewRel> lstQuesRevRel = new ArrayList<>();
        List<String> questionsId = new ArrayList<>();

        Type type = new TypeToken<List<String>>() {
        }.getType();

        try {
            questionsId = conv.fromJson(txtListSel, type);

            for (String cad : questionsId) {

                QuestionReviewRel rel = new QuestionReviewRel();

                Question quest = new Question();
                quest.setId(Long.parseLong(cad, 10));
                rel.setQuestion(quest);

                rel.setTechnicalReview(technicalReview);
                lstQuesRevRel.add(rel);
            }
        } catch (Exception e) {
            logException.Write(e,this.getClass(),"generateQuesRevRel",sharedUserService);
            System.out.println(e.getMessage());
        }

        return lstQuesRevRel;
    }


    public String genLstJsonQuesSel(List<QuestionReviewRel> lstQuesSelPrev) {

        Gson conv = new Gson();

        List<Long> lstView = new ArrayList<>();

        for (QuestionReviewRel quesSel : lstQuesSelPrev) {
            lstView.add(quesSel.getQuestion().getId());
        }

        return conv.toJson(lstView);
    }

    @Autowired
    FieldMeetingSourceRepository fieldMeetingSourceRepository;

    public TechnicalReviewInfoFileView fillInfoFile(Long idVerification) {
        TechnicalReviewInfoFileView file = new TechnicalReviewInfoFileView();

        Verification ver = verificationRepository.findOne(idVerification);

        Meeting meeting = ver.getCaseDetention().getMeeting();

        file.setIdFolder(ver.getCaseDetention().getIdFolder());
        file.setName(meeting.getImputed().getName());
        file.setLastNameP(meeting.getImputed().getLastNameP());
        file.setLastNameM(meeting.getImputed().getLastNameM());

        file.setAddress(meeting.getImputedHomes().get(0).getAddress().getAddressString());
        String template = "Campo: {0} <br/>Valor: {1}<br/> Fuente: {2}<br/>Raz&oacute;n: {3}<br/>";
        for(int i=0; i< Constants.NAMES_MEETING.length;i++){
            List<FieldMeetingSource> listFMS = fieldMeetingSourceRepository.getAllFinalByIdCaseAndSectionCode(ver.getCaseDetention().getId(), (i + 1));
            if(listFMS.size()>0 && listFMS.get(0)!=null){
                Section section = new Section(listFMS.get(0).getFieldVerification().getSection());
                for(FieldMeetingSource fms: listFMS){
                    String v = template;
                    v = v.replace("{0}",fms.getFieldVerification().getFieldName());
                    v = v.replace("{2}",fms.getSourceVerification().getFullName());
                    if(fms.getReason()==null){
                        fms.setReason("Sin raz&oacute;n registrada.");
                    }
                    v = v.replace("{3}",fms.getReason());
                    if(fms.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)){
                        v = v.replace("{1}",Constants.UNABLE_VERIF_TEXT_DOC);
                    }else{
                        v = v.replace("{1}",fms.getValue());
                    }
                    section.getValues().add(v);
                }
                file.getSections().add(section);
            }
        }



        List<String> sourcesTxt = new ArrayList<>();

        for (SourceVerification source : ver.getSourceVerifications()) {
            if (source.getVisible() == true && source.getAuthorized() == true) {
                StringBuilder sb = new StringBuilder();
                sb.append(source.getFullName());
                sb.append(" relaci&oacute;n con el imputado ");
                sb.append(source.getRelationship().getName());
                sourcesTxt.add(sb.toString());
            }
        }

        file.setSources(sourcesTxt);

        List<String> questSelTxt = new ArrayList<>();

        for (QuestionReviewRel rel : ver.getCaseDetention().getTechnicalReview().getQuestionsSel()) {
            if (!questSelTxt.contains(rel.getQuestion().getQuestion()))
                questSelTxt.add(rel.getQuestion().getQuestion());
        }

        file.setQuestSel(questSelTxt);

        TechnicalReview technicalReview = ver.getCaseDetention().getTechnicalReview();
        file.setComment(technicalReview.getComments());

        String risk = "";
        Integer total = technicalReview.getTotalRisk();
        if(total<-15){
            risk = "Riesgo alto! Libertad muy dif&iacute;cil de cumplir.";
        }else if(total>-16 && total>0){
            risk = "Riesgo medio! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        }else if(total>-1 && total<10){
            risk = "Riesgo bajo! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        }else if(total>9){
            risk = "Riesgo mínimo! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        }

        file.setResult(risk);

        return file;
    }
}
