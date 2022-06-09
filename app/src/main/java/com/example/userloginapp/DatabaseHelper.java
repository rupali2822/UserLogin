package com.example.userloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public  boolean c=true;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "name.db", null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists User(id integer primary key autoincrement,username text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists User");
        onCreate(db);
    }

    public boolean registerUser(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", data.getUsername());
        values.put("password", data.getPassword());
        long user = sqLiteDatabase.insert("User", null, values);
        if (user != -1) {
            Log.e(TAG, "registerUser:User register successfully");
        } else {

            Log.e(TAG, "registerUser:error in registering user");
        }
        return true;
    }

    public boolean loginUser(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user", null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String pass = cursor.getString(2);


                if (name.equals(data.getUsername()) && pass.equals(data.getPassword())) {
                    Log.e(TAG, "loginUser: Login Successfully");
                     c=true;
                }
                else {
                    Log.e(TAG, "loginUser:error in login");
                    c=false;
                }
        }
        return c;
    }

}
