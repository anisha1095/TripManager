package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TripActivity extends MainActivity {
  EditText et1,et2,et3,et4,et5,et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trip);
       // super.onCreateDrawer();
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_trip,null,false);
        drawer.addView(contentView,0);
        Intent i=getIntent();

        et1=(EditText)findViewById(R.id.editText2);//destination
        et2=(EditText)findViewById(R.id.editText3);//startdate
        et3=(EditText)findViewById(R.id.editText4);//enddate
        et4=(EditText)findViewById(R.id.editText5);//budget
        et5=(EditText)findViewById(R.id.editText6);//from
        et6=(EditText)findViewById(R.id.editText7);//tripid

    }//end of onCreate()

    public void new_trip(View v){

        SQLiteDatabase d=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        String s="create table if not exists trip1(TripID varchar primary key,Destination varchar,Source varchar,Startdate varchar,Enddate varchar," +
                "Budget varchar,Remaining varchar)";
        d.execSQL(s);

        SharedPreferences sp=getSharedPreferences("Trip",0);
        String p=sp.getString("STATUS","Not Initialized");

        if(p.equals("Not Initialized")){
            d.execSQL("insert into trip1 values('trip1','new york','delhi','12/03/2003','12/23/2003','40000','5000')");
            SharedPreferences.Editor editor= sp.edit();
            editor.putString("STATUS","Initialized");
            editor.commit();
            Toast.makeText(TripActivity.this,"record inserted and commited",Toast.LENGTH_LONG).show();

        }
        else {
           // SharedPreferences sp = getSharedPreferences("Trip", 0);
            String id = sp.getString("ID", "new");
            String n = "insert into trip1 values('" + et6.getText().toString() + "','" + et1.getText().toString() + "','" + et5.getText().toString() + "','" + et2.getText().toString() + "','" + et3.getText().toString() + "','" + et4.getText().toString() + "','" + et4.getText().toString() + "')";
            d.execSQL(n);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("ID", et6.getText().toString());
            editor.commit();
            Toast.makeText(TripActivity.this, "Added new trip", Toast.LENGTH_LONG).show();
            d.close();
        }
    }

}
