package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

    LoginTask loginTask;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Check logged in status, if logged in, skip the login screen and go straight to take survey
        sessionManager = new SessionManager(this);
        boolean isLoggedIn = sessionManager.isLoggedIn();

        if (isLoggedIn){
            Intent intent = new Intent(this, BrowseSurveysActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText userField = (EditText) findViewById(R.id.login_username_edittext);
        EditText passwordField = (EditText) findViewById(R.id.login_password_edittext);

        userField.setText("the.biri@gmail.com");
        passwordField.setText("test@123");
    }

    // Override the back button to go home instead of back to the list of surveys (from tapping logout)
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    // Will be executed in a background thread in order to not slow down the main UI thread
    public void checkCredentials(View v){
        EditText passwordField = (EditText) findViewById(R.id.login_password_edittext);
        EditText usernameField = (EditText) findViewById(R.id.login_username_edittext);

        String password = passwordField.getText().toString();
        String username = usernameField.getText().toString().toLowerCase();

        loginTask = new LoginTask(this, username, password);
        loginTask.execute();
     }

}
