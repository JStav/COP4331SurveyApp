package edu.ucf.cop4331.supersweetsurveyapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by stavr on 5/31/2015.
 *
 * Class responsible for managing the logged in state of users.
 */
public class SessionManager {

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    private static final String LOGIN_KEY = "LoginCache";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    private static final String USERNAME = "Username";
    private static final String ID = "ID";

    public SessionManager (Context context){
        this.context = context;
        mSharedPreferences = context.getSharedPreferences(LOGIN_KEY, PRIVATE_MODE);
        editor = mSharedPreferences.edit();
    }

    // Create a login session by setting the SharedPreference key IS_LOGGED_IN to true and storing the username
    public void createLoginSession(String username, String userId){
        editor.putString(USERNAME, username);
        editor.putString(ID, userId);
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.commit();
    }

    // Log out user by changing the the SharedPreference IS_LOGGED_IN to false
    public void logOutUser(){
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.commit();
    }

    public boolean isLoggedIn(){
        return (mSharedPreferences.getBoolean(IS_LOGGED_IN, false));
    }

    public String getCurrentUserId(){

        return mSharedPreferences.getString(ID, "NULL");

    }

    public String getCurrentUserName(){
        return mSharedPreferences.getString(USERNAME, "NULL");
    }

}
