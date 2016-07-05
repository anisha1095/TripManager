package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        s=i.getIntExtra("TID",100);
        final int[] pos = new int[1];
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
                pos[0] =position;
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
                int y;
               // show("hello");
                SQLiteDatabase db = openOrCreateDatabase("Expense", MODE_APPEND, null);
               // show("hello2");
                db.execSQL(" Create table if not exists Expense (ExpenseID integer primary key autoincrement,Category varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");
               // show("hello1");

                //  String id=db.rawQuery("select  from ")
                SharedPreferences sp=getSharedPreferences("ExpenseDb",0);
                y=sp.getInt("EID",100);
                db.execSQL("insert into Expense values('EID"+y+ ",'"+category[pos[0]]+"','"+ et1.getText().toString() + "','" +
                        et2.getText().toString() + "','TID"+ s + "')");
                show("Trip ID:TID" + s + "Expense ID:EID" + y);
                show(category[pos[0]]);
                y++;
                SharedPreferences.Editor editor =sp.edit();
                editor.putInt("EID",y);
                editor.commit();

                // db.execSQL("Drop table expense");
            }
        });
        
    }


}// end of public expense