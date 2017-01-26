package com.example.rizwan444.das;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

public class UserProfile extends AppCompatActivity {

    GridView grdV;
    Button btnappt;
    DataBase dataBase;
    String name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        dataBase= new DataBase(this);
        grdV=(GridView) findViewById(R.id.GrdV_UPApptmt);
        btnappt=(Button) findViewById(R.id.btn_UP_Appointments);
        grdV.setVisibility(View.INVISIBLE);

        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        name1=sharepref.getString("User_Name","Got Nothing....");

        btnappt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void LoadAppointmentData( ) {
        // database handler
        dataBase = new DataBase(getApplicationContext());

        // Spinner Drop down elements
        Cursor cursor = dataBase.getAppData(name1);

    }
}
