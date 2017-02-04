package com.example.rizwan444.das;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
    EditText fname, lname, email, password,passwordrepeat, contact;
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

        passwordrepeat=(EditText) findViewById(R.id.editTxt_RePassword);

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

        passwordrepeat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(NewAccountActivity.this, "You Missed Password", Toast.LENGTH_SHORT).show();

                }
                else {
                    if (!hasFocus) {
                        if (password.getText().toString().equals(passwordrepeat.getText().toString())) {
                            Toast.makeText(NewAccountActivity.this, "Password Matched", Toast.LENGTH_SHORT).show();


                        } else {
                            passwordrepeat.setText("");
                            Toast.makeText(NewAccountActivity.this, "Password Does not Match", Toast.LENGTH_SHORT).show();
                            contact.clearFocus();
                            passwordrepeat.requestFocus();

                        }
                    }
                }
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(email.getText().toString().isEmpty()){
                    if(!hasFocus){
                        if(isEmailValid(email.getText().toString()))
                        {
                            password.clearFocus();
                            Toast.makeText(NewAccountActivity.this, "Format error in Email", Toast.LENGTH_SHORT).show();
                            email.requestFocus();

                        }

                    }

                }
            }
        });


        Button btn= (Button) findViewById(R.id.btn_SIGNUP);



        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    VCheckLogIn.setVisibility(TextView.INVISIBLE);

                if(fname.getText().toString().isEmpty()){
                    if(email.getText().toString().isEmpty()){
                        if(password.getText().toString().isEmpty()){
                            fname.requestFocus();
                            Toast.makeText(NewAccountActivity.this, "Your Name, Email and Password Must Required", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                else if(email.getText().toString().isEmpty()){
                    if(password.getText().toString().isEmpty()){
                        email.requestFocus();
                        Toast.makeText(NewAccountActivity.this, "Email and Password Required", Toast.LENGTH_SHORT).show();
                    }

                }
                else if(email.getText().toString().isEmpty()){
                    email.requestFocus();
                    Toast.makeText(NewAccountActivity.this, "Email Must Required", Toast.LENGTH_SHORT).show();

                }
                else if(password.getText().toString().isEmpty()){
                    password.requestFocus();
                    Toast.makeText(NewAccountActivity.this, "Password Required", Toast.LENGTH_SHORT).show();
                }

                else {
                    Boolean emailcheck = dataBase.getPatEmails(email.getText().toString());

                    if (emailcheck.equals(true)) {
                        new AlertDialog.Builder(NewAccountActivity.this)
                                .setTitle("Duplicate Email")
                                .setMessage("This Email has been already used. Try Another One")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        email.setText("");
                                        email.requestFocus();
                                        dialog.dismiss();
                                    }
                                })

                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                        Toast.makeText(NewAccountActivity.this, "Sorry This Email is Already in Use", Toast.LENGTH_SHORT).show();
                    }

                else {


                    isInserted = dataBase.insert_PATIENTS_ACCOUNT(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), password.getText().toString(), contact.getText().toString());

                    if(name1.isEmpty()){


                    if (isInserted == true) {
                        Toast.makeText(NewAccountActivity.this, "Data Has Been Inserted", Toast.LENGTH_LONG).show();
                    }
                    else if(isInserted == false) {

                        Toast.makeText(NewAccountActivity.this, "Sorry Data Has Not Been Inserted", Toast.LENGTH_LONG).show();
                    }
                    }


                    else if (name1.equals("Got Nothing....")) {
                        isInserted = dataBase.insert_PATIENTS_ACCOUNT(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), password.getText().toString(), contact.getText().toString());

                        if (isInserted == true) {
                            Toast.makeText(NewAccountActivity.this, "Data Has Been Inserted", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(NewAccountActivity.this, "Sorry Data Has Not Been Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
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
            }

                Intent intent= new Intent(NewAccountActivity.this,LogINActivity.class);
                startActivity(intent);


            }
        });
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
