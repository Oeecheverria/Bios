package com.example.obligatorioandroid.Persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.obligatorioandroid.Compartidos.DataTypes.DTCliente;
import com.example.obligatorioandroid.Compartidos.DataTypes.DTEvento;
import com.example.obligatorioandroid.Compartidos.DataTypes.DTParticular;
import com.example.obligatorioandroid.Compartidos.Excepciones.ExcepcionPersistencia;
import com.example.obligatorioandroid.Compartidos.Excepciones.ExcepcionPersonalizada;
import com.example.obligatorioandroid.Persistencia.Interfaces.IPersistenciaEvento;

import java.util.List;

class PersistenciaEvento implements IPersistenciaEvento {

    private static PersistenciaEvento instancia;


    public static PersistenciaEvento getInstancia(Context contexto) {
        if (instancia == null) {
            instancia = new PersistenciaEvento(contexto);
        }

        return instancia;
    }

    private Context contexto;

    private PersistenciaEvento(Context contexto) {
        this.contexto = contexto.getApplicationContext();

    }


    @Override
    public void nuevoEvento(DTEvento evento) throws ExcepcionPersonalizada {

    }

    @Override
    public void modificarEvento(DTEvento evento) throws ExcepcionPersonalizada {

    }

    @Override
    public void eliminarEvento(DTEvento evento) throws ExcepcionPersonalizada {

    }

    @Override
    public DTEvento BuscarEvento(String descripcion) throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.EVENTOS, BD.Eventos.COLUMNAS, BD.Eventos.TITULO + " = ?", new String[]{String.valueOf(descripcion)}, null, null, null);
            DTEvento evento = null;

            if (datos.moveToNext()) {
                evento = (instanciarClienteParticular(datosPadre, datosHijo));
                else {
                    datosHijo = baseDatos.query(BD.COMERCIALES, BD.Comerciales.COLUMNAS, BD.Comerciales.IDCLIENTE + " = ?", new String[]{String.valueOf(id)}, null, null, null);
                    cliente = (instanciarClienteComercial(datosPadre, datosHijo));

                }
            }

            return cliente;

        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener el cliente.", ex);
        } finally {
            if (datosHijo != null) datosHijo.close();
            if (datosPadre != null) datosPadre.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public List<DTEvento> ListarEventos() throws ExcepcionPersonalizada {
        return null;
    }

    public DTParticular instanciarEvento(Cursor datos) {
        int columnaDescripcion = datosHijo.getColumnIndex(BD.Particulares.CEDULA);
        int columnaNombre = datosHijo.getColumnIndex(BD.Particulares.NOMBRECOMPLETO);
        int columnaID = datosPadre.getColumnIndex(BD.Clientes._ID);
        int columnaDireccion = datosPadre.getColumnIndex(BD.Clientes.DIRECCION);
        int columnaTelefono = datosPadre.getColumnIndex(BD.Clientes.TELEFONO);
        int columnaEmail = datosPadre.getColumnIndex(BD.Clientes.EMAIL);


        DTParticular clienteParticular = new DTParticular(datosPadre.getInt(columnaID), datosPadre.getString(columnaDireccion), datosPadre.getString(columnaTelefono), datosPadre.getString(columnaEmail), datosHijo.getString(columnaCedula), datosHijo.getString(columnaNombre));

        return clienteParticular;
    }

}
