package com.example.intercambiarfagments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BFragment extends Fragment {

    public  BFragment newInstance(String mensaje) {
        if (mensaje == null || mensaje.isEmpty()) {
            mensaje = "Fragmento A";
        }

        Bundle argumentos = new Bundle();
        argumentos.putString("mensaje", mensaje);

        BFragment fragmento = new BFragment();
        fragmento.setArguments(argumentos);

        return fragmento;
    }

    protected TextView tvMensaje;

    protected String mensaje;

    // El constructor por defecto siempre debe estar, el IDE lo crea igual pero recordar que siempre debe estar

    public BFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mensaje = getArguments().getString("mensaje");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvMensaje = getView().findViewById(R.id.tvMensaje);

        tvMensaje.setText(mensaje);

    }
}
