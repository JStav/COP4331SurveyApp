package edu.ucf.cop4331.supersweetsurveyapp;

/**
 * Created by stavr on 6/16/2015.
 */
public class SurveyAnswer {

    String question, answer, submitter;

    public SurveyAnswer(String question, String answer, String submitter){
        this.answer = answer;
        this.question = question;
        this.submitter = submitter;
    }

}
