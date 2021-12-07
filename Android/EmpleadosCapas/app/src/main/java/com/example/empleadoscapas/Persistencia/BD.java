package com.example.empleadoscapas.Persistencia;

import android.provider.BaseColumns;

final class BD {

    public static final String NOMBRE_BASE_DATOS = "EmpleadosCapas.sqLite3";

    public static final int VERSION_BASE_DATOS = 1;

    public static final String EMPLEADOS = "Empleados";

    private BD() {
    }

    public static abstract class Empleados implements BaseColumns {
        public static final String CEDULA = "Cedula";
        public static final String NOMBRE = "Nombre";
        public static final String SUELDO = "Sueldo";

        public static final String[] COLUMNAS = {_ID, CEDULA, NOMBRE, SUELDO};

        public static final String SQL_CREAR_TABLA = new StringBuilder("CREATE TABLE ")
                .append(EMPLEADOS).append(" (")
                .append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                .append(CEDULA).append(" INTEGER NOT NULL UNIQUE, ")
                .append(NOMBRE).append(" TEXT NOT NULL, ")
                .append(SUELDO).append(" REAL NOT NULL); ").toString();


        public static final String SQL_ELIMINAR_TABLA = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(EMPLEADOS).append(";").toString();

    }
}
