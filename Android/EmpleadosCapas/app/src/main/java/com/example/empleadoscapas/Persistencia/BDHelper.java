package com.example.empleadoscapas.Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class BDHelper extends SQLiteOpenHelper {
    private Context context;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD.Empleados.SQL_CREAR_TABLA);

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS)
                .append(" VALUES (NULL , 1, 'Armando Esteban Quito', 1000);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS)
                .append(" VALUES (NULL , 2, 'Elba Gallo', 2000);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EMPLEADOS)
                .append(" VALUES (NULL , 3, 'Elvis Cochuelo', 2500);").toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL(BD.Empleados.SQL_ELIMINAR_TABLA);

       onCreate(db);
    }

    public BDHelper(Context contexto) {
        super(contexto, BD.NOMBRE_BASE_DATOS, null, BD.VERSION_BASE_DATOS);

        this.context = contexto;
    }
}
