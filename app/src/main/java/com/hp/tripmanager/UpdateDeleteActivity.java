package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class UpdateDeleteActivity extends MainActivity {
  RadioGroup rg;
    Intent j;
    Button b;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_delete);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_update_delete,null,false);
        drawer.addView(contentView,0);
        rg=(RadioGroup)findViewById(R.id.radioGroup1);
        b= (Button) findViewById(R.id.button1);
        Intent i=getIntent();
         ID=i.getStringExtra("ID");
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId){
                    case R.id.radioButton1 : {
                         j=new Intent(UpdateDeleteActivity.this,Update.class);
                        j.putExtra("id",ID);
                       // startActivity(i);

                    }
                        break;
                    case R.id.radioButton2 :{
                         j=new Intent(UpdateDeleteActivity.this,Delete.class);
                        j.putExtra("id",ID);
                       // startActivity(i);
                    }

                }//end of switch



            }
        });//statement terminated

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(j);
            }
        });

    }
}
