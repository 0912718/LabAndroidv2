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

import ictlab.app1.Adapters.BuildingList;
import ictlab.app1.Adapters.ListViewAdapter;
import ictlab.app1.R;

/**
 * This whole code underneath is coded by Edgar Buyten - 0912718
 */

public class ChooseBuilding extends AppCompatActivity {
    public TextView textView;
    public String buildings_url = "http://192.168.0.101:3000/api/v1/buildings";// "http://192.168.2.6:3000/buildings.json"; TODO ENTER IP ADDRESS HERE
    RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private List<BuildingList> buildingListList = new ArrayList<>();
    private ListViewAdapter adapter;
    private ListView listView;
    public String accessToken, clientToken, uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jsonmainactivity_list);
        requestQueue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.name);
        listView = findViewById(R.id.list);
        adapter = new ListViewAdapter(this, buildingListList);
        listView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... ");
        getBuildingsJSON();

        Intent intent = getIntent();
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
    }
    public void getBuildingsJSON(){
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, buildings_url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        progressDialog.hide();
                        try {
                            JSONArray array = response.getJSONArray("buildings");
                        for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //JSONArray jsonArray = obj.getJSONArray("buildings");
                               // String classroom = obj.getString("classrooms");
                                BuildingList buildingList = new BuildingList();
                                //final JSONObject building = obj.getJSONObject("classrooms");
                                buildingList.setBuilding_name(obj.getString("name"));
                                buildingListList.add(buildingList);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        BuildingList b = (BuildingList) adapter.getItem(position);
                                        Intent i = new Intent(ChooseBuilding.this, chooseClassroom.class);
                                        i.putExtra("name",  b.getBuilding_name());
                                        i.putExtra("clientToken", clientToken);
                                        i.putExtra("accessToken", accessToken);
                                        i.putExtra("uid", uid);
                                        startActivity(i);
                                        System.out.println("Test "+ b.getBuilding_name());
                                    }
                                });
                            }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                        progressDialog.hide();
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("access-token", accessToken);
                params.put("client", clientToken);
                params.put("uid",uid);
                return params;
            }};
        requestQueue.add(obreq);
    }
}


