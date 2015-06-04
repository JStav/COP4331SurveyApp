package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  Get the survey from the database and set it for display
 */
public class GetSurveyTask extends AsyncTask<String , Void, Survey> {

    Context context;
    Activity activity;
    String action1 = "get_questions";
    String action2 = "get_options";
    String surveyId;
    List<String> optionsText = new ArrayList<>();


    public GetSurveyTask(String surveyId){
        this.surveyId = surveyId;
    }

    @Override
    protected Survey doInBackground(String... params) {

        String ops, option;

        MySQLQuery getSurveyQuery = new MySQLQuery(action1);
        MySQLQuery getOptionsQuery = new MySQLQuery(action2);
        Survey s = new Survey(surveyId);
        SurveyQuestion question = new SurveyQuestion();

        getSurveyQuery.setSurveyId(surveyId);

        String qs = getSurveyQuery.query();
        String qText, qType, qId;

        // Deserialize the JSON to get all the questions
        try {
            JSONObject questions = new JSONObject(qs);
            JSONArray questionsArray = questions.getJSONArray("questions");

            for(int i = 0; i < questionsArray.length(); i++){

                JSONObject questionObject = questionsArray.getJSONObject(i);
                qText = questionObject.getString("question_text");
                qId = questionObject.getString("question_id");
                qType = questionObject.getString("qType");

                question.setQuestionText(qText);
                question.setQuestionId(qId);
                question.setqTypeId(qType);

                getOptionsQuery.setQuestionId(qId);
                ops = getOptionsQuery.query();

                JSONObject options = new JSONObject(ops);
                JSONArray optionsArray = options.getJSONArray("options");

                switch (qType){
                    case "1":
                        question.setTFOptions();
                        break;

                    case "2":
                        for (int j = 0; j < optionsArray.length(); j++){

                            JSONObject optionObject = optionsArray.getJSONObject(j);
                            option = optionObject.getString("option_text");
                            optionsText.add(option);
                        }

                        question.setOption1(optionsText.get(0));
                        question.setOption2(optionsText.get(1));
                        question.setOption3(optionsText.get(2));
                        question.setOption4(optionsText.get(3));
                        break;

                    case "3":
                        break;
                }

                s.addQuestion(question);
            }

        } catch(JSONException e){
            e.printStackTrace();
        }

        return s;
    }
}
