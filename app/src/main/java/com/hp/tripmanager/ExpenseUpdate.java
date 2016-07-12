package com.hp.tripmanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

public class ExpenseUpdate extends MainActivity
{  //TextView tv2;
    Button bt1;
    EditText et1,et2;
    Spinner s1;
    int pos;
    String options[]={"None ","Particulars","Amount","Date of Expense"};
    void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_expense_update);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_expense_update,null,false);
        drawer.addView(contentView,0);
        Intent intent1=getIntent();
        // tv2=(TextView)findViewById(R.id.textView2);
        et1=(EditText)findViewById(R.id.editText1);//id
        et2=(EditText)findViewById(R.id.editText2);//update
        bt1=(Button)findViewById(R.id.button1);
        s1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter adapter= new ArrayAdapter(ExpenseUpdate.this,android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // showToast("Spinner not selected");

            }
        });// end of statement

        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SQLiteDatabase db = openOrCreateDatabase("TripExpense", MODE_APPEND, null);
                String id=et1.getText().toString();
               if (options[pos].equals("Particulars")) {
                    String s = "Update Expense set Particular='" + et2.getText().toString() + "' where ExpenseID='" + id + "'";
                    db.execSQL(s);
                    Toast.makeText(ExpenseUpdate.this, "Updated", Toast.LENGTH_SHORT).show();

                } else if (options[pos].equals("Amount")) {
                    String s = "Update Expenses set Amount='" + et2.getText().toString() + "' where ExpenseID='" + id + "'";
                    db.execSQL(s);
                    Toast.makeText(ExpenseUpdate.this, "Updated", Toast.LENGTH_SHORT).show();

                } else if (options[pos].equals("Date of Expense")) {
                    String s = "Update Expenses set Expense_Date='" + et2.getText().toString() + "' where ExpenseID='" + id + "'";
                    db.execSQL(s);
                    Toast.makeText(ExpenseUpdate.this, "Updated", Toast.LENGTH_SHORT).show();

                }
                Intent i=new Intent (ExpenseUpdate.this,StartScreen.class);
                startActivity(i);


            }
        });
    }
}



