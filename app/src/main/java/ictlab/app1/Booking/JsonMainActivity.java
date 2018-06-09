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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import ictlab.app1.Adapters.BuildingList;
import ictlab.app1.Adapters.ListViewAdapter;
import ictlab.app1.R;

public class JsonMainActivity extends AppCompatActivity {
    public TextView textView;
    public String buildings_url = "";// "http://192.168.2.6:3000/buildings.json"; TODO ENTER IP ADDRESS HERE
    RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private List<BuildingList> buildingListList = new ArrayList<>();
    private ListViewAdapter adapter;
    private ListView listView;

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
    }
    public void getBuildingsJSON(){
        JsonArrayRequest obreq = new JsonArrayRequest(Request.Method.GET, buildings_url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response.toString());
                        progressDialog.hide();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(0);
                                JSONArray jsonArray = obj.getJSONArray("buildings");
                                BuildingList buildingList = new BuildingList();
                                final JSONObject building = jsonArray.getJSONObject(i);
                                buildingList.setBuilding_name(building.getString("name"));
                                buildingListList.add(buildingList);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        BuildingList b = (BuildingList) adapter.getItem(position);
                                        Intent i = new Intent(JsonMainActivity.this, chooseClassroom.class);
                                        i.putExtra("name",  b.getBuilding_name());
                                        startActivity(i);
                                        System.out.println("Test "+ b.getBuilding_name());
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                        progressDialog.hide();
                    }
                }
        );
        requestQueue.add(obreq);
    }
}


