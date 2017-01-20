package com.example.rizwan444.das;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBase = new DataBase(this);
        Button btn= (Button)  findViewById(R.id.btn_SIGNUP);
        Button btn1= (Button) findViewById(R.id.btn_LogIn);
        Button btn2= (Button) findViewById(R.id.btn_Check);
        Button btn3= (Button) findViewById(R.id.btn_Search);
        Button btn4=(Button)  findViewById(R.id.btn_SharedPreference);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, NewAccountActivity.class);
                startActivity(intent);


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, LogINActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, SharedPrefSaveData.class);
                startActivity(intent);
            }
        });
    }





}
