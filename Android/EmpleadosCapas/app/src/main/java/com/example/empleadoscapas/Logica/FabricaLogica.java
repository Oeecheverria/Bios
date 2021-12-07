package com.example.empleadoscapas.Logica;

import android.content.Context;

public class FabricaLogica {

    public static IControladorMantenimientoEmpleado getControladorMantenimientoEmpleado(Context contexto) {
        return ControladorMantenimientoEmpleado.getInstancia(contexto);
    }
}
