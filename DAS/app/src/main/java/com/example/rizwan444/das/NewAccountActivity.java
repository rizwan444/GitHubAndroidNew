package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewAccountActivity extends AppCompatActivity
{

    DataBase dataBase;
    EditText fname;

    EditText lname;

    EditText email;

    EditText password;

    EditText contact;
    TextView VCheckLogIn;
    boolean isInserted;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        Intent i=getIntent();
        dataBase = new DataBase(this);

         fname= (EditText) findViewById(R.id.editTxt_FName);

         lname= (EditText) findViewById(R.id.editTxt_LName);

         email= (EditText) findViewById(R.id.editTxt_Email);

         password= (EditText) findViewById(R.id.editTxt_Password);

         contact= (EditText) findViewById(R.id.editTxt_CntctNumber);

         VCheckLogIn=(TextView) findViewById(R.id.txtV_checkLogIn);

        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
         name1=sharepref.getString("User_Name","Got Nothing....");
        String pass1=sharepref.getString("Password","Got Nothing....");

        if(name1.isEmpty()){
           VCheckLogIn.setVisibility(TextView.INVISIBLE);

        }
        if (name1.equals("Got Nothing....")){
            VCheckLogIn.setVisibility(TextView.INVISIBLE);
        }
        else
        {
            VCheckLogIn.setText("You are Already LogIn");
        }

        Button btn= (Button) findViewById(R.id.btn_SIGNUP);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(name1.isEmpty())
                {
                    VCheckLogIn.setVisibility(TextView.INVISIBLE);

                    isInserted= dataBase.insert_PATIENTS_ACCOUNT(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),password.getText().toString(),contact.getText().toString());

                    if (isInserted==true){
                        Toast.makeText(NewAccountActivity.this,"Data Has Been Inserted", Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(NewAccountActivity.this,"Sorry Data Has Not Been Inserted", Toast.LENGTH_LONG).show();
                    }

                }

             else if (name1.equals("Got Nothing...."))
                {
                    isInserted= dataBase.insert_PATIENTS_ACCOUNT(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),password.getText().toString(),contact.getText().toString());

                    if (isInserted==true){
                        Toast.makeText(NewAccountActivity.this,"Data Has Been Inserted", Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(NewAccountActivity.this,"Sorry Data Has Not Been Inserted", Toast.LENGTH_LONG).show();
                    }
                }


                else
                {
                    VCheckLogIn.setText("You are Already LogIn");
                    VCheckLogIn.setVisibility(TextView.VISIBLE);
                    VCheckLogIn.setTextColor(getResources().getColor(R.color.TextVColorRed));
                    VCheckLogIn.setTextSize(20);

                    //Following Code for Blinking TextView
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(1200); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    VCheckLogIn.startAnimation(anim);
                }

            }
        });
    }
}
