package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MIS_LOGS = "MIS_LOGS";

    protected ListView lvArtistas;

    private BDHelper bdHelper;
    private SQLiteDatabase baseDatos;

    private SimpleCursorAdapter adaptadorArtistas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvArtistas = (ListView) findViewById(R.id.lvArtistas);

        bdHelper = new BDHelper(this);
        baseDatos = bdHelper.getWritableDatabase();

        EliminarArtistas();
        agregarArtistas();

        adaptadorArtistas = new SimpleCursorAdapter(this, R.layout.listitem_artista, ListarArtistas(), BD.Artistas.COLUMNAS, new int[]{R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento}, 0);
        lvArtistas.setAdapter(adaptadorArtistas);

        lvArtistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvArtistasOnItemClick(parent, view, position, id);
            }


        });


    }

    protected void EliminarArtistas(){
        baseDatos.delete(BD.ARTISTAS, BD.Artistas._ID + " > ?", new String[]{"1"});
    }

    protected void agregarArtistas() {

        ContentValues valores = new ContentValues();
        baseDatos.beginTransaction();
        try {
            valores.put(BD.Artistas.NOMBRE, "Frank Sinatra");
            valores.put(BD.Artistas.ANIO_NACIMIENTO, 1915);
            baseDatos.insert(BD.ARTISTAS, null, valores);

            valores.clear();

            valores.put(BD.Artistas.NOMBRE, "Lady Gaga");
            valores.put(BD.Artistas.ANIO_NACIMIENTO, 1986);
            baseDatos.insert(BD.ARTISTAS, null, valores);

            valores.clear();

            valores.put(BD.Artistas.NOMBRE, "Jawny Cash");
            valores.put(BD.Artistas.ANIO_NACIMIENTO, 1932);
            baseDatos.insert(BD.ARTISTAS, null, valores);

            valores.clear();

            valores.put(BD.Artistas.NOMBRE, "Ludwig van Beethoven");
            valores.put(BD.Artistas.ANIO_NACIMIENTO, 1770);
            baseDatos.insert(BD.ARTISTAS, null, valores);

            baseDatos.setTransactionSuccessful();


        } catch (Exception ex) {

            Log.e(MIS_LOGS, "No se pudo insertar los artistas de prueba");

        } finally {
            baseDatos.endTransaction();
        }
    }

    protected Cursor ListarArtistas() {

        //    return baseDatos.query(BD.ARTISTAS, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");

        //   return baseDatos.rawQuery("SELECT * FROM "+ BD.ARTISTAS + " ORDER BY " + BD.Artistas.NOMBRE + " DESC; ", null);

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        qb.setTables(BD.ARTISTAS); // En caso de dos o mas tablas: X INNER JOIN Y ON X.y = Y.y

        return qb.query(baseDatos, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");
    }

    public void btnCorregirOnClick(View view) {

        ContentValues valores = new ContentValues();

        valores.put(BD.Artistas.NOMBRE, "Johnny Cash");

        baseDatos.update(BD.ARTISTAS, valores, BD.Artistas.NOMBRE + " = ?", new String[]{"Jawny Cash"});

        adaptadorArtistas.getCursor().requery();

        adaptadorArtistas.notifyDataSetChanged();
    }


    private void lvArtistasOnItemClick(AdapterView<?> parent, View view, int position, long id) {
    Cursor cursor = ((SimpleCursorAdapter) adaptadorArtistas).getCursor();
    cursor.moveToPosition(position);

    int columnID = cursor.getColumnIndex(BD.Artistas._ID);
    int columnNombre = cursor.getColumnIndex(BD.Artistas.NOMBRE);
    int columnNacimiento = cursor.getColumnIndex(BD.Artistas.ANIO_NACIMIENTO);


    StringBuilder informacion = new StringBuilder(String.valueOf(cursor.getInt(columnID)))
            .append(" - ").append(cursor.getString(columnNombre)).append(" (")
            .append(String.valueOf(cursor.getInt(columnNacimiento))).append(")");

        Toast.makeText(this,informacion.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseDatos.close();
        // bdHelper.eliminarBaseDatos();

        bdHelper.close();
    }
}