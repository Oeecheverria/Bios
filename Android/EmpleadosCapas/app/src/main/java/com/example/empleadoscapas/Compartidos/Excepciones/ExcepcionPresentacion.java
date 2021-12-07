package com.example.empleadoscapas.Compartidos.Excepciones;

public class ExcepcionPresentacion extends ExcepcionPersonalizada{

    public ExcepcionPresentacion(String mensaje) {
        super(mensaje);
    }

    public ExcepcionPresentacion(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
