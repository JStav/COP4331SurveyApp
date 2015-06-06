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

    public Survey(){

    }

    public void addQuestion(SurveyQuestion q){
        questions.add(q);
    }

    public List<SurveyQuestion> getQuestions(){
        return questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    @Override
    public String toString() {
        return id;
    }
}
