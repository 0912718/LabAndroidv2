package ictlab.app1;

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

import java.util.Calendar;

public class newBookingClass extends AppCompatActivity implements View.OnClickListener
{
    Button btnDatePicker, btnTimePicker, submitSearch;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    CheckBox cBeamer, cWhiteboard, cWindows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);

        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);

        submitSearch = findViewById(R.id.search);

        cBeamer = findViewById(R.id.checkBeamer);
        cWhiteboard = findViewById(R.id.checkWhiteboard);
        cWindows = findViewById(R.id.checkWindows);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitSearch.setOnClickListener(this);


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
            if (cBeamer.isChecked() && cWhiteboard.isChecked() && cWindows.isChecked()) {
                System.out.println("check1");
            } if (!cBeamer.isChecked() && cWhiteboard.isChecked() && cWindows.isChecked()) {
                System.out.println("check2");
            }
        }
    }

}
//https://github.com/geronald10/GomesStudioReservation/blob/master/app/src/main/java/gomes/com/gomesstudioreservation/BookingActivity.java
// https://www.tutorialspoint.com/android/android_json_parser.htm
// http://codetheory.in/android-filters/

