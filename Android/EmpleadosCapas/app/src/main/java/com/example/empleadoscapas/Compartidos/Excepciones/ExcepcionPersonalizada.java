package com.example.empleadoscapas.Compartidos.Excepciones;

public class ExcepcionPersonalizada extends Exception{

    public ExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }

    public ExcepcionPersonalizada(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
