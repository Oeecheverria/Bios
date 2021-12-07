package com.example.fragmentsmaestrodetalle;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ListadoEmpleadosFragment extends Fragment {

    public static ListadoEmpleadosFragment newInstance() {
        return new ListadoEmpleadosFragment();
    }

    protected ListView lvEmpleados;

    protected OnEmpleadoSeleccionadoListener onEmpleadoSeleccionadoListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnEmpleadoSeleccionadoListener) {
            onEmpleadoSeleccionadoListener = (OnEmpleadoSeleccionadoListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        onEmpleadoSeleccionadoListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_empleados, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvEmpleados = (ListView) getView().findViewById(R.id.lvEmpleado);

        List<Empleado> empleados = new ArrayList<>();

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
        empleados.add(new Empleado(12121212, "Ximena Castro"));

        ArrayAdapter<Empleado> adaptadorEmpleados = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, empleados);

        lvEmpleados.setAdapter(adaptadorEmpleados);

        lvEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvEmpleadoOnItemClick(parent, view, position, id);
            }
        });

    }

    public void lvEmpleadoOnItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (onEmpleadoSeleccionadoListener != null) {
            onEmpleadoSeleccionadoListener.onEmpleadoSeleccionado((Empleado) parent.getItemAtPosition(position));

        }
    }

    public interface OnEmpleadoSeleccionadoListener {
        void onEmpleadoSeleccionado(Empleado empleado);
    }
}
