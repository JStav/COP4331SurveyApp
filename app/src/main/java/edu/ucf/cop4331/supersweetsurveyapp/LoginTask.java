package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginTask extends AsyncTask<String, Void, String> {

    private Context context;
    private String password;
    private String username;
    private String action = "login";
    SessionManager sessionManager;

    public LoginTask(Context context, String username, String password){
        this.context = context;
        this.password = password;
        this.username = username;
    }

    @Override
    protected String doInBackground(String... params) {

        MySQLQuery usernameQuery = new MySQLQuery(action, username);
        return usernameQuery.query();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        CredentialsManager cm = new CredentialsManager();

        boolean isLoginValid = cm.isCredentialValid(password, s);

        if (isLoginValid){
            // Stores logged in status and username
            sessionManager = new SessionManager(context);
            sessionManager.createLoginSession(username);

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
