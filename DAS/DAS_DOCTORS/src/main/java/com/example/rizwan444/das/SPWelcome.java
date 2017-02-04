package com.example.rizwan444.das;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SPWelcome extends AppCompatActivity {

    TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spwelcome);


        v=(TextView) findViewById(R.id.txtV_RcvMsg);


        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        String name1=sharepref.getString("User_Name","Got Nothing....");
        String pass1=sharepref.getString("Password","Got Nothing....");

        v.setTextColor(getResources().getColor(R.color.highlightedTextViewColor));

        v.setText("Welcome UserID: '"+name1+"' in the World of Mobile Applications!!!!!");

        //v.setText( "Welcome "+getIntent().getExtras().getString("Welcome"));
        v.setTextSize(25);

        //v.setTextAppearance(R.style.boldText); //Require API 23


       /* Bundle data=getIntent().getExtras();
        if(data==null){
            return;
        }
        v=(TextView) findViewById(R.id.txtV_RcvMsg);
        String recvddata= data.toString();
        v.setText(data.toString());*/

    }
}
