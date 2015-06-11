package edu.ucf.cop4331.supersweetsurveyapp;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by stavr on 5/31/2015.
 * Class responsible for MySQL queries, will be used in conjunction with AsyncTask to provide multithreaded network operations.
 */
public class MySQLQuery {

    String action;
    String username;
    String surveyId;
    String questionId;
    String answers;

    public MySQLQuery(String action, String username) {
        this.username = username;
        this.action = action;
    }

    public MySQLQuery(String action){
        this.action = action;
    }

    public String query() {

        String link;
        String phpFileName = "android_sql.php";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        HttpResponse response;

        switch (action) {
            case "login":
                link = "http://jstav.site50.net/" + phpFileName + "?action=login&username=" + username;
                System.out.println(link);
                break;

            case "get_survey_list":
                link = "http://jstav.site50.net/" + phpFileName + "?action=get_surveys";
                break;

            case "get_questions":
                link = "http://jstav.site50.net/" + phpFileName + "?action=get_questions&survey_id=" + surveyId;
                break;

            case "get_options":
                link = "http://jstav.site50.net/" + phpFileName + "?action=get_options&question_id=" + questionId;
                break;

            // Placeholder default
            // TODO: Change to a more appropriate default later on
            default:
                link = "http://jstav.site50.net/login.php";
                break;
        }

        try {

            request.setURI(new URI(link));
            response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            // Using StringBuffer as it is thread safe
            StringBuffer sb = new StringBuffer("");
            String line;

            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }

            in.close();

            return sb.toString();

        } catch (URISyntaxException | java.io.IOException e) {
            return "Exception: " + e.getMessage();
        }
    }


    public void setSurveyId(String surveyId){
        this.surveyId = surveyId;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setQuestionId(String questionId){
        this.questionId = questionId;
    }

    public void setAnswers(String answers){
        this.answers = answers;
    }

}