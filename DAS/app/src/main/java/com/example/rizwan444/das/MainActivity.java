package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase;
    TextView VMSG1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this);
        Button btnSIGNUP= (Button)  findViewById(R.id.btn_SIGNUP);
        Button btnLogIn= (Button) findViewById(R.id.btn_LogIn);
        Button btnCheck= (Button) findViewById(R.id.btn_Check);
        Button btnSearch= (Button) findViewById(R.id.btn_Search);
        Button btnSharPrf=(Button)  findViewById(R.id.btn_SharedPreference);
        Button btnFragment=(Button)  findViewById(R.id.btn_Fragment);
        Button btnSFragment=(Button)  findViewById(R.id.btn_SingleFragment);
        Button btnLogOut=(Button)  findViewById(R.id.btn_LogOut);
        Button btnRefresh=(Button) findViewById(R.id.btn_Refresh);
        VMSG1=(TextView) findViewById(R.id.txtV_loginperson);

        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        String name1=sharepref.getString("User_Name","Got Nothing....");
        String pass1=sharepref.getString("Password","Got Nothing....");

        if(name1.equals("Got Nothing....")) {
            VMSG1.setText("Not Logged IN");
        }
        else
        {
            VMSG1.setText(name1);
        }

        btnSIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, NewAccountActivity.class);
                startActivity(intent);


            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, LogINActivity.class);
                startActivity(intent);
            }
        });
        btnSharPrf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, SharedPrefSaveData.class);
                startActivity(intent);
            }
        });
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, FragmentsMeme.class);
                startActivity(intent);

            }
        });
        btnSFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Singlefragment.class);
                startActivity(intent);

            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting SharedPrefferences of names "LoginInfo"
                SharedPreferences mySPrefs =getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                // Editor used for editing in SharedPrefferences
                SharedPreferences.Editor editor = mySPrefs.edit();
                // dot remove function used the values in key of User_Name
                editor.remove("User_Name" );
                editor.remove("Password");
                //editor dot apply just apply our editings
                editor.apply();

                //Refresh an activity
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //these three lines i used for refresh an activity Means restart
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, SearchDoctor.class);
                startActivity(intent);

            }
        });
    }





}
