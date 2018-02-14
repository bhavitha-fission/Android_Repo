package com.example.bhavithagottimukkula.login_java.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "User";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD= "password";


    public DataBaseHelper(Context context) {
        super(context, USER_TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE "+USER_TABLE_NAME + "("+USER_COLUMN_NAME+" TEXT PRIMARY KEY, "+USER_COLUMN_EMAIL+" TEXT, "+ USER_COLUMN_PASSWORD+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);

        onCreate(db);
    }

    public  boolean insertDataIntoDataBase(String name,String email, String  password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME,name);
        contentValues.put(USER_COLUMN_EMAIL,email);
        contentValues.put(USER_COLUMN_PASSWORD,password);
        long error = db.insert(USER_TABLE_NAME,null, contentValues);

        if(error == -1) {
            return false;
        }
        else {
            return  true;
        }
    }
}
