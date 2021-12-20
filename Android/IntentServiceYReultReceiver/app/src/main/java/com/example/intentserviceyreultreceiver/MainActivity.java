package com.example.intentserviceyreultreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements MensajeProcesadoReceiver.MensajeProcesadoListener {

    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE";
    public static final String EXTRA_MENSAJE_PROCESADO_RECEIVER = "EXTRA_MENSAJE_PROCESADO_RECEIVER";


    protected TextView tvMenasaje;

    private String mensajePredeterminado;

    private MensajeProcesadoReceiver mensajeProcesadoReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tvMenasaje = (TextView)findViewById(R.id.tvMensaje);
      //  mensajePredeterminado = getResources().getString(R.string.tvMensaje);
        mensajePredeterminado = getResources().getString(R.string.tvMensaje);

    mensajeProcesadoReceiver = new MensajeProcesadoReceiver(new Handler());
    mensajeProcesadoReceiver.setMensajeProcesadoListener(this);

    }



    public void btnRestaurarOnClick(View view) {
        tvMenasaje.setText(mensajePredeterminado);
    }

    public void btnProcesarOnClick(View view) {
        Intent intentcionServicioProcesarMensaje =  new Intent(getApplicationContext(),ProcesarMensajeService.class);
        intentcionServicioProcesarMensaje.putExtra(EXTRA_MENSAJE, mensajePredeterminado);
        intentcionServicioProcesarMensaje.putExtra(EXTRA_MENSAJE_PROCESADO_RECEIVER, mensajeProcesadoReceiver);

        startService(intentcionServicioProcesarMensaje);

    }

    @Override
    public void alRecibirResultado(int codigoResultado, Bundle datosResultado) {
        if(codigoResultado == ProcesarMensajeService.CODIGO_RESULTADO_OK){


            tvMenasaje.setText(datosResultado.getString(ProcesarMensajeService.RESULTADO_MENSAJE_PROCESADO));

        }

    }
}