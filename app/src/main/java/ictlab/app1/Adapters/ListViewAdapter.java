package ictlab.app1.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import ictlab.app1.R;

public class ListViewAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<BuildingList> buildingLists;

    public ListViewAdapter(Activity activity, List<BuildingList> buildingLists) {
        this.activity = activity;
        this.buildingLists = buildingLists;
    }

    @Override
    public int getCount() {
        return buildingLists.size();
    }

    @Override
    public Object getItem(int location) {
        return buildingLists.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_row, null);}

            TextView gebouwnaam = convertView.findViewById(R.id.name);
            TextView klasnaam = convertView.findViewById(R.id.klas);
            BuildingList b = buildingLists.get(position);
            gebouwnaam.setText(b.getBuilding_name());
            klasnaam.setText(b.getClassroom());

        return convertView;
    }
}