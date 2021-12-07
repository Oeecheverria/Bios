package com.example.empleadoscapas.logica;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IControladorMantenimientoEmpleados {

    List<DTSucursal> listarSucursales() throws ExcepcionPersonalizada;
    List<DTEmpleado> listarEmpleados() throws ExcepcionPersonalizada;
    void agregarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void modificarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void eliminarEmpleado(int cedula) throws ExcepcionPersonalizada;

}
