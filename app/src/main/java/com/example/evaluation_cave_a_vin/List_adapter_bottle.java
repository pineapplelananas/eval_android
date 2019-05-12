package com.example.evaluation_cave_a_vin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class List_adapter_bottle  extends ArrayAdapter<Bottle> {

    private LayoutInflater mInflater;
    private ArrayList<Bottle> bottles;
    private int mViewResourceId;

    public List_adapter_bottle(Context context, int textViewResourceId, ArrayList<Bottle> bottles) {
        super(context, textViewResourceId, bottles);
        this.bottles = bottles;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Bottle bottle = bottles.get(position);

        if (bottle != null) {
            TextView appellation = (TextView) convertView.findViewById(R.id.appelation);
            TextView exploitation = (TextView) convertView.findViewById(R.id.exploitation);
            TextView type = (TextView) convertView.findViewById(R.id.type);
            TextView millesime = (TextView) convertView.findViewById(R.id.millesime);
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView stock_i = (TextView) convertView.findViewById(R.id.stock_in);
            TextView stock_o = (TextView) convertView.findViewById(R.id.stock_out);
            TextView price = (TextView) convertView.findViewById(R.id.price);

            if (exploitation != null) {
                exploitation.setText(bottle.getExploitation());
            }
            if (appellation != null) {
                appellation.setText((bottle.getAppellation()));
            }
            if (type != null) {
                type.setText((bottle.getType()));
            }
            if (millesime != null) {
                millesime.setText((bottle.getMillesime()));
            }
            if (date != null) {
                date.setText((bottle.getDate()));
            }
            if (stock_i != null) {
                stock_i.setText((bottle.getStock_in()));
            }
            if (stock_o != null) {
                stock_o.setText((bottle.getStock_out()));
            }if (price != null) {
                price.setText((bottle.getPrice()));
            }
        }

        return convertView;
    }
}
