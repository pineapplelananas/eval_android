package com.example.evaluation_cave_a_vin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ButtleActivity extends AppCompatActivity {

    private String TAG = ButtleActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<Bottle> bottleList;
    Bottle bottle;
    Bottle item;
    MAbaseOpenHelper bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttle);

        bdd = new MAbaseOpenHelper(this);

        lv = (ListView) findViewById(R.id.listView);
        viewBottle();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = lv.getItemAtPosition(position).toString();
                System.out.println(text);
                Toast.makeText(ButtleActivity.this, "" + text, Toast.LENGTH_SHORT).show();
                Cursor data = bdd.getAllBottle();
                int numRows = data.getCount();
                System.out.println(numRows);

                if (numRows == 0) {
                    Toast.makeText(ButtleActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
                } else {
                    data.moveToPosition(position);
                    item = new Bottle(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8));

                    String id_b = item.getId();
                    String exploitation = item.getExploitation();
                    String appellation = item.getAppellation();
                    String type = item.getType();
                    String millesime = item.getMillesime();
                    String date = item.getDate();
                    String stock_in = item.getStock_in();
                    String stock_out = item.getStock_out();
                    String price = item.getPrice();

                    Intent intent = new Intent(ButtleActivity.this, ItemBottleActivity.class);

                    intent.putExtra("id",id_b);
                    intent.putExtra("exploitation", exploitation);
                    intent.putExtra("appellation", appellation);
                    intent.putExtra("type", type);
                    intent.putExtra("millesime", millesime);
                    intent.putExtra("date", date);
                    intent.putExtra("stock_in", stock_in);
                    intent.putExtra("stock_out", stock_out);
                    intent.putExtra("price", price);
                    startActivity(intent);
                }
            }
        });
    }

    public void viewBottle() {
        bottleList = new ArrayList<>();
        Cursor data = bdd.getAllBottle();

        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ButtleActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                bottle = new Bottle(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8));
                bottleList.add(i, bottle);
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3));
                System.out.println(bottleList.get(i).getExploitation());
                i++;
            }
            List_adapter_bottle adapter = new List_adapter_bottle(this, R.layout.bottlelist, bottleList);
            lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(adapter);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    /* appel de la classe reglages (menu de config)
    uniquement si l'option action_settings est choisie
    */
        if (id == R.id.bottles
        ) {
            startActivityForResult(new Intent(this, ButtleActivity.class),
                    1
            );
        }
        if (id == R.id.purchase
        ) {
            startActivityForResult(new Intent(this, PurchaseActivity.class),
                    1
            );
        }
        if (id == R.id.degusts
        ) {
            startActivityForResult(new Intent(this, DegustActivity.class),
                    1
            );
        }
        if (id == R.id.new_degust
        ) {
            startActivityForResult(new Intent(this, MakeDegustActivity.class),
                    1
            );
        }
        return super.onOptionsItemSelected(item);
    }
}


