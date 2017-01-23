package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class SharedPrefSaveData extends AppCompatActivity {

    EditText Uname,Password;
    TextView txtVSPresult;
    Button Save,login,display;
    String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_save_data);

        txtVSPresult=(TextView) findViewById(R.id.txtV_resultSP);
        Uname=(EditText) findViewById(R.id.editTxt_Uname);
        Password=(EditText) findViewById(R.id.editTxt_Password);

        name=Uname.getText().toString();
        password=Password.getText().toString();

        Save=(Button) findViewById(R.id.btn_Save);
        login=(Button) findViewById(R.id.btn_loginSP);
        display=(Button) findViewById(R.id.btn_displayDta);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);
                Intent intent = getIntent();
                finish();
                startActivity(intent);


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                String name1=sharepref.getString("User_Name","Got Nothing....");
                String pass1=sharepref.getString("Password","Got Nothing....");

                if(Uname.getText().toString().equals(name1) ){
                    txtVSPresult.setText(name1+pass1);
                    if(Password.getText().toString().equals(pass1)) {
                        Intent intent = new Intent(SharedPrefSaveData.this, SPWelcome.class);
                        intent.putExtra("Welcome", name1);
                        startActivity(intent);
                    }
                    else
                    {
                       // txtVSPresult.setText(name1+pass1);
                        Toast.makeText(SharedPrefSaveData.this,"Wrong Password!!!!",Toast.LENGTH_LONG).show();
                        /*Intent intent = getIntent();
                        finish();
                        startActivity(intent);*/
                    }

                }
               else
                {
                    //txtVSPresult.setText(name1+pass1);

                    Toast.makeText(SharedPrefSaveData.this,"U are Not Welcome",Toast.LENGTH_LONG).show();
                   /* Intent intent = getIntent();
                    finish();
                    startActivity(intent);*/
                }


            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

                String name1=sharepref.getString("User_Name","Got Nothing....");
                String pass1=sharepref.getString("Password","Got Nothing....");

                txtVSPresult.setText(name1+pass1);

                Map<String, ?> allEntries = sharepref.getAll();
                for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                    Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                }


            }
        });
    }

    //Save Data
    public  void saveInfo(View view){

        SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharepref.edit();

        editor.putString("User_Name",Uname.getText().toString());
        editor.putString("Password",Password.getText().toString());
        editor.apply();

         Toast.makeText(this,"Data SaveD", Toast.LENGTH_LONG).show();

    }
    //GetSaved Data
    public void getData(View view){



    }
}
