package ictlab.app1.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;

import ictlab.app1.R;
import ictlab.app1.Booking.chooseBuilding;
import ictlab.app1.qrCodeClass;
import ictlab.app1.reservationClass;
import ictlab.app1.settingsClass;

/**
 * Created by edgar on 20-2-2018.
 */

public class afterLoginSuccess extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterloginsuccess);

        mainGrid = findViewById(R.id.mainGrid);

    }

    public void bookingCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, chooseBuilding.class);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void qrCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, qrCodeClass.class);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void reservationCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, reservationClass.class);
        afterLoginSuccess.this.startActivity(myIntent);
    }
    public void settingsCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, settingsClass.class);
        afterLoginSuccess.this.startActivity(myIntent);
    }

}
