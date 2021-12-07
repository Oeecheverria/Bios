package com.example.fragmentsmaestrodetalle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetalleEmpleadoActivity extends AppCompatActivity {

    protected DetalleEmpleadoFragment fragDetalleEmpleado;
    private Empleado empleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);
        fragDetalleEmpleado = (DetalleEmpleadoFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetalleEmpleado);

        empleado = (Empleado)getIntent().getSerializableExtra(MainActivity.EXTRA_EMPLEADO);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragDetalleEmpleado.MostrarEmpleado(empleado);
    }
}