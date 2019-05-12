package com.example.evaluation_cave_a_vin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PurchaseActivity extends AppCompatActivity {
    MAbaseOpenHelper bdd;
    String table = "Bouteille_table";// nom de la table dans la bdd
    Bottle item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Button button_add_bottle = findViewById(R.id.button_add_b);

    }
    public void view_addBottle(View v) {
        EditText et_appellation = findViewById(R.id.et_appelation);
        EditText et_exploitation = findViewById(R.id.et_exploitation);
        EditText et_millesime = findViewById(R.id.et_millesime);
        EditText et_type = findViewById(R.id.et_type);
        EditText et_date = findViewById(R.id.et_date);
        EditText et_stock_in = findViewById(R.id.et_stock_in);
        EditText et_price = findViewById(R.id.et_price);
        Button button_purchase = findViewById(R.id.button_add_b);

        String appellation = et_appellation.getText().toString();
        String exploitation = et_exploitation.getText().toString();
        String millesime = et_millesime.getText().toString();
        String type = et_type.getText().toString();
        String date = et_date.getText().toString();
        String stock_in = et_stock_in.getText().toString();
        String price = et_price.getText().toString();

        bdd = new MAbaseOpenHelper(this);
        SQLiteDatabase db =  bdd.openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("exploitation", exploitation);
        values.put("appellation", appellation);
        values.put("millesime", millesime);
        values.put("type", type);
        values.put("purchase_date", date);
        values.put("stock_in", stock_in);
        values.put("stock_out", 0);
        values.put("price", price);

        // Inserting Row
        db.insert(table, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        Toast.makeText(PurchaseActivity.this, "Bouteille(s)ajoutée(s) !!!", Toast.LENGTH_SHORT).show();

        Cursor data = bdd.getAllBottle();
        data.moveToLast();
        item = new Bottle(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5), data.getString(6), data.getString(7), data.getString(8));

        String id_b = item.getId();
        String exploitation_i = item.getExploitation();
        String appellation_i = item.getAppellation();
        String type_i = item.getType();
        String millesime_i = item.getMillesime();
        String date_i = item.getDate();
        String stock_in_i = item.getStock_in();
        String stock_out_i = item.getStock_out();
        String price_i = item.getPrice();

        Intent intent = new Intent(PurchaseActivity.this, ItemBottleActivity.class);


        intent.putExtra("id",id_b);
        intent.putExtra("exploitation", exploitation_i);
        intent.putExtra("appellation", appellation_i);
        intent.putExtra("type", type_i);
        intent.putExtra("millesime", millesime_i);
        intent.putExtra("date", date_i);
        intent.putExtra("stock_in", stock_in_i);
        intent.putExtra("stock_out", stock_out_i);
        intent.putExtra("price", price_i);
        startActivity(intent);
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
