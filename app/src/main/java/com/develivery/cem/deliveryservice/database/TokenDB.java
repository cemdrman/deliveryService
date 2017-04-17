package com.develivery.cem.deliveryservice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cem on 17.04.2017.
 */
public class TokenDB extends SQLiteOpenHelper {

    private static final int dbVersion = 1;
    private static final String dbName = "token_database";

    public TokenDB(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","creating db");
        db.execSQL("CREATE TABLE TOKEN (id INTEGER PRIMARY KEY AUTOINCREMENT,token TEXT)");
        Log.d("db","created db");
    }

    public void saveToken(String token){
        Log.d("db","saving token");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("token",token);
        db.insert("TOKEN", null, values);
        db.close();
        Log.d("saved-token","token saved");
    }

    public void deleteToken(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TOKEN","id=1",null);
        Log.d("db","deleting token");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
