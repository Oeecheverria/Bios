package com.example.fragmentsmaestrodetalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetalleEmpleadoFragment extends Fragment {

    public static DetalleEmpleadoFragment newInstance(){
        return new DetalleEmpleadoFragment();
    }

    protected TextView tvCedula;
    protected TextView tvNombreCompleto;
    protected TextView tvDescripcion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_empleado, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvCedula = getView().findViewById(R.id.tvCedula);
        tvNombreCompleto = getView().findViewById(R.id.tvNombre);
        tvDescripcion = getView().findViewById(R.id.tvDescripcion);
    }

    public void MostrarEmpleado(Empleado empleado)
    {
        tvCedula.setText(String.valueOf(empleado.getCedula()));
        tvNombreCompleto.setText(empleado.getNombreCompleto());
        tvDescripcion.setText(empleado.getDescripcion());
    }
}
