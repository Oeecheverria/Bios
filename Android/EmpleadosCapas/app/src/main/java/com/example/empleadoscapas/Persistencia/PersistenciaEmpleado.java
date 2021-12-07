package com.example.empleadoscapas.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersistencia;
import com.example.empleadoscapas.Compartidos.Excepciones.ExcepcionPersonalizada;

import java.util.ArrayList;
import java.util.List;

class PersistenciaEmpleado implements IPersistenciaEmpleado {

    private static PersistenciaEmpleado instancia;

    public static PersistenciaEmpleado getInstancia(Context contexto) {
        if (instancia == null)
            instancia = new PersistenciaEmpleado(contexto);
        return instancia;
    }


    private Context contexto;


    private PersistenciaEmpleado(Context contexto) {
        this.contexto = contexto.getApplicationContext();
    }

    @Override
    public List<DTEmpleado> listar() throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.EMPLEADOS, BD.Empleados.COLUMNAS, null, null, null, null, BD.Empleados.CEDULA);

            List<DTEmpleado> empleados = new ArrayList<>();


            while (datos.moveToNext()) {
                DTEmpleado empleado = instanciarEmpleado(datos);
                empleados.add(empleado);

            }

            return empleados;

        } catch (Exception ex) {

            throw new ExcepcionPersistencia("No se pudo listar los empleados");

        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }

    }

    @Override
    public DTEmpleado obtener(int cedula) throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.EMPLEADOS, BD.Empleados.COLUMNAS, BD.Empleados.CEDULA + "= ?", new String[]{String.valueOf(cedula)}, null, null, BD.Empleados.CEDULA);
            DTEmpleado empleado = null;
            if (datos.moveToNext()) {
                empleado = instanciarEmpleado(datos);
            }

            return empleado;

        } catch (Exception ex) {

            throw new ExcepcionPersistencia("No se pudo obtener el empleados");

        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void agregar(DTEmpleado empleado) throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            if (obtener(empleado.getCedula()) != null) {
                throw new ExcepcionPersistencia("El empleado ya existe");
            }

            ContentValues valores = new ContentValues();
            valores.put(BD.Empleados.CEDULA, empleado.getCedula());
            valores.put(BD.Empleados.NOMBRE, empleado.getNombre());
            valores.put(BD.Empleados.SUELDO, empleado.getSueldo());

            baseDatos.insertOrThrow(BD.EMPLEADOS, null, valores);

        } catch (ExcepcionPersistencia ex) {

            throw ex;

        } catch (Exception ex) {

            throw new ExcepcionPersistencia("No se pudo agregar el empleados");

        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void modificar(DTEmpleado empleado) throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();


            ContentValues valores = new ContentValues();
            valores.put(BD.Empleados.CEDULA, empleado.getCedula());
            valores.put(BD.Empleados.NOMBRE, empleado.getNombre());
            valores.put(BD.Empleados.SUELDO, empleado.getSueldo());


            int filasAfectadas = baseDatos.update(BD.EMPLEADOS, valores, BD.Empleados.CEDULA + " = ?", new String[]{ String.valueOf(empleado.getCedula())});

            if (filasAfectadas <1){
                throw new ExcepcionPersistencia( "El empleado no existe");
            }

        } catch (ExcepcionPersistencia ex) {

            throw ex;

        } catch (Exception ex) {

            throw new ExcepcionPersistencia("No se pudo modificar el empleados");

        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void eliminar(int cedula) throws ExcepcionPersonalizada {

        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();



            int filasAfectadas = baseDatos.delete(BD.EMPLEADOS, BD.Empleados.CEDULA + " = ?", new String[]{ String.valueOf(cedula)});

            if (filasAfectadas <1){
                throw new ExcepcionPersistencia( "El empleado no existe");
            }

        } catch (ExcepcionPersistencia ex) {

            throw ex;

        } catch (Exception ex) {

            throw new ExcepcionPersistencia("No se pudo eliminar el empleados");

        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }



    public DTEmpleado instanciarEmpleado(Cursor datos) {
        int columnID = datos.getColumnIndex(BD.Empleados._ID);
        int columnaCedula = datos.getColumnIndex(BD.Empleados.CEDULA);
        int columnaNombre = datos.getColumnIndex(BD.Empleados.NOMBRE);
        int columnaSueldo = datos.getColumnIndex(BD.Empleados.SUELDO);

        DTEmpleado empleado = new DTEmpleado(datos.getLong(columnID), datos.getInt(columnaCedula), datos.getString(columnaNombre), datos.getDouble(columnaSueldo));

        return empleado;

    }
}
