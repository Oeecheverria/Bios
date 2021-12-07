package com.example.empleadoscapas.Logica;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IControladorMantenimientoEmpleado {

    List<DTEmpleado> listarEmpleados() throws ExcepcionPersonalizada;

    void agregarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada;

    void modificarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada;

    void eliminarEmpleado(int cedula) throws ExcepcionPersonalizada;
}
