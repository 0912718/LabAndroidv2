package ictlab.app1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


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

