package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TripActivity extends MainActivity {
  EditText et1,et2,et3,et4,et5,et6;
    Date d1,d2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_trip);
       // super.onCreateDrawer();
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_trip,null,false);
        drawer.addView(contentView,0);
        Intent i=getIntent();

        et1=(EditText)findViewById(R.id.editText1);//destination
        et2=(EditText)findViewById(R.id.editText2);//startdate
        et3=(EditText)findViewById(R.id.editText4);//enddate
        et4=(EditText)findViewById(R.id.editText5);//budget
        et5=(EditText)findViewById(R.id.editText6);//from
        et6=(EditText)findViewById(R.id.editText7);//tripid

    }//end of onCreate()

    public void new_trip(View v) throws ParseException {

        SQLiteDatabase d=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        String s="create table if not exists trip1(TripID varchar primary key,Destination varchar,Source varchar,Startdate varchar,Enddate varchar," +
                "Budget varchar,Remaining varchar)";
        d.execSQL(s);
        if(isDateValid(et2.getText().toString(),"MM/dd/yyyy")&&isDateValid(et3.getText().toString(),"MM/dd/yyyy"))

        {
            try {
                d1 = parseDate(et2.getText().toString(), "MM/dd/yyyy");  //start
            } catch (ParseException e) {

            }
            try {
                d2 = parseDate(et3.getText().toString(), "MM/dd/yyyy"); //end
            } catch (ParseException e) {

            }
            if (d1.after(d2)) {
                Toast.makeText(TripActivity.this, "Start Date after End Date. Enter correct dates.", Toast.LENGTH_SHORT).show();
                return;
            }
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date dateobj = new Date();
            Date da = parseDate(df.format(dateobj), "MM/dd/yyyy"); //current date
            if(da.after(d2)&&d1.before(da)){
                Toast.makeText(TripActivity.this, "Trip End Date should be later than the current date or start date should be after the current date", Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences sp = getSharedPreferences("Trip", 0);
            String p = sp.getString("STATUS", "Not Initialized");

            String id = sp.getString("ID", "trip1");
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("STATUS", "Initialized");
            editor.putString("ID", et6.getText().toString());
            editor.commit();
            String n = "insert into trip1 values('" + et6.getText().toString() + "','" + et1.getText().toString() + "','" + et5.getText().toString() + "','" + et2.getText().toString() + "','" + et3.getText().toString() + "','" + et4.getText().toString() + "','" + et4.getText().toString() + "')";
            d.execSQL(n);

            Toast.makeText(TripActivity.this, "Added new trip", Toast.LENGTH_LONG).show();
            d.close();
            Intent i = new Intent(TripActivity.this, StartScreen.class);
            startActivity(i);
        }

        else{
                Toast.makeText(TripActivity.this, "InValid Date", Toast.LENGTH_SHORT).show();
                return;
            }



    }



    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
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

}
/*
try {
                        thedate = parseDate(c.getString(4), "MM/dd/yyyy");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        da = parseDate(df.format(dateobj), "MM/dd/yyyy");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (da.after(thedate)) {  //current date after trip end date
                        Toast.makeText(BudgetValueActivity.this, "No Ongoing or Upcoming Trips", Toast.LENGTH_SHORT).show();
                        return;
                    }
 */