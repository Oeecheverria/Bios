package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_LOGS = "MIS_LOGS";

    private ConexionServicioNumerosAleatorios conexionServicioNumerosAleatorios;
    private NumerosAleatoriosService numerosAleatoriosService;
    private boolean enlazadoServicioNumerosAleatorios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i(MIS_LOGS, "La actividad corre en el hilo: " + Thread.currentThread().getId());
    }

    public void btnIniciarServicioOnClick(View view) {
        Intent intencioNumerosAleatorios = new Intent(getApplicationContext(), NumerosAleatoriosService.class);
        intencioNumerosAleatorios.putExtra(NumerosAleatoriosService.EXTRA_CANTIDAD_NUMEROS, 1000);

        startService(intencioNumerosAleatorios);
    }

    public void btnDetenerServicioOnClick(View view) {
        Intent intencioNumerosAleatorios = new Intent(getApplicationContext(), NumerosAleatoriosService.class);

        stopService(intencioNumerosAleatorios);
    }

    public void btnEnlazarServicioOnClick(View view) {
        if (conexionServicioNumerosAleatorios == null) {
            conexionServicioNumerosAleatorios = new ConexionServicioNumerosAleatorios();
        }

        Intent intencionServicioNumerosAleatorios = new Intent(getApplicationContext(), NumerosAleatoriosService.class);

        bindService(intencionServicioNumerosAleatorios, conexionServicioNumerosAleatorios, BIND_AUTO_CREATE);
    }

    public void btnDesenlazarServicioOnClick(View view) {
        if (enlazadoServicioNumerosAleatorios) {
            unbindService(conexionServicioNumerosAleatorios);
            enlazadoServicioNumerosAleatorios = false;
        }
    }

    public void btnObtenerNumeroActualOnClick(View view) {
        if (enlazadoServicioNumerosAleatorios) {
            Toast.makeText(this, String.valueOf(numerosAleatoriosService.getNumeroActual()), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "¡No enlazado al servicio!", Toast.LENGTH_SHORT).show();
        }
    }


    private class ConexionServicioNumerosAleatorios implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            numerosAleatoriosService = ((NumerosAleatoriosService.NumerosAleatoriosBinder)service).getServicio();
            enlazadoServicioNumerosAleatorios = true;

            Log.i(MIS_LOGS, "Se conectó con el servicio.");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            numerosAleatoriosService = null;
            enlazadoServicioNumerosAleatorios = false;

            Log.i(MIS_LOGS, "Se desconectó del servicio.");
        }

    }

}