package com.example.fabricmanufacturing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {



    Button btnlogIn;
    EditText E_Username, E_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogIn=(Button) findViewById(R.id.btn_Home_LogIn);
        E_Username=(EditText) findViewById(R.id.EditTxt_Home_UserName);
        E_password=(EditText) findViewById(R.id.EditTxt_Home_Password);

        btnlogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(E_Username.getText().toString().equals("Rizwan")){
                    if(E_password.getText().toString().equals("fabric123")){
                        Toast.makeText(Home.this, "Welcome to Fabric Manufacturing", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(Home.this,FabricList.class);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(Home.this, "Sorry Wrong Password or Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
