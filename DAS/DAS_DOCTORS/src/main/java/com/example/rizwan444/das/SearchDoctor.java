package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SearchDoctor extends AppCompatActivity {

    Spinner spinner;
    DataBase dataBase;
    Button btn1;
     String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        dataBase = new DataBase(this);
        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
         username=sharepref.getString("User_Name","Got Nothing....");

        btn1=(Button) findViewById(R.id.btn_selectDoc);
        spinner=(Spinner) findViewById(R.id.spinr_SearcDoc);
        //spinner.setOnItemSelectedListener(this);
        loadSpinnerData();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docName = spinner.getSelectedItem().toString();


                    //Getting value from DOCTOR TABLE
                    String result = dataBase.getDocID(docName);
                    String result1 = dataBase.getPatientID(username);

                    //Stroing that DOCTOR NAME FOR BOOKING
                    SharedPreferences sharepref = getSharedPreferences("DOCPATIDs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref.edit();
                    editor.putString("Doctor_ID", result);
                    editor.putString("Patient_ID", result1);
                    editor.apply();
                    Intent intent = new Intent(SearchDoctor.this, Doctors_Details.class);
                    startActivity(intent);


            }
        });


    }

    private void loadSpinnerData() {
        // database handler
         dataBase = new DataBase(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = dataBase.FetchDoctors();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

}
