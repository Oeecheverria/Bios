package com.example.obligatorioandroid.Persistencia;

import android.content.Context;

import com.example.obligatorioandroid.Persistencia.Interfaces.IPersistenciaCliente;

public class FabricaPersistencia {

    public static IPersistenciaCliente getPersistenciaCliente(Context contexto) {
        return PersistenciaCliente.getInstancia(contexto);
    }
}
