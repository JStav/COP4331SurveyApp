package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowseSurveysActivity extends Activity {

    ListView l;
    String[] surveys = {"Survey 1", "Survey 2", "Survey 3", "Survey 4", "Survey 5", "Survey 6", "Survey 7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_surveys);

        l = (ListView) findViewById(R.id.survey_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.survey_layout_text, surveys);
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setAdapter(adapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                l.setItemChecked(position, true);
            }
        });
    }

    public void takeSurvey(View view){
        Intent intent = new Intent(this, TakeSurveyActivity.class);
        startActivity(intent);
    }

    public void viewSurveyStats(View view){
        Intent intent = new Intent(this, SurveyStatsActivity.class);
        startActivity(intent);
    }

}
