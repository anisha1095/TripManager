package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
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
        tl = (TableLayout) findViewById(R.id.tableLayout);
/*
        Intent intent = getIntent();
        SQLiteDatabase db = openOrCreateDatabase("Expenses", MODE_APPEND, null);//TripExpense
        String q = "SELECT ExpenseID,destination,Category,Amount FROM Expenses order by Category";
        Cursor c = db.rawQuery(q, null);
        String ExpenseID, To, Category, Amount;
        TableRow tr;
        while (c.moveToNext()) {
            ExpenseID = c.getString(0);
            To = c.getString(1);
            Category = c.getString(2);
            Amount = c.getString(3);

            tr = new TableRow(this);
            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);
            tv1.setText("ExpenseID=" + ExpenseID + "    ");
           // tv2.setText("To=" + destination + "     ");
            tv3.setText("Category=" + Category + "    ");
            tv4.setText("Amount=" + Amount + "    ");
            tr.addView(tv1);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            tl.addView(tr);
        } //end of while
        db.close();
        setResult(999, intent);*/
        Toast.makeText(this,"category working",Toast.LENGTH_LONG).show();
    }//oncreate
}//catwise