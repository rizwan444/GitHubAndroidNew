package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefSaveData extends AppCompatActivity {

    EditText Uname,Password;
    TextView txtVSPresult;
    Button Save,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref_save_data);

        txtVSPresult=(TextView) findViewById(R.id.txtV_resultSP);
        Uname=(EditText) findViewById(R.id.editTxt_Uname);
        Password=(EditText) findViewById(R.id.editTxt_Password);

        Save=(Button) findViewById(R.id.btn_Save);
        login=(Button) findViewById(R.id.btn_loginSP);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                String name=sharepref.getString("User_Name","Got Nothing....");
                String pass=sharepref.getString("Password","Got Nothing....");


                Intent intent = new Intent(SharedPrefSaveData.this, SPWelcome.class);
                intent.putExtra("Welcome", name);
                startActivity(intent);

                txtVSPresult.setText(name+pass);
                /*
                if(name==Uname.getText().toString() ) {
                    txtVSPresult.setText(name+pass);

                    if (pass==Password.getText().toString()) {
                        Intent intent = new Intent(SharedPrefSaveData.this, SPWelcome.class);
                        intent.putExtra("Welcome", name);
                        startActivity(intent);
                    }
                }*/

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
