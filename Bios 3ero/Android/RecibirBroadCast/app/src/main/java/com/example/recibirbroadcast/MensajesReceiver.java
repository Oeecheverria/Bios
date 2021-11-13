package com.example.recibirbroadcast;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;


public class MensajesReceiver extends BroadcastReceiver {



    public static final String EXTRA_MENSAJE ="EXTRA_MENSAJE";
    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION = 1;
    public static final int ID_NOTIFICACION = 1;
    @Override
    public void onReceive(Context context, Intent intent) {
        String mensaje = intent.getStringExtra(EXTRA_MENSAJE);

        if(getResultData() !=null){
            mensaje += " (" + getResultData() +") ";
        }


        Intent intencionMainActivity = new Intent(context, MainActivity.class);
        intencionMainActivity.putExtra(EXTRA_MENSAJE, mensaje);

        //context.startActivity(intencionMainActivity);

        mostrarNotificacion(context, android.R.drawable.ic_dialog_alert,"llego un mensaje",
                "hace click en la notifocacion para verlo", intencionMainActivity,true);



    }
    private void mostrarNotificacion(Context contexto, int icono, String titulo, String texto, Intent intencion, boolean cancelarAutomaticamente) {
        PendingIntent intencionContenido = PendingIntent.getActivity(contexto, CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION, intencion, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(contexto);
        constructorNotificacion
                .setSmallIcon(icono)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setContentIntent(intencionContenido)
                .setAutoCancel(cancelarAutomaticamente);

        NotificationManager gestorNotificaciones = (NotificationManager)contexto.getSystemService(Context.NOTIFICATION_SERVICE);
        gestorNotificaciones.notify(ID_NOTIFICACION, constructorNotificacion.build());
    }
}

