package com.example.fragmentsmaestrodetalle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListadoEmpleadosFragment.OnEmpleadoSeleccionadoListener {

    public static final String EXTRA_EMPLEADO = "EXTRA_EMPLEADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onEmpleadoSeleccionado(Empleado empleado) {
        DetalleEmpleadoFragment fragDetalleEmpleado = (DetalleEmpleadoFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetalleEmpleado);

        if(fragDetalleEmpleado != null)
        {
fragDetalleEmpleado.MostrarEmpleado(empleado);
        }
        else
        {
            Intent intencionDetalleEmpleado = new Intent(this, DetalleEmpleadoActivity.class);
            intencionDetalleEmpleado.putExtra(EXTRA_EMPLEADO, empleado);
            startActivity(intencionDetalleEmpleado);
        }
    }
}