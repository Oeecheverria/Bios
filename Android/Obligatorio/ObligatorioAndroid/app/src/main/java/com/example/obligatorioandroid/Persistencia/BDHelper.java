package com.example.obligatorioandroid.Persistencia;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BDHelper extends SQLiteOpenHelper {

    private Context contexto;

    public BDHelper(Context contexto) {
        super(contexto, BD.NOMBRE_BASE_DATOS, null, BD.VERSION_BASE_DATOS);

        this.contexto = contexto;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD.Eventos.SQL_CREAR_TABLA);
        db.execSQL(BD.Tareas.SQL_CREAR_TABLA);
        db.execSQL(BD.Reuniones.SQL_CREAR_TABLA);
        db.execSQL(BD.Gastos.SQL_CREAR_TABLA);
        db.execSQL(BD.Clientes.SQL_CREAR_TABLA);
        db.execSQL(BD.Particulares.SQL_CREAR_TABLA);
        db.execSQL(BD.Comerciales.SQL_CREAR_TABLA);

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.CLIENTES).append(" VALUES (NULL, 'Centro', 'oscar@gmail.com'); ").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.PARTICULARES).append(" VALUES ('63396826', 1, 'Oscar Echeverria');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.CLIENTES).append(" VALUES (NULL, 'Malvin', 'Enrique@gmail.com'); ").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.COMERCIALES).append(" VALUES ('12345678', 2, 'Oscar Company');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.CLIENTES).append(" VALUES (NULL, 'Buceo', 'cheva@gmail.com'); ").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.PARTICULARES).append(" VALUES ('63396827',3, 'Enrique Echeverria');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.CLIENTES).append(" VALUES (NULL, 'Pocitos', 'Enrique@gmail.com'); ").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.COMERCIALES).append(" VALUES ('987654321', 4, 'Enrique Society');").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EVENTOS).append(" VALUES ('Salsa', '11/01/2022', 1, 20, 'Fiesta', 10);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EVENTOS).append(" VALUES ('Merengue', '11/02/2022', 2, 30, 'Rumba', 15);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.EVENTOS).append(" VALUES ('Bachata', '11/03/2022', 3, 6, 'Pachanga', 100);").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.REUNIONES).append(" VALUES ('Coreografia 1', 'Salsa', 'Aprender a bailar 1', '01/12/2021', 'Caracas', 1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.REUNIONES).append(" VALUES ('Coreografia 2', 'Salsa', 'Aprender a bailar 2', '12/12/2021', 'Montevideo', 0);").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.TAREAS).append(" VALUES ('Charla', 'Salsa', '02/01/2022', 1);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.TAREAS).append(" VALUES ('Pasos basicos', 'Salsa', '03/01/2022', 0);").toString());

        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.GASTOS).append(" VALUES ('Profesor 1', 'Salsa', 'Bailarin.com', 1000);").toString());
        db.execSQL(new StringBuilder("INSERT INTO ").append(BD.GASTOS).append(" VALUES ('Profesor 2', 'Salsa', 'Bailarin.com', 3000);").toString());



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BD.Eventos.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Tareas.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Reuniones.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Gastos.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Clientes.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Particulares.SQL_ELIMINAR_TABLA);
        db.execSQL(BD.Comerciales.SQL_ELIMINAR_TABLA);

        onCreate(db);
    }
}
