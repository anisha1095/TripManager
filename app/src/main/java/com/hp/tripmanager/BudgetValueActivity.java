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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BudgetValueActivity extends MainActivity {
   TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    Date thedate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String destination = "", budget = "", from = "", edate = "", sdate = "", rembudget = "",id="";

        //setContentView(R.layout.activity_budget_value);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_budget_value, null, false);
        drawer.addView(contentView, 0);

        Intent i = getIntent();

        tv1 = (TextView) findViewById(R.id.textView1); //id
        tv2 = (TextView) findViewById(R.id.textView2); //departure
        tv3 = (TextView) findViewById(R.id.textView3); //from
        tv4 = (TextView) findViewById(R.id.textView4);  //startdate
        tv5 = (TextView) findViewById(R.id.textView5);  //end date
        tv6 = (TextView) findViewById(R.id.textView6);  //budget
        tv7 = (TextView) findViewById(R.id.textView7); //rembudget
        int flag=0;
        SharedPreferences sp=getSharedPreferences("Trip",0);
        String s=sp.getString("STATUS","Not Initialized");
        if(s.equals("Not Initialized")){
            Toast.makeText(this,"no record inserted",Toast.LENGTH_LONG).show();

        }
        else{
            SQLiteDatabase d=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
            String q="select * from trip1";
            String id1=sp.getString("ID","trip1");
            Cursor c= d.rawQuery(q,null);

           // System.out.println(df.format(dateobj));
            while(c.moveToNext()) {
                Date da = new Date();
                if (id1.equals(c.getString(0))) {
                    // if (id1.equals(c.getString(0))) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date dateobj = new Date();
                    try {
                        da = parseDate(df.format(dateobj), "MM/dd/yyyy"); //current date
                    } catch (ParseException e) {
                    }
                    try {
                        if (parseDate(c.getString(4), "MM/dd/yyyy").before(da)) {
                            Toast.makeText(BudgetValueActivity.this, "No Ongoing Trips", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (ParseException e) {
                    }

                    flag = 1;
                    id = c.getString(0);
                    destination = c.getString(1);
                    from = c.getString(2);
                    sdate = c.getString(3);
                    edate = c.getString(4);
                    budget = c.getString(5);
                    rembudget = c.getString(6);
                    /*
                    try {
                         thedate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(edate);
                    } catch (ParseException e) {

                    }
                    // Date eda=(Date)format.parse(edate);*/
                    // if(thedate.after(da)) {
                    tv1.append("    " + id);
                    tv2.append("    " + destination);
                    tv3.append("    " + from);
                    tv4.append("    " + sdate);
                    tv5.append("    " + edate);
                    tv6.append("    " + budget);
                    tv7.append("    " + rembudget);
                    //  }

                }
                else try {
                    if (parseDate(c.getString(4), "MM/dd/yyyy").after(da)){
                        flag = 1;
                        id = c.getString(0);
                        destination = c.getString(1);
                        from = c.getString(2);
                        sdate = c.getString(3);
                        edate = c.getString(4);
                        budget = c.getString(5);
                        rembudget = c.getString(6);
                    /*
                    try {
                         thedate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(edate);
                    } catch (ParseException e) {

                    }
                    // Date eda=(Date)format.parse(edate);*/
                        // if(thedate.after(da)) {
                        tv1.append("    " + id);
                        tv2.append("    " + destination);
                        tv3.append("    " + from);
                        tv4.append("    " + sdate);
                        tv5.append("    " + edate);
                        tv6.append("    " + budget);
                        tv7.append("    " + rembudget);
                    }
                } catch (ParseException e) {

                }
            }

            }
            if(flag==0){
                Toast.makeText(BudgetValueActivity.this, "No ongoing trips", Toast.LENGTH_SHORT).show();
            }
           // Toast.makeText(this,"No records",Toast.LENGTH_LONG).show();


        }
  //  }
    private Date parseDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }
}
