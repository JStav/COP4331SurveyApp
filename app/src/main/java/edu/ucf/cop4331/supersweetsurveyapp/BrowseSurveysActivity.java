package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class BrowseSurveysActivity extends Activity {

    ListView l;
    String surveyId;
    GetSurveysTask getSurveysTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_surveys);

        // Query the database and get the list of surveys, then populate the list
        getSurveysTask = new GetSurveysTask(this);
        getSurveysTask.execute();


        l = (ListView) findViewById(R.id.survey_list_view);
        // Override the onClickListener to highlight selected choice
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                l.setItemChecked(position, true);
                surveyId = arg0.getItemAtPosition(position).toString();
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

        // Check if the user has taken the survey first
        // Network operation must be run on a separate thread
        Thread thread = new Thread (new Runnable() {
            @Override
            public void run() {

                SessionManager sm = new SessionManager(getApplicationContext());

                MySQLQuery hasTakenSurveyQuery = new MySQLQuery("has_taken_survey");
                hasTakenSurveyQuery.setSurveyId(surveyId);
                hasTakenSurveyQuery.setUserId(sm.getCurrentUserId());

                // Query the database, will return true if the user has taken the survey, false otherwise
                String hasTakenSurvey = hasTakenSurveyQuery.query();

                if(hasTakenSurvey.trim().equals("false")) {

                    Intent intent = new Intent(getApplicationContext(), TakeSurveyActivity.class);
                    // Pass the surveyId to TakeSurveyActivity
                    intent.putExtra("surveyId", surveyId);
                    startActivity(intent);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "You have already taken this survey.", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        thread.start();


    }

    public void viewSurveyStats(View view){
        Intent intent = new Intent(this, SurveyStatsActivity.class);
        intent.putExtra("surveyId", surveyId);
        startActivity(intent);
    }


    public void viewSurveyFeed(View view){
        Intent intent = new Intent(this, SurveyFeedActivity.class);
        intent.putExtra("surveyId", surveyId);
        startActivity(intent);
    }

    public void logout(View view){
        // End the session
        SessionManager sessionManager = new SessionManager(this);
        Intent intent = new Intent(this, LoginActivity.class);
        Toast toast = Toast.makeText(this, R.string.logout_message, Toast.LENGTH_SHORT);

        toast.show();
        sessionManager.logOutUser();
        startActivity(intent);
    }
}
