package com.umeca.controller.catalogs;

import com.umeca.service.catalog.InsertCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/20/14
 * Time: 4:01 PM
 */

@Controller
public class InsertCatalogController {

    @Autowired
    InsertCatalogService service;

    @RequestMapping(value = "/catalogs/insertRole", method = RequestMethod.GET)
    public String insertRole(){
        service.role();
        return "Role OK!";
    }


    @RequestMapping(value = "/catalogs/insertUser", method = RequestMethod.GET)
    public String insertUser(){
        service.user();
        return "User OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionType", method = RequestMethod.GET)
    public String insertQuestionType(){
        service.questionType();
        return "QuestionType OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionary", method = RequestMethod.GET)
    public String insertQuestionary(){
        service.questionary();
        return "Questionary OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestionarySection", method = RequestMethod.GET)
    public String insertQuestionarySection(){
        service.questionarySection();
        return "QuestionarySection OK!";
    }


    @RequestMapping(value = "/catalogs/insertQuestion", method = RequestMethod.GET)
    public String insertQuestion(){
        service.question();
        return "Question OK!";
    }

    @RequestMapping(value="/catalogs/insertActivity", method = RequestMethod.GET)
    public String insertActivity(){
        service.activity();
        return "Activity OK!";
    }

    @RequestMapping(value="/catalogs/insertPhysicalCondition", method = RequestMethod.GET)
    public String insertPhysicalCondition(){
        service.physicalCondition();
        return "Physical condition OK!";
    }

    @RequestMapping(value="/catalogs/insertStatusMeeting", method = RequestMethod.GET)
    public String insertStatusMeeting(){
        service.statusMeeting();
        return "Physical condition OK!";
    }

    @RequestMapping(value = "/catalogs/insertArrangement", method = RequestMethod.GET)
    public String insertArrangement(){
        service.arrangement();
        return "Arrangement OK!";
    }

   @RequestMapping(value="/catalogs/insertMaritalStatus", method = RequestMethod.GET)
    public String insertMaritalStatus(){
        service.maritalStatus();
        return "Marital  Status  OK!";
    }

   @RequestMapping(value="/catalogs/insertElection", method = RequestMethod.GET)
    public String insertElection(){
        service.election();
        return "Election OK!";
    }

   @RequestMapping(value="/catalogs/insertRelationship", method = RequestMethod.GET)
    public String insertRelationship(){
        service.relationship();
        return "Relationship OK!";
    }

   @RequestMapping(value="/catalogs/insertDocumentType", method = RequestMethod.GET)
    public String insertDocumentType(){
        service.documentType();
        return "Document Type OK!";
    }

    @RequestMapping(value="/catalogs/insertDrugType", method = RequestMethod.GET)
    public String insertDrugType(){
        service.drugType();
        return "Drug Type OK!";
    }

    @RequestMapping(value="/catalogs/insertPeriodicity", method = RequestMethod.GET)
    public String insertPeriodicity(){
        service.periodicity();
        return "Periodicity OK!";
    }

    @RequestMapping(value="/catalogs/insertDayWeek", method = RequestMethod.GET)
    public String insertDayWeek(){
        service.dayWeek();
        return "Day Week OK!";
    }

    @RequestMapping(value="/catalogs/insertSchoolLevel", method = RequestMethod.GET)
    public String insertSchoolLevel(){
        service.schoolLevel();
        return "School level OK!";
    }

    @RequestMapping(value = "/catalogs/insertTypeRegister", method = RequestMethod.GET)
    public String insertTypeRegister(){
        service.registerType();
        return "Register Type OK!";
    }

    @RequestMapping(value = "/catalogs/insertCatalogAll", method = RequestMethod.GET)
    public String insertCatalogAll(){
        service.role();
        service.user();
        service.arrangement();
        service.questionType();
        service.questionary();
        service.questionarySection();
        service.question();
        service.activity();
        service.physicalCondition();
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
        service.schoolLevel();
        service.registerType();
        return "insertCatalog OK!!";
    }
}
