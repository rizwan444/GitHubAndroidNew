package com.example.rizwan444.das;

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

        v.setText( getIntent().getExtras().toString());


       /* Bundle data=getIntent().getExtras();
        if(data==null){
            return;
        }
        v=(TextView) findViewById(R.id.txtV_RcvMsg);
        String recvddata= data.toString();
        v.setText(data.toString());*/

    }
}
