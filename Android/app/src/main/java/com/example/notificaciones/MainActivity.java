package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION =1;
    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_ACCION =1;
    public static final int ID_NOTIFICACION =1;
    private static int notifiacionesCreadas =0;

    public static void ResetearNotifiacionesCreadas(){
        notifiacionesCreadas =0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCrearNotificacionOnClick(View view) {

        Intent intencionOtraActivity = new Intent(getApplicationContext(), OtraActivity.class);

        PendingIntent intencionContenido = PendingIntent.getActivity(getApplicationContext(), CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION, intencionOtraActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(getApplicationContext(),"");

        constructorNotificacion
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setTicker("Kikiriki")
                .setLargeIcon((((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round)).getBitmap()))
                .setContentTitle("Ejemplo de Notificaciones")
                .setContentText("Has recibido una notificacion")
                .setContentInfo("x " + ++notifiacionesCreadas)
                .setContentIntent(intencionContenido)
                .setLights(Color.RED, 500, 2000)
                .setSound(Uri.parse("android.resource://com.example.notificaciones/" + R.raw.gallo))
                .setVibrate(new long[]{0, 200, 300, 300})
                .setColor(Color.RED)
                .setPriority(Notification.PRIORITY_HIGH);
               // .setAutoCancel(true);

        Bitmap imagenGallo = BitmapFactory.decodeResource(this.getResources(), R.drawable.gallos);
        NotificationCompat.BigPictureStyle estiloImagenGrande = new NotificationCompat.BigPictureStyle();
        estiloImagenGrande.bigPicture(imagenGallo);
        estiloImagenGrande.setSummaryText("Has recibido una notificacion");

        constructorNotificacion.setStyle(estiloImagenGrande);

        Intent intencionIrABios = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        PendingIntent intencionAcercaDe = PendingIntent.getActivity(getApplicationContext(),
                CODIGO_SOLICITUD_INTENCION_CONTENIDO_ACCION, intencionIrABios, 0);

        NotificationCompat.Action.Builder constructorAcercaDe = new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_info_details, "Acerca de... ", intencionAcercaDe);

        constructorNotificacion.addAction(constructorAcercaDe.build());



        NotificationManager gestorNotificaciones = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        gestorNotificaciones.notify(ID_NOTIFICACION,constructorNotificacion.build());

    }

}