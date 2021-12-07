package com.example.empleadoscapas.Presentacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;
import com.example.empleadoscapas.Logica.FabricaLogica;
import com.example.empleadoscapas.R;

import java.text.DecimalFormat;

public class EditarEmpleadoActivity extends AppCompatActivity {

    protected TextView tvTitulo;
    protected EditText etCedula;
    protected EditText etNombre;
    protected EditText etSueldo;

    protected DTEmpleado empleadoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);

        tvTitulo = findViewById(R.id.tvTitulo);
        etCedula = findViewById(R.id.etCedula);
        etNombre = findViewById(R.id.etNombre);
        etSueldo = findViewById(R.id.etSueldo);

        empleadoSeleccionado = (DTEmpleado) getIntent().getSerializableExtra(Constantes.EXTRA_EMPLEADO);

        if (empleadoSeleccionado != null) {
            tvTitulo.setText("Modficar Empleado");
            etCedula.setText(String.valueOf(empleadoSeleccionado.getCedula()));
            etNombre.setText(empleadoSeleccionado.getNombre());
            etSueldo.setText(new DecimalFormat("#.00").format(empleadoSeleccionado.getSueldo()));

            etCedula.setEnabled(false);

            etNombre.requestFocus();
        }
    }

    public void btnGuardarOnClick(View view) {

        boolean errores = false;

        int cedula = 0;
        try {
            cedula = Integer.parseInt(etCedula.getText().toString());
        } catch (Exception ex) {
            etCedula.setError("Cedula invalida");
            errores = true;
        }
        String nombre = etNombre.getText().toString().trim();

        double sueldo = 0;
        try {
            sueldo = Double.parseDouble(etSueldo.getText().toString());
        } catch (Exception ex) {
            etCedula.setError("Cedula invalida");
            errores = true;
        }

        if (!errores) {
            if (empleadoSeleccionado == null) {
                // Agregando
                agregarEmpleado(cedula, nombre, sueldo);
            } else {
                // Modificando
                modificarEmpleado(cedula, nombre, sueldo);

            }
        }
    }

    protected void agregarEmpleado(int cedula, String nombre, double sueldo){

        try {
            DTEmpleado empleado = new DTEmpleado(cedula,nombre,sueldo);
            FabricaLogica.getControladorMantenimientoEmpleado(getApplicationContext()).agregarEmpleado(empleado);
            Toast.makeText(this,"Empleado agregado con exito",Toast.LENGTH_SHORT).show();
            finish();

        }catch (ExcepcionPersonalizada ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "No se pudo agregar el empleado", Toast.LENGTH_SHORT).show();

        }

    }

    protected void modificarEmpleado(int cedula, String nombre, double sueldo) {

        try {
            DTEmpleado empleado = new DTEmpleado(cedula,nombre,sueldo);
            FabricaLogica.getControladorMantenimientoEmpleado(getApplicationContext()).modificarEmpleado(empleado);
            Toast.makeText(this,"Empleado modificado con exito",Toast.LENGTH_SHORT).show();
            finish();

        }catch (ExcepcionPersonalizada ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "No se pudo modificar el empleado", Toast.LENGTH_SHORT).show();

        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editar_empleado, menu);
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        if (empleadoSeleccionado == null) {
            menu.removeItem(R.id.mniEliminar);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mniInicio:
                Intent intencionInicio = new Intent(getApplicationContext(), MainActivity.class);
                intencionInicio.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intencionInicio);
                finish();
                return true;
            case R.id.mniEliminar:
                try {
                    FabricaLogica.getControladorMantenimientoEmpleado(getApplicationContext()).eliminarEmpleado(empleadoSeleccionado.getCedula());
                    Toast.makeText(this,"Empleado eliminado con exito",Toast.LENGTH_SHORT).show();
                    finish();

                }catch (ExcepcionPersonalizada ex){
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(this, "No se pudo eliminar el empleado", Toast.LENGTH_SHORT).show();

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}