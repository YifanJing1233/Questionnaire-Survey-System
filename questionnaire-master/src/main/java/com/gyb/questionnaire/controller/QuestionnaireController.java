package com.gyb.questionnaire.controller;


import com.gyb.questionnaire.config.RequiredLogin;
import com.gyb.questionnaire.entity.Questionnaire;
import com.gyb.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 问卷
 *
 */
@Controller
@RequestMapping("/questionnaire")
@Validated
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping("/gomake")
    @RequiredLogin
    public String gomake() {
        return "gomake";
    }

    @PostMapping("/doMake")
    @RequiredLogin
    @ResponseBody
    public ResponseResult doMake(Questionnaire questionnaire) {
        Questionnaire insertQuestionnaire = questionnaireService.save(questionnaire);
        return ResponseResult.ok(insertQuestionnaire);
    }
}
