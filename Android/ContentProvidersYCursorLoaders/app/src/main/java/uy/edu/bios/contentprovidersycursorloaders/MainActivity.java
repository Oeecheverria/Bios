package uy.edu.bios.contentprovidersycursorloaders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String MIS_LOGS = "MIS_LOGS";

    public static final int ID_LOADER_CONTACTOS = 1;


    private SimpleCursorAdapter adaptadorContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        adaptadorContactos = new SimpleCursorAdapter(this, R.layout.listitem_contacto, null, new String[] { ContactsContract.Contacts.DISPLAY_NAME }, new int[] { R.id.tvNombreContacto }, 0);
        setListAdapter(adaptadorContactos);

        Log.i(MIS_LOGS, "Inicializando el loader...");

        getLoaderManager().initLoader(ID_LOADER_CONTACTOS, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> loader = null;

        if (id == ID_LOADER_CONTACTOS) {
            String[] proyeccion = { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME };
            String seleccion = ContactsContract.Contacts.DISPLAY_NAME + " NOT NULL AND " + ContactsContract.Contacts.DISPLAY_NAME + " <> '' AND " + ContactsContract.Contacts.STARRED + " = ?";
            String[] parametrosSeleccion = { "1" };
            String orden = ContactsContract.Contacts._ID + " ASC";

            loader = new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, proyeccion, seleccion, parametrosSeleccion, orden);

            Log.i(MIS_LOGS, "Se creó el loader.");
        }

        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (loader.getId() == ID_LOADER_CONTACTOS) {
            adaptadorContactos.swapCursor(data);

            Log.i(MIS_LOGS, "Se cargaron los datos.");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if (loader.getId() == ID_LOADER_CONTACTOS) {
            adaptadorContactos.swapCursor(null);

            Log.i(MIS_LOGS, "Se reinició el loader.");
        }
    }

}
