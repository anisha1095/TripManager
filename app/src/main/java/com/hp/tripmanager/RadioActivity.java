package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends MainActivity
{
    RadioGroup rg;
    Button bt;
    int i;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_radio);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView=inflater.inflate(R.layout.activity_radio,null,false);
        drawer.addView(contentView,0);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        bt = (Button) findViewById(R.id.button);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1:
                        Toast.makeText(RadioActivity.this, "Confirm to see Trip wise Expense Details", Toast.LENGTH_SHORT).show();
                        i = 1;

                        break;
                    case R.id.radioButton2:
                        Toast.makeText(RadioActivity.this, "Confirm to see Day wise Expense Details", Toast.LENGTH_SHORT).show();
                        i = 2;


                        break;
                    case R.id.radioButton3:
                        Toast.makeText(RadioActivity.this, "Confirm to see Category wise Expense Details", Toast.LENGTH_SHORT).show();

                        i = 3;


                        break;
                }//switch close

            }//onChecked
        });//setOnChecked


        // bt.setOnClickListener(new View.OnClickListener()
        // {
        //  @Override
    }

    public void click(View v)
    {
        // Toast.makeText(ReportActivity.this, ""+i, Toast.LENGTH_SHORT).show();

        switch (i)
        {
            case 1:
               Intent intent= new Intent(RadioActivity.this,TripwiseActivity.class);
                startActivity(intent);
                break;

            case 2:
                Intent intent2= new Intent(RadioActivity.this,DaywiseActivity.class);
                startActivity(intent2);
                break;

            case 3:
                Intent intent3= new Intent(RadioActivity.this,CategorywiseActivity.class);
                startActivity(intent3);
                break;

        }


    }
}

//on create end

