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

public class DegustActivity extends AppCompatActivity {

    private String TAG = DegustActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<Degust> degustList;
    Degust degust;
    Degust item;
    MAbaseOpenHelper bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degust);

        bdd = new MAbaseOpenHelper(this);
        lv = (ListView) findViewById(R.id.listdegust);
        viewDegust();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor data = bdd.getAllDegust();
                int numRows = data.getCount();
                System.out.println(numRows);

                if (numRows == 0) {
                    Toast.makeText(DegustActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
                } else {
                    data.moveToPosition(position);
                    item = new Degust(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4));

                    String exploitation = item.getExploitation();
                    String date_d = item.getDate_degust();
                    String note = item.getNote();
                    String comment = item.getComment();

                    Intent intent = new Intent(DegustActivity.this, ItemDegustActivity.class);

                    intent.putExtra("exploitation", exploitation);
                    intent.putExtra("date_degust", date_d);
                    intent.putExtra("note", note);
                    intent.putExtra("comment", comment);
                    startActivity(intent);
                }
            }
        });
    }

    public void viewDegust() {
        degustList = new ArrayList<>();
        Cursor data = bdd.getAllDegust();

        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(DegustActivity.this, "The Database is empty  :(.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                System.out.println(data.getString(1) + " " + data.getString(2) + " " + data.getString(3)+ " " + data.getString(4));
                degust = new Degust(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                degustList.add(i, degust);
                i++;
            }
            List_adapter_degust adapter = new List_adapter_degust(this, R.layout.degustlist, degustList);
            lv = (ListView) findViewById(R.id.listdegust);
            lv.setAdapter(adapter);
        }

    }

    public void openDegust(View v){
        Intent myIntent = new Intent(DegustActivity.this, MakeDegustActivity.class);
        DegustActivity.this.startActivity(myIntent);
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


