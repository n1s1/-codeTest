package com.humanproject.fitness.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.humanproject.fitness.R;
import com.humanproject.fitness.bean.User;
import com.humanproject.fitness.manager.SessionManager;


public class MainActivity extends ActionBarActivity {
    private SessionManager session;
    private Button btnLogout;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
         
        TextView nameTxt = (TextView) findViewById(R.id.nameVal);
        TextView emailTxt = (TextView) findViewById(R.id.emailVal);
        TextView locationTxt = (TextView) findViewById(R.id.locationVal);
        TextView dstepsTxt = (TextView) findViewById(R.id.dstepsVal);
        TextView tstepsTxt = (TextView) findViewById(R.id.tstepsVal);

         
         
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        User user = session.getUserDetails(); 
        nameTxt.setText(user.getName());
        emailTxt.setText(user.getEmail());
        locationTxt.setText(user.getLocation());        
        dstepsTxt.setText(String.valueOf(user.getDailySteps())); 
        tstepsTxt.setText(String.valueOf(user.getTotalSteps()));         
//        nameTxt.setText(Html.fromHtml("Name: <b>" + user.getName() + "</b>"));
//        emailTxt.setText(Html.fromHtml("Email: <b>" + user.getEmail() + "</b>"));
//        locationTxt.setText(Html.fromHtml("Location: <b>" + user.getLocation() + "</b>"));        
//        dstepsTxt.setText(Html.fromHtml("Daily Steps: <b>" + user.getDailySteps() + "</b>")); 
//        tstepsTxt.setText(Html.fromHtml("Total Steps: <b>" + user.getTotalSteps() + "</b>")); 
         
        btnLogout.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
                session.logoutUser();
            }
        });
    }
         
}