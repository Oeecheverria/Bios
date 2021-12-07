package com.example.empleadoscapas.Compartidos.DataType;

import java.io.Serializable;

public class DTEmpleado implements Serializable {

    private long id;
    private int cedula;
    private String nombre;
    private double sueldo;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DTEmpleado(int cedula, String nombre, double sueldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public DTEmpleado() {
        this(1, 1, "N/D", 0);
    }

    public DTEmpleado(long id, int cedula, String nombre, double sueldo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }


}
