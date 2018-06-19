package ictlab.app1.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import ictlab.app1.Login.afterLoginSuccess;
import ictlab.app1.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyReservations extends AppCompatActivity {
    public String id, name, accessToken, clientToken, uid, date, title, description, from, to;
    public TextView classroomnaam, classroomdatum, classroomTitel, classroomDescription, classroomFrom, classroomTo;
    Button deletebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservations);

        classroomnaam = findViewById(R.id.classroomnaam);
        classroomdatum = findViewById(R.id.classroomdatum);
        classroomTitel = findViewById(R.id.classroomTitel);
        classroomDescription = findViewById(R.id.classroomDescription);
        classroomFrom = findViewById(R.id.classroomFrom);
        classroomTo = findViewById(R.id.classroomTo);

        Intent i = getIntent();

        accessToken = i.getStringExtra("accessToken");
        clientToken = i.getStringExtra("clientToken");
        uid = i.getStringExtra("uid");

        id = i.getStringExtra("id");
        name = i.getStringExtra("name");
        date = i.getStringExtra("date");
        title = i.getStringExtra("title");
        description = i.getStringExtra("description");
        from = i.getStringExtra("from");
        to = i.getStringExtra("to");

        switch (from) {
            case "1":
                from =  "8:30";
                break;
            case "2":
                from = "9:20";
                break;
            case "3":
                from = "10:30";
                break;
            case "4":
                from = "11:20";
                break;
            case "5":
                from = "12:10";
                break;
            case "6":
                from = "13:00";
                break;
            case "7":
                from = "13:50";
                break;
            case "8":
                from = "15:00";
                break;
            case "9":
                from = "15:50";
                break;
            case "10":
                from = "17:00";
                break;
            case "11":
                from = "17:50";
                break;
            case "12":
                from = "18:40";
                break;
            case "13":
                from = "19:30";
                break;
            case "14":
                from = "20:20";
                break;
            case "15":
                from = "21:10";
                break;
        }
        switch (to) {
            case "1":
                to =  "8:30";
                break;
            case "2":
                to = "9:20";
                break;
            case "3":
                to = "10:30";
                break;
            case "4":
                to = "11:20";
                break;
            case "5":
                to = "12:10";
                break;
            case "6":
                to = "13:00";
                break;
            case "7":
                to = "13:50";
                break;
            case "8":
                to = "15:00";
                break;
            case "9":
                to = "15:50";
                break;
            case "10":
                to = "17:00";
                break;
            case "11":
                to = "17:50";
                break;
            case "12":
                to = "18:40";
                break;
            case "13":
                to = "19:30";
                break;
            case "14":
                to = "20:20";
                break;
            case "15":
                to = "21:10";
                break;
        }

        classroomnaam.setText("Reservation name: " + name);
        classroomdatum.setText("Reservation date: " + date);
        classroomTitel.setText("Reservation Title: " + title);
        classroomDescription.setText("Reservation Description: " + description);
        classroomFrom.setText("Beginning Timeslot: " + from);
        classroomTo.setText("Ending Timeslot: " + to);
    }
    public void deleteButton(View view){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://192.168.0.101:3000/api/v1/reservations/" + id)
                .delete()
                .addHeader("Content-Type", "application/json")
                .addHeader("client", clientToken)
                .addHeader("access-token", accessToken)
                .addHeader("uid", uid)
                .build();
        Intent myIntent = new Intent(MyReservations.this, afterLoginSuccess.class);
        myIntent.putExtra("clientToken", clientToken);
        myIntent.putExtra("accessToken", accessToken);
        myIntent.putExtra("uid", uid);
        MyReservations.this.startActivity(myIntent);
        //Intent i = new Intent();
        try {
            Response response = client.newCall(request).execute();

            Toast.makeText(MyReservations.this, "Reservation Deleted", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
