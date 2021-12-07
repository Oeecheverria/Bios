package com.example.empleadoscapas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersistencia;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;
import java.util.List;

class PersistenciaSucursal implements IPersistenciaSucursal {

    private static PersistenciaSucursal instancia;


    public static PersistenciaSucursal getInstancia(Context contexto) {
        if (instancia == null) {
            instancia = new PersistenciaSucursal(contexto);
        }

        return instancia;
    }


    private Context contexto;


    private PersistenciaSucursal(Context contexto) {
        this.contexto = contexto.getApplicationContext();
    }

    @Override
    public List<DTSucursal> listar()
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.VISTA_SUCURSALES, BD.VistaSucursales.COLUMNAS, null, null, null, null, BD.VistaSucursales.SUCURSALES_NOMBRE);

            List<DTSucursal> sucursales = new ArrayList<DTSucursal>();

            while (datos.moveToNext()) {
                DTSucursal sucursal = instanciarSucursal(datos);
                sucursales.add(sucursal);
            }

            return sucursales;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar las sucursales.", ex);
        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public DTSucursal obtener(long id)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.VISTA_SUCURSALES, BD.VistaSucursales.COLUMNAS, BD.VistaSucursales.SUCURSALES_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null);

            DTSucursal sucursal = null;

            if (datos.moveToNext()) {
                sucursal = instanciarSucursal(datos);
            }

            return sucursal;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener la sucursal.", ex);
        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void agregar(DTSucursal sucursal)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(BD.Sucursales.NOMBRE, sucursal.getNombre());
            valores.put(BD.Sucursales.DIRECCION, sucursal.getDireccion());
            valores.put(BD.Sucursales.SUPERFICIE, sucursal.getSuperficie());
            valores.put(BD.Sucursales.ESTACIONAMIENTO, sucursal.getEstacionamiento());

            long id = baseDatos.insertOrThrow(BD.SUCURSALES, null, valores);

            sucursal.setId(id);
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar la sucursal.", ex);
        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void modificar(DTSucursal sucursal)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(BD.Sucursales.NOMBRE, sucursal.getNombre());
            valores.put(BD.Sucursales.DIRECCION, sucursal.getDireccion());
            valores.put(BD.Sucursales.SUPERFICIE, sucursal.getSuperficie());
            valores.put(BD.Sucursales.ESTACIONAMIENTO, sucursal.getEstacionamiento());

            int filasAfectadas = baseDatos.update(BD.SUCURSALES, valores, BD.Sucursales._ID + " = ?", new String[] { String.valueOf(sucursal.getId()) });

            if (filasAfectadas < 1) {
                throw new ExcepcionPersistencia("La sucursal no existe.");
            }
        } catch (ExcepcionPersistencia ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo modificar la sucursal.", ex);
        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void eliminar(long id)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            datos = baseDatos.query(BD.VISTA_EMPLEADOS, BD.VistaEmpleados.COLUMNAS, BD.VistaSucursales.SUCURSALES_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null);

            if (datos.moveToNext()) {
                throw new ExcepcionPersistencia("La sucursal tiene empleados.");
            }

            int filasAfectadas = baseDatos.delete(BD.SUCURSALES, BD.Sucursales._ID + " = ?", new String[] { String.valueOf(id) });

            if (filasAfectadas < 1) {
                throw new ExcepcionPersistencia("La sucursal no existe.");
            }
        } catch (ExcepcionPersistencia ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar la sucursal.", ex);
        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    public DTSucursal instanciarSucursal(Cursor datos) {
        int columnaSucursalesId = datos.getColumnIndex(BD.VistaSucursales.SUCURSALES_ID);
        int columnaSucursalesNombre = datos.getColumnIndex(BD.VistaSucursales.SUCURSALES_NOMBRE);
        int columnaSucursalesDireccion = datos.getColumnIndex(BD.VistaSucursales.SUCURSALES_DIRECCION);
        int columnaSucursalesSuperficie = datos.getColumnIndex(BD.VistaSucursales.SUCURSALES_SUPERFICIE);
        int columnaSucursalesEstacionamiento = datos.getColumnIndex(BD.VistaSucursales.SUCURSALES_ESTACIONAMIENTO);

        Integer superficie = datos.isNull(columnaSucursalesSuperficie) ? null : datos.getInt(columnaSucursalesSuperficie);
        boolean estacionamiento = datos.getInt(columnaSucursalesEstacionamiento) == 1;

        DTSucursal sucursal = new DTSucursal(datos.getLong(columnaSucursalesId), datos.getString(columnaSucursalesNombre), datos.getString(columnaSucursalesDireccion), superficie, estacionamiento);

        return sucursal;
    }

}
