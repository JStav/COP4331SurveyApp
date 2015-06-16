package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by stavr on 6/16/2015.
 */
public class GetStatsTask extends AsyncTask<Void, Void, ArrayList<SurveyAnswer>> {


    static final String TAG = "GetStatsTask";
    String surveyId;
    Context context;
    Activity activity;

    public GetStatsTask(Context context){
        this.context = context;
        this.activity = (Activity) context;
    }

    @Override
    protected ArrayList<SurveyAnswer> doInBackground(Void... params) {

        MySQLQuery getStatsQuery = new MySQLQuery("live_stats");
        getStatsQuery.setSurveyId(surveyId);
        String statsFeed = getStatsQuery.query();


        ArrayList<SurveyAnswer> answers = new ArrayList<>();

        try{
            JSONObject statsJSON = new JSONObject(statsFeed);
            JSONArray answersArray = statsJSON.getJSONArray("data");

            for(int i = 0; i < answersArray.length(); i++){

                JSONObject answer = answersArray.getJSONObject(i);

                SurveyAnswer currentAnswer = new SurveyAnswer(answer.getString("question_text"), answer.getString("answer"), answer.getString("first_name"));

                answers.add(currentAnswer);

            }

        } catch (JSONException e){
            Log.e(TAG, e.getMessage());
        }

        return answers;
    }


    @Override
    protected void onPostExecute(ArrayList<SurveyAnswer> surveyAnswers) {
        super.onPostExecute(surveyAnswers);

        ListView l = (ListView) activity.findViewById(R.id.stats_list_view);
        LiveStatsAdapter liveStatsAdapter = new LiveStatsAdapter(context, surveyAnswers);
        liveStatsAdapter.setActivity((SurveyStatsActivity) context);
        l.setAdapter(liveStatsAdapter);
    }

    public void setSurveyId(String surveyId){
        this.surveyId = surveyId;
    }
}
