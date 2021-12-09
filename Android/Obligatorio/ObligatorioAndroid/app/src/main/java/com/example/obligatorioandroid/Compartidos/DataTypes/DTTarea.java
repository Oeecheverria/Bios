package com.example.obligatorioandroid.Compartidos.DataTypes;

import java.io.Serializable;
import java.time.LocalDate;

public class DTTarea implements Serializable {

    protected String descripcion;
    protected LocalDate deadline;
    protected boolean completado;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public DTTarea() {
    }

    public DTTarea(String descripcion, LocalDate deadline, boolean completado) {
        this.descripcion = descripcion;
        this.deadline = deadline;
        this.completado = completado;
    }
}
