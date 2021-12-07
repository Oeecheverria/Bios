package com.example.empleadoscapas.Persistencia;

import android.content.Context;

public class FabricaPersistencia {

    public static IPersistenciaEmpleado getPersistenciaEmpleado(Context contexto){
        return PersistenciaEmpleado.getInstancia(contexto);
    }

}
