package com.example.empleadoscapas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.empleadoscapas.compartidos.datatypes.DTEmpleado;
import com.example.empleadoscapas.compartidos.datatypes.DTSucursal;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersistencia;
import com.example.empleadoscapas.compartidos.excepciones.ExcepcionPersonalizada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class PersistenciaEmpleado implements IPersistenciaEmpleado {

    private static PersistenciaEmpleado instancia;


    public static PersistenciaEmpleado getInstancia(Context contexto) {
        if (instancia == null) {
            instancia = new PersistenciaEmpleado(contexto);
        }

        return instancia;
    }


    private Context contexto;
    private SimpleDateFormat formateadorFechas;


    private PersistenciaEmpleado(Context contexto) {
        this.contexto = contexto.getApplicationContext();

        formateadorFechas = new SimpleDateFormat("yyyy-MM-dd");
    }


    @Override
    public List<DTEmpleado> listar()
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;
        Cursor datos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getReadableDatabase();
            datos = baseDatos.query(BD.VISTA_EMPLEADOS, BD.VistaEmpleados.COLUMNAS, null, null, null, null, BD.VistaEmpleados.EMPLEADOS_CEDULA);

            List<DTEmpleado> empleados = new ArrayList<DTEmpleado>();

            while (datos.moveToNext()) {
                DTEmpleado empleado = instanciarEmpleado(datos);
                empleados.add(empleado);
            }

            return empleados;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo listar los empleados.", ex);
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
            datos = baseDatos.query(BD.VISTA_EMPLEADOS, BD.VistaEmpleados.COLUMNAS, BD.VistaEmpleados.EMPLEADOS_CEDULA + " = ?", new String[] { String.valueOf(cedula) }, null, null, null);

            DTEmpleado empleado = null;

            if (datos.moveToNext()) {
                empleado = instanciarEmpleado(datos);
            }

            return empleado;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo obtener el empleado.", ex);
        } finally {
            if (datos != null) datos.close();
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void agregar(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            if (obtener(empleado.getCedula()) != null) {
                throw new ExcepcionPersistencia("El empleado ya existe.");
            }

            ContentValues valores = new ContentValues();
            valores.put(BD.Empleados.CEDULA, empleado.getCedula());
            valores.put(BD.Empleados.NOMBRE, empleado.getNombre());
            valores.put(BD.Empleados.FECHA_INGRESO, formateadorFechas.format(empleado.getFechaIngreso()));
            valores.put(BD.Empleados.SUELDO, empleado.getSueldo());
            valores.put(BD.Empleados.SUCURSAL, empleado.getSucursal().getId());

            long id = baseDatos.insertOrThrow(BD.EMPLEADOS, null, valores);

            empleado.setId(id);
        } catch (ExcepcionPersistencia ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo agregar el empleado.", ex);
        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void modificar(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(BD.Empleados.CEDULA, empleado.getCedula());
            valores.put(BD.Empleados.NOMBRE, empleado.getNombre());
            valores.put(BD.Empleados.FECHA_INGRESO, formateadorFechas.format(empleado.getFechaIngreso()));
            valores.put(BD.Empleados.SUELDO, empleado.getSueldo());
            valores.put(BD.Empleados.SUCURSAL, empleado.getSucursal().getId());

            int filasAfectadas = baseDatos.update(BD.EMPLEADOS, valores, BD.Empleados.CEDULA + " = ?", new String[] { String.valueOf(empleado.getCedula()) });

            if (filasAfectadas < 1) {
                throw new ExcepcionPersistencia("El empleado no existe.");
            }
        } catch (ExcepcionPersistencia ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo modificar el empleado.", ex);
        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    @Override
    public void eliminar(int cedula)
            throws ExcepcionPersonalizada {
        BDHelper bdHelper = null;
        SQLiteDatabase baseDatos = null;

        try {
            bdHelper = new BDHelper(contexto);
            baseDatos = bdHelper.getWritableDatabase();

            int filasAfectadas = baseDatos.delete(BD.EMPLEADOS, BD.Empleados.CEDULA + " = ?", new String[] { String.valueOf(cedula) });

            if (filasAfectadas < 1) {
                throw new ExcepcionPersistencia("El empleado no existe.");
            }
        } catch (ExcepcionPersistencia ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionPersistencia("No se pudo eliminar el empleado.", ex);
        } finally {
            if (baseDatos != null) baseDatos.close();
            if (bdHelper != null) bdHelper.close();
        }
    }

    public DTEmpleado instanciarEmpleado(Cursor datos)
            throws ParseException {
        int columnaEmpleadosId = datos.getColumnIndex(BD.VistaEmpleados.EMPLEADOS_ID);
        int columnaEmpleadosCedula = datos.getColumnIndex(BD.VistaEmpleados.EMPLEADOS_CEDULA);
        int columnaEmpleadosNombre = datos.getColumnIndex(BD.VistaEmpleados.EMPLEADOS_NOMBRE);
        int columnaEmpleadosFechaIngreso = datos.getColumnIndex(BD.VistaEmpleados.EMPLEADOS_FECHA_INGRESO);
        int columnaEmpleadosSueldo = datos.getColumnIndex(BD.VistaEmpleados.EMPLEADOS_SUELDO);

        Date fechaIngreso = formateadorFechas.parse(datos.getString(columnaEmpleadosFechaIngreso));

        DTSucursal sucursal = PersistenciaSucursal.getInstancia(contexto).instanciarSucursal(datos);

        DTEmpleado empleado = new DTEmpleado(datos.getLong(columnaEmpleadosId), datos.getInt(columnaEmpleadosCedula), datos.getString(columnaEmpleadosNombre), fechaIngreso, datos.getDouble(columnaEmpleadosSueldo), sucursal);

        return empleado;
    }

}
