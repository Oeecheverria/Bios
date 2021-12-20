package com.example.intentserviceyreultreceiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class MensajeProcesadoReceiver  extends ResultReceiver {
    private MensajeProcesadoListener mensajeProcesadoListener;

    public void setMensajeProcesadoListener(MensajeProcesadoListener mensajeProcesadoListener){
        this.mensajeProcesadoListener = mensajeProcesadoListener;
    }





    public MensajeProcesadoReceiver(Handler gestor){
        super(gestor);
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
     if(MensajeProcesadoListener != null){
         mensajeProcesadoListener.alRecibirResultado(resultCode,resultData);

     }


    }




    public interface MensajeProcesadoListener {
        void alRecibirResultado(int codigoResultado, Bundle datosResultado);
    }
}
