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
    String s1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_result);
       // super.onCreateDrawer();

        tv=(TextView)findViewById(R.id.textView2);

        Intent i=getIntent();
        s1=i.getStringExtra("USER");

            t=new Thread(){
                public void run(){
                    try{
                        tv.append("Welcome " + s1 + "!!!");
                        t.sleep(2000);
                        Intent k=new Intent(LoginResultActivity.this,StartScreen.class);
                        startActivity(k);
                    }catch(Exception e){}

                }
            };
        t.start();


    }

}
