package com.example.fabricmanufacturing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FabricList extends AppCompatActivity {

    Button btnWoven,btnKnitted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabric_list);
        btnKnitted=(Button) findViewById(R.id.btn_FList_btnKnitted);
        btnWoven=(Button) findViewById(R.id.btn_FList_Woven);

        btnWoven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FabricList.this, WovenCalculation.class);
                startActivity(intent);

            }
        });
        btnKnitted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FabricList.this, KnittedCalcultaion.class);
                startActivity(intent);

            }
        });
    }
}
