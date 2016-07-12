package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class Delete extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_delete);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_trip,null,false);
        drawer.addView(contentView,0);
        Intent i=getIntent();
        String s=i.getStringExtra("id");
        SQLiteDatabase db=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        String str="delete from trip1 where TripID='"+s+"'";
        db.execSQL(str);

        Toast.makeText(Delete.this, "ID:"+i+" deleted", Toast.LENGTH_SHORT).show();
        Intent j= new Intent(this,ViewAllActivity.class);
        startActivity(j);
    }
}
