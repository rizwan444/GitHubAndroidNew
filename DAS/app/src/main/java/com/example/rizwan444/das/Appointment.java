package com.example.rizwan444.das;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Appointment extends AppCompatActivity {



    Button btnFix;
    EditText edtxt1,edtxt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        btnFix=(Button) findViewById(R.id.btn_appFixApp);
        edtxt1=(EditText) findViewById(R.id.EditTxt_appDate);
        edtxt2=(EditText) findViewById(R.id.EditTxt_appTime);

        //Getting Value of DOC ID from SHARED PREFERENCE
        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        String username=sharepref.getString("User_Name","Got Nothing....");

        //Getting Value of USERID from SHARED PREFERENCE
        SharedPreferences sharepref1= getSharedPreferences("DOCNAME", Context.MODE_PRIVATE);
        String docName=sharepref1.getString("Doctor_ID","Got Nothing....");


    }
}
