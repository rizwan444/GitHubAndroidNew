package com.example.rizwan444.das;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AppCompatActivity {



    Button btnFix;
    EditText edtxt1,edtxt2;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBase(this);

        setContentView(R.layout.activity_appointment);
        btnFix=(Button) findViewById(R.id.btn_appFixApp);
        edtxt1=(EditText) findViewById(R.id.EditTxt_appDate);
        edtxt2=(EditText) findViewById(R.id.EditTxt_appTime);

        /*SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = new Date();
        String Date = timeStampFormat.format(myDate);*/

        Date d = new Date();
        CharSequence Date  = DateFormat.format("EEEE,d MMMM, yyyy ", d.getTime());


        SimpleDateFormat timeStampFormat = new SimpleDateFormat("HH:mm:ss aa");
        Date myDate = new Date();
        String Time = timeStampFormat.format(myDate);


        edtxt1.setText(Date);
        edtxt2.setText(Time);



        //Getting Value of USERID from SHARED PREFERENCE
        SharedPreferences sharepref1= getSharedPreferences("DOCPATIDs", Context.MODE_PRIVATE);
        final String docName=sharepref1.getString("Doctor_ID","Got Nothing....");
        final String PatName=sharepref1.getString("Patient_ID","Got Nothing....");

        btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.BOOKING_APPOINTMENT(PatName,docName,edtxt1.getText().toString(),edtxt2.getText().toString());
                Toast.makeText(Appointment.this, "Your Appointment is Fixed!!!!", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Appointment.this,MainActivity.class);
                startActivity(intent);



            }
        });


    }

}
