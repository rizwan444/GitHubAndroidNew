package com.example.android_exam_preparation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=(Button)findViewById(R.id.btn_MA_STARTSERVCIE);

        Button btnstop=(Button)findViewById(R.id.btn_MA_STOPSERVCIE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, TestServices.class);
                i.putExtra("KEY","https://www.google.com/search?q=image&client=firefox-b-" +
                        "ab&source=lnms&tbm=isch&sa=X&ved=0ahUKEwijke6m3ebRAhVJ0hoKHb6nBkkQ_AUICCgB&biw=1600&bih=789#imgrc=TVEPc8yBbrThFM%3A");
                startService(i);
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, TestServices.class);
                stopService(intent);

            }
        });
    }
}
