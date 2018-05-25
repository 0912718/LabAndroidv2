package ictlab.app1.Booking;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by edgar on 14-3-2018.
 */
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ictlab.app1.ListTest.RoomList;
import ictlab.app1.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

// http://rdcworld-android.blogspot.nl/2013/01/listview-with-checkbox-using.html
                                // https://developer.android.com/guide/topics/ui/controls/checkbox.html
                                // https://stackoverflow.com/questions/31791101/working-with-checkbox-in-listview-and-filterable-on-android


public class pickerDateTime extends AppCompatActivity implements View.OnClickListener {
    Button btnDatePicker, btnTimePicker, submitSearch;
    EditText txtDate, txtTime;
    public int mYear, mMonth, mDay, mHour, mMinute;
    public int begintime_id, endtime_id, classroom_id, from, to;
    public String date_id;
    public String title, description;
    RoomList roomList;
    //String url = "http://192.168.0.101:3000/reservations.json";
   // String url = "http://127.0.0.1:3000/reservations.json";


    // private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);

        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        submitSearch = findViewById(R.id.submitSearch);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_date:

                datePicker();

                break;
            case R.id.btn_time:
//
//                // Get Current Time
//                final Calendar ca = Calendar.getInstance();
//                mHour = ca.get(Calendar.HOUR_OF_DAY);
//                mMinute = ca.get(Calendar.MINUTE);
//
//                // Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePickerDialog view, int hourOfDay,
//                                                  int minute) {
//
//                                txtTime.setText(hourOfDay + ":" + minute);
//                            }
//                        }, mHour, mMinute, true);
//                timePickerDialog.show();
//                break;
            case R.id.submitSearch:
                JsonPost post = new JsonPost();
                post.sendData();
                Toast.makeText(pickerDateTime.this,
                        "Post sended, check webapp for reservation", Toast.LENGTH_LONG).show();

        }

    }
//    public void setDate(final Calendar calendar){
//        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
//        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//    }


    public void datePicker(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        date_id = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        System.out.println(date_id);
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();

    }
}


