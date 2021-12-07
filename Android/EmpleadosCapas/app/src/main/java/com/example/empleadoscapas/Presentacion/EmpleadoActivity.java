package com.example.empleadoscapas.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;
import com.example.empleadoscapas.Logica.FabricaLogica;
import com.example.empleadoscapas.R;

import java.util.List;

public class EmpleadoActivity extends AppCompatActivity {

    protected ListView lvEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        lvEmpleados = (ListView) findViewById(R.id.lvEmpleados);

        lvEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvEmpleadsOnItemClick(parent, view, position, id);
            }

            private void lvEmpleadsOnItemClick(AdapterView<?> parent, View view, int position, long id) {

                DTEmpleado empleado = (DTEmpleado) parent.getItemAtPosition(position);
                Intent intencionEDitarEmpleado = new Intent(getApplicationContext(),EditarEmpleadoActivity.class);
                intencionEDitarEmpleado.putExtra(Constantes.EXTRA_EMPLEADO, empleado);

                startActivity(intencionEDitarEmpleado);
            }
        });

    }

    public void fabAgregarOnClick(View view) {

        Intent intencionEditarEmpleado = new Intent(getApplicationContext(), EditarEmpleadoActivity.class);
        startActivity(intencionEditarEmpleado);
    }

    protected void mostrarEmpleados() {
        try {
            List<DTEmpleado> empleados = FabricaLogica.getControladorMantenimientoEmpleado(getApplicationContext()).listarEmpleados();
            EmpleadosAdapter adaptadorEmpleados = new EmpleadosAdapter(this, empleados);
            lvEmpleados.setAdapter(adaptadorEmpleados);

        } catch (ExcepcionPersonalizada ex) {
            Toast.makeText(this, "No se pudo listar los empleados", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mostrarEmpleados();
    }
}
