package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpenseDelete extends MainActivity {

    EditText et1;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_expense_delete);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_expense_delete,null,false);
        drawer.addView(contentView,0);
        et1 = (EditText) findViewById(R.id.editText1);
        bt1 = (Button) findViewById(R.id.button1);
    }
    public void delete(View v)
    {
        String str=et1.getText().toString();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense",MODE_APPEND,null);
        String str1="delete from Expenses where ExpenseID='"+str+"'";
        db.execSQL(str1);
        Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
        Intent i= new Intent(this,StartScreen.class);


    }
}
