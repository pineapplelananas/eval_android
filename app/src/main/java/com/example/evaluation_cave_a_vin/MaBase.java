package com.example.evaluation_cave_a_vin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaBase extends SQLiteOpenHelper {
    private static final String TAG = MainActivity.class.getName();
    // numéro de version
    public static final int VERSION = 1;
    // nom de la base de données
    public static final String DATABASE = "cave.db";
    // nom table
    public static final String TABLE_NAME_BOUTEILLE = "Bouteille_table";
    public static final String TABLE_NAME_DEGUSTATION = "Degustation_table";

    // creation des champs les table
    public static final String col_1 = "_id",col_2 = "exploitation",col_3 = "appellation",col_4 = "type",col_5 = "millesime",col_6 = "stock_in",col_7 = "stock_out",col_8 = "purchase_date",col_9 = "price";
    public static final String d_col_1 = "_id",d_col_3 = "date_d",d_col_4 = "note",d_col_5 = "comment",d_col_6="bouteille";

    private static final String CREATE_BOUTEILLE = String.format("create table %s" +
                    " (%s integer primary key autoincrement, %s text, %s text, %s text, %s integer, %s integer, %s integer, %s text, %s real);"
            ,TABLE_NAME_BOUTEILLE,col_1,col_2,col_3,col_4,col_5,col_6,col_7,col_8,col_9);

    private static final String POPULATE_PREFIXE = String.format("INSERT INTO %s" +
                    " (%s , %s , %s, %s, %s, %s, %s, %s) VALUES\n"
            ,TABLE_NAME_BOUTEILLE,col_2,col_3,col_4,col_5,col_6,col_7,col_8,col_9);

    private static final String CREATE_DEGUSTATION = String.format("create table %s" +
                    " (%s integer primary key autoincrement, %s text, %s real, %s text, %s text);"
            ,TABLE_NAME_DEGUSTATION,d_col_1,d_col_3,d_col_4,d_col_5,d_col_6);

    private static final String POPULATE_PREFIXE_DEGUSTATION = String.format("INSERT INTO %s" +
                    " (%s , %s, %s, %s) VALUES\n"
            ,TABLE_NAME_DEGUSTATION,d_col_3,d_col_4,d_col_5,d_col_6);


    private static final String POPULATE_SUFFIXE = "('Château Saint-Go','Saint-Mont','rouge','2011',8,6,'24/03/2013',9.6);";

    private static final String POPULATE = POPULATE_PREFIXE + POPULATE_SUFFIXE;

    private static final String POPULATE_SUFFIXE_DEGUSTATION = "('15/10/2014',3.5,'vin encore jeune, tanins très puissants, arômes de\n" +
            "fruits rouges','Château Saint-Go'),('25/04/2018'\n" +
            ",3,\n" +
            "'commence à décroître, arômes en baisse, notes de\n" +
            "cuir, à consommer en 2020 au plus tard','Château Saint-Go');";

    private static final String POPULATE_D = POPULATE_PREFIXE_DEGUSTATION + POPULATE_SUFFIXE_DEGUSTATION;


    public MaBase(Context context) {
        super(context, DATABASE, null, VERSION);
    }


    /**
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "création de la base de données");
        Log.d(TAG, CREATE_BOUTEILLE);
        db.execSQL(CREATE_BOUTEILLE);// creation de la table
        db.execSQL(POPULATE); // peuplement de la table
        Log.d(TAG, CREATE_DEGUSTATION);
        db.execSQL(CREATE_DEGUSTATION);// creation de la table
        db.execSQL(POPULATE_D); // peuplement de la table
    }

    /**
     * @param db
    The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG , "upgrade de la base de données");
    }
}