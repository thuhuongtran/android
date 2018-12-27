package com.vtcac.thuhuong.lab9.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "lab9DB.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "create table Product (\n" +
                "id integer primary key autoincrement,\n" +
                "name text,\n" +
                "type text,\n" +
                "quantity integer,\n" +
                "price text\n" +
                ");";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("drop table if exists Product");
            onCreate(db);
        }
    }
    public void insertProduct(String name, String type, int quantity, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("type", type);
        values.put("quantity", quantity);
        values.put("price", price);
        db.insert("Product", null, values);
    }
    public void updateProduct(int id, String name, String type, int quantity, String price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("type", type);
        values.put("quantity", quantity);
        values.put("price", price);
        db.update("Product",values, "id = ?", new String[]{String.valueOf(id)});
    }
    public void deleteProduct(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Product", "id=?", new String[]{String.valueOf(id)});
    }
}
