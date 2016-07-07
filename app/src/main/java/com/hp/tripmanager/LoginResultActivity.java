package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginResultActivity extends MainActivity {
  TextView tv;
    Thread t;
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
        s1=s1.trim();
        s2=s2.trim();
        SQLiteDatabase db=openOrCreateDatabase("TripExpense", MODE_APPEND,null);   //if file doesnt exist the open new else just open
        int flag=0;
        String q="select * from login";
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

        }
        if(flag==0){
            Toast.makeText(LoginResultActivity.this,"User Not in Database or incorrect password /username ",Toast.LENGTH_LONG).show();
            Intent j=new Intent(this,LoginActivity.class);
            startActivity(j);
        }
        else{
            t=new Thread(){
                public void run(){
                    try{
                        t.sleep(2000);
                        Intent k=new Intent(LoginResultActivity.this,BudgetValueActivity.class);
                        startActivity(k);
                    }catch(Exception e){}

                }
            };

        }

        t.start();


    }

}
