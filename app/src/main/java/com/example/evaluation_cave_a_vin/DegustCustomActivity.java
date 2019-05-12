package com.example.evaluation_cave_a_vin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DegustCustomActivity extends AppCompatActivity {

    private String TAG = DegustCustomActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<Degust> degustList;
    Degust custom_degust;
    Degust item;
    Bottle bottle;
    MAbaseOpenHelper bdd;
    String id_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_degust);
        id_c = getIntent().getStringExtra("id");
        bdd = new MAbaseOpenHelper(this);
        lv = (ListView) findViewById(R.id.listdegust_c);
        openCustomDegust();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor data = bdd.getAllDegust();
                int numRows = data.getCount();
                System.out.println(numRows);

                if (numRows == 0) {
                    Toast.makeText(DegustCustomActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
                } else {
                    data.moveToPosition(position);
                    item = new Degust(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4));

                    String exploitation = item.getExploitation();
                    String date_d = item.getDate_degust();
                    String note = item.getNote();
                    String comment = item.getComment();

                    Intent intent = new Intent(DegustCustomActivity.this, ItemDegustActivity.class);

                    intent.putExtra("exploitation", exploitation);
                    intent.putExtra("date_degust", date_d);
                    intent.putExtra("note", note);
                    intent.putExtra("comment", comment);
                    startActivity(intent);
                }
            }
        });
    }

    public void openCustomDegust(){
        degustList = new ArrayList<>();
        Cursor data = bdd.getAllDegust();
        int numRows = data.getCount();
        boolean good_degust = false;
        if (numRows == 0) {
            Toast.makeText(DegustCustomActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                custom_degust = new Degust(data.getString(0), data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                String value = getIntent().getStringExtra("id");
                int value_int = Integer.parseInt(value);
                Cursor data_a = bdd.getAllBottle();
                while (data_a.moveToNext()) {
                    bottle = new Bottle(data_a.getString(0),data_a.getString(1), data_a.getString(2), data_a.getString(3), data_a.getString(4), data_a.getString(5), data_a.getString(6), data_a.getString(7), data_a.getString(8));
                    String id_bottle = custom_degust.getId();
                    int id_b_int = Integer.parseInt(id_bottle);
                    if(id_b_int==value_int){
                        good_degust = true;
                        break;
                    }
                }
                if(good_degust) {
                    degustList.add(i, custom_degust);
                    i++;
                }
            }
            List_adapter_degust adapter = new List_adapter_degust(this, R.layout.degustlist, degustList);
            lv = (ListView) findViewById(R.id.listdegust_c);
            lv.setAdapter(adapter);
        }

    }

    public void openDegust(View v){
        Intent myIntent = new Intent(DegustCustomActivity.this, MakeDegustActivity.class);
        DegustCustomActivity.this.startActivity(myIntent);
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
            startActivityForResult(new Intent(this, DegustCustomActivity.class),
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


