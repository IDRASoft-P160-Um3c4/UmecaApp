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

    @RequestMapping(value = "/catalogs/fulfillmentReport", method = RequestMethod.GET)
    public String fulfillmentReport() {
        service.fulfillmentReport();
        return "Fulfillment Report OK!";
    }

    @RequestMapping(value = "/catalogs/typeFileName", method = RequestMethod.GET)
    public String typeFileName() {
        service.typeFileName();
        return "typeName OK!";
    }

    @RequestMapping(value = "/catalogs/activityGroup", method = RequestMethod.GET)
    public String activityGroup() {
        return "activityGroup OK!";
    }

    @RequestMapping(value = "/catalogs/immigrationDocument", method = RequestMethod.GET)
    public String immigrationDocument() {
        service.immigrationDocument();
        return "immigrationDocument";
    }

    @RequestMapping(value = "/catalogs/closeCause", method = RequestMethod.GET)
    public String closeCause() {
        service.closeCause();
        return "closeCause";
    }

    @RequestMapping(value = "/catalogs/district", method = RequestMethod.GET)
    public String district() {
        service.district();
        return "closeCause";
    }

    @RequestMapping(value = "/catalogs/priority", method = RequestMethod.GET)
    public String priority() {
        service.priority();
        return "priority";
    }

    @RequestMapping(value = "/catalogs/courseType", method = RequestMethod.GET)
    public String courseType() {
        service.courseType();
        return "courseType";
    }

    @RequestMapping(value = "/catalogs/schoolDocType", method = RequestMethod.GET)
    public String schoolDocType() {
        service.schoolDocType();
        return "schoolDocType";
    }

    @RequestMapping(value = "/catalogs/UmecaPost", method = RequestMethod.GET)
    public String umecaPost() {
        service.umecaPost();
        return "umecaPost";
    }

    @RequestMapping(value = "/catalogs/incident", method = RequestMethod.GET)
    public String incident() {
        service.incident();
        return "incident";
    }

    @RequestMapping(value = "/catalogs/area", method = RequestMethod.GET)
    public String area() {
        service.area();
        return "area";
    }

    @RequestMapping(value = "/catalogs/weekDay", method = RequestMethod.GET)
    public String weekDay() {
        service.weekDay();
        return "weekDay";
    }


    @RequestMapping(value = "/catalogs/educationLevel", method = RequestMethod.GET)
    public String educationLevel() {
        service.educationLevel();
        return "educationLevel";
    }

    @RequestMapping(value = "/catalogs/preventionType", method = RequestMethod.GET)
    public String preventionType() {
        service.preventionType();
        return "preventionType";
    }

    @RequestMapping(value = "/catalogs/economicSupport", method = RequestMethod.GET)
    public String economicSupport() {
        service.economicSupport();
        return "economicSupport";
    }

    @RequestMapping(value = "/catalogs/channelingType", method = RequestMethod.GET)
    public String channelingType() {
        service.channelingType();
        return "channelingType";
    }

    @RequestMapping(value = "/catalogs/informationAvailability", method = RequestMethod.GET)
    public String informationAvailability() {
        service.informationAvailability();
        return "informationAvailability";
    }

    @RequestMapping(value = "/catalogs/evaluationActivity", method = RequestMethod.GET)
    public String evaluationActivity() {
        service.evaluationActivity();
        return "evaluationActivity";
    }

    @RequestMapping(value = "/catalogs/channelingDropType", method = RequestMethod.GET)
    public String channelingDropType() {
        service.channelingDropType();
        return "channelingDropType";
    }


    @RequestMapping(value = "/catalogs/eventType", method = RequestMethod.GET)
    public String eventType() {
        service.eventType();
        return "eventType";
    }

    @RequestMapping(value = "/catalogs/statisticReportType", method = RequestMethod.GET)
    public String statisticReportType() {
        service.statisticReportType();
        return "statisticReportType";
    }

    @RequestMapping(value = "/catalogs/statisticOperatorReportType", method = RequestMethod.GET)
    public String statisticOperatorReportType() {
        service.statisticOperatorReportType();
        return "statisticOperatorReportType";
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
        service.typeFileName();
        service.fulfillmentReport();
        service.immigrationDocument();
        service.closeCause();
        service.district();
        service.priority();
        service.courseType();
        service.schoolDocType();
        service.umecaPost();
        service.incident();
        service.area();
        service.weekDay();
        service.educationLevel();
        service.preventionType();
        service.economicSupport();
        service.channelingType();
        service.informationAvailability();
        service.evaluationActivity();
        service.channelingDropType();
        service.eventType();
        service.statisticReportType();
        service.statisticOperatorReportType();
        return "insertCatalog OK!!";
    }
}
