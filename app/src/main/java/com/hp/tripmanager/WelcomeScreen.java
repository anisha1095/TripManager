package com.hp.tripmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class WelcomeScreen extends AppCompatActivity {
   Thread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        t= new Thread() {
           public void run() {
                try{
                    Thread.sleep(2000);
                }catch(Exception e){}
                SharedPreferences sp=getSharedPreferences("Trip1",0);
                String s= sp.getString("STATUS","NOT INITIALIZED");

                if (s.equals("NOT INITIALIZED"))

                {  SharedPreferences.Editor editor=sp.edit();
                    editor.putString("STATUS","INITIALIZED");
                    editor.commit();
                    Intent i=new Intent(WelcomeScreen.this,SignupActivity.class);
                    //Toast.makeText(WelcomeScreen.this, "SignUp", Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }
                else{

                    Intent i= new Intent(WelcomeScreen.this,LoginActivity.class);
                   // Toast.makeText(WelcomeScreen.this, "Login", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        };
        t.start();
    }
}
