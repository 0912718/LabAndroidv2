package ictlab.app1.Booking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by edgar on 14-3-2018.
 */
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import java.util.Calendar;

import ictlab.app1.R;


public class pickerDateTime extends AppCompatActivity implements View.OnClickListener {
    Button  submitSearch, btnDescription, btnTitle;
    EditText txtDate;
    public int mYear, mMonth, mDay;
    public String date_id, gebouwnaam, klasnaam, title, description, endtime, begintime;
    private String m_Text = "";
    private String m_TextDes = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);
        btnDescription = findViewById(R.id.btnDescription);
        btnTitle = findViewById(R.id.btnTitle);
        txtDate = findViewById(R.id.in_date);
        submitSearch = findViewById(R.id.submitSearch);
        submitSearch.setOnClickListener(this);
        btnDescription.setOnClickListener(this);
        btnTitle.setOnClickListener(this);
        Intent intent = getIntent();
        gebouwnaam = intent.getStringExtra("name");
        klasnaam = intent.getStringExtra("classroom");
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
        final NumberPicker np = findViewById(R.id.np);
        final NumberPicker np1 = findViewById(R.id.np1);
        np1.setMinValue(1);
        np1.setMaxValue(15);
        np.setMinValue(1);
        np.setMaxValue(15);
        np.getDisplayedValues();
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                np1.setMinValue(np.getValue() + 1);
                begintime = String.valueOf(np.getValue());
                System.out.println(begintime);
            }
        });
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                endtime = String.valueOf(np1.getValue());
                System.out.println(endtime);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTitle:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Please enter you studentnumber");
                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
            case R.id.btnDescription:
                AlertDialog.Builder builderDes = new AlertDialog.Builder(this);
                builderDes.setTitle("Please enter reason you are booking, i.e. ICT-lab meeting");
                final EditText inputDes = new EditText(this);
                inputDes.setInputType(InputType.TYPE_CLASS_TEXT );
                builderDes.setView(inputDes);
                builderDes.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_TextDes = inputDes.getText().toString();
                    }
                });
                builderDes.show();
                break;
            case R.id.submitSearch:
                if(m_Text.isEmpty() || m_TextDes.isEmpty()) {
                    Toast.makeText(pickerDateTime.this,
                            "Please enter studentnumber and description", Toast.LENGTH_LONG).show();
                } else {
                    JsonPost post = new JsonPost();
                    post.sendData(date_id, m_Text, m_TextDes, begintime, endtime, klasnaam);
                    System.out.println(post.toString());
                    Toast.makeText(pickerDateTime.this,
                            "Post sended, check webapp for reservation", Toast.LENGTH_LONG).show();
                }
        }

    }
}





