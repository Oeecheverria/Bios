package com.example.empleadoscapas.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class BDHelper extends SQLiteOpenHelper {

    private Context contexto;


    public BDHelper(Context contexto) {
        super(contexto, BD.NOMBRE_BASE_DATOS, null, BD.VERSION_BASE_DATOS);

        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD.Sucursales.SQL_CREAR_TABLA);
        db.execSQL(BD.Empleados.SQL_CREAR_TABLA);

        db.execSQL(BD.VistaSucursales.SQL_CREAR_VISTA);
        db.execSQL(BD.VistaEmpleados.SQL_CREAR_VISTA);

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.SUCURSALES).append(" VALUES (NULL, 'Centro', '18 de Julio 1234', 1000, 1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.SUCURSALES).append(" VALUES (NULL, 'Ciudad Vieja', 'Rincón 4321', NULL, 0);").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS).append(" VALUES (NULL, 1, 'José Pérez', '1991-07-10', 1000, 1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS).append(" VALUES (NULL, 2, 'Ana Fernández', '2002-03-28', 2000, 1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS).append(" VALUES (NULL, 3, 'Pedro Rodríguez', '2014-12-05', 2500, 2);").toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BD.Empleados.SQL_ELIMINAR_TABLA);

        onCreate(db);
    }

}
