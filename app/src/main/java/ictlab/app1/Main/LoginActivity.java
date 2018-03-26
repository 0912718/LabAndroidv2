package ictlab.app1.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import ictlab.app1.R;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void loginButtonClick(View view){
        Intent myIntent = new Intent(LoginActivity.this, afterLoginSuccess.class);
        LoginActivity.this.startActivity(myIntent);
    }



}

