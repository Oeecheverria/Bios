package com.example.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class BD {

    public static final String NOMBRE_BASE_DATOS = "BaseDatosArtistas.sqlite3";

    public static final int VERSION_BASE_DATOS = 1;

    public static final String ARTISTAS = "Artistas"; // Una constante por cada tabla

    private BD(){}

    public static abstract class Artistas implements BaseColumns {

        public static final String NOMBRE = "Nombre";
        public static final String ANIO_NACIMIENTO = "AnioNacimiento";

        public static final String[] COLUMNAS= {_ID, NOMBRE, ANIO_NACIMIENTO};

        public static final String SQL_CREAR_TABLA = new StringBuilder("CREATE TABLE ").append(ARTISTAS)
                .append(" (").append(_ID).append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ")
                .append(NOMBRE).append(" TEXT NOT NULL, ")
                .append(ANIO_NACIMIENTO).append(" INTEGER NOT NULL); ")
                .toString();

        public static final String SQL_ELIMINAR_TABLE =  new StringBuilder("DROP TABLE IF EXISTS ")
                .append(ARTISTAS).append(";").toString();


    }

}
