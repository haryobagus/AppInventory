package com.example.appinventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    /* deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel, nama-nama kolom, nama database, dan versi dari database */
    public static final String TABLE_NAME = "data_inventori";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama_barang";
    public static final String COLUMN_MERK = "merk_barang";
    public static final String COLUMN_HARGA = "harga_barang";
    private static final String db_name = "inventori.db";
    public static final int db_version = 1;

    // Perintah SQL untuk membuat tabel database baru
    public static final String db_create = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " VARCHAR(50) NOT NULL, "
            + COLUMN_MERK + " VARCHAR(50) NOT NULL, "
            + COLUMN_HARGA + " VARCHAR(50) NOT NULL); ";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    // mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

