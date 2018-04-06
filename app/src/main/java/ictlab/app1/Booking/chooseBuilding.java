package ictlab.app1.Booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ictlab.app1.ListTest.ListTestActivity;
import ictlab.app1.R;

public class chooseBuilding extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosebuilding);
        expandableListView =  findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == "1st floor"){
                    Intent intent = new Intent(chooseBuilding.this, ListTestActivity.class);
                    startActivity(intent);
                }
                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == "2nd floor"){
                    Intent intent = new Intent(chooseBuilding.this, pickerDateTime.class);
                    startActivity(intent);
                }
                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == "3rd floor"){
                    Intent intent = new Intent(chooseBuilding.this, pickerDateTime.class);
                    startActivity(intent);
                }
                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == "4th floor"){
                    Intent intent = new Intent(chooseBuilding.this, ListTestActivity.class);
                    startActivity(intent);
                }
                if(expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition) == "5th floor"){
                    Intent intent = new Intent(chooseBuilding.this, pickerDateTime.class);
                    startActivity(intent);
                }


                return false;
            }
        });
    }

}