package com.humanproject.fitness.manager;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.humanproject.fitness.activity.LoginActivity;
import com.humanproject.fitness.bean.User;


public class SessionManager {
    private SharedPreferences pref;   
    private Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "FitnessPref";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";    
    public static final String KEY_LOCATION = "location";
    public static final String KEY_TSTEPS = "tsteps"; //total steps
    public static final String KEY_DSTEPS = "dsteps"; //daily steps   
    
     
    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
     
    public void createLoginSession(String email){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }   
     
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);             
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);             
            context.startActivity(i);
        }         
    }
     
    public void logoutUser(){
        editor.clear();
        editor.commit();         
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);         
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);         
        context.startActivity(i);
    }
     
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGGED_IN, false);
    }
    
    public User getUserDetails(){
        User user = new User();
        user.setEmail(pref.getString(KEY_NAME, null));
        user.setName(pref.getString(KEY_NAME, null));
        user.setTotalSteps(pref.getLong(KEY_TSTEPS, 0));
        user.setDailySteps(pref.getInt(KEY_DSTEPS, 0));
   
        return user;
    }
    
    public void updateCurrentSession(User user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_LOCATION, user.getLocation());
        editor.putLong(KEY_TSTEPS, user.getTotalSteps());
        editor.putInt(KEY_DSTEPS, user.getDailySteps());
        
        editor.commit();    	
    }
}