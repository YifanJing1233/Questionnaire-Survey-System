package com.gyb.questionnaire.service;


import com.gyb.questionnaire.dao.QuestionnaireDao;
import com.gyb.questionnaire.entity.Questionnaire;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuestionnaireService {

    private final QuestionnaireDao questionnaireDao;

    public QuestionnaireService(QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    public Questionnaire save(Questionnaire questionnaire) {
        questionnaireDao.addQuestionnaire(questionnaire);
        return questionnaire;
    }
}
