package com.example.fabricmanufacturing;

import android.animation.TypeConverter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class WastagesValues extends AppCompatActivity {

    Spinner  s1,s2,s3,s4,s5;
    Button btnfinalresult;
    String sandCperc,dyeperc,beachperc,knittingperc,dyeorBleach;
    Float AreaOfRectangle,slevlengthactual,slevlength,slevlength1;
    Float slevlengthfinal,bodylengthactual,bodylength,bodylength1,bodylengthfinal,chestwidthactual,chestwidth,chestwidth1,chestwidthfinal,GSM1,firstresult,SandCuttingWastage;
    Float sandCWaste,bleachDyeWaste,knittingWaste,DyeWaste,bleachWaste;
    Float secondresult;
    TextView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wastages_values);

        s1=(Spinner) findViewById(R.id.Spinner_WValues_SandC_Wastages);
        s2=(Spinner) findViewById(R.id.Spinner_WValues_BleachDye);
        s3=(Spinner) findViewById(R.id.Spinner_WValues_Dye_Value);
        s4=(Spinner) findViewById(R.id.Spinner_WValues_Bleach_Value);
        s5=(Spinner) findViewById(R.id.Spinner_WValues_Knitting);

        s3.setVisibility(View.INVISIBLE);
        s4.setVisibility(View.INVISIBLE);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(s2.getSelectedItem().toString().matches("MANUAL_DYEING")){
                    s3.setVisibility(View.VISIBLE);
                    s4.setVisibility(View.INVISIBLE);


                }
                else if(s2.getSelectedItem().toString().matches("MANUAL_BLEACHING")){
                    s4.setVisibility(View.VISIBLE);
                    s3.setVisibility(View.INVISIBLE);
                }
                else{
                    s3.setVisibility(View.INVISIBLE);
                    s4.setVisibility(View.INVISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        v1=(TextView) findViewById(R.id.check1);






        btnfinalresult=(Button) findViewById(R.id.btn_WValues_btnCalculate);

        btnfinalresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                SharedPreferences sharepref= getSharedPreferences("WastageInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharepref.edit();
                editor.putString("SandC_Wastage",s1.getSelectedItem().toString());
                editor.putString("Bleach_Dye",s2.getSelectedItem().toString());
                editor.putString("Knitting_Wastage",s5.getSelectedItem().toString());
                editor.apply();*/

                //Getting Previous values of Dimensions and Allowances
                SharedPreferences sharepref5= getSharedPreferences("DimensionInfo", Context.MODE_PRIVATE);
                String gsm=sharepref5.getString("GSM","Got Nothing....");
                String unit=sharepref5.getString("UNIT","Got Nothing....");
                String cw=sharepref5.getString("CW","Got Nothing....");
                String bl=sharepref5.getString("BL","Got Nothing....");
                String sl=sharepref5.getString("SL","Got Nothing....");


                SharedPreferences sharepref4= getSharedPreferences("AllowanceInfo", Context.MODE_PRIVATE);
                String salcw=sharepref4.getString("SAL_CW","Got Nothing....");
                String sarcw=sharepref4.getString("SAR_CW","Got Nothing....");
                String sabl=sharepref4.getString("SA_BL","Got Nothing....");
                String habl=sharepref4.getString("HA_BL","Got Nothing....");
                String sasl=sharepref4.getString("SA_SL","Got Nothing....");
                String hasl=sharepref4.getString("HA_SL","Got Nothing....");


                //Converting get values of Dimensions in Float for Calculations
                slevlengthactual= Float.parseFloat(sl);
                bodylengthactual= Float.parseFloat(bl);
                chestwidthactual=Float.parseFloat(cw);
                GSM1=Float.parseFloat(gsm);

                //Converting get values of Allowances

                slevlength= Float.parseFloat(sasl);
                slevlength1=Float.parseFloat(hasl);
                bodylength=Float.parseFloat(sabl);
                bodylength1=Float.parseFloat(habl);
                chestwidth=Float.parseFloat(salcw);
                chestwidth1=Float.parseFloat(sarcw);



                //Addition of Allowances and Actual Dimensions
                slevlengthfinal=slevlength+slevlength1+slevlengthactual;
                bodylengthfinal=bodylength+bodylength1+bodylengthactual;
                chestwidthfinal=chestwidth+chestwidth1+chestwidthactual;


               //Calculating Area of Rectangle
                AreaOfRectangle=((slevlengthfinal+bodylengthfinal)*chestwidthfinal)*2;

                // Calculating Required Grams from Area and GSM Value
                firstresult=GSM1*AreaOfRectangle;


                //Getting Values of Wastages in Percentage

                if(s2.getSelectedItem().toString().matches("BLEACHING")){
                    beachperc="5.0";
                    sandCperc = s1.getSelectedItem().toString();
                    knittingperc = s5.getSelectedItem().toString();


                    sandCWaste = Float.parseFloat(sandCperc);//Convert Value to float
                    sandCWaste = (sandCWaste / 100) * firstresult;


                    bleachWaste = Float.parseFloat(beachperc);
                    bleachWaste = (bleachWaste / 100) * firstresult;


                    knittingWaste = Float.parseFloat(knittingperc);
                    knittingWaste = (knittingWaste / 100) * firstresult;


                    secondresult = firstresult + knittingWaste + sandCWaste + bleachWaste;
                    v1.setText(secondresult.toString());

                    SharedPreferences sharepref3 = getSharedPreferences("FinalResultSP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref3.edit();
                    editor.putString("FinalResult", secondresult.toString());

                    editor.apply();


                    Intent intent = new Intent(WastagesValues.this, FINALRESULTS.class);
                    startActivity(intent);

                }
                else if (s2.getSelectedItem().toString().matches("DYEING")){
                    dyeperc="5.0";
                    sandCperc = s1.getSelectedItem().toString();
                    knittingperc = s5.getSelectedItem().toString();


                    sandCWaste = Float.parseFloat(sandCperc);//Convert Value to float
                    sandCWaste = (sandCWaste / 100) * firstresult;


                    DyeWaste = Float.parseFloat(dyeperc);
                    DyeWaste = (DyeWaste / 100) * firstresult;


                    knittingWaste = Float.parseFloat(knittingperc);
                    knittingWaste = (knittingWaste / 100) * firstresult;


                    secondresult = firstresult + knittingWaste + sandCWaste + DyeWaste;
                    v1.setText(secondresult.toString());

                    SharedPreferences sharepref3 = getSharedPreferences("FinalResultSP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref3.edit();
                    editor.putString("FinalResult", secondresult.toString());

                    editor.apply();


                    Intent intent = new Intent(WastagesValues.this, FINALRESULTS.class);
                    startActivity(intent);
                }
                else if(s2.getSelectedItem().toString().matches("MANUAL_BLEACHING")) {

                    sandCperc = s1.getSelectedItem().toString();
                    beachperc = s4.getSelectedItem().toString();
                    knittingperc = s5.getSelectedItem().toString();


                    sandCWaste = Float.parseFloat(sandCperc);//Convert Value to float
                    sandCWaste = (sandCWaste / 100) * firstresult;


                    bleachWaste = Float.parseFloat(beachperc);
                    bleachWaste = (bleachWaste / 100) * firstresult;


                    knittingWaste = Float.parseFloat(knittingperc);
                    knittingWaste = (knittingWaste / 100) * firstresult;


                    secondresult = firstresult + knittingWaste + sandCWaste + bleachWaste;
                    v1.setText(secondresult.toString());

                    SharedPreferences sharepref3 = getSharedPreferences("FinalResultSP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref3.edit();
                    editor.putString("FinalResult", secondresult.toString());

                    editor.apply();


                    Intent intent = new Intent(WastagesValues.this, FINALRESULTS.class);
                    startActivity(intent);

                }
                else if(s2.getSelectedItem().toString().matches("MANUAL_DYEING")){
                    sandCperc = s1.getSelectedItem().toString();
                    dyeperc = s3.getSelectedItem().toString();
                    knittingperc = s5.getSelectedItem().toString();


                    sandCWaste = Float.parseFloat(sandCperc);//Convert Value to float
                    sandCWaste = (sandCWaste / 100) * firstresult;


                    DyeWaste = Float.parseFloat(dyeperc);
                    DyeWaste = (DyeWaste / 100) * firstresult;


                    knittingWaste = Float.parseFloat(knittingperc);
                    knittingWaste = (knittingWaste / 100) * firstresult;


                    secondresult = firstresult + knittingWaste + sandCWaste + DyeWaste;
                    v1.setText(secondresult.toString());

                    SharedPreferences sharepref3 = getSharedPreferences("FinalResultSP", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref3.edit();
                    editor.putString("FinalResult", secondresult.toString());

                    editor.apply();


                    Intent intent = new Intent(WastagesValues.this, FINALRESULTS.class);
                    startActivity(intent);

                }
            }
        });
    }
}
