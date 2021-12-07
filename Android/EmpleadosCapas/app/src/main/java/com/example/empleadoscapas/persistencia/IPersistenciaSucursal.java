package com.example.empleadoscapas.persistencia;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IPersistenciaSucursal {

    List<DTSucursal> listar() throws ExcepcionPersonalizada;
    DTSucursal obtener(long id) throws ExcepcionPersonalizada;
    void agregar(DTSucursal sucursal) throws ExcepcionPersonalizada;
    void modificar(DTSucursal sucursal) throws ExcepcionPersonalizada;
    void eliminar(long id) throws ExcepcionPersonalizada;

}
