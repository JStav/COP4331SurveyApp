package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class TakeSurveyActivity extends Activity {

    GetSurveyQuestionsTask getSurveyQuestionsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);
        Intent intent = getIntent();
        String surveyId = intent.getStringExtra("surveyId");
        getSurveyQuestionsTask = new GetSurveyQuestionsTask(this, surveyId);
        getSurveyQuestionsTask.execute();
    }
}
