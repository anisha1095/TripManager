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
  EditText et1,et2,et3,et4,et5;
 CoordinatorLayout c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trip);
       // super.onCreateDrawer();
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_trip,null,false);
        drawer.addView(contentView,0);
        Intent i=getIntent();

        et1=(EditText)findViewById(R.id.editText2);
        et2=(EditText)findViewById(R.id.editText3);
        et3=(EditText)findViewById(R.id.editText4);
        et4=(EditText)findViewById(R.id.editText5);
        et5=(EditText)findViewById(R.id.editText6);

    }//end of onCreate()
    public void newtrip(View v){

        int x;
        SQLiteDatabase db=openOrCreateDatabase("Trip",MODE_APPEND,null);
        String q="create table if not exists Trip(tripid varchar,departure varchar ,startdate date,enddate date,budget varchar,remark varchar,rembudget varchar)";
        db.execSQL(q);
        SharedPreferences sp=getSharedPreferences("TripDB",0);
       // String msg=sp.getString("STATUS","Not Initialized");
        x=sp.getInt("TID",100);

        db.execSQL("insert into trip values('TIP"+x+"','"+et1.getText().toString()+"','"+et2.getText().toString()+"','"
                    +et3.getText().toString()+"','"+et4.getText().toString()+"','"+et5.getText().toString()+"','"+et4.getText().toString()+"')");
        Snackbar.make(findViewById(android.R.id.content),"TripID alloted:"+x, Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();
        //Snackbar.make(c, "TripID alloted:"+x, Snackbar.LENGTH_LONG).show();
       // Toast.makeText(TripActivity.this,"TripID alloted:"+x,Toast.LENGTH_LONG).show();
        x++;
        SharedPreferences.Editor editor =sp.edit();
        editor.putInt("TID",x);
        editor.commit();

        Toast.makeText(TripActivity.this,"New Trip Created",Toast.LENGTH_LONG).show();
        Intent i=new Intent(TripActivity.this,ExpenseActivity.class);
        i.putExtra("ID",x);
        startActivity(i);
        finish();

    }
}
