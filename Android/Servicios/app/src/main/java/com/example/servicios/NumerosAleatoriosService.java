package com.example.servicios;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Random;

public class NumerosAleatoriosService extends Service {

    public static final String MIS_LOGS = "MIS_LOGS";

    public static final String EXTRA_CANTIDAD_NUMEROS = "EXTRA_CANTIDAD_NUMEROS";

    public static final int CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION = 1;
    public static final int ID_NOTIFICACION = 1;


    private Thread hiloGeneracionNumeros;

    private IBinder numerosAleatoriosBinder;

    private int numeroActual;


    public int getNumeroActual() {
        return numeroActual;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        numerosAleatoriosBinder = new NumerosAleatoriosBinder();

        Log.i(MIS_LOGS, "Se creó el servicio de números aleatorios.");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(MIS_LOGS, "Se enlazó con el servicio de números aleatorios.");

        return numerosAleatoriosBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(MIS_LOGS, "Se inició el servicio de números aleatorios.");
        Log.i(MIS_LOGS, "Por defecto, el servicio corre en el hilo: " + Thread.currentThread().getId());

        hiloGeneracionNumeros = new Thread(new Runnable() {

            @Override
            public void run() {
                int cantidadNumeros = 5;

                if (intent != null) {
                    cantidadNumeros = intent.getIntExtra(EXTRA_CANTIDAD_NUMEROS, cantidadNumeros);
                }

                if (cantidadNumeros > 5) {
                    Intent intencionMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent intencionContenido = PendingIntent.getActivity(getApplicationContext(),
                            CODIGO_SOLICITUD_INTENCION_CONTENIDO_NOTIFICACION, intencionMainActivity,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    Notification notificacion = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(android.R.drawable.ic_media_play)
                            .setContentTitle("Servicio Números Aleatorios")
                            .setContentText("El servicio se está ejecutando...")
                            .setContentIntent(intencionContenido)
                            .setAutoCancel(true).build();
                    notificacion.flags = notificacion.flags | Notification.FLAG_ONGOING_EVENT;

                    startForeground(ID_NOTIFICACION, notificacion);
                }

                Random azar = new Random();

                for (int i = 1; i <= cantidadNumeros && !hiloGeneracionNumeros.isInterrupted(); i++) {
                    SystemClock.sleep(1000);

                    numeroActual = azar.nextInt(1000);
                    Log.i(MIS_LOGS, "Hilo: " + Thread.currentThread().getId() + " - Número actual: " + numeroActual);
                }

                //stopSelf();
                stopSelf(startId);
            }

        });

        hiloGeneracionNumeros.start();

        //return START_NOT_STICKY;
        return START_STICKY;
        //return START_REDELIVER_INTENT;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(MIS_LOGS, "Se desenlazó del servicio de números aleatorios.");

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (hiloGeneracionNumeros != null) {
            hiloGeneracionNumeros.interrupt();
        }

        Log.i(MIS_LOGS, "Se destruyó el servicio de números aleatorios.");
    }


    public class NumerosAleatoriosBinder extends Binder {

        public NumerosAleatoriosService getServicio() {
            return NumerosAleatoriosService.this;
        }

    }

}
