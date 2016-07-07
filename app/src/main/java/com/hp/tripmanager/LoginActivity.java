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

public class LoginActivity extends AppCompatActivity{
    EditText e1,e2;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // super.onCreateDrawer();
        //LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View contentView=inflater.inflate(R.layout.activity_login,null,false);
        //drawer.addView(contentView,0);
        //add this to call the Navigation menu.
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);

    }
    public void login(View v){
        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        if(s1.equals("")||s2.equals(""))
        {
            Toast.makeText(LoginActivity.this,"Either username or password not entered",Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(LoginActivity.this, "working", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, LoginResultActivity.class);
            SQLiteDatabase db=openOrCreateDatabase("TripExpense", MODE_APPEND,null);
            String q="create table if not exists login(username varchar,password varchar,email varchar,phone varchar,dob date)";
            db.execSQL(q);
            intent.putExtra("USER", s1);
            intent.putExtra("PASS", s2);
            startActivity(intent);
        }
    }
    public void signup(View v){
        Intent i=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(i);
        Toast.makeText(LoginActivity.this,"signup",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
