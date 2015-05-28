package edu.ucf.cop4331.supersweetsurveyapp;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by stavr on 5/28/2015.
 */
public class MySQLQueryTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        String link = "http://jstav.site50.net/login.php?username=stav";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        HttpResponse response;

        try {
            request.setURI(new URI(link));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            response = client.execute(request);
            return response.toString();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
