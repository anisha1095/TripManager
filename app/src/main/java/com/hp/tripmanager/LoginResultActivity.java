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

public class LoginResultActivity extends MainActivity {
  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_login_result);
       // super.onCreateDrawer();
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_login_result,null,false);
        drawer.addView(contentView,0);
        tv=(TextView)findViewById(R.id.textView2);

        Intent i=getIntent();
        String s1=i.getStringExtra("USER");
        String s2=i.getStringExtra("PASS");

        SQLiteDatabase db=openOrCreateDatabase("Accounts", MODE_APPEND,null);   //if file doesnt exist the open new else just open
        //db.execSQL("create table if not exists Login(user varchar,password varchar)");

       // SharedPreferences sp=getSharedPreferences("LoginDB_file",0);
        //String msg=sp.getString("STATUS","Not Initialized");
        //if(msg.equals("Not Initialized")) {
            //db.execSQL("insert into Login (user,password) values('anishajauhari','ani1095')");
          //  SharedPreferences.Editor editor =sp.edit();
            //editor.putString("STATUS","Initialized");
            //editor.commit();
        //}
        //else{
        int flag=0;
        String q="select * from LOGIN";
        Cursor c= db.rawQuery(q,null);
        String user,pass;
        while(c.moveToNext()) {
            user = c.getString(0);
            pass = c.getString(1);
            if (user.equals(s1) && pass.equals(s2)) {
                tv.append("Welcome " + user + "!!!");
                flag=1;
                break;
            }
       // }
        }
        if(flag==0){
            Toast.makeText(LoginResultActivity.this,"User Not in Database. please create an account first",Toast.LENGTH_LONG).show();
        }

    }
    /*
    public void newtrip(View v){
        Intent i=new Intent(LoginResultActivity.this,TripActivity.class);
        startActivity(i);
        finish();

    }
    public void newexpense(View v){
     //  Intent i=new Intent(this,ExpenseActivity.class);
       // startActivity(i);
        //finish();
    }
    */
}
