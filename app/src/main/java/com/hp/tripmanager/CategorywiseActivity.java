package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CategorywiseActivity extends MainActivity {
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_categorywise);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_categorywise,null,false);
        drawer.addView(contentView,0);
        tl = (TableLayout) findViewById(R.id.tableLayout1);

        Intent intent = getIntent();
        SQLiteDatabase db = openOrCreateDatabase("TripExpense", MODE_APPEND, null);//TripExpense
        db.execSQL(" Create table if not exists Expenses(ExpenseID integer primary key autoincrement,Category varchar,Particular varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");

        String q = "SELECT * FROM Expenses group by TripID, Category";
        Cursor c = db.rawQuery(q, null);
        String ExpenseID, To, Category, Amount,Particular;
        TableRow tr;
        String checkTripid;
        while (c.moveToNext()) {
            //checkTripid=c.getString();
            ExpenseID = c.getString(0);
            Category = c.getString(1);
            Particular = c.getString(2);
            Amount = c.getString(3);

            tr = new TableRow(this);
            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);
            tv1.setText( ExpenseID + "    ");
            // tv2.setText("To=" + destination + "     ");
            tv3.setText(Category + "    ");
            tv4.setText( Amount + "    ");
            tr.addView(tv1);
           // tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            tl.addView(tr);
        } //end of while
        db.close();
       // setResult(999, intent);
        Toast.makeText(this,"category working",Toast.LENGTH_LONG).show();
    }//oncreate
}//catwise