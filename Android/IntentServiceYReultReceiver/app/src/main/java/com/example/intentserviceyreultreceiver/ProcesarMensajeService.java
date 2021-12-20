package com.example.intentserviceyreultreceiver;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class ProcesarMensajeService extends IntentService {

    public static final String MIS_LOGS = "MIS_LOGS";
    public static final String CODIGO_RESULTADO_OK = "CODIGO_RESULTADO_OK";
    public static final String RESULTADO_MENSAJE_PROCESADO ="RESULTADO_MENSAJE_PROCESADO";

    public ProcesarMensajeService() {
        super(ProcesarMensajeService.class.getSimpleName());

    }

    public ProcesarMensajeService(String nombre){
        super(nombre);


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(MIS_LOGS,"onhandeintent corre ne el hilo : "+ Thread.currentThread().getId());

        String mensaje = intent.getStringExtra(MainActivity.EXTRA_MENSAJE);
        ResultReceiver mensajeProcesadoReceiver = Intent.getParcelableExtra(MainActivity.EXTRA_MENSAJE_PROCESADO_RECEIVER);

        StringBuilder mensajeProcesado = new StringBuilder();
        for(int i = mensaje.length() - 1; i >= 0; i--){
            SystemClock.sleep(500);

            mensajeProcesado.append(mensaje.charAt(i));
        }

        Log.i(MIS_LOGS,"Mensaje Procesado : " + mensajeProcesado.toString());

        Bundle datosResultado = new Bundle();
        datosResultado.putString(RESULTADO_MENSAJE_PROCESADO,mensajeProcesado.toString());

        mensajeProcesadoReceiver.send(CODIGO_RESULTADO_OK,datosResultado);



    }
}
