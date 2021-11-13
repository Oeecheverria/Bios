package com.example.enviarbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String ACCION_MENSAJE_ENVIADO = "com.Example.enviarbroadcast.ACCION_MENSAJE_ENVIADO";
    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE";

    protected EditText etMensaje;
    protected Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                btnEnviarOnClick(v);
            }
        });

    }

    protected void btnEnviarOnClick(View v){
        String mensaje = etMensaje.getText().toString();

        Intent intencionBroadcast = new Intent(ACCION_MENSAJE_ENVIADO);
        intencionBroadcast.putExtra(EXTRA_MENSAJE,mensaje);

        intencionBroadcast.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intencionBroadcast.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

        //sendBroadcast(intencionBroadcast);
        sendOrderedBroadcast(intencionBroadcast,null);


    }
}