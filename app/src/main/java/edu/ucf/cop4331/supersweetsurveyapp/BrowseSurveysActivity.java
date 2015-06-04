package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BrowseSurveysActivity extends Activity {

    ListView l;
    String surveyTitle;
    GetSurveysTask getSurveysTask;
    GetSurveyTask getSurveyTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_surveys);

        // Query the database and get the list of surveys, then populate the list
        getSurveysTask = new GetSurveysTask(this);
        getSurveysTask.execute();

        getSurveyTask = new GetSurveyTask("1");
        getSurveyTask.execute();

        l = (ListView) findViewById(R.id.survey_list_view);
        // Override the onClickListener to highlight selected choice
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                l.setItemChecked(position, true);
                surveyTitle = (String) arg0.getItemAtPosition(position);
            }
        });
    }

    // Override to go home instead of back to the login screen
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    public void takeSurvey(View view){

        Toast toast = Toast.makeText(this, surveyTitle, Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(this, TakeSurveyActivity.class);
        startActivity(intent);
    }

    public void viewSurveyStats(View view){
        Intent intent = new Intent(this, SurveyStatsActivity.class);
        startActivity(intent);
    }

    public void logout(View view){
        SessionManager sessionManager = new SessionManager(this);
        Intent intent = new Intent(this, LoginActivity.class);
        Toast toast = Toast.makeText(this, R.string.logout_message, Toast.LENGTH_SHORT);

        toast.show();
        sessionManager.logOutUser();
        startActivity(intent);
    }
}
