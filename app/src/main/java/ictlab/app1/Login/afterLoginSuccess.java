package ictlab.app1.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;

import ictlab.app1.Booking.ChooseBuilding;
import ictlab.app1.R;
import ictlab.app1.qrCodeClass;
import ictlab.app1.Booking.reservationClass;
import ictlab.app1.SettingsPage.settingsClass;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */

public class afterLoginSuccess extends AppCompatActivity {

    GridLayout mainGrid;
    public String accessToken, clientToken, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterloginsuccess);
        mainGrid = findViewById(R.id.mainGrid);


        Intent intent = getIntent();
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
    }
    public void bookingCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, ChooseBuilding.class);
        myIntent.putExtra("clientToken", clientToken);
        myIntent.putExtra("accessToken", accessToken);
        myIntent.putExtra("uid", uid);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void qrCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, qrCodeClass.class);
        myIntent.putExtra("clientToken", clientToken);
        myIntent.putExtra("accessToken", accessToken);
        myIntent.putExtra("uid", uid);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void reservationCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, reservationClass.class);
        myIntent.putExtra("clientToken", clientToken);
        myIntent.putExtra("accessToken", accessToken);
        myIntent.putExtra("uid", uid);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void settingsCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, settingsClass.class);
        myIntent.putExtra("clientToken", clientToken);
        myIntent.putExtra("accessToken", accessToken);
        myIntent.putExtra("uid", uid);
        afterLoginSuccess.this.startActivity(myIntent);
    }

}
