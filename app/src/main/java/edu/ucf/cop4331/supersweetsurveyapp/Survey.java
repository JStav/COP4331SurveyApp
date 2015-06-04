package edu.ucf.cop4331.supersweetsurveyapp;

import java.util.ArrayList;
import java.util.List;

/**
 *  Represent a single survey
 */
public class Survey {


    String id;
    String surveyName;
    List<SurveyQuestion> questions;

    public Survey(String id){
        this.id = id;
        questions = new ArrayList<>();
    }

    public void addQuestion(SurveyQuestion q){
        questions.add(q);
    }

    public List<SurveyQuestion> getQuestions(){
        return questions;
    }

}