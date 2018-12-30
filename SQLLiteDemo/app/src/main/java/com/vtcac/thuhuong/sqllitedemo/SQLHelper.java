package com.vtcac.thuhuong.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "SQLLiteDemo.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "create table Product (\n" +
                "id integer primary key autoincrement,\n" +
                "name text,\n" +
                "price integer\n" +
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

    public void insertProduct(String name, int price) {
        //way 1
        /*String queryInsert = "insert into Product (name,price) values ('Coca',24);";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(queryInsert);*/
        SQLiteDatabase db = getWritableDatabase();

        //way 2

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        db.insert("Product", null, values);
    }

    public void updateProduct(int id, String newName, int newPrice) {
        // way 1
       /* String queryUpdate = "update Product set name = '" +
                newName+"', price="+newPrice+" where id = "+id+"";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(queryUpdate);*/

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("price", newPrice);
        db.update("Product", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteProduct(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Product", "price < ?", new String[]{"25"});
    }

    public void getAll() {
//        String querySelect = "get * from Product";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(false, "Product", null, null,
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));

            Log.d("sqlite", "SQLHelpergetAll: ID " + id + ", Name " + name + " , price " + price);
        }
    }
    public void getProductByPrice(int priceQuery, String nameQuery){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(false, "Product", null, "price > ? and name = ?",
                new String[]{String.valueOf(priceQuery), nameQuery}, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));

            Log.d("sqlite", "SQLHelpergetAll: ID " + id + ", Name " + name + " , price " + price);
        }
    }

}
