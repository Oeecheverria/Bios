package com.example.recibirbroadcast;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OtroMensajeReceiver extends BroadcastReceiver {
    public static final String EXTRA_MENSAJE = "EXTRA_MENSAJE";
    public static final String MIS_LOGS ="MIS_LOGS";
    @Override
    public void onReceive(Context context, Intent intent) {


        String mensaje = intent.getStringExtra(EXTRA_MENSAJE);




        Log.i(MIS_LOGS,"menajerecibido" + mensaje);

        if (mensaje.contains("(oculto)")){
            abortBroadcast();
        }else
        {
            setResultData("procesado");
        }
    }
}
