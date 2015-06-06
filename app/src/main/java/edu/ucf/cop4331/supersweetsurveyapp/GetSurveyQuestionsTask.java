package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  Get the survey from the database and set it for display
 */
public class GetSurveyQuestionsTask extends AsyncTask<String , Void, Survey> {

    Context context;
    Activity activity;
    String action1 = "get_questions";
    String action2 = "get_options";
    String surveyId;
    List<String> optionsText = new ArrayList<>();


    public GetSurveyQuestionsTask(Context context, String surveyId){
        this.context = context;
        this.surveyId = surveyId;
        activity = (Activity) context;
    }

    @Override
    protected Survey doInBackground(String... params) {

        String ops, option;

        MySQLQuery getSurveyQuery = new MySQLQuery(action1);
        MySQLQuery getOptionsQuery = new MySQLQuery(action2);
        Survey s = new Survey(surveyId);

        getSurveyQuery.setSurveyId(surveyId);

        String qs = getSurveyQuery.query();
        String qText, qType, qId;

        // Deserialize the JSON to get all the questions
        try {
            JSONObject questions = new JSONObject(qs);
            JSONArray questionsArray = questions.getJSONArray("questions");

            // Get one question at a time from the JSON
            for(int i = 0; i < questionsArray.length(); i++){

                // Extract text, id, and type
                JSONObject questionObject = questionsArray.getJSONObject(i);
                qText = questionObject.getString("question_text");
                qId = questionObject.getString("question_id");
                qType = questionObject.getString("qtype_id");

                // Create a SurveyQuestion
                SurveyQuestion question = new SurveyQuestion();
                question.setQuestionText(qText);
                question.setQuestionId(qId);
                question.setqTypeId(qType);

                getOptionsQuery.setQuestionId(qId);

                // Query the database for the options
                ops = getOptionsQuery.query();

                JSONObject options = new JSONObject(ops);
                JSONArray optionsArray = options.getJSONArray("options");

                // Based on the type, we construct our question differently
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

                // Add the question to the survey and then repeat for the rest
                s.addQuestion(question);
            }

        } catch(JSONException e){
            e.printStackTrace();
        }

        return s;
    }


    @Override
    protected void onPostExecute(Survey survey) {
        super.onPostExecute(survey);
        ListView l = (ListView) activity.findViewById(R.id.take_survey_list_view);
        SurveyQuestionAdapter adapter = new SurveyQuestionAdapter(context, survey);
        l.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        l.setAdapter(adapter);

    }
}
