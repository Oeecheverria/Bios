package com.example.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_PREFERENCIAS = "MIS_PREFERENCIAS";
    public static final String PREFERENCIA_PUNTAJE_RECORD = "PREFERENCIA_PUNTAJE_RECORD";
    public static final String ESTADO_ETIQUETA_PUNTAJE_OBTENIDO = "ESTADO_ETIQUETA_PUNTAJE_OBTENIDO";

    protected TextView tvPuntajeRecord;
    protected TextView tvPuntajeObtenido;

    protected SharedPreferences preferencias;

    private int puntajeRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPuntajeRecord = findViewById(R.id.tvPuntajeRecord);
        tvPuntajeObtenido = findViewById(R.id.tvPuntajeObtenido);

        //preferencias = getSharedPreferences(MIS_PREFERENCIAS, MODE_PRIVATE);
        preferencias = getPreferences(MODE_PRIVATE);

        puntajeRecord = preferencias.getInt(PREFERENCIA_PUNTAJE_RECORD, 0);

        mostrarPuntajeRecord();

//        if (savedInstanceState != null){
//            tvPuntajeObtenido.setText(savedInstanceState.getString(ESTADO_ETIQUETA_PUNTAJE_OBTENIDO));
//        }
    }

    protected void mostrarPuntajeRecord() {
        tvPuntajeRecord.setText("Puntaje record: " + puntajeRecord);

    }

    public void btnReiniciarOnClick(View view) {
        puntajeRecord = 0;
        guardarPuntajeRecord();
        mostrarPuntajeRecord();
    }

    public void btnJugarOnclick(View view) {

        int puntajeObtenido = new Random().nextInt(1000);
        tvPuntajeObtenido.setText("Puntaje obtenido: " + puntajeObtenido);
        if (puntajeObtenido > puntajeRecord)
            puntajeRecord = puntajeObtenido;

        mostrarPuntajeRecord();

        guardarPuntajeRecord();

    }

    protected void guardarPuntajeRecord() {
        SharedPreferences.Editor editorPreferencias = preferencias.edit();
        editorPreferencias.putInt(PREFERENCIA_PUNTAJE_RECORD, puntajeRecord);
        editorPreferencias.commit();// Sincronico
        editorPreferencias.apply();// Asincronico

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String puntajeObtenido = tvPuntajeObtenido.getText().toString();

        if (puntajeObtenido.length() > 0) {
            outState.putString(ESTADO_ETIQUETA_PUNTAJE_OBTENIDO, puntajeObtenido);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvPuntajeObtenido.setText(savedInstanceState.getString(ESTADO_ETIQUETA_PUNTAJE_OBTENIDO));

    }
}