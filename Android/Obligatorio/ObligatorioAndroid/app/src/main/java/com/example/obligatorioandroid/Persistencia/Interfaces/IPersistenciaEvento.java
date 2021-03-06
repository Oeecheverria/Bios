package com.example.obligatorioandroid.Persistencia.Interfaces;

import com.example.obligatorioandroid.Compartidos.DataTypes.DTEvento;
import com.example.obligatorioandroid.Compartidos.Excepciones.ExcepcionPersonalizada;

import java.util.List;

public interface IPersistenciaEvento {

    void nuevoEvento(DTEvento evento) throws ExcepcionPersonalizada;

    void modificarEvento(DTEvento evento) throws ExcepcionPersonalizada;

    void eliminarEvento(DTEvento evento) throws ExcepcionPersonalizada;

    DTEvento BuscarEvento(String descripcion) throws ExcepcionPersonalizada;

    List<DTEvento> ListarEventos() throws ExcepcionPersonalizada;
}
