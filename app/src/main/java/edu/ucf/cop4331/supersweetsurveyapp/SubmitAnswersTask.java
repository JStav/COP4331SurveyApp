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
    String answers;
    Activity activity;

    public SubmitAnswersTask(Context context, String answers){
        super();
        this.context = context;
        this.answers = answers;
        this.activity = (Activity) context;
    }


    @Override
    protected String doInBackground(Void... params) {

        SessionManager sessionManager = new SessionManager(context);

        MySQLQuery submitAnswersQuery = new MySQLQuery("submit_answers", sessionManager.getCurrentUserName());

        submitAnswersQuery.setAnswers(answers);

        String isSubmitted = submitAnswersQuery.query();

        System.out.println("SUBMITTED? " + isSubmitted);

        if (isSubmitted.equals("TRUE")){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Thank you for completing this survey!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, BrowseSurveysActivity.class);
                    context.startActivity(intent);
                }
            });

        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(context, "An error occurred, please try again", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }
        return "Ok";
    }
}
