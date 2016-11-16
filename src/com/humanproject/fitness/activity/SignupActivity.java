package com.humanproject.fitness.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.humanproject.fitness.R;
import com.humanproject.fitness.manager.SessionManager;

public class SignupActivity extends Activity {
    
    private EditText txtName, txtEmail, txtPassword;
     
    private Button btnSignup;
     
    private SessionManager session;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); 
         
        session = new SessionManager(getApplicationContext());                
        
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword); 
         
        btnSignup = (Button) findViewById(R.id.btnSignup);
         
         
        btnSignup.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {
            	String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                 
                signup();
                 
            }
        });
    }

	protected void signup() {
		// TODO Auto-generated method stub
		
	}        
}
