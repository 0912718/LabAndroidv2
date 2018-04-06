package ictlab.app1.Booking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by edgar on 14-3-2018.
 */

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import ictlab.app1.ListTest.RoomList;
import ictlab.app1.ListTest.classRooms;
import ictlab.app1.R;

// http://rdcworld-android.blogspot.nl/2013/01/listview-with-checkbox-using.html
                                // https://developer.android.com/guide/topics/ui/controls/checkbox.html
                                // https://stackoverflow.com/questions/31791101/working-with-checkbox-in-listview-and-filterable-on-android


public class pickerDateTime extends AppCompatActivity implements View.OnClickListener
{
    Button btnDatePicker, btnTimePicker, submitSearch;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private int begintime_id, endtime_id, classroom_id;
    private int date_id;
    RoomList roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimepicker_layout);

        btnDatePicker=findViewById(R.id.btn_date);
        btnTimePicker=findViewById(R.id.btn_time);
        txtDate=findViewById(R.id.in_date);
        txtTime=findViewById(R.id.in_time);

        submitSearch = findViewById(R.id.submitSearch);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        submitSearch.setOnClickListener(this);
        classroom_id = 1;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
        case R.id.btn_date:

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
            date_id = mDay + mMonth + mYear;
            datePickerDialog.show();
            System.out.println(date_id);
            break;
            case R.id.submitSearch:
//                if(!validate())
//                    Toast.makeText(getBaseContext(), "Enter some data!", Toast.LENGTH_LONG).show();
                // call AsynTask to perform network operation on separate thread
                new HttpAsyncTask().execute("http://145.24.222.187:3000/classrooms.json");
                break;

//        }
//        if (v == btnTimePicker) {
//
//            // Get Current Time
//            final Calendar c = Calendar.getInstance();
//            mHour = c.get(Calendar.HOUR_OF_DAY);
//            mMinute = c.get(Calendar.MINUTE);
//
//            // Launch Time Picker Dialog
//            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                    new TimePickerDialog.OnTimeSetListener() {
//
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay,
//                                              int minute) {
//
//                            txtTime.setText(hourOfDay + ":" + minute);
//                        }
//                    }, mHour, mMinute, true);
//            timePickerDialog.show();
//        }
        }

    }
                                                // TODO: CODE OVERGENOMEN VAN http://hmkcode.com/android-send-json-data-to-server/ , ONDERAAN STAAN NOG MEER LINKS VOOR JSON POSTS IN ANDROID!!!!
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {


            roomList = new RoomList(begintime_id, classroom_id, date_id);
            roomList.setBegintime_id(date_id);
//            roomList.setEndtime_id(etCountry.getText().toString());
//            roomList.setDate_id(etTwitter.getText().toString());
            roomList.setClassroom_id(classroom_id);

            return POST(urls[0], roomList);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static String POST(String url, RoomList roomList){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("begintime", roomList.getBegintime_id());
            //jsonObject.accumulate("endtime", roomList.getEndtime_id());
            jsonObject.accumulate("date", roomList.getDate_id());
            jsonObject.accumulate("classroom", roomList.getClassroom_id());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();



            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "reservations");
            httpPost.setHeader("Content-type", "reservations");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }
}

// https://www.tutorialspoint.com/android/android_json_parser.htm

//http://www.baeldung.com/httpclient-post-http-request
//http://www.xyzws.com/Javafaq/how-to-use-httpurlconnection-post-data-to-web-server/139
//https://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/
//https://www.codingdemos.com/android-cardview-example-with-gridlayout/

