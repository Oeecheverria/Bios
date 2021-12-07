package com.example.empleadoscapas.logica;

import android.content.Context;

import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;
import com.example.empleadoscapas.persistencia.FabricaPersistencia;

import java.util.List;

public class ControladorMantenimientoSucursales implements IControladorMantenimientoSucursales {

    private static ControladorMantenimientoSucursales instancia;


    public static ControladorMantenimientoSucursales getInstancia(Context contexto) {
        if (instancia == null) {
            instancia = new ControladorMantenimientoSucursales(contexto);
        }

        return instancia;
    }


    private Context contexto;


    private ControladorMantenimientoSucursales(Context contexto) {
        this.contexto = contexto.getApplicationContext();
    }


    @Override
    public List<DTSucursal> listarSucursales()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaSucursal(contexto).listar();
    }

    @Override
    public void agregarSucursal(DTSucursal sucursal)
            throws ExcepcionPersonalizada {
        LogicaSucursal.getInstancia().validar(sucursal);

        FabricaPersistencia.getPersistenciaSucursal(contexto).agregar(sucursal);
    }

    @Override
    public void modificarSucursal(DTSucursal sucursal)
            throws ExcepcionPersonalizada {
        LogicaSucursal.getInstancia().validar(sucursal);

        FabricaPersistencia.getPersistenciaSucursal(contexto).modificar(sucursal);
    }

    @Override
    public void eliminarSucursal(long id)
            throws ExcepcionPersonalizada {
        FabricaPersistencia.getPersistenciaSucursal(contexto).eliminar(id);
    }

}
