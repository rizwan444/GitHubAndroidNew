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
    String gcm,cw,bl,sl;
    Float gcmf,cwf,blf,slf;
    float inchconvert=2.54f;
    double gcmf1,cwf1,blf1,slf1;
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
                    if(unit.getSelectedItem().toString().matches("Centimeter")){
                        cw=CW.getText().toString();
                        cwf=Float.parseFloat(cw);
                        cwf=cwf/100;

                        bl=BL.getText().toString();
                        blf=Float.parseFloat(cw);
                        blf=blf/100;

                        sl=SL.getText().toString();
                        slf=Float.parseFloat(cw);
                        slf=slf/100;

                        //Saving the values of Dimensions,Unit and GSM
                        SharedPreferences sharepref = getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharepref.edit();
                        editor.putString("GSM", GSM.getText().toString());
                        editor.putString("UNIT", unit.getSelectedItem().toString());
                        editor.putString("CW",cwf.toString());
                        editor.putString("BL",blf.toString());
                        editor.putString("SL", slf.toString());
                        editor.apply();
                        Intent intent= new Intent(KnittedCalcultaion.this, FinalCalculationKnitted.class);
                        startActivity(intent);

                    }
                    else if(unit.getSelectedItem().toString().matches("Inches")){

                        Toast.makeText(KnittedCalcultaion.this, "This Finction is Under Development", Toast.LENGTH_SHORT).show();


                        cw=CW.getText().toString();
                        cwf=Float.parseFloat(cw);
                        cwf=cwf*0.0254f;


                        bl=BL.getText().toString();
                        blf=Float.parseFloat(cw);
                        blf=blf*0.0254f;

                        sl=SL.getText().toString();
                        slf=Float.parseFloat(cw);
                        slf=slf*0.0254f;

                        //Saving the values of Dimensions,Unit and GSM
                        SharedPreferences sharepref = getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharepref.edit();
                        editor.putString("GSM", GSM.getText().toString());
                        editor.putString("UNIT", unit.getSelectedItem().toString());
                        editor.putString("CW",cwf.toString());
                        editor.putString("BL",blf.toString());
                        editor.putString("SL", slf.toString());
                        editor.apply();



                        Intent intent= new Intent(KnittedCalcultaion.this, FinalCalculationKnitted.class);
                        startActivity(intent);

                    }
                    else {


                        //Saving the values of Dimensions,Unit and GSM
                        SharedPreferences sharepref = getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharepref.edit();
                        editor.putString("GSM", GSM.getText().toString());
                        editor.putString("UNIT",unit.getSelectedItem().toString());
                        editor.putString("CW", CW.getText().toString());
                        editor.putString("BL", BL.getText().toString());
                        editor.putString("SL", SL.getText().toString());
                        editor.apply();
                        Intent intent= new Intent(KnittedCalcultaion.this, FinalCalculationKnitted.class);
                        startActivity(intent);
                    }



                }





            }
        });
    }
}
