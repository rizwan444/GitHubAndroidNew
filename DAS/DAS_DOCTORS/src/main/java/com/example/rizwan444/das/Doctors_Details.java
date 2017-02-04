package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Doctors_Details extends AppCompatActivity {

    ListView lvDocDetails;
    Button Appt,back;
    DataBase dbobj;
    String DocID,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors__details);
        dbobj= new DataBase(this);

        SharedPreferences sharepref = getSharedPreferences("DOCPATIDs", Context.MODE_PRIVATE);

        DocID=sharepref.getString("Doctor_ID","Null");
        SharedPreferences sharepref2= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        username=sharepref2.getString("User_Name","Got Nothing....");



        Appt=(Button) findViewById(R.id.btn_DDA_APPOINTMENT);
        back=(Button) findViewById(R.id.btn_DDA_BACK);

        lvDocDetails=(ListView) findViewById(R.id.lv_DDA_DOCTORDETAILS);
        LoadListView();

        Appt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.equals("Got Nothing....")){
                    Toast.makeText(Doctors_Details.this, "Please LogIn to Fix an Appointment", Toast.LENGTH_SHORT).show();

                }
                else{


                    Intent intent = new Intent(Doctors_Details.this, Appointment.class);
                    startActivity(intent);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Doctors_Details.this,SearchDoctor.class);
                startActivity(intent);
            }
        });






    }
    private void LoadListView() {
        // database handler
        dbobj = new DataBase(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = dbobj.GetDocDetails(DocID);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        lvDocDetails.setAdapter(dataAdapter);
    }

}
