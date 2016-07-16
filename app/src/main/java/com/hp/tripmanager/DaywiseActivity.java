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

public class DaywiseActivity extends MainActivity
{
    TableLayout tl;
    TextView tv1,tv2,tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_daywise);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_daywise,null,false);
        drawer.addView(contentView,0);
        tv1=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);

        tl=(TableLayout)findViewById(R.id.tableLayout1);

        Intent intent=getIntent();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense", MODE_APPEND, null);//TripExpense
        String q="SELECT * FROM Expenses group by TripID,Expense_Date";
        Cursor c=db.rawQuery(q,null);
        String ExpenseID,Date,To,Amount;
        TableRow tr;
        while(c.moveToNext())
        {


            ExpenseID = c.getString(0);
            Date = c.getString(4);
            Amount=c.getString(3);

            tr = new TableRow(this);
            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3=new TextView(this);
            tv1.setText(ExpenseID+"    ");
            tv2.setText(Date+"    ");
            //  tv3.setText("To="+destination+"");
            tv3.setText(Amount+"    ");
            tr.addView(tv1);
            tr.addView(tv2);
            tr.addView(tv3);
            //tr.addView(tv4);
            tl.addView(tr);
        } //end of while
        db.close();
        //setResult(999,intent);
       // Toast.makeText(this,"Daywise working",Toast.LENGTH_LONG).show();
    }//oncreate
}//public Daywise
