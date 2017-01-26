package com.example.fabricmanufacturing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class KnittedCalcultaion extends AppCompatActivity {


    Button btnSave;
    Spinner unit;
    EditText GSM,CW,BL,SL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knitted_calcultaion);
        btnSave=(Button) findViewById(R.id.btn_KCal_saveDimensions);
        CW=(EditText) findViewById(R.id.EditTxt_KCal_CW);
        BL=(EditText) findViewById(R.id.EditTxt_KCal_BL);
        SL=(EditText) findViewById(R.id.EditTxt_KCal_SL);
        GSM=(EditText) findViewById(R.id.EditTxt_KCal_GSM);
        unit=(Spinner) findViewById(R.id.Spinner_KCalc_SizeUnit);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(GSM.getText().toString().matches("")){
                    Toast.makeText(KnittedCalcultaion.this, "GSM Value Must Required", Toast.LENGTH_LONG).show();
                }
                else if(CW.getText().toString().matches("")){
                    Toast.makeText(KnittedCalcultaion.this, "Chest Width Value Must Required", Toast.LENGTH_SHORT).show();

                }
                else if(BL.getText().toString().matches("")){
                    Toast.makeText(KnittedCalcultaion.this, "Body Length Value Must Required", Toast.LENGTH_SHORT).show();

                }
                else if(SL.getText().toString().matches("")){
                    Toast.makeText(KnittedCalcultaion.this, "Sleeve Length Must Required", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //Saving the values of Dimensions,Unit and GSM
                    SharedPreferences sharepref= getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharepref.edit();
                    editor.putString("GSM",GSM.getText().toString());
                    editor.putString("UNIT",CW.getText().toString());
                    editor.putString("CW",CW.getText().toString());
                    editor.putString("BL",BL.getText().toString());
                    editor.putString("SL",SL.getText().toString());
                    editor.apply();

                    Intent intent= new Intent(KnittedCalcultaion.this, FinalCalculationKnitted.class);
                    startActivity(intent);

                }





            }
        });
    }
}
