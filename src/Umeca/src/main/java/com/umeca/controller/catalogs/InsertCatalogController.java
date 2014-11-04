package com.umeca.controller.catalogs;

import com.umeca.service.catalog.InsertCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InsertCatalogController {

    @Autowired
    InsertCatalogService service;

    @RequestMapping(value = "/catalogs/insertRole", method = RequestMethod.GET)
    public String insertRole() {
        service.role();
        return "Role OK!";
    }


    @RequestMapping(value = "/catalogs/insertUser", method = RequestMethod.GET)
    public String insertUser() {
        service.user();
        return "User OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionType", method = RequestMethod.GET)
    public String insertQuestionType() {
        service.questionType();
        return "QuestionType OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionary", method = RequestMethod.GET)
    public String insertQuestionary() {
        service.questionary();
        return "Questionary OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionarySection", method = RequestMethod.GET)
    public String insertQuestionarySection() {
        service.questionarySection();
        return "QuestionarySection OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestion", method = RequestMethod.GET)
    public String insertQuestion() {
        service.question();
        return "Question OK!";
    }

    @RequestMapping(value = "/catalogs/insertActivity", method = RequestMethod.GET)
    public String insertActivity() {
        service.activity();
        return "Activity OK!";
    }

    @RequestMapping(value = "/catalogs/insertStatusMeeting", method = RequestMethod.GET)
    public String insertStatusMeeting() {
        service.statusMeeting();
        return "Physical condition OK!";
    }

    @RequestMapping(value = "/catalogs/insertArrangement", method = RequestMethod.GET)
    public String insertArrangement() {
        service.arrangement();
        return "Arrangement OK!";
    }

    @RequestMapping(value = "/catalogs/insertMaritalStatus", method = RequestMethod.GET)
    public String insertMaritalStatus() {
        service.maritalStatus();
        return "Marital  Status  OK!";
    }

    @RequestMapping(value = "/catalogs/insertElection", method = RequestMethod.GET)
    public String insertElection() {
        service.election();
        return "Election OK!";
    }

    @RequestMapping(value = "/catalogs/insertRelationship", method = RequestMethod.GET)
    public String insertRelationship() {
        service.relationship();
        return "Relationship OK!";
    }

    @RequestMapping(value = "/catalogs/insertDocumentType", method = RequestMethod.GET)
    public String insertDocumentType() {
        service.documentType();
        return "Document Type OK!";
    }

    @RequestMapping(value = "/catalogs/insertDrugType", method = RequestMethod.GET)
    public String insertDrugType() {
        service.drugType();
        return "Drug Type OK!";
    }

    @RequestMapping(value = "/catalogs/insertPeriodicity", method = RequestMethod.GET)
    public String insertPeriodicity() {
        service.periodicity();
        return "Periodicity OK!";
    }

    @RequestMapping(value = "/catalogs/insertDayWeek", method = RequestMethod.GET)
    public String insertDayWeek() {
        service.dayWeek();
        return "Day Week OK!";
    }

    @RequestMapping(value = "/catalogs/insertAcademicLevel", method = RequestMethod.GET)
    public String insertSchoolLevel() {
        service.academicDegree();
        return "Degree OK!";
    }

    @RequestMapping(value = "/catalogs/insertTypeRegister", method = RequestMethod.GET)
    public String insertTypeRegister() {
        service.registerType();
        return "Register Type OK!";
    }

    @RequestMapping(value = "/catalogs/insertVerificationMethod", method = RequestMethod.GET)
    public String insertVerificationMethod() {
        service.verificationMethod();
        return "Register Verification method OK!";
    }

    @RequestMapping(value = "/catalogs/insertSupervisionActivity", method = RequestMethod.GET)
    public String insertSupervisionActivity() {
        service.supervisionActivity();
        return "Supervision Activity OK!";
    }

    @RequestMapping(value = "/catalogs/insertActivityGoal", method = RequestMethod.GET)
    public String insertActivityGoal() {
        service.insertActivityGoal();
        return "Activity Goal OK!";
    }

    @RequestMapping(value = "/catalogs/insertAidSource", method = RequestMethod.GET)
    public String insertAidSource() {
        service.insertAidSource();
        return "Aid Source OK!";
    }


    @RequestMapping(value = "/catalogs/insertStatusCase", method = RequestMethod.GET)
    public String insertStatusCase() {
        service.statusCase();
        return "Status Case OK!";
    }


    @RequestMapping(value = "/catalogs/fieldVerification", method = RequestMethod.GET)
    public String fieldVerification() {
        service.fieldVerification();
        return "Field Verification OK!";
    }

    @RequestMapping(value = "/catalogs/statusFieldVerification", method = RequestMethod.GET)
    public String statusFieldVerification() {
        service.statusFieldVerification();
        return "Status Field  Verification OK!";
    }


    /*@RequestMapping(value = "/catalogs/insertHearingFormatType", method = RequestMethod.GET)
    public String insertHearingFormatType(){ //NO APLICA PARA ESTA VERSIÓN
        service.hearingFormatType();
        return "Hearing Format Type OK!";
    }*/

    @RequestMapping(value = "/catalogs/insertFramingRisk", method = RequestMethod.GET)
    public String insertFramingRisk() {
        service.framingRisk();
        return "Status Case OK!";
    }

    @RequestMapping(value = "/catalogs/insertFramingThreat", method = RequestMethod.GET)
    public String insertFramingThreat() {
        service.framingThreat();
        return "Status Case OK!";
    }

    @RequestMapping(value = "/catalogs/insertHomeType", method = RequestMethod.GET)
    public String insertHomeType() {
        service.homeType();
        return "Home type OK!";
    }

    @RequestMapping(value = "/catalogs/insertFileType", method = RequestMethod.GET)
    public String insertFileType() {
        service.fileType();
        return "File type OK!";
    }

    @RequestMapping(value = "/catalogs/insertSystemSettings", method = RequestMethod.GET)
    public String insertSystemSettings() {
        service.systemSettings();
        return "Home type OK!";
    }


    @RequestMapping(value = "/catalogs/requestType", method = RequestMethod.GET)
    public String insertRquestType() {
        service.requestType();
        return "Request type OK!";
    }


    @RequestMapping(value = "/catalogs/responseType", method = RequestMethod.GET)
    public String responseType() {
        service.responseType();
        return "Response type OK!";
    }

    @RequestMapping(value = "/catalogs/electionNotApply", method = RequestMethod.GET)
    public String electionNotApply() {
        service.electionNotApply();
        return "Response election not apply OK!";
    }

    @RequestMapping(value = "/catalogs/insertHearingType", method = RequestMethod.GET)
    public String insertHearingType() {
        service.hearingType();
        return "hearingType OK!";
    }

    @RequestMapping(value = "/catalogs/crime", method = RequestMethod.GET)
    public String crime() {
        service.crime();
        return "crime OK!";
    }

    @RequestMapping(value = "/catalogs/insertCatalogAll", method = RequestMethod.GET)
    public String insertCatalogAll() {
        service.role();
        service.user();
        service.arrangement();
        service.questionType();
        service.questionary();
        service.questionarySection();
        service.question();
        service.activity();
        service.statusMeeting();
        service.statusVerification();
        service.statusCase();
        service.maritalStatus();
        service.election();
        service.relationship();
        service.documentType();
        service.drugType();
        service.periodicity();
        service.dayWeek();
        service.academicDegree();
        service.registerType();
        service.verificationMethod();
        service.supervisionActivity();
        service.insertActivityGoal();
        service.insertAidSource();
        service.fieldVerification();
        service.statusFieldVerification();
        service.framingRisk();
        service.framingThreat();
        service.homeType();
        service.fileType();
        service.systemSettings();
        service.requestType();
        service.responseType();
        service.electionNotApply();
        service.hearingType();
        service.crime();
        return "insertCatalog OK!!";
    }
}
