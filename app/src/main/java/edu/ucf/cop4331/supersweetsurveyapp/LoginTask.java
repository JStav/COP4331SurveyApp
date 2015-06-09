package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginTask extends AsyncTask<Void, Void, SurveyUser> {

    private Context context;
    private String username, password, jUser, jPassword, jUserId;
    private String action = "login";
    SessionManager sessionManager;

    public LoginTask(Context context, String username, String password){
        this.context = context;
        this.password = password;
        this.username = username;
    }

    @Override
    protected SurveyUser doInBackground(Void... params) {

        MySQLQuery usernameQuery = new MySQLQuery(action, username);
        String userJSON = usernameQuery.query();

        System.out.println(userJSON);

        SurveyUser surveyUser = new SurveyUser("", "");

        try {

            JSONObject userQuery = new JSONObject(userJSON);
            JSONArray jsonArray = userQuery.getJSONArray("user");
            JSONObject user = jsonArray.getJSONObject(0);

            jUser = user.getString("email");
            jPassword = user.getString("password");
            jUserId = user.getString("user_id");

            surveyUser.setUserId(jUserId);
            surveyUser.setUsername(jUser);

        } catch(JSONException e){
            e.printStackTrace();
        }

        return surveyUser;
    }

    @Override
    protected void onPostExecute(SurveyUser su) {
        super.onPostExecute(su);
        CredentialsManager cm = new CredentialsManager();

        boolean isLoginValid = cm.isCredentialValid(password, jPassword);

        if (isLoginValid){
            // Stores logged in status and username
            sessionManager = new SessionManager(context);
            sessionManager.createLoginSession(su.getUsername(), su.getUserId());

            // Start the browse survey activity
            Intent intent = new Intent(context, BrowseSurveysActivity.class);
            context.startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(context, "Invalid username or password, please try again.", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
