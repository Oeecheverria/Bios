package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    private Context contexto;

    public BDHelper(Context contexto){
        super(contexto, BD.NOMBRE_BASE_DATOS,null, BD.VERSION_BASE_DATOS);

        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD.Artistas.SQL_CREAR_TABLA);

        db.execSQL(new StringBuilder("INSERT INTO ")
                .append(BD.ARTISTAS)
        .append(" VALUES (NULL, 'Phil Collins', 1951);").toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(BD.Artistas.SQL_ELIMINAR_TABLE);

        onCreate(db);
    }

    public void eliminarBaseDatos(){
        contexto.deleteDatabase(BD.NOMBRE_BASE_DATOS);
    }
}
