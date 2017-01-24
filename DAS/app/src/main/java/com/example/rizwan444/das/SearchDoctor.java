package com.example.rizwan444.das;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class SearchDoctor extends AppCompatActivity {

    Spinner spinner;
    DataBase dataBase;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        dataBase = new DataBase(this);

        btn1=(Button) findViewById(R.id.btn_selectDoc);
        spinner=(Spinner) findViewById(R.id.spinr_SearcDoc);
        //spinner.setOnItemSelectedListener(this);
        loadSpinnerData();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docName = spinner.getSelectedItem().toString();

                //Getting value from DOCTOR TABLE
                String result=dataBase.getDocID(docName);

                //Stroing that DOCTOR NAME FOR BOOKING
                SharedPreferences sharepref= getSharedPreferences("DOCNAME", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharepref.edit();
                editor.putString("Doctor_ID",result);
                editor.apply();

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
