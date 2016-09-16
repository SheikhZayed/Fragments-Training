package com.techjini.drawerbasics.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by almukthar on 15/9/16.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "USER";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + "user" + " TEXT," + "password" + " TEXT" + ")";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUsers(String mUsername, String mPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user",mUsername);
        values.put("password",mPassword);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
public String checkIfUserExists(String username){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor=db.query(TABLE_NAME, null, " user=?", new String[]{username}, null, null, null);
    if(cursor.getCount()<1)
    {
        cursor.close();
        return "null";
    }
    cursor.moveToFirst();
    String password= cursor.getString(cursor.getColumnIndex("password"));
    cursor.close();
    return password;
}

}
