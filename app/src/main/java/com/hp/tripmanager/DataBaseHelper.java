package com.hp.tripmanager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }
    //called when no database exists in the disk and helper class creates a new one

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
    }
    //called when there is a database version mismatch

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the old table and create a new one
        db.execSQL("DROP TABLE IF EXISTS"+"TEMPLATE");
        onCreate(db);
    }
} //end of DataBaseHelper class
