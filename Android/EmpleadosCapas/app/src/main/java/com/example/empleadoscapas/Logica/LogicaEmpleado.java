package com.example.empleadoscapas.Logica;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionLogica;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;

class LogicaEmpleado {

    private static LogicaEmpleado instancia;

    public static LogicaEmpleado getInstancia() {
        if (instancia == null)
            instancia = new LogicaEmpleado();
        return instancia;
    }

    private LogicaEmpleado() {
    }

    public void validarEmpleado(DTEmpleado empleado) throws ExcepcionPersonalizada {
        if (empleado == null)
            throw new ExcepcionLogica("El empleado es nulo");
        else if (empleado.getCedula() < 1)
            throw new ExcepcionLogica("La cedula debe ser menor o mayor a 1");
        else if (empleado.getNombre().trim().isEmpty() || empleado.getNombre() == null)
            throw new ExcepcionLogica("El nombre del empleado no puede estar vacio");
        else if (empleado.getNombre().length() > 25)
            throw new ExcepcionLogica("El nombre del empleado debe ser menor a 25 caracteres");
        else if (empleado.getSueldo() < 0)
            throw new ExcepcionLogica("El sueldo debe ser mayor igual a 0");


    }


}
