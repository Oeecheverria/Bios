package com.example.empleadoscapas.Persistencia;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IPersistenciaEmpleado {

    List<DTEmpleado> listar() throws ExcepcionPersonalizada;

    DTEmpleado obtener(int cedula) throws ExcepcionPersonalizada;

    void agregar(DTEmpleado empleado) throws ExcepcionPersonalizada;

    void modificar(DTEmpleado empleado)throws ExcepcionPersonalizada;

    void eliminar (int cedula)throws ExcepcionPersonalizada;
}
