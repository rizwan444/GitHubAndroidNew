package com.example.fabricmanufacturing;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FINALRESULTS extends AppCompatActivity {

    TextView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalresults);
        v1=(TextView) findViewById(R.id.txtV_FResult_Msg1);
        SharedPreferences sharepref32= getSharedPreferences("FinalResultSP", Context.MODE_PRIVATE);
        String finalresult=sharepref32.getString("FinalResult","Got Nothing....");
        v1.setText("Total Fabric Required for Given Dimension and GSM value : "+finalresult);


    }
}
