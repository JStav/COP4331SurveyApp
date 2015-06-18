package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class SurveyStatsActivity extends Activity {

    String surveyId;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_stats);


        Intent intent = getIntent();
        this.surveyId = intent.getStringExtra("surveyId");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        GetStatsTask getStatsTask = new GetStatsTask(context);
                        getStatsTask.setSurveyId(surveyId);
                        getStatsTask.execute();
                        Thread.sleep(6000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();

    }
}
