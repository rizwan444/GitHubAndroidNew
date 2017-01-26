package com.example.fabricmanufacturing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FinalCalculationKnitted extends AppCompatActivity {

    TextView V1,V2,V3,V4,V5,V6;
    Button btnSave;
    Spinner unitmode;
    Float SARCW,SALCW,SASL,HASL,SABL,HABL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_calculation_knitted);
        V1=(TextView) findViewById(R.id.EditTxt_FCal_SAL_CW);
        V2=(TextView) findViewById(R.id.EditTxt_FCal_SAR_CW);
        V3=(TextView) findViewById(R.id.EditTxt_FCal_SA_BL);
        V4=(TextView) findViewById(R.id.EditTxt_FCal_HA_BL);
        V5=(TextView) findViewById(R.id.EditTxt_FCal_SA_SL);
        V6=(TextView) findViewById(R.id.EditTxt_FCal_HA_SL);
        unitmode=(Spinner) findViewById(R.id.Spinner_FCal_InputUnit);
        SharedPreferences sharepref6= getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
         String unit=sharepref6.getString("UNIT","Got Nothing....");
        if(unit.equals("Centimeter")){
            unitmode.setSelection(0);

        }
        else if(unit.equals("Inches")){
            unitmode.setSelection(1);

        }
        else{
            unitmode.setSelection(2);
        }



        btnSave=(Button) findViewById(R.id.btn_FCal_Save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(V1.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Seaming Allowance Left Required for Chest Width", Toast.LENGTH_LONG).show();
                }
                else if(V2.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Seaming Allowance Left Required for Chest Width", Toast.LENGTH_SHORT).show();

                }
                else if(V3.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Seaming Allowance for Body Length Required", Toast.LENGTH_SHORT).show();

                }
                else if(V4.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Heaming Allowance for Body Length Required", Toast.LENGTH_SHORT).show();

                }
                else if(V5.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Seaming Allowance for Sleeve Length Required", Toast.LENGTH_SHORT).show();

                }
                else if(V6.getText().toString().matches("")){
                    Toast.makeText(FinalCalculationKnitted.this, "Heaming Allowance for Sleeve Length Required", Toast.LENGTH_SHORT).show();

                }
                else
                {

                    if(unitmode.getSelectedItem().toString().matches("Centimeter")){

                        SALCW=Float.parseFloat(V1.getText().toString());
                        SALCW=SALCW/100;
                        SARCW=Float.parseFloat(V2.getText().toString());
                        SARCW=SARCW/100;
                        SABL=Float.parseFloat(V3.getText().toString());
                        SABL=SABL/100;
                        HABL=Float.parseFloat(V4.getText().toString());
                        HABL=HABL/100;
                        SASL=Float.parseFloat(V5.getText().toString());
                        SASL=SASL/100;
                        HASL=Float.parseFloat(V6.getText().toString());
                        HASL=HASL/100;
                        //Saving the values of Dimensions,Unit and GSM After converting fromCentimeter to Meter
                        SharedPreferences sharepref2= getSharedPreferences("AllowanceInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharepref2.edit();

                        editor.putString("SAL_CW",SALCW.toString());
                        editor.putString("SAR_CW",SARCW.toString());
                        editor.putString("SA_BL",SABL.toString());
                        editor.putString("HA_BL",HABL.toString());
                        editor.putString("SA_SL",SASL.toString());
                        editor.putString("HA_SL",HASL.toString());
                        editor.apply();

                        Intent intent= new Intent(FinalCalculationKnitted.this, WastagesValues.class);
                        startActivity(intent);




                    }
                    else if(unitmode.getSelectedItem().toString().matches("Inches")){
                        SALCW=Float.parseFloat(V1.getText().toString());
                        SALCW=SALCW*0.0254f;
                        SARCW=Float.parseFloat(V2.getText().toString());
                        SARCW=SARCW*0.0254f;
                        SABL=Float.parseFloat(V3.getText().toString());
                        SABL=SABL*0.0254f;
                        HABL=Float.parseFloat(V4.getText().toString());
                        HABL=HABL*0.0254f;
                        SASL=Float.parseFloat(V5.getText().toString());
                        SASL=SASL*0.0254f;
                        HASL=Float.parseFloat(V6.getText().toString());
                        HASL=HASL*0.0254f;
                        //Saving the values of Dimensions,Unit and GSM After converting fromCentimeter to Meter
                        SharedPreferences sharepref2= getSharedPreferences("AllowanceInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharepref2.edit();

                        editor.putString("SAL_CW",SALCW.toString());
                        editor.putString("SAR_CW",SARCW.toString());
                        editor.putString("SA_BL",SABL.toString());
                        editor.putString("HA_BL",HABL.toString());
                        editor.putString("SA_SL",SASL.toString());
                        editor.putString("HA_SL",HASL.toString());
                        editor.apply();

                        Intent intent= new Intent(FinalCalculationKnitted.this, WastagesValues.class);
                        startActivity(intent);

                    }
                    else{

                    //Saving the values of Dimensions,Unit and GSM
                    SharedPreferences sharepref2= getSharedPreferences("AllowanceInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharepref2.edit();



                    editor.putString("SAL_CW",V1.getText().toString());
                    editor.putString("SAR_CW",V2.getText().toString());
                    editor.putString("SA_BL",V3.getText().toString());
                    editor.putString("HA_BL",V4.getText().toString());
                    editor.putString("SA_SL",V5.getText().toString());
                    editor.putString("HA_SL",V6.getText().toString());
                    editor.apply();

                    Intent intent= new Intent(FinalCalculationKnitted.this, WastagesValues.class);
                    startActivity(intent);

                    }

                }

            }
        });



    }
}
