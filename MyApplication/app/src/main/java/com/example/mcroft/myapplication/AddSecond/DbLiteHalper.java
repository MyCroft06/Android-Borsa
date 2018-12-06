package com.example.mcroft.myapplication.AddSecond;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbLiteHalper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "sqllite_database";//database adı

    private static final String TABLE_NAME = "kitap_listesi";
    private static String MONEY = "mny";
    private static String WALLET_ID = "id";
    private static String TYPE = "typ";

    public DbLiteHalper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + WALLET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MONEY + " TEXT,"
                + TYPE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public void walletEkle(String mny, String typ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONEY, mny);
        values.put(TYPE, typ);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    /*public void walletSil(int id){ //id si belli olan row u silmek için
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, WALLET_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }*/

    public ArrayList<Add> wallets(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<Add> kitaplist = new ArrayList<Add>();
        while (cursor.moveToNext())
            kitaplist.add(new Add(cursor.getString(1),cursor.getString(2)));

        cursor.close();
        db.close();
        return kitaplist;
    }

    /*public void walletDuzenle(String mny, String typ, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(MONEY, mny);
        values.put(TYPE, typ);

        // updating row
        db.update(TABLE_NAME, values, WALLET_ID + " = ?",
                new String[] { String.valueOf(id) });
    }*/

    /*public int getRowCount() {
        // Bu method bu uygulamada kullanılmıyor ama her zaman lazım olabilir.Tablodaki row sayısını geri döner.
        //Login uygulamasında kullanacağız
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }*/

    /*public void resetTables(){
        //Bunuda uygulamada kullanmıyoruz. Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}