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

/**
 * This whole code underneath this line is coded by Edgar Buyten - 0912718
 */

public class ReservationsAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<ReservationsList> reservationsLists;


    public ReservationsAdapter(Activity activity, List<ReservationsList> reservationsLists) {
        this.activity = activity;
        this.reservationsLists = reservationsLists;
    }

    @Override
    public int getCount() {
        return reservationsLists.size();
    }


    @Override
    public Object getItem(int location) {
        return reservationsLists.get(location);
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

        TextView date = convertView.findViewById(R.id.reserveringid);
        ReservationsList r = reservationsLists.get(position);
        date.setText(r.getTitle());


        return convertView;
    }
}