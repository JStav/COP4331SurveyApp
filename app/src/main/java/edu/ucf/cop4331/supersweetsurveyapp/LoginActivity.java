package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //TODO: Connect to MySQL database and verify credentials. Possibly store logged in status in sharedpreferences/cache?
    public void checkCredentials(View v){

        EditText passwordField = (EditText) findViewById(R.id.login_password_edittext);
        EditText usernameField = (EditText) findViewById(R.id.login_username_edittext);
        CredentialsManager cm = new CredentialsManager();

        String password = passwordField.getText().toString();
        String username = usernameField.getText().toString();

        String md5 = cm.md5(password);
        Toast toast = Toast.makeText(getApplicationContext(), md5, Toast.LENGTH_LONG);
        toast.show();

        //new MySQLQueryTask().execute();

        Intent intent = new Intent(this, BrowseSurveysActivity.class);

        if (username.equals("surv")) {
            startActivity(intent);
        }
     }
}
