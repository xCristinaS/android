package c.ejercicio43_usocontentprovider_historialllamada;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static int limit = 15;
    private static final int CARGADOR = 1;
    private RecyclerView lstLlamadas;
    private Adaptador adaptador;
    private LoaderManager mLoaderManager;
    private EndlessRecyclerviewScrollListener endlessListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLoaderManager = getSupportLoaderManager();
        initViews();
        mLoaderManager.initLoader(CARGADOR, null, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void initViews() {
        lstLlamadas = (RecyclerView) findViewById(R.id.lstLlamadas);
        LinearLayoutManager mLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstLlamadas.setLayoutManager(mLayout);
        adaptador = new Adaptador();
        //cargarLista();
        lstLlamadas.setAdapter(adaptador);

        endlessListener = new EndlessRecyclerviewScrollListener(mLayout) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page != 2) {
                    limit += limit;
                    mLoaderManager.restartLoader(CARGADOR, null, MainActivity.this);
                } else
                    limit = 15;
            }
        };

        lstLlamadas.addOnScrollListener(endlessListener);

    }
/*
    // Carga de datos la lista, pero llamando al loadInBackground, sin usar un loader.
    private ArrayList<Llamada> cargarLista() {
        ArrayList<Llamada> llamadas = new ArrayList<>();
        int offset = 0;
        String[] campos = {CallLog.Calls.NUMBER};
        String orden = CallLog.Calls.DATE + " DESC limit " + offset + "," + limit;
        CursorLoader cargador = new CursorLoader(this, CallLog.Calls.CONTENT_URI, campos, null, null, orden);
        Cursor cursor = cargador.loadInBackground();
        if (cursor != null)
            while (cursor.moveToNext())
                llamadas.add(new Llamada(cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))));

        adaptador.setLlamadas(llamadas);
        return llamadas;
    }
*/

    @Override
    protected void onResume() {
        super.onResume();
        endlessListener.reset(0, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // config loader.
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] campos = {CallLog.Calls.NUMBER};
        String orden = CallLog.Calls.DATE + " DESC limit " + 0 + "," + limit;
        return new CursorLoader(this, CallLog.Calls.CONTENT_URI, campos, null, null, orden);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ArrayList<Llamada> llamadas = new ArrayList<>();
        if (data != null) {
            data.moveToFirst();
            while (!data.isAfterLast()) {
                llamadas.add(new Llamada(data.getString(data.getColumnIndexOrThrow(CallLog.Calls.NUMBER))));
                data.moveToNext();
            }
        }
        adaptador.setLlamadas(llamadas);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adaptador.setLlamadas(null);
    }
}
