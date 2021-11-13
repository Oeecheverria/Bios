package com.example.recibirbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MENSAJE_LOCAL = "EXTRA_MENSAJE_LOCAL";
    public static final String ACCION_MENSAJE_LOCAL_ENVIADO = "ACCION_MENSAJE_LOCAL_ENVIADO";



    protected TextView tvMensaje;
    protected Button btnEnviarMensajeLocal;

    private LocalBroadcastManager gestorMensajesLocales;

    private MensajesLocalesReceiver mensajesLocalesReceiver;
    private IntentFilter filtroAccionMensajesLocalesReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMensaje = (TextView)findViewById(R.id.tvMensaje);
        btnEnviarMensajeLocal = (Button)findViewById(R.id.btnEnviarMensajeLocal);


        String mensaje = getIntent().getStringExtra(MensajesReceiver.EXTRA_MENSAJE);

        if (mensaje == null){
            mensaje = "no se ha recibido ningun mensaje";
        }

        tvMensaje.setText(mensaje);

        gestorMensajesLocales = LocalBroadcastManager.getInstance(this);

        mensajesLocalesReceiver = new MensajesLocalesReceiver();
        filtroAccionMensajesLocalesReceiver = new IntentFilter(ACCION_MENSAJE_LOCAL_ENVIADO);

    }
    @Override
    protected void  onResume() {

        super.onResume();
        gestorMensajesLocales.registerReceiver((mensajesLocalesReceiver, filtroAccionMensajesLocalesReceiver);

    }
    @Override
    protected void  onPause() {

    super.onPause();

    gestorMensajesLocales.unregisterReceiver(mensajesLocalesReceiver);

    }







    public void btnEnviarMensajeLocal(View view) {
        Intent intencionMensajeLocal = new Intent(ACCION_MENSAJE_LOCAL_ENVIADO);
        intencionMensajeLocal.putExtra(EXTRA_MENSAJE_LOCAL, "hola este es el menaje local");

        gestorMensajesLocales.sendBroadcast(intencionMensajeLocal);

    }

    public class MensajesLocalesReceiver extends BroadcastReceiver {



        @Override
        public void onReceive(Context context, Intent intent) {

            String mensaje = intent.getStringExtra(EXTRA_MENSAJE_LOCAL);
            tvMensaje.setText(mensaje);

        }
    }

}