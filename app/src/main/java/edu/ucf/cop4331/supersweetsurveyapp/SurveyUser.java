package edu.ucf.cop4331.supersweetsurveyapp;

/**
 * Created by stavr on 6/8/2015.
 */
public class SurveyUser {

    String username, userId;

    public SurveyUser(String username, String userId){
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
