package ictlab.app1.Booking;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.api.client.json.Json;

import java.util.Calendar;

import ictlab.app1.R;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */

public class pickerDateTime extends AppCompatActivity implements View.OnClickListener {
    Button  submitSearch, btnDescription;
    EditText txtDate;
    public int mYear, mMonth, mDay;
    public String date_id, gebouwnaam, klasnaam, title, description, endtime, begintime,accessToken, clientToken, uid, klasID;
    private String m_TextDes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);
        btnDescription = findViewById(R.id.btnDescription);
        txtDate = findViewById(R.id.in_date);
        submitSearch = findViewById(R.id.submitSearch);
        submitSearch.setOnClickListener(this);
        btnDescription.setOnClickListener(this);

        Intent intent = getIntent();
        klasID = intent.getStringExtra("id");
        gebouwnaam = intent.getStringExtra("name");
        klasnaam = intent.getStringExtra("classroom");
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
        System.out.println(accessToken + " " + clientToken + " " +uid);

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
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

        final NumberPicker np = findViewById(R.id.np);
        final NumberPicker np1 = findViewById(R.id.np1);
        np.setMinValue(1);
        np.setMaxValue(15);
        np1.setMinValue(1);
        np1.setMaxValue(15);
        np.getDisplayedValues();
        np1.getDisplayedValues();
        np.setWrapSelectorWheel(true);
        begintime = String.valueOf(np.getMinValue());
        endtime = String.valueOf(np1.getMinValue());
        System.out.println(begintime + " " + endtime);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                np1.setMinValue(np.getValue());
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
                title = uid;
                        if(m_TextDes.equals("")){
                            Toast.makeText(pickerDateTime.this, "Error: Please enter description i.e. ICT-lab meeting ", Toast.LENGTH_LONG).show();
                            break;
                        }
                        if(date_id.equals("")){
                            Toast.makeText(pickerDateTime.this, "Error: Must choose a valid date", Toast.LENGTH_LONG).show();
                            break;
                        }
                        else {
                            JsonPost post = new JsonPost();
                            post.clientToken = clientToken;
                            post.accessToken = accessToken;
                            post.uid = uid;
                            if(post.sendData(date_id, title, m_TextDes, begintime, endtime, klasID)){
                                Toast.makeText(pickerDateTime.this, "Reservation made! ", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(pickerDateTime.this, "Reservation failed, please choose another date and/or time", Toast.LENGTH_LONG).show();
                            }
                            //System.out.println(post.toString());

//                            Toast.makeText(pickerDateTime.class,
//                                    "Post sended, check webapp for reservation", Toast.LENGTH_LONG).show();
                }
        }

    }

}






