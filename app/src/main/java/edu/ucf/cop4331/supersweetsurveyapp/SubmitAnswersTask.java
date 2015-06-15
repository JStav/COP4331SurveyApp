package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by stavr on 6/10/2015.
 */
public class SubmitAnswersTask extends AsyncTask<Void, Void, String>{

    Context context;
    String answers, surveyId, userId;
    Activity activity;
    public SubmitAnswersTask(Context context, String answers, String surveyId, String userId){
        super();
        this.context = context;
        this.answers = answers;
        this.activity = (Activity) context;
        this.surveyId = surveyId;
        this.userId = userId;
    }


    @Override
    protected String doInBackground(Void... params) {

        SessionManager sessionManager = new SessionManager(context);

        MySQLQuery submitAnswersQuery = new MySQLQuery("submit_answers");
        submitAnswersQuery.setUserId(sessionManager.getCurrentUserId());

        submitAnswersQuery.setAnswers(answers);
        submitAnswersQuery.setSurveyId(surveyId);

        String isSubmitted = submitAnswersQuery.query();

        System.out.println("SUBMITTED? " + isSubmitted);

        return isSubmitted;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s.equals("1")) {
            Toast.makeText(context, "Thank you for completing this survey!", Toast.LENGTH_LONG).show();
            Activity activity = (Activity) context;
            activity.finish();
        } else {
            Toast.makeText(context, "An error occurred, please try again", Toast.LENGTH_LONG).show();
        }
    }
}
