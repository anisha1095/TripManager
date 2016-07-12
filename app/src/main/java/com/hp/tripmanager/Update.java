package com.hp.tripmanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Update extends MainActivity {
 EditText et1,et2;
    Spinner s1;
    String choice[]={"Destination City","Source City","Start Date","EndDate","Budget"};
    String id;
    Button b;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_update,null,false);
        drawer.addView(contentView,0);
        s1=(Spinner)findViewById(R.id.spinner1);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        et1= (EditText) findViewById(R.id.editText1);//trip id
        et1.setText(id);
        b= (Button) findViewById(R.id.button1);
        et2= (EditText) findViewById(R.id.editText3);//update
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,choice);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        s1.setAdapter(adapter);
        String s;
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                                     {
                                         @Override
                                         public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                         {
                                             pos=position;

                                         }

                                         @Override
                                         public void onNothingSelected(AdapterView<?> parent)
                                         {}
                                     }

        ); //Statement termination

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=openOrCreateDatabase("TripExpense",MODE_APPEND,null );
                if(choice[pos].equals("Destination City"))
                {
                    ContentValues newValues = new ContentValues();
                    newValues.put("Destination", et2.getText().toString());
                    Toast.makeText(Update.this, "hhhh", Toast.LENGTH_SHORT).show();
                    db.update("trip1", newValues, "TripID='"+id+"'", null);
                    db.close();
                    SQLiteDatabase d=openOrCreateDatabase("TripExpense", MODE_APPEND,null);
                    String x="Select * from trip1 where TripId=';"+id+"'";
                    Cursor c=d.rawQuery(x,null);
                    if(c.moveToNext()){
                        String n=c.getString(1);
                        Toast.makeText(Update.this, "destination"+n, Toast.LENGTH_SHORT).show();
                    }

                    // String s="Update trip1 set Destination='"+et2.getText().toString()+"' where TripID='"+id+"'";
                    //db.execSQL(s);
                    //Toast.makeText(Update.this, "Update Database", Toast.LENGTH_SHORT).show();

                }
                else if(choice[pos].equals("Source City"))
                {
                    String s="Update trip1 set Source='"+et2.getText().toString()+"' where TripID='"+id+"'";
                    db.execSQL(s);
                    Toast.makeText(Update.this, "Update Database", Toast.LENGTH_SHORT).show();

                }
                else if(choice[pos].equals("StartDate"))
                {
                    String s="Update trip1 set Startdate='"+et2.getText().toString()+"' where TripID='"+id+"'";
                    db.execSQL(s);
                    Toast.makeText(Update.this, "Update Database", Toast.LENGTH_SHORT).show();

                }
                else if(choice[pos].equals("EndDate"))
                {
                    String s="Update trip1 set Enddate='"+et2.getText().toString()+"' where TripID='"+id+"'";
                    db.execSQL(s);
                    Toast.makeText(Update.this, "Update Database", Toast.LENGTH_SHORT).show();

                }
                else if(choice[pos].equals("Budget"))
                {
                    String s="Update trip1 set Budget='"+et2.getText().toString()+"' where TripID='"+id+"'";
                    db.execSQL(s);
                    Toast.makeText(Update.this, "Update Database", Toast.LENGTH_SHORT).show();

                }

                Intent i=new Intent(Update.this,StartScreen.class);
                startActivity(i);
            }
        });
    }
}
