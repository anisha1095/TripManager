package com.hp.tripmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity{
    EditText e1,e2;
    Button b1,b2;
    int flag=0;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // super.onCreateDrawer();
        //LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View contentView=inflater.inflate(R.layout.activity_login,null,false);
        //drawer.addView(contentView,0);
        //add this to call the Navigation menu.
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button1);
       // b2=(Button)findViewById(R.id.button2);


    }
    public void login(View v){
        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        s1=s1.trim();
        s2=s2.trim();
        if(s1.equals("")||s2.equals(""))
        {
            Toast.makeText(LoginActivity.this,"Either username or password not entered",Toast.LENGTH_LONG).show();
        }
        else {
            //Toast.makeText(LoginActivity.this, "working", Toast.LENGTH_LONG).show();
          //  Intent intent = new Intent(LoginActivity.this, LoginResultActivity.class);
            SQLiteDatabase db=openOrCreateDatabase("TripExpense", MODE_APPEND,null);
            String q="create table if not exists login(username varchar,password varchar,email varchar,phone varchar,dob date)";
            db.execSQL(q);

            String s="select * from login";
            Cursor c= db.rawQuery(s,null);

            while(c.moveToNext()) {
                user = c.getString(0);
                pass = c.getString(1);
                if (user.equals(s1) && pass.equals(s2)) {
                    flag=1;
                    break;
                }

            }
            if(flag==0){
                Toast.makeText(LoginActivity.this,"User Not in Database or incorrect password /username ",Toast.LENGTH_LONG).show();
                return;
            }
            else{
                Intent i=new Intent(this,LoginResultActivity.class);
                i.putExtra("USER",user);
                startActivity(i);
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

}
