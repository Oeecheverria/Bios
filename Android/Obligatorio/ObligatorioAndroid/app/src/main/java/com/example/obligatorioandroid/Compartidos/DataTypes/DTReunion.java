package com.example.obligatorioandroid.Compartidos.DataTypes;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DTReunion implements Serializable {

    protected String descripcion;
    protected String objetivo;
    protected LocalDateTime fechaYHora;
    protected String lugar;
    protected boolean enviarNotificacion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isEnviarNotificacion() {
        return enviarNotificacion;
    }

    public void setEnviarNotificacion(boolean enviarNotificacion) {
        this.enviarNotificacion = enviarNotificacion;
    }

    public DTReunion() {
    }

    public DTReunion(String descripcion, String objetivo, LocalDateTime fechaYHora, String lugar, boolean enviarNotificacion) {
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.fechaYHora = fechaYHora;
        this.lugar = lugar;
        this.enviarNotificacion = enviarNotificacion;
    }
}
