package com.hp.tripmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //Builder is a static inner class of AlertDialog , builder is a helping class
        builder.setTitle("About the App");
        builder.setMessage("Created by :\n Anisha Jauhari\n Anvi Johari\n Shivangi Prasad \n Yash Khandelwal\n Copyright2016");
        builder.setIcon(R.mipmap.trip_icon);
        //adding buttons
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
               Intent i=new Intent(AboutActivity.this,StartScreen.class);
                startActivity(i);
                finish();

            }
        });//end of statement

        AlertDialog dialog=builder.create();    //now create the outer object which will create the graphics of the dialog box.
        dialog.show();

    }
}
