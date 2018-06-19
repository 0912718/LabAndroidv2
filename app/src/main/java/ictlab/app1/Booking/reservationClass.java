package ictlab.app1.Booking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ictlab.app1.Adapters.ListViewAdapter;
import ictlab.app1.Adapters.ReservationsAdapter;
import ictlab.app1.Adapters.ReservationsList;
import ictlab.app1.R;

//shows current made reservations and could delete them, no refactoring needed on change
public class reservationClass extends Activity {
  //  public TextView textView;
    RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private ReservationsAdapter reservationsAdapter;
    private ListView listView;
    public String accessToken, clientToken, uid;
    private List<ReservationsList> reservationsListList = new ArrayList<>();
    public String myresURL = "http://145.24.222.187:3000/api/v1/reservations"; //TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonmainactivity_list);
        requestQueue = Volley.newRequestQueue(this);
        listView = findViewById(R.id.list);
        reservationsAdapter = new ReservationsAdapter(this, reservationsListList);
        listView.setAdapter(reservationsAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... ");
        getMyReservations();

        Intent intent = getIntent();
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
    }
    public void getMyReservations() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, myresURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("reservations");
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject objectRequest = array.getJSONObject(i);
                                ReservationsList reservationsList = new ReservationsList();
                                reservationsList.setId(objectRequest.getString("id"));
                                reservationsList.setClassroom_id(objectRequest.getString("classroom_id"));
                                reservationsList.setClassroom_name(objectRequest.getString("classroom_name"));
                                reservationsList.setDate(objectRequest.getString("date"));
                                reservationsList.setTitle(objectRequest.getString("title"));
                                reservationsList.setDescription(objectRequest.getString("description"));
                                reservationsList.setFrom_block(objectRequest.getString("from_block"));
                                reservationsList.setTo_block(objectRequest.getString("to_block"));
                                reservationsListList.add(reservationsList);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        ReservationsList r = (ReservationsList) reservationsAdapter.getItem(position);
                                        Intent i = new Intent(reservationClass.this, MyReservations.class);
                                        i.putExtra("id", r.getId());
                                        i.putExtra("name", r.getClassroom_name());
                                        i.putExtra("date", r.getDate());
                                        i.putExtra("title", r.getTitle());
                                        i.putExtra("description", r.getDescription());
                                        i.putExtra("from", r.getFrom_block());
                                        i.putExtra("to", r.getTo_block());
                                        i.putExtra("clientToken", clientToken);
                                        i.putExtra("accessToken", accessToken);
                                        i.putExtra("uid", uid);
                                          startActivity(i);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        reservationsAdapter.notifyDataSetChanged();

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
        requestQueue.add(objectRequest);
    }
}
