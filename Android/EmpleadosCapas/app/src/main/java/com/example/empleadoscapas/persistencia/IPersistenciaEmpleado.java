package com.example.empleadoscapas.persistencia;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IPersistenciaEmpleado {

    List<DTEmpleado> listar() throws ExcepcionPersonalizada;
    DTEmpleado obtener(int cedula) throws ExcepcionPersonalizada;
    void agregar(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void modificar(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void eliminar(int cedula) throws ExcepcionPersonalizada;

}
