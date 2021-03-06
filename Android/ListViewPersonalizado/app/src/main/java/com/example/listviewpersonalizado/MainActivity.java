package com.example.listviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected ListView lvEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvEmpleados = (ListView)findViewById(R.id.lvEmpleados);
        lvEmpleados.addHeaderView(cabezalEmplados);

        View cabelzalEmpleados = getLayoutInflater().inflate(R.layout.listitem_empleado,null);

        List<Empleado> empleados = new ArrayList<Empleado>();
        empleados.add(new Empleado(11111111, "Juan Pérez"));
        empleados.add(new Empleado(22222222, "Laura Aguirre"));
        empleados.add(new Empleado(33333333, "Roberto Gómez"));
        empleados.add(new Empleado(44444444, "Ana Fernández"));
        empleados.add(new Empleado(55555555, "Leonardo Sosa"));
        empleados.add(new Empleado(66666666, "Pablo Alonso"));
        empleados.add(new Empleado(77777777, "Graciela Santos"));
        empleados.add(new Empleado(88888888, "Nicolás García"));
        empleados.add(new Empleado(99999999, "Angélica Domínguez"));
        empleados.add(new Empleado(10101010, "Estela López"));
        empleados.add(new Empleado(11111110, "Mauricio Pintos"));


        AdaptadorEmpleados adaptadorEmpleados = new AdaptadorEmpleados(this,empleados);
       lvEmpleados.setOnItemClickListener((new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id)
           {
               lvEmpleadosOnItemClick(parent,view,position,id);
           }

           private void lvEmpleadosOnItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(this,"emplado seleccionado :  " +
                       ((Empleado)parent.getItemAtPosition(position)).getNombreCompleto(),Toast.LENGTH_SHORT.show();

           }
       }));



    }
}