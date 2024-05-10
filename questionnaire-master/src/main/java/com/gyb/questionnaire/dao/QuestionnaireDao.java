package com.gyb.questionnaire.dao;

import com.gyb.questionnaire.entity.Questionnaire;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireDao {

    @Insert("insert into " +
            "questionnaire(fied1,fied2,fied3,fied4,fied5,"
            + "fied6,fied7,fied8)" +
            " value(#{fied1},#{fied2},#{fied3},#{fied4},#{fied5},#{fied6},"
            + "#{fied7},#{fied8})")
    void addQuestionnaire(Questionnaire questionnaire);
}
