package com.example.rizwan444.das;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccountActivity extends AppCompatActivity {

    DataBase dataBase;
    EditText fname;

    EditText lname;

    EditText email;

    EditText password;

    EditText contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        Intent i=getIntent();
        dataBase = new DataBase(this);

         fname= (EditText) findViewById(R.id.editTxt_FName);

         lname= (EditText) findViewById(R.id.editTxt_LName);

         email= (EditText) findViewById(R.id.editTxt_Email);

         password= (EditText) findViewById(R.id.editTxt_Password);

         contact= (EditText) findViewById(R.id.editTxt_CntctNumber);


        Button btn= (Button) findViewById(R.id.btn_SIGNUP);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted;
               isInserted= dataBase.insert_PATIENTS_ACCOUNT(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),password.getText().toString(),contact.getText().toString());

                if (isInserted==true){
                     Toast.makeText(NewAccountActivity.this,"Data Has Been Inserted", Toast.LENGTH_LONG).show();
                }
                else{

                        Toast.makeText(NewAccountActivity.this,"Sorry Data Has Not Been Inserted", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
}
