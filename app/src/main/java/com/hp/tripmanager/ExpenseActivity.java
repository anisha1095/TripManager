package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseActivity extends MainActivity {
    TableLayout t1;
    EditText et1, et2,et3;
    int y=0;
    String x = "0";
    Spinner s1;
    Button b1;
    Date dat;
     int pos;
    String category[] = {"None selected", "Meal", "Travel", "Lodging", " Miscellaneous"};

    public void show(String msg)//toast method
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String str = data.getStringExtra("Meal");
        Toast.makeText(ExpenseActivity.this, str, Toast.LENGTH_SHORT).show();
        et3.setText(str);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_expense);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_expense,null,false);
        drawer.addView(contentView,0);
        b1 = (Button) findViewById(R.id.button1);

        t1 = (TableLayout) findViewById(R.id.tableLayout1);

        et1 = (EditText) findViewById(R.id.editText1);//amount
        et2 = (EditText) findViewById(R.id.editText2);//date
        et3 = (EditText) findViewById(R.id.editText3);//particulars

        s1 = (Spinner) findViewById(R.id.spinner1);
      //  final SQLiteDatabase db = openOrCreateDatabase("TripExpense", MODE_APPEND, null);
        //db.execSQL(" Create table if not exists Expenses(ExpenseID integer primary key autoincrement,Category varchar,Particular varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");

        ArrayAdapter adapter = new ArrayAdapter(ExpenseActivity.this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()


        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                pos=position;
                if (category[position].toString().equalsIgnoreCase("None selected"))
                {
                    if (et3.getText().toString().isEmpty())
                    {
                        Toast.makeText(ExpenseActivity.this, "Please Select A Category", Toast.LENGTH_SHORT).show();

                    } else
                    {
                        et3.setText("");
                    }

                }
                if (category[position].toString().equalsIgnoreCase("Meal"))
                {
                    Intent intent = new Intent(ExpenseActivity.this, MealActivity.class);
                    startActivityForResult(intent, 100);
                    // b1.isClickable();

                }
                if (category[position].toString().equalsIgnoreCase("Travel")) {
                    if (et3.getText().toString().isEmpty()) {
                        Toast.makeText(ExpenseActivity.this, "Mention means of travel", Toast.LENGTH_SHORT).show();
                        // b1.isEnabled();
                    } else {
                        et3.setText("");
                    }
                }

                if (category[position].toString().equalsIgnoreCase("Lodging")) {
                    if (et3.getText().toString().isEmpty()) {
                        Toast.makeText(ExpenseActivity.this, "Mention Hotel Name", Toast.LENGTH_SHORT).show();
                        // b1.isEnabled();
                    } else {
                        et3.setText("");
                    }
                }
                if (category[position].toString().equalsIgnoreCase("Miscellaneous"))
                {

                    if (et3.getText().toString().isEmpty())
                    {
                        Toast.makeText(ExpenseActivity.this, "Mention Required Details", Toast.LENGTH_SHORT).show();
                        // b1.isEnabled();
                    }

                    else
                    {
                        et3.setText("");
                    }
                }
                if (et3.getText().toString().isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please Mention Required Details", Toast.LENGTH_SHORT).show();

                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                show("Select A Category");
            }
        });//end of spinner



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please Mention Required Details", Toast.LENGTH_SHORT).show();


                }/*
                if(isDateValid(et2.getText().toString(),"mm/dd/yyyy")==false){
                    Toast.makeText(ExpenseActivity.this, "Invalid Date", Toast.LENGTH_SHORT).show();
                    return;

                }
                try {
                    dat=parseDate(et2.getText().toString(),"mm/dd/yyyy");
                } catch (ParseException e) {

                }

               // SQLiteDatabase d=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
                Cursor c=db.rawQuery("select * from trip1",null);
                if(c.moveToNext()){
                    String sd=c.getString(2);
                    String ed=c.getString(3);
                    try {
                        if(parseDate(sd,"mm/dd/yyyy").after(dat)||parseDate(ed,"mm/dd/yyyy").before(dat)){
                            Toast.makeText(ExpenseActivity.this, "Date not valid", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {

                    }
                }*/

                else {
                    SQLiteDatabase db = openOrCreateDatabase("TripExpense", MODE_APPEND, null);
                   /* SharedPreferences sp=getSharedPreferences("Trip",0);
                    String y=sp.getString("ID","trip1");
                     x=sp.getString("EID","0");
                    int z=Integer.parseInt(x);
                    z++;
                    x= String.valueOf(z);*/
                    db.execSQL(" Create table if not exists Expenses(ExpenseID integer primary key autoincrement,Category varchar,Particular varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");
                    db.execSQL("insert into Expenses values( null" + ",'" + category[pos] + "','" + et3.getText().toString() + "','" +
                            et1.getText().toString()+"','"  + et2.getText().toString() + "','" + y + "')");
                    //show("Trip ID:T" + y + "Expense ID:E" + x);
                   // SharedPreferences.Editor e=sp.edit();
                   // e.putString("EID",x);
                    //e.commit();

                    //show(category[pos]);
                    Toast.makeText(ExpenseActivity.this, "Recorded", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent (ExpenseActivity.this,StartScreen.class);
                    startActivity(i);

                    // db.execSQL("Drop table expense");
                }
            }
        });


    }//OnCreate

    public boolean isDateValid(String dateString, String pattern)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            if (sdf.format(sdf.parse(dateString)).equals(dateString))
                return true;
        }
        catch (ParseException pe) {}

        return false;
    }

    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
}// end of public expense