package com.example.empleadoscapas.Logica;

import android.content.Context;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;
import com.example.empleadoscapas.Persistencia.FabricaPersistencia;

import java.security.PrivateKey;
import java.util.List;

class ControladorMantenimientoEmpleado implements IControladorMantenimientoEmpleado {


    private static ControladorMantenimientoEmpleado instancia;

    public static ControladorMantenimientoEmpleado getInstancia(Context contexto) {
        if (instancia == null)
            instancia = new ControladorMantenimientoEmpleado(contexto);
        return instancia;
    }

    private Context contexto;

    private ControladorMantenimientoEmpleado(Context contexto) {
        this.contexto = contexto.getApplicationContext();
    }

    @Override
    public List<DTEmpleado> listarEmpleados() throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaEmpleado(contexto).listar();
    }

    @Override
    public void agregarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada {
        LogicaEmpleado.getInstancia().validarEmpleado(empleado);

        FabricaPersistencia.getPersistenciaEmpleado(contexto).agregar(empleado);
    }

    @Override
    public void modificarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada {
        LogicaEmpleado.getInstancia().validarEmpleado(empleado);

        FabricaPersistencia.getPersistenciaEmpleado(contexto).modificar(empleado);

    }

    @Override
    public void eliminarEmpleado(int cedula) throws ExcepcionPersonalizada {

        FabricaPersistencia.getPersistenciaEmpleado(contexto).eliminar(cedula);
    }

}
