package ictlab.app1.Booking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by edgar on 14-3-2018.
 */

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import ictlab.app1.R;

// http://rdcworld-android.blogspot.nl/2013/01/listview-with-checkbox-using.html
                                // https://developer.android.com/guide/topics/ui/controls/checkbox.html
                                // https://stackoverflow.com/questions/31791101/working-with-checkbox-in-listview-and-filterable-on-android


public class pickerDateTime extends AppCompatActivity implements View.OnClickListener
{
    Button btnDatePicker, btnTimePicker, submitSearch;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    CheckBox cBeamer, cWhiteboard, cWindows;
//    //url to get json
//    private static String url = "rooms.json";
//
//    //json node names
//    private static final String TAG_rooms = "roomnumber";
//    private static final String TAG_hasBeamer = "hasBeamer";
//    private static final String TAG_hasWhiteboard = "hasWhiteboard";
//    private static final String TAG_hasWindows = "hasOpenWindows";
//
//    JSONArray rooms = null;
//
//    ArrayList<HashMap<String, String>> roomList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);

        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);

        submitSearch = findViewById(R.id.search);

//        cBeamer = findViewById(R.id.checkBeamer);
//        cWhiteboard = findViewById(R.id.checkWhiteboard);
//        cWindows = findViewById(R.id.checkWindows);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitSearch.setOnClickListener(this);

      //  roomList = new ArrayList<HashMap<String, String>>();



    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
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

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if(v == submitSearch){
            // TODO only show rooms for availability.
            //https://github.com/square/moshi

        }
    }
//    public void oncCheckBoxClicked(View view){
//        boolean checked = ((CheckBox) view).isChecked();
//
//        switch(view.getId()){
//            case R.id.checkBeamer:
//                if(checked)
//                    //if checkbox is clicked, then beamer in json is yes, only show rooms with beamer
//                    ;
//                else
//                    ;
//                break;
//            case R.id.checkWhiteboard:
//                if(checked)
//                    ;
//                else
//                    ;
//                break;
//            case R.id.checkWindows:
//                if(checked)
//                    ;
//                else
//                    ;
//                break;
//                }
//        }
//    }

}
//https://github.com/geronald10/GomesStudioReservation/blob/master/app/src/main/java/gomes/com/gomesstudioreservation/BookingActivity.java
// https://www.tutorialspoint.com/android/android_json_parser.htm
// http://codetheory.in/android-filters/

