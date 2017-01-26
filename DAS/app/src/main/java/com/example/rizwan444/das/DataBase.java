package com.example.rizwan444.das;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rizwan444 on 1/12/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static String DB_PATH = "/data/data/DAS/databases/";
    private static String DB_NAME = "DAS.db";
    public static final String PTABLE_NAME = "PATIENTS_ACCOUNT";
    public static final String PCOL_1 = "PATIENT_ID";
    public static final String PCOL_2 = "FIRSTNAME";
    public static final String PCOL_3 = "LASTNAME";
    public static final String PCOL_4 = "EMAIL";
    public static final String PCOL_5 = "PASSWORD";
    public static final String PCOL_6 = "CONTACT";
    public static final String PCOL_7 = "CODE";


    public static final String DTABLE_NAME = "DOCTORS_ACCOUNT";
    public static final String DCOL_1 = "DOCTOR_ID";
    public static final String DCOL_2 = "DOCTOR_NAME";
    public static final String DCOL_3 = "DOCTOR_Email";
    public static final String DCOL_4 = "PASSWORD";
    public static final String DCOL_5 = "SPECIALIZATION";
    public static final String DCOL_6 = "Average_Appt_Rate";
    public static final String DCOL_7 = "FEE";
    public static final String DCOL_8 = "DESCRIPTION";
    public static final String DCOL_9 = "CLINIC_NAME";
    public static final String DCOL_10 = "CLINIC_ADDRESS";
    public static final String DCOL_11= "CONTACT#";


    public static final String ApptTABLE_NAME = "APPOINTMENT_TABLE";
    public static final String ApptCOL_1 = "APPT_ID";
    public static final String ApptCOL_4 = "PATIENT_ACCOUNT_ID";
    public static final String ApptCOL_6 = "DOCTOR_ID";
    public static final String ApptCOL_7 = "DATE";
    public static final String ApptCOL_8 = "TIME";
    public static final String ApptCOL_9 = "STATUS";





    public DataBase(Context context) {
        super(context, "DAS.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + PTABLE_NAME + "(PATIENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, EMAIL TEXT,PASSWORD TEXT, CONTACT TEXT, CODE TEXT );");

        sqLiteDatabase.execSQL("CREATE TABLE " + DTABLE_NAME + "(DOCTOR_ID INTEGER PRIMARY KEY AUTOINCREMENT, DOCTOR_NAME TEXT, DOCTOR_Email TEXT,PASSWORD TEXT, SPECIALIZATION TEXT, Average_Appt_Rate TEXT,FEE TEXT,DESCRIPTION TEXT,CLINIC_NAME TEXT,CLINIC_ADDRESS TEXT, CONTACT TEXT );");

        sqLiteDatabase.execSQL("CREATE TABLE " + ApptTABLE_NAME + "(APPT_ID INTEGER PRIMARY KEY AUTOINCREMENT , PATIENT_ACCOUNT_ID INTEGER, DOCTOR_ID INTEGER ,DATE TEXT,TIME TEXT, STATUS TEXT, FOREIGN KEY (PATIENT_ACCOUNT_ID) REFERENCES PATIENTS_ACCOUNT(PATIENT_ACCOUNT_ID)FOREIGN KEY (DOCTOR_ID) REFERENCES DOCTORS_ACCOUNT(DOCTOR_ID) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PATIENTS_ACCOUNT;");
        onCreate(sqLiteDatabase);
    }

    public boolean insert_PATIENTS_ACCOUNT(String firstname, String lastname, String email, String password, String contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_2, firstname);
        contentValues.put(PCOL_3, lastname);
        contentValues.put(PCOL_4, email);
        contentValues.put(PCOL_5, password);
        contentValues.put(PCOL_6, contact);

        long result = db.insert(PTABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else
            return true;

        // this.getWritableDatabase().insertOrThrow("PATIENTS_ACCOUNT", "", contentValues);
        // Log.i("TAG","Inserted");
    }

    public List<String> FetchDoctors(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> labels = new ArrayList<String>();

        String query="SELECT * FROM "+DTABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;

    }
    public String getDocID(String name){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT *  FROM "+DTABLE_NAME+" WHERE DOCTOR_NAME = '"+name+"'";
        Cursor cursor= db.rawQuery(query,null);
        String docID="";
        if (cursor.moveToFirst()) {
             docID=cursor.getString(0);
        }

        return docID;
 }
    public String getPatientID(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT *  FROM "+PTABLE_NAME+" WHERE EMAIL = '"+email+"'";
        Cursor cursor= db.rawQuery(query,null);
        String PatientID="";
        if (cursor.moveToFirst()) {
            PatientID=cursor.getString(0);
        }

        return PatientID;
    }
    public Cursor getAppData(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="SELECT * FROM"+ApptTABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;

    }

    public boolean BOOKING_APPOINTMENT(String patientid, String docid, String date, String time) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ApptCOL_4, patientid);
        contentValues.put(ApptCOL_6, docid);
        contentValues.put(ApptCOL_7, date);
        contentValues.put(ApptCOL_8, time);


        long result = db.insert(ApptTABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else
            return true;

        // this.getWritableDatabase().insertOrThrow("PATIENTS_ACCOUNT", "", contentValues);
        // Log.i("TAG","Inserted");
    }


    /* code==009 in LogINActivity
    public boolean LOGIN(String email, String password) {
        Cursor cursor;
        String q="SELECT * FROM " + TABLE_NAME;
        String Query="SELECT * FROM " + TABLE_NAME + " WHERE " + PCOL_3 + " = " +"'"+email+"'" + PCOL_4 + " = " + "'"+password+"'";
            // db = this.getReadableDatabase();
             cursor = db.rawQuery(Query, null);
             cursor.moveToFirst();
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }

 }*/

    public String searchpass(String email) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PTABLE_NAME ;
        Cursor cursor = db.rawQuery(query, null);
        String userid, password, result = null;
        password = null;
        if(cursor.getCount()==0){
            result="Some Data";
        }
        if (cursor.moveToFirst()) {
            do {
                userid = cursor.getString(3);
                result=userid;

                if (email.equals(userid.toString())) {
                    password = cursor.getString(4);
                    //result=password;
                    break;
                }

            } while (cursor.moveToNext());

        }

        return password;
    }
}
