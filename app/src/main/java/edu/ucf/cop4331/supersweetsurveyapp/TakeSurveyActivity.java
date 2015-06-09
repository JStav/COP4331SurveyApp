package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TakeSurveyActivity extends Activity {

    ArrayList<String> responses = new ArrayList<>();

    public TakeSurveyActivity(){

    }

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


    public void addResponse(String response){
        responses.add(response);
    }

    public void submitAnswers(View view){

        String finalString = "";

        for(String str: responses){
            finalString += str + " ";
        }

        Toast toast = Toast.makeText(this, finalString, Toast.LENGTH_LONG);

    }
}
