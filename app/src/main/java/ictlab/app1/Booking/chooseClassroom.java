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

public class chooseClassroom extends AppCompatActivity {
    public TextView textView;
    private ProgressDialog progressDialog;
    private List<BuildingList> buildingListList = new ArrayList<>();
    private ListViewAdapter adapter;
    private ListView listView;
    public String url = "http://145.24.222.187"; // "http://192.168.2.6"; TODO ENTER IP ADDRESS
    public String buildingString,accessToken, clientToken, uid, classroomId;
    RequestQueue requestQueue;


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

        Intent intent = getIntent();
        String building_id = intent.getStringExtra("id");
        String buildingClick= intent.getStringExtra("name");
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");
        buildingString = buildingClick;
        System.out.println("chooseCLASSROOM " + buildingClick);

        url = url + ":3000/buildings/" + building_id+ ".json";
        getClassroomsJSON();
        System.out.println(url);

    }

    public void getClassroomsJSON() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.hide();
                        try {
                            JSONObject obj = response.getJSONObject(0);
                            JSONArray jsonArray = obj.getJSONArray("classrooms");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject classroom = jsonArray.getJSONObject(i);
                                final BuildingList classroomList = new BuildingList();

                                classroomList.setClassroom(classroom.getString("name"));
                                classroomList.setClassroom_id(classroom.getString("id"));

                                buildingListList.add(classroomList);
                                System.out.println(classroomList.toString());

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                        BuildingList c = (BuildingList) adapter.getItem(position);
                                        Intent i = new Intent(chooseClassroom.this, pickerDateTime.class);
                                        i.putExtra("id", c.getClassroom_id());
                                        i.putExtra("name", buildingString);
                                        i.putExtra("classroom", c.getClassroom());
                                        i.putExtra("clientToken", clientToken);
                                        i.putExtra("accessToken", accessToken);
                                        i.putExtra("uid", uid);
                                        startActivity(i);
                                        System.out.println("Test " + c.getClassroom() + " Test " + c.getClassroom_id());
                                    }
                                });
                                }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", error.toString());
                        progressDialog.hide();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("access-token", accessToken);
                params.put("client", clientToken);
                params.put("uid",uid);
                return params;
            }
        };
        requestQueue.add(request);
    }
}
