package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TakeSurveyActivity extends Activity {

    HashMap<Integer, String> responses;
    String surveyId;


    public TakeSurveyActivity(){
    }

    GetSurveyQuestionsTask getSurveyQuestionsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);
        Intent intent = getIntent();
        this.surveyId = intent.getStringExtra("surveyId");

        responses = new HashMap<>();

        getSurveyQuestionsTask = new GetSurveyQuestionsTask(this, surveyId);
        getSurveyQuestionsTask.execute();

    }


    public void addResponse(int questionId, String response){
        responses.put(questionId, response);
    }

    public void submitAnswers(View view){

        SessionManager sessionManager = new SessionManager(this);

        String userId = sessionManager.getCurrentUserId();

        int size = responses.size();

        String finalString = "(" + 1 + "," + userId + ", " + surveyId + "," + responses.get(1) + ")";

        for(int i = 2; i <= size; i++){
            finalString += ",(" + i + "," + userId + ", " + surveyId + "," + responses.get(i) + ")";
        }

        finalString = finalString.replaceAll(" ", "%20");
        finalString = finalString.replaceAll("'", "%27");
        System.out.println(finalString);


        SubmitAnswersTask submitAnswersTask = new SubmitAnswersTask(this, finalString);
        submitAnswersTask.execute();

        Button b = (Button) findViewById(R.id.submit_survey_button);
        b.setEnabled(false);

    }
}
