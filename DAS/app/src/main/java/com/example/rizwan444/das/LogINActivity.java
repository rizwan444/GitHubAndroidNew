package com.example.rizwan444.das;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogINActivity extends AppCompatActivity {

    DataBase dataBase;
    EditText Useremail;
    EditText Password;

    TextView V;
    String userid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        dataBase = new DataBase(this);



        V=(TextView) findViewById(R.id.txtV_Msg);

        //V.setText("");
        Useremail=(EditText) findViewById(R.id.editTxt_UserID);
        //userid= Userid.getText().toString();
        Password=(EditText) findViewById(R.id.editTxt_Password);

        password= Password.getText().toString();




        Button btnLOGIN= (Button) findViewById(R.id.btn_LogIn);

        btnLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String info=userid+password;
                String FPassword=dataBase.searchpass(Useremail.getText().toString());
                V.setText(FPassword);
                if(Password.getText().toString().equals(FPassword)){
                    Toast.makeText(LogINActivity.this,"Welcome", Toast.LENGTH_LONG).show();

                    SharedPreferences sharepref= getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharepref.edit();

                    editor.putString("User_Name",Useremail.getText().toString());
                    editor.putString("Password",Password.getText().toString());
                    editor.apply();


                    Intent intent = new Intent(LogINActivity.this, SPWelcome.class);
                    //intent.putExtra("Welcome", name1);
                    startActivity(intent);

                }
              else {
                    Toast.makeText(LogINActivity.this,"NOT Welcome", Toast.LENGTH_LONG).show();
                }
                dataBase.close();


                /*code==009 in DataBase
                Boolean result=dataBase.LOGIN(Userid.getText().toString(),Password.getText().toString());
                if(result==true){
                    Toast.makeText(LogINActivity.this,"Welcome", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LogINActivity.this,"NOT Welcome", Toast.LENGTH_LONG).show();
                }*/

                /*
               Intent intent = getIntent();
                finish();
                startActivity(intent);
                */



                /*
                    StringBuffer buffer= new StringBuffer();
                    while(cursor.moveToNext()){
                    buffer.append("ID :"+cursor.getString(0)+"\n");
                    buffer.append("ID :"+cursor.getString(1)+"\n");
                    buffer.append("ID :"+cursor.getString(2)+"\n");
                    buffer.append("ID :"+cursor.getString(3)+"\n");
                    buffer.append("ID :"+cursor.getString(4)+"\n");
                    buffer.append("ID :"+cursor.getString(5)+"\n\n");
                }
                V.setText(buffer);*/

            }
        });

    }
}
