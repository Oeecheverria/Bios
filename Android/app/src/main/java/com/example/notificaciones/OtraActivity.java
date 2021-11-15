package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;

public class OtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra);

        NotificationManager gestorNotificaciones = (NotificationManager) getSystemService(NOTIFICATION_SERVICE) ;

        gestorNotificaciones.cancel(MainActivity.ID_NOTIFICACION);

        //gestorNotificaciones.cancelAll();

        MainActivity.ResetearNotifiacionesCreadas();
    }
}