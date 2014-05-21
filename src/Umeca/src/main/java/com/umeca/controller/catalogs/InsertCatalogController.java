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

    @RequestMapping(value = "/catalogs/insertCatalogAll", method = RequestMethod.GET)
    public String insertCatalogAll(){
        service.role();
        service.user();
        service.questionType();
        service.questionary();
        service.questionarySection();
        service.question();
        return "insertCatalog OK!!";
    }
}
