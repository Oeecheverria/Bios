package com.example.empleadoscapas.logica;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IControladorMantenimientoSucursales {

    List<DTSucursal> listarSucursales() throws ExcepcionPersonalizada;
    void agregarSucursal(DTSucursal sucursal) throws ExcepcionPersonalizada;
    void modificarSucursal(DTSucursal sucursal) throws ExcepcionPersonalizada;
    void eliminarSucursal(long id) throws ExcepcionPersonalizada;

}
