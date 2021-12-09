package com.example.obligatorioandroid.Compartidos.DataTypes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DTEvento implements Serializable {

    protected LocalDateTime fechayHora;
    protected int duracion;
    protected String titulo;
    protected String tipo;
    protected int cantidadDeAsistentes;

    public LocalDateTime getFechayHora() {
        return fechayHora;
    }

    public void setFechayHora(LocalDateTime fechayHora) {
        this.fechayHora = fechayHora;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadDeAsistentes() {
        return cantidadDeAsistentes;
    }

    public void setCantidadDeAsistentes(int cantidadDeAsistentes) {
        this.cantidadDeAsistentes = cantidadDeAsistentes;
    }

    public DTEvento() {
    }

    public DTEvento(LocalDateTime fechayHora, int duracion, String titulo, String tipo, int cantidadDeAsistentes) {
        this.fechayHora = fechayHora;
        this.duracion = duracion;
        this.titulo = titulo;
        this.tipo = tipo;
        this.cantidadDeAsistentes = cantidadDeAsistentes;
    }
}
