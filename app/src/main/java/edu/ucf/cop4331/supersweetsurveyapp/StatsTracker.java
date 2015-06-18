package edu.ucf.cop4331.supersweetsurveyapp;

import java.util.HashMap;

/**
 * Created by stavr on 6/18/2015.
 */
public class StatsTracker {

    HashMap<String, Integer> questionStats = new HashMap<>();
    String question;

    public StatsTracker(){

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
