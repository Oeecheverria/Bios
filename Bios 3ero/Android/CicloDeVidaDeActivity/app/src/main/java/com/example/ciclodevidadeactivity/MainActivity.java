package com.example.ciclodevidadeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_LOGS = "MIS_LOGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    //    Log.i(MIS_LOGS,  "los logs del nivel (v,d,i,w,e),una etiqueta de mensaje.)");
        Log.d(MIS_LOGS, "se ejecuto el mtodo onCreate");
    }


    protected void onPause() {
        super.onPause();
        Log.d(MIS_LOGS, "se ejecuto el mtodo onPause");
    }
    protected void onStop() {
        super.onStop();
        Log.d(MIS_LOGS, "se ejecuto el mtodo onStop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(MIS_LOGS, "se ejecuto el mtodo onRestart");
    }c
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MIS_LOGS, "se ejecuto el mtodo onDestroy");
    }
}