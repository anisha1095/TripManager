package com.hp.tripmanager;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MealActivity extends AppCompatActivity
{   RadioGroup rg;
    Button b1;
    String str="You have selected:";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        rg=(RadioGroup)findViewById(R.id.radioGroup1);
        b1=(Button)findViewById(R.id.button1);
        Intent intent=getIntent();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                switch(checkedId)
                {
                    case R.id.radioButton1:
                        str="Breakfast";
                        break;
                    case R.id.radioButton2:
                        str="Lunch";
                        break;
                    case R.id.radioButton3:
                        str="Snacks";
                        break;
                    case R.id.radioButton4:
                        str="Dinner";
                        break;
                }


            }
        });// end of statement
    }
    public void returnToPage(View v)
    {
        Intent intent1=new Intent();
        //Intent intent= new Intent(MealActivity.this,ExpenseActivity.class);
        intent1.putExtra( "Meal",str);


        //Toast.makeText(this, Meal , Toast.LENGTH_SHORT).show();

        setResult(100, intent1);
        finish();



    }
}

