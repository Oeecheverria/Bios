package com.example.empleadoscapas.Compartidos.Excepciones;

public class ExcepcionPersistencia extends ExcepcionPersonalizada{

    public ExcepcionPersistencia(String mensaje) {
        super(mensaje);
    }

    public ExcepcionPersistencia(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}