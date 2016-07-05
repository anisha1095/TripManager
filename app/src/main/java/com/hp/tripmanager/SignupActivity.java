package com.hp.tripmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText editTextUsername; //to input username
    EditText editTextPassword; //to input password
    EditText editTextConfirmPassword; //to input confirm password
    EditText editTextEmail; //to input email id
    EditText editTextPhone; //to input contact number
    EditText editTextDate; //to input DOB
    Button buttonCreateAccount; //button to create account

    //LoginDataBaseAdapter loginDataBaseAdapter;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // View contentView=inflater.inflate(R.layout.activity_signup,null,false);
        //drawer.addView(contentView,0);
       setContentView(R.layout.activity_signup);
        Intent i=getIntent();
        //get instance of database adapter
        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        //get the reference of views
        editTextUsername= (EditText) findViewById(R.id.editTextUsername);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword= (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPhone= (EditText) findViewById(R.id.editTextPhone);
        editTextDate= (EditText) findViewById(R.id.editTextDOB);

        buttonCreateAccount= (Button) findViewById(R.id.buttonCreateAccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editTextUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String email=editTextEmail.getText().toString();
                String phone=editTextPhone.getText().toString();
                String dob=editTextDate.getText().toString();

                //check if any of the fields are vacant
                if(username.equals("")||password.equals("")||confirmPassword.equals("")||email.equals("")||phone.equals("")||dob.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Field Vacant",Toast.LENGTH_SHORT).show();
                    return;
                }

                //check if both passwords match
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                { //save data in database
                    loginDataBaseAdapter.insertEntry(username, password,email,phone,dob);
                    Toast.makeText(getApplicationContext(),"Account Successfully Created!!",Toast.LENGTH_SHORT).show();
                }
            }
        }); //end of statement
         // Intent it = new Intent(this,LoginResultActivity.class);
        //it.putExtra("USER",editTextUsername.getText().toString());
        //it.putExtra("PASS",edit)
    } //end of onCreate() method

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    } //end of onDestroy() method
} //end of SignupActivity class
