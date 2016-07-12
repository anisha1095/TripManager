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

public class TripwiseActivity extends MainActivity
{
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_tripwise);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_tripwise,null,false);
        drawer.addView(contentView,0);
        tl=(TableLayout)findViewById(R.id.tableLayout1);

        Intent intent=getIntent();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense", MODE_APPEND, null);//TripExpense
        db.execSQL(" Create table if not exists Expenses(ExpenseID integer primary key autoincrement,Category varchar,Particular varchar,Amount Varchar,Expense_Date varchar,TripID varchar)");

        Toast.makeText(this,"Tripwise working",Toast.LENGTH_LONG).show();
         String q="SELECT * FROM Expenses ";
        Cursor c=db.rawQuery(q,null);
      //  String TripID,To,StartDate,EndDate,ApprovedBudget,AmtSpent,AmtLeft;
        String eid,tid,amount,category,particular,date;
        TableRow tr;
        while(c.moveToNext())
        {  eid=c.getString(0);
            tid=c.getString(5);
            category=c.getString(1);
            particular=c.getString(2);
            amount=c.getString(3);
            date=c.getString(4);

            /*
            TripID = c.getString(0);
            To = c.getString(1);
            StartDate=c.getString(2);
            EndDate=c.getString(3);
            ApprovedBudget=c.getString(4);
            AmtSpent=c.getString(5);
            AmtLeft=c.getString(6);*/

          //  long left=Long.parseLong(ApprovedBudget)-Long.parseLong(AmtSpent); //check

            tr = new TableRow(this);
            TextView tv1 = new TextView(this);
            tv1.setText(eid);//eid

            TextView tv2 = new TextView(this);
            tv2.setText(tid);//id
            TextView tv3=new TextView(this);
            tv3.setText(category);//category
            TextView tv4=new TextView(this);
            tv4.setText(particular);//particular
            TextView tv5=new TextView(this);
            tv5.setText(amount);//amount
           // TextView tv6=new TextView(this);
            //tv6.setText(date);//date

            //tv1.setText("TripID="+tripid+"    ");
            //tv2.setText("To="+destination+"    ");
            //tv3.setText("Start Date="+startdate+"    ");
            //tv4.setText("End Date="+enddate+"    ");
            //tv5.setText("Approved Budget="+budget+"    ");
            //tv6.setText("Amount Spent="+Amount+"    ");
            //tv7.setText("Amount Left="+left+"    ");
            tr.addView(tv1);
            tr.addView(tv2);
            tr.addView(tv3);
            tr.addView(tv4);
            tr.addView(tv5);
           // tr.addView(tv6);

            tl.addView(tr);
        } //end of while
        db.close();
        //  setResult(999,intent);
    }//oncreate
}//public Tripwise
