package com.example.evaluation_cave_a_vin;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class MAbaseOpenHelper {
    public SQLiteOpenHelper openHelper;
    // private MaBase maBase;

    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "cave.db";
    private static final String TAG = MainActivity.class.getName();

    public MAbaseOpenHelper(Context context) {
        openHelper = new MaBase(context );
    }

    public boolean verifBouteille(String exploitation, String appellation, String type, int millesime, int stock_in, int stock_out, Date purchase_date,double price) {
        db = openHelper.getReadableDatabase();
        boolean isPresent = false;
        String table = "Bouteille_table";// nom de la table dans la bdd
        String[] columns = { "exploitation", "appellation", "type","millesime","stock_in","stock_out","purchase_date","price"};// nom des colonnes
        String selection = String.format("%s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ? ",  "exploitation", "appellation", "type","millesime","stock_in","stock_out","purchase_date","price");//prepared stat.
        String[] selectionArgs = { exploitation, appellation, type, String.valueOf(millesime), String.valueOf(stock_in), String.valueOf(stock_out), String.valueOf(purchase_date), String.valueOf(price)};
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor c= null;
        Log.d(TAG , selection);
        try {
            c = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        } catch (SQLException se){
            Log.e(TAG,"erreur lors de verif bouteille" );
        }
        if (c.getCount() == 1) isPresent = true;
        Log.e(TAG,"-------> BASE OK !!!" );

        return isPresent;
    }

    public Cursor getAllBottle(){
        db = openHelper.getReadableDatabase();
        String query = "SELECT * FROM Bouteille_table;";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
    public Cursor getAllDegust(){
        db = openHelper.getReadableDatabase();
        String query = "SELECT * FROM Degustation_table;";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
   /* void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    */

}

