package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

        Set questions = countQuestions(surveyAnswers);
        ArrayList<StatsTracker> statsAray = new ArrayList<>();

        for(Object s : questions){

            StatsTracker st = new StatsTracker();
            st.setQuestion(s.toString());

            int count = 0;

            for (int j = 0; j < surveyAnswers.size(); j++){

                if (surveyAnswers.get(j).question.equals(s.toString())){
                    count++;
                    st.questionStats.put(surveyAnswers.get(j).answer, count);
                }
            }

            statsAray.add(st);
        }

        ListView l = (ListView) activity.findViewById(R.id.listView);
        StatsAdapter statsAdapter = new StatsAdapter(context, statsAray);

        l.setAdapter(statsAdapter);

    }

    public void setSurveyId(String surveyId){
        this.surveyId = surveyId;
    }

    public Set countQuestions(ArrayList<SurveyAnswer> surveyAnswers){

        Set questions = new HashSet<>();

        for(SurveyAnswer ans : surveyAnswers){

            questions.add(ans.question);

        }

        return questions;
    }


}
