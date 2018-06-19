package ictlab.app1.Booking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ictlab.app1.Adapters.ReservationsAdapter;
import ictlab.app1.Adapters.ReservationsList;
import ictlab.app1.R;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */
//gives a list of classrooms from a json on the server. when adding new classroom, no refactoring is needed!!!

public class chooseClassroom extends AppCompatActivity {
    public TextView textView;
    private ProgressDialog progressDialog;
    private List<ReservationsList> reservationsListList = new ArrayList<>();
    private ReservationsAdapter adapter;
    private ListView listView;
    public String url = "http://192.168.0.101"; // "http://192.168.2.6"; TODO ENTER IP ADDRESS
    public String buildingString,accessToken, clientToken, uid;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonmainactivity_list);
        requestQueue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.name);
        listView = findViewById(R.id.list);
        adapter = new ReservationsAdapter(this, reservationsListList);
        listView.setAdapter(adapter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... ");

        Intent intent = getIntent();
        String building_id = intent.getStringExtra("id");
        String buildingClick= intent.getStringExtra("name");
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
        buildingString = buildingClick;
        System.out.println("chooseCLASSROOM " + buildingClick);

        url = url + ":3000/api/v1/buildings/" + building_id+ "/classrooms";
        getClassroomsJSON();
        System.out.println(url);

    }

    public void getClassroomsJSON() {
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        progressDialog.hide();
                        try {
                            JSONArray array = response.getJSONArray("classrooms");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //JSONArray jsonArray = obj.getJSONArray("buildings");
                                // String classroom = obj.getString("classrooms");
                                ReservationsList reservationsList = new ReservationsList();
                                //final JSONObject building = obj.getJSONObject("classrooms");

                                reservationsList.setTitle(obj.getString("name"));
                                reservationsList.setClassroom_id(obj.getString("id"));

                                reservationsListList.add(reservationsList);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        ReservationsList r = (ReservationsList) adapter.getItem(position);

                                        Intent i = new Intent(chooseClassroom.this, pickerDateTime.class);
                                        System.out.println(r.getClassroom_id());
                                        i.putExtra("id", r.getClassroom_id());
                                        i.putExtra("clientToken", clientToken);
                                        i.putExtra("accessToken", accessToken);
                                        i.putExtra("uid", uid);
                                          startActivity(i);
                                         System.out.println("Test " + r.getClassroom_id());
                                    }
                                });
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();

                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("access-token", accessToken);
                params.put("client", clientToken);
                params.put("uid", uid);
                return params;
            }
        };
        requestQueue.add(obreq);
    }
}
