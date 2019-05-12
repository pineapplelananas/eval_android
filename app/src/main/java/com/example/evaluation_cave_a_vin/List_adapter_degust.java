package com.example.evaluation_cave_a_vin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class List_adapter_degust extends ArrayAdapter<Degust> {

    private LayoutInflater mInflater;
    private ArrayList<Degust> degusts;
    private int mViewResourceId;

    public List_adapter_degust(Context context, int textViewResourceId, ArrayList<Degust> degusts) {
        super(context, textViewResourceId, degusts);
        this.degusts = degusts;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Degust degust = degusts.get(position);

        if (degust != null) {
            TextView exploitation = (TextView) convertView.findViewById(R.id.exploitation_degut_list);
            TextView date_d = (TextView) convertView.findViewById(R.id.date_d);
            TextView note = (TextView) convertView.findViewById(R.id.note);
            TextView comment = (TextView) convertView.findViewById(R.id.comment);

            if (exploitation != null) {
                exploitation.setText((degust.getExploitation()));
            }
            if (note != null) {
                note.setText((degust.getNote()));
            }
            if (comment != null) {
                comment.setText((degust.getComment()));
            }
            if (date_d != null) {
                date_d.setText((degust.getDate_degust()));
            }
        }

        return convertView;
    }
}
