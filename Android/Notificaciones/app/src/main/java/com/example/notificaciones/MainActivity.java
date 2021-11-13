package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION = 1;
    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_ACCION = 1;
    public static final int ID_NOTIFICACION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnCrearNotificacionOnClick(View view) {

        Intent intencionOtraActivity = new Intent(getApplicationContext(), OtraActivity.class);
        PendingIntent intencionContenido = PendingIntent.getActivity(getApplicationContext(), CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION, intencionOtraActivity, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder constructorNotificacion = new NotificationCompat.Builder(getApplicationContext());

        constructorNotificacion.setSmallIcon(android.R.drawable.ic_dialog_alert)

    }
}