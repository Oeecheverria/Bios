package com.example.intense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DefinirMensajeActivity extends AppCompatActivity {

    protected TextView tvMensaje;
    protected Button btnDefinirMensaje;
    protected Button BtnIraBios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definir_mensaje);
        tvMensaje = (TextView)findViewById(R.id.tvMensaje);

        btnDefinirMensaje = (Button)findViewById(R.id.btnDefinirMensaje);
        BtnIraBios =(Button)findViewById(R.id.BtnIraBios);


        btnDefinirMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDefinirMensajeOnClick(v);
            }
        });

     protected void btnDefinirMensajeOnClick(View v){
            Toast.makeText(this,"hola mundo", Toast.LENGTH_SHORT).show();




        }

    }






}