package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

public class ExpenseActivity extends MainActivity {
    TableLayout t1;
    EditText et1, et2;

    Spinner s1;
    int s;
    Button b1;
    String category[] = {"Food and drinks", "Transport", "Health", "Accommodation", "Leisure", "Miscellaneous"};
    int pos;
    public void show(String msg)//toast method
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_expense);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_expense,null,false);
        drawer.addView(contentView,0);

        Intent i=getIntent();

        b1 = (Button) findViewById(R.id.button1);

        et1 = (EditText) findViewById(R.id.editText1);//amount
        et2 = (EditText) findViewById(R.id.editText2);//date
        t1 = (TableLayout) findViewById(R.id.tableLayout1);
        s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()


        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos =position;
                show("You Selected: " + category[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                show("Select A Category");
            }
        });//end of spinner


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("TripDB",0);
                int y=sp.getInt("EID",100);
                s=sp.getInt("TID",100);

                SQLiteDatabase db = openOrCreateDatabase("TripExpense", MODE_APPEND, null);
                db.execSQL(" Create table if not exists Expenses(ExpenseID integer primary key autoincrement,Category varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");
                db.execSQL("insert into Expenses values( null"+ ",'"+category[pos]+"','"+ et1.getText().toString() + "','" +
                        et2.getText().toString() + "','"+ s + "')");
                show("Trip ID:T" + s + "Expense ID:E" + y);
                show(category[pos]);


                }


        });

        
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


}// end of public expense