package com.example.evaluation_cave_a_cin;

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

public class MakeDegustActivity extends AppCompatActivity {
    MAbaseOpenHelper bdd;
    String table = "Degustation_table";// nom de la table dans la bdd
    Degust item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_degust);

        if (getIntent().getStringExtra("exploitation") != null) {
            EditText et_exp = findViewById(R.id.editText_expl);
            et_exp.setText(getIntent().getStringExtra("exploitation"));
        }

    }

    public void addDegust(View v){
        EditText et_note = findViewById(R.id.editText3);
        EditText et_exploitation = findViewById(R.id.editText_expl);
        EditText et_dat = findViewById(R.id.editText_date_new_degust);
        EditText et_comment = findViewById(R.id.editText_comment);

        String note = et_note.getText().toString();
        String exploitation = et_exploitation.getText().toString();
        String date_degust = et_dat.getText().toString();
        String comment = et_comment.getText().toString();


        bdd = new MAbaseOpenHelper(this);
        SQLiteDatabase db =  bdd.openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("date_d", date_degust);
        values.put("note", note);
        values.put("comment", comment);
        values.put("bouteille", exploitation);

        // Inserting Row
        db.insert(table, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        Toast.makeText(MakeDegustActivity.this, "Dégustation ajoutée !!!", Toast.LENGTH_SHORT).show();

        Cursor data = bdd.getAllDegust();
        data.moveToLast();
        item = new Degust(data.getString(0),data.getString(1), data.getString(2), data.getString(3), data.getString(4));

        String id_b = item.getId();
        String exploitation_i = item.getExploitation();
        String note_i = item.getNote();
        String date_i = item.getDate_degust();
        String comment_i = item.getComment();

        Intent intent = new Intent(MakeDegustActivity.this, ItemDegustActivity.class);

        intent.putExtra("id",id_b);
        intent.putExtra("date_d", date_i);
        intent.putExtra("note", note_i);
        intent.putExtra("comment", comment_i);
        intent.putExtra("bouteille", exploitation_i);
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
