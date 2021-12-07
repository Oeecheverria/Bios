package com.example.empleadoscapas.Compartidos.Excepciones;

public class ExcepcionLogica extends ExcepcionPersonalizada{

    public ExcepcionLogica(String mensaje) {
        super(mensaje);
    }

    public ExcepcionLogica(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
