package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stavr on 5/31/2015.
 * Query the database and update the listview with all the surveys
 */
public class GetSurveysTask extends AsyncTask<String, Void, ArrayList<Survey>> {

    Activity activity;
    Context context;
    String action = "get_survey_list";

    public GetSurveysTask(Context context){
        this.activity = (Activity) context;
        this.context = context;
    }

    @Override
    protected ArrayList<Survey> doInBackground(String... params) {

        MySQLQuery getSurveysQuery = new MySQLQuery(action);
        String s = getSurveysQuery.query();

        ArrayList<Survey> surveyList = new ArrayList<>();

        // Parse JSON to get all survey titles and return a string array to populate the listview
        // TODO: Make all JSON parsing in a separate class?
        try {
            JSONObject surveys = new JSONObject(s);
            JSONArray jArray = surveys.getJSONArray("surveys");

            for (int i = 0; i < jArray.length(); i++){

                Survey survey = new Survey();

                JSONObject surveyObject = jArray.getJSONObject(i);
                survey.setSurveyName(surveyObject.getString("survey_name"));
                survey.setId(surveyObject.getString("id"));

                System.out.println(survey.surveyName + " " + survey.getId());

                surveyList.add(survey);
            }



        } catch (JSONException e){
            e.printStackTrace();
        }

        return surveyList;
    }


    @Override
    protected void onPostExecute(ArrayList<Survey> s){
        super.onPostExecute(s);

        ListView l = (ListView) activity.findViewById(R.id.survey_list_view);
        SurveyAdapter adapter = new SurveyAdapter(context, R.layout.survey_layout_text, s);
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setAdapter(adapter);

    }
}
