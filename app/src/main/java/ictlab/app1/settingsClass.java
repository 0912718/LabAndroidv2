package ictlab.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ictlab.app1.Main.afterLoginSuccess;

/**
 * Created by edgar on 24-2-2018.
 */

public class settingsClass extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void changeNotificationButtonClick(View view){
        Intent myIntent = new Intent(settingsClass.this, notificationClass.class);
        settingsClass.this.startActivity(myIntent);
    }

    public void helpButtonOnClick(View view){
        Intent myIntent = new Intent(settingsClass.this, helpClass.class);
        settingsClass.this.startActivity(myIntent);
    }
}
