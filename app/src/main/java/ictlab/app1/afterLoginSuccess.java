package ictlab.app1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.support.v7.widget.CardView;
import android.widget.Toast;

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

        //Set Event
        //setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(afterLoginSuccess.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(afterLoginSuccess.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Intent intent = new Intent(afterLoginSuccess.this,ActivityOne.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
//                    startActivity(intent);

                }
            });
        }
    }

    public void bookingCardClick(View view){
        Intent myIntent = new Intent(afterLoginSuccess.this, newBookingClass.class);
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
