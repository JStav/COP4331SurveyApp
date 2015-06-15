package edu.ucf.cop4331.supersweetsurveyapp;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stavr on 5/31/2015.
 * Class responsible for MySQL queries, will be used in conjunction with AsyncTask to provide multithreaded network operations.
 */
public class MySQLQuery {

    String action, username, surveyId, questionId, answers, userId;

    private static final String TAG = "MySQLQuery";

    public MySQLQuery(String action, String username) {
        this.username = username;
        this.action = action;
    }

    public MySQLQuery(String action){
        this.action = action;
    }

    public String query() {

        String link = "http://jstav.site50.net/";
        String phpFileName = "android_sql.php";
        URL url;

        try {
            switch (action) {
                case "login":
                    url = new URL(link + phpFileName + "?action=login&username=" + username);
                    break;

                case "get_survey_list":
                    url = new URL(link + phpFileName + "?action=get_surveys");
                    break;

                case "get_questions":
                    url = new URL(link + phpFileName + "?action=get_questions&survey_id=" + surveyId);
                    break;

                case "get_options":
                    url = new URL(link + phpFileName + "?action=get_options&question_id=" + questionId + "&survey_id=" + surveyId);
                    break;

                case "submit_answers":
                    url = new URL(link + phpFileName + "?action=submit_answers&survey_id=" + surveyId + "&user_id=" + userId + "&answers=" + answers);
                    break;

                case "has_taken_survey":
                    url = new URL(link + phpFileName + "?action=has_taken_survey&user_id=" + userId + "&survey_id=" + surveyId);
                    System.out.println(url.toString());
                    break;

                // Placeholder default
                // TODO: Change to a more appropriate default later on
                default:
                    url = new URL("http://jstav.site50.net/login.php");
                    break;
            }

            return URLResponse(url);


        } catch(MalformedURLException e){
            Log.e(TAG, e.getMessage());
        }

        return "";
    }


    public void setSurveyId(String surveyId){
        this.surveyId = surveyId;
    }

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }

    public void setAnswers(String answers){
        this.answers = answers;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    private String readStream(InputStream is){

        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(is), 1000);

            for (String line = br.readLine(); line != null; line = br.readLine()){
                sb.append(line);
            }

            is.close();
            return sb.toString();
        } catch (IOException e){
            Log.e(TAG, e.getMessage());
        }
        return "";
    }

    private String URLResponse(URL url){

        try {
            HttpURLConnection mUrlConnection = (HttpURLConnection) url.openConnection();
            mUrlConnection.setDoInput(true);
            InputStream is = new BufferedInputStream(mUrlConnection.getInputStream());
            String s = readStream(is);
            Log.i(TAG, s);
            return s;

        } catch (IOException e){
            Log.e(TAG, e.getMessage());
        }

        return "";
    }
}