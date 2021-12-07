package com.example.almacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_LOGS = "MIS_LOGS";
    public static final String ARCHIVO_LOG = "log.txt";
    public static final String DIRECTORIO_MIS_ARCHIVOS = "mis_archivos";
    public static final String ARCHIVO_SALUDO = "saludo.txt";

    protected LinearLayout llLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llLog = findViewById(R.id.llLog);
        FileOutputStream fos = null;
        PrintStream ps = null;

        MostrarLog();
        try {
            fos = openFileOutput(ARCHIVO_LOG, MODE_PRIVATE | MODE_APPEND);

            ps = new PrintStream(fos);

            ps.println(new Date());

            ps.close();
            fos.close();

            File d = getDir(DIRECTORIO_MIS_ARCHIVOS, MODE_PRIVATE);
            File f = new File(d, ARCHIVO_SALUDO);
            fos = new FileOutputStream(f, false);
            ps = new PrintStream(fos);
            ps.println("Hola Mundo");

        } catch (IOException ex) {
            Log.e(MIS_LOGS, "No se pudo escribir en el almacenamiento interno");
        } finally {
            try {
                if (ps != null) ps.close();
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Log.e(MIS_LOGS, "Ocurrio un error al cerrar los recursos");

            }

        }
    }

    public void btnMostrarLogOnClick(View view) {
        MostrarLog();
    }

    public void btnBorrarLogOnClick(View view) {
        deleteFile(ARCHIVO_LOG);

        llLog.removeAllViews();
    }

    public void btnListarArchivosOnClick(View view) {
        String[]archivos = fileList();
        TextView tvLinea;

        llLog.removeAllViews();

        for (String a: archivos){
            tvLinea = new TextView(this);
            tvLinea.setText(a);
            llLog.addView(tvLinea);
        }
    }

    protected void MostrarLog() {
        FileInputStream fis = null;
        BufferedReader br = null;
        TextView tvLinea;

        llLog.removeAllViews();
        try {
            fis = openFileInput(ARCHIVO_LOG);
            br = new BufferedReader(new InputStreamReader(fis));

            String linea;

            while ((linea = br.readLine()) != null) {
                tvLinea = new TextView(this);
                tvLinea.setText(linea);

                llLog.addView(tvLinea);
            }

        } catch (IOException ex) {
            tvLinea = new TextView(this);
            tvLinea.setText("El archivo de log no existe");

            llLog.addView(tvLinea);


        } finally {
            try {
                if (br != null) br.close();
                if (fis != null) fis.close();
            } catch (IOException ex) {
                Log.e(MIS_LOGS, "Ocurrio un error al cerrar los recursos");

            }

        }
    }
}