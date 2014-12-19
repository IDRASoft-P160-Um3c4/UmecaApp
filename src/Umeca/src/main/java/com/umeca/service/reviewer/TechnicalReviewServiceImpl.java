package com.umeca.service.reviewer;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeca.model.catalog.Question;
import com.umeca.model.catalog.QuestionarySection;
import com.umeca.model.entities.reviewer.*;
import com.umeca.model.entities.reviewer.View.*;
import com.umeca.model.entities.reviewer.dto.SourceVerificationDto;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.SelectList;
import com.umeca.repository.reviewer.FieldMeetingSourceRepository;
import com.umeca.repository.reviewer.SourceVerificationRepository;
import com.umeca.repository.reviewer.TechnicalReviewRepository;
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
            logException.Write(e, this.getClass(), "generateQuesRevRel", sharedUserService);
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

    @Autowired
    TechnicalReviewRepository technicalReviewRepository;


    public TechnicalReviewInfoFileView fillInfoFile(Long idVerification) {
        TechnicalReviewInfoFileView file = new TechnicalReviewInfoFileView();

        Verification ver = verificationRepository.findOne(idVerification);

        Meeting meeting = ver.getCaseDetention().getMeeting();
        Imputed im = meeting.getImputed();
        file.setIdFolder(sharedUserService.convertToValidString(ver.getCaseDetention().getIdFolder()));
        file.setName(sharedUserService.convertToValidString(im.getName()));
        file.setLastNameP(sharedUserService.convertToValidString(im.getLastNameP()));
        file.setLastNameM(sharedUserService.convertToValidString(im.getLastNameM()));
        Long idCase= ver.getCaseDetention().getId();
        file.setAddress(sharedUserService.convertToValidString(meeting.getImputedHomes().get(0).getAddress().getAddressString()));
        String template = "Campo: {0} <br/>Valor: {1}<br/> Fuente: {2}<br/>Raz&oacute;n: {3}<br/>";
        String templateUnable = "Campo: {0} <br/>Valor: {1}<br/>Raz&oacute;n: {3}<br/>";
        for (int i = 0; i < Constants.NAMES_MEETING.length; i++) {
            List<FieldMeetingSource> listFMS = fieldMeetingSourceRepository.getAllFinalByIdCaseAndSectionCode(idCase, (i + 1));
            if (listFMS.size() > 0 && listFMS.get(0) != null) {
                Section section = new Section(listFMS.get(0).getFieldVerification().getSection());
                for (FieldMeetingSource fms : listFMS) {
                    String v;
                    if (fms.getStatusFieldVerification().getName().equals(Constants.ST_FIELD_VERIF_UNABLE)) {
                        v = templateUnable;
                        List<ChoiceView> list = new ArrayList<>();
                        List<SearchToChoiceIds> idSources = new ArrayList<>();
                        if (fms.getIdFieldList() == null) {
                            idSources = fieldMeetingSourceRepository.getIdSourceByCodeWithoutState(idCase, fms.getFieldVerification().getCode(),Constants.ST_FIELD_VERIF_UNABLE);

                        } else {
                            idSources = fieldMeetingSourceRepository.getIdSourceByCodeWhithIdListWithoutState(idCase, fms.getFieldVerification().getCode(), fms.getIdFieldList(), Constants.ST_FIELD_VERIF_UNABLE);
                        }
                        String sourcessay="<br/>Informaci&oacute; recopilada:<br/>";
                        for (SearchToChoiceIds e : idSources) {
                                List<FieldMeetingSource> result = new ArrayList<>();
                                if (fms.getIdFieldList() == null) {
                                    result = fieldMeetingSourceRepository.getGroupFieldMeeting(e.getIdSource(), e.getIdSubsection(), Constants.ST_FIELD_VERIF_UNABLE);
                                } else {
                                    result = fieldMeetingSourceRepository.getGroupFieldMeetingWithIdList(e.getIdSource(), e.getIdSubsection(), fms.getIdFieldList(), Constants.ST_FIELD_VERIF_UNABLE);
                                }
                                list.add(new ChoiceView().choiceDto(result));
                        }
                        for(ChoiceView choice: list){
                            sourcessay+=choice.getNameSource()+": ";
                            for(String s: choice.getValues()){
                                sourcessay+=s+",";
                            }
                            sourcessay+="<br/>";

                        }
                        String finalText= Constants.UNABLE_VERIF_TEXT_DOC + sourcessay;
                        v = v.replace("{1}", finalText);

                    } else {
                        v = template;
                        v = v.replace("{2}", fms.getSourceVerification().getFullName());
                        v = v.replace("{1}", fms.getValue());
                    }
                    if (fms.getReason() == null) {
                        fms.setReason("Sin raz&oacute;n registrada.");
                    }
                    v = v.replace("{3}", fms.getReason());
                    v = v.replace("{0}", fms.getFieldVerification().getFieldName());
                    section.getValues().add(sharedUserService.convertToValidString(v));
                }
                file.getSections().add(section);
            }
        }


        List<String> sourcesTxt = new ArrayList<>();

        for (SourceVerification source : ver.getSourceVerifications()) {
            if (source.getVisible() == true && source.getAuthorized() == true) {
                StringBuilder sb = new StringBuilder();
                sb.append(sharedUserService.convertToValidString(source.getFullName()));
                sb.append(" relaci&oacute;n con el imputado ");
                String relationship = source.getRelationship().getName();
                if (source.getRelationship().getSpecification()) {
                    relationship += source.getSpecification();
                }
                sb.append(sharedUserService.convertToValidString(relationship));
                sourcesTxt.add(sharedUserService.convertToValidString(sb.toString()));
            }
        }

        file.setSources(sourcesTxt);

        List<String> questSelTxt = new ArrayList<>();
        List<String> questRisks = new ArrayList<>();
        List<String> questLinks = new ArrayList<>();

        List<SelectList> lstQuest = technicalReviewRepository.getQuestionValuesByCaseId(ver.getId());

        for (SelectList act : lstQuest) {
            if (!questSelTxt.contains(act.getDescription())) {
                questSelTxt.add(act.getDescription());

                String cad = SharedUserService.convertToValidString(act.getDescription());

                if (act.getIdAux() > 0)
                    questLinks.add(cad);
                else if (act.getIdAux() < 0)
                    questRisks.add(cad);
            }
        }

        file.setQuestSel(questSelTxt);
        file.setQuestLinks(questLinks);
        file.setQuestRisk(questRisks);

        TechnicalReview technicalReview = ver.getCaseDetention().getTechnicalReview();
        file.setComment(technicalReview.getComments());

        String risk = "";
        Integer total = technicalReview.getTotalRisk();
        if (total < -15) {
            risk = "Riesgo alto! Libertad muy dif&iacute;cil de cumplir.";
        } else if (total > -16 && total < 0) {
            risk = "Riesgo medio! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        } else if (total > -1 && total < 10) {
            risk = "Riesgo bajo! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        } else if (total > 9) {
            risk = "Riesgo mínimo! Se puede recomendar combinaci&oacute;n de medidas cautelares en libertad bajo niveles de supervisi&oacute;n.";
        }

        file.setResult(risk);

        return file;
    }

    @Autowired
    SourceVerificationRepository sourceVerificationRepository;

    @Override
    public TechnicalReviewInfoFileAllSourcesView fillInfoFileAllSources(Long id) {
        TechnicalReviewInfoFileAllSourcesView file = new TechnicalReviewInfoFileAllSourcesView();
        Verification ver = verificationRepository.findOne(id);

        Meeting meeting = ver.getCaseDetention().getMeeting();
        Imputed im = meeting.getImputed();
        file.setIdFolder(sharedUserService.convertToValidString(ver.getCaseDetention().getIdFolder()));
        file.setName(sharedUserService.convertToValidString(im.getName()));
        file.setLastNameP(sharedUserService.convertToValidString(im.getLastNameP()));
        file.setLastNameM(sharedUserService.convertToValidString(im.getLastNameM()));

        file.setAddress(sharedUserService.convertToValidString(meeting.getImputedHomes().get(0).getAddress().getAddressString()));
        String template = "Campo: {0} <br/>Valor: {1}";
        Long idCase = ver.getCaseDetention().getId();
        List<Long> sourcesId = sourceVerificationRepository.getAllSourcesByCase(idCase);

        for (Long idSource : sourcesId) {
            SourceVerificationDto sv = new SourceVerificationDto();
            sv.dtoSourceVerification(sourceVerificationRepository.findOne(idSource));
            sv.setSections(new ArrayList<Section>());
            for (int i = 0; i < Constants.NAMES_MEETING.length; i++) {
                List<FieldMeetingSource> fieldMeetingSources = fieldMeetingSourceRepository.getFieldMeetingBySource(idCase, idSource, Constants.ST_FIELD_VERIF_UNABLE, (i + 1));
                if (fieldMeetingSources != null && fieldMeetingSources.size() > 0) {
                    Section s = new Section(fieldMeetingSources.get(0).getFieldVerification().getSection());
                    List<String> messages = new ArrayList<>();
                    for (FieldMeetingSource fms : fieldMeetingSources) {
                        String finalString = template.replace("{0}", sharedUserService.convertToValidString(fms.getFieldVerification().getFieldName()));
                        finalString = finalString.replace("{1}", sharedUserService.convertToValidString(fms.getValue()));
                        //finalString = finalString;
                        messages.add(finalString);
                    }
                    s.setValues(messages);
                    sv.getSections().add(s);

                }
            }

            file.getSources().add(sv);

        }
        return file;
    }

    public Integer calculateLevelRisk(Integer total) {

        if (total < -15)
            return 1; //alto
        else if (total > -16 && total < 0)
            return 2; //medio
        else if (total > -1 && total < 10)
            return 3;//bajo
        else if (total > 9)
            return 4;//minimo

        return 0;
    }
}

