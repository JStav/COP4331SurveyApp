package edu.ucf.cop4331.supersweetsurveyapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CreateAccountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    public void createAccount(View v){

        CredentialsManager cm = new CredentialsManager();
        final Toast toast = new Toast(this);

        EditText firstNameET = (EditText) findViewById(R.id.fname_edit_text);
        EditText lastNameET = (EditText) findViewById(R.id.lname_edit_text);
        EditText emailET = (EditText) findViewById(R.id.email_edit_text);
        EditText passET = (EditText) findViewById(R.id.password_edit_text);
        EditText confirmPassET = (EditText) findViewById(R.id.confirm_password_edit_text);

        String firstName = firstNameET.getText().toString().trim();
        String lastName = lastNameET.getText().toString().trim();
        String email = emailET.getText().toString();
        String pass = passET.getText().toString();
        String confirmPass = confirmPassET.getText().toString();

        final String finalString = "('" + email + "','" + cm.md5(pass) + "','" + firstName + "','" + lastName +  "')";

        if(!pass.equals(confirmPass)){

            toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();

        } else {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    MySQLQuery createAccountQuery = new MySQLQuery("create_account");
                    createAccountQuery.setCredentials(finalString);

                    String isQuerySuccessful = createAccountQuery.query();

                    if(isQuerySuccessful.trim().equals("1")){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast.makeText(getApplicationContext(), "Account has been created!", Toast.LENGTH_SHORT).show();

                            }
                        });

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                toast.makeText(getApplicationContext(), "An account already exists with that email address, please try again.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            });

            thread.start();

        }
    }

}
