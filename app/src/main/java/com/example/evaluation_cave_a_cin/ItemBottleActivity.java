package com.example.evaluation_cave_a_cin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemBottleActivity extends AppCompatActivity {
    MAbaseOpenHelper bdd;
    String table = "Bouteille_table";// nom de la table dans la bdd
    Degust degust;
    private ListView lv;
    ArrayList<Degust> degustList;
    Degust custom_degust;
    Degust item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bottle);
        TextView expl = findViewById(R.id.item_exp);
        TextView ap = findViewById(R.id.item_ap);
        TextView ty = findViewById(R.id.item_ty);
        TextView mil = findViewById(R.id.item_mil);
        TextView date = findViewById(R.id.item_da);
        TextView stock = findViewById(R.id.item_st);
        TextView price = findViewById(R.id.item_pr);

        expl.setText("Exploitation:  "+getIntent().getStringExtra("appellation"));
        ap.setText("Appellation:  "+getIntent().getStringExtra("exploitation"));
        ty.setText("Type:  "+getIntent().getStringExtra("type"));
        mil.setText("Millésime:  "+getIntent().getStringExtra("millesime"));
        date.setText("Date d'achat:  "+getIntent().getStringExtra("date"));
        price.setText("Prix unitaire:  "+getIntent().getStringExtra("price"));

        int out = Integer.parseInt(getIntent().getStringExtra("stock_out"));
        int in = Integer.parseInt(getIntent().getStringExtra("stock_in"));
        int stock_dispo_int = in - out;
        String stock_dispo = String.valueOf(stock_dispo_int);
        stock.setText(stock_dispo);

        //moyenne des dégustation
        bdd = new MAbaseOpenHelper(this);

        Cursor data = bdd.getAllDegust();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(ItemBottleActivity.this, "Pas de dégustation: moyenne des notes impossible", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            double sum = 0.0;
            while (data.moveToNext()) {
                degust = new Degust(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4));
                double d = Double.parseDouble(degust.getNote());

                sum += d;
                i++;
            }
            double moy = sum/i;
            String moy_string = String.valueOf(moy);
            TextView tv_note = findViewById(R.id.tv_note);
            tv_note.setText(moy_string);
        }

    }
    public void dbStock(int current_stock_int, int add_stock_int, String col){
        // db opreation
        int c_stock = current_stock_int + add_stock_int;
        String c_stock_string = String.valueOf(c_stock);
        String id = "_id="+getIntent().getStringExtra("id");
        bdd = new MAbaseOpenHelper(this);
        SQLiteDatabase db =  bdd.openHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(col, c_stock_string );
        db.update(table, newValues, id, null);
    }

    public void addStock(View v){
        EditText add = findViewById(R.id.et_number_add);
        TextView stock = findViewById(R.id.item_st);
        String current_stock = stock.getText().toString();
        String add_string = add.getText().toString();
        int current_stock_int = Integer.parseInt(current_stock);
        int add_stock_int = Integer.parseInt(add_string);
        int result = current_stock_int + add_stock_int;
        String result_string = String.valueOf(result);
        stock.setText(result_string);
        dbStock(current_stock_int,add_stock_int, "stock_in");

    }

    public void minusStock(View v){
        EditText minus = findViewById(R.id.et_minus);
        TextView stock = findViewById(R.id.item_st);
        String current_stock = stock.getText().toString();
        String minus_string = minus.getText().toString();
        int current_stock_int = Integer.parseInt(current_stock);
        int minus_stock_int = Integer.parseInt(minus_string);
        int result = current_stock_int - minus_stock_int;
        String result_string = String.valueOf(result);
        stock.setText(result_string);
        dbStock(current_stock_int,minus_stock_int, "stock_out");

    }
    public void newDegust(View v){
        Toast.makeText(ItemBottleActivity.this, "Stock ajuster: -1.", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(ItemBottleActivity.this, MakeDegustActivity.class);
        TextView expl = findViewById(R.id.item_exp);
        String expl_to_degust = expl.getText().toString();
        myIntent.putExtra("exploitation", expl_to_degust);
        ItemBottleActivity.this.startActivity(myIntent);
    }

    public void openCustomDegustList(View v){
        String id_c = getIntent().getStringExtra("id");
        Intent myIntent = new Intent(ItemBottleActivity.this, DegustCustomActivity.class);
        myIntent.putExtra("id", id_c); //Optional parameters
        ItemBottleActivity.this.startActivity(myIntent);
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
