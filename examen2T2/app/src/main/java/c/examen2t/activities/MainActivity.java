package c.examen2t.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import c.examen2t.R;
import c.examen2t.adaptadores.ProductosAdapter;
import c.examen2t.bdd.BddContract;
import c.examen2t.bdd.MyContentProvider;
import c.examen2t.fragmentos.FragmentoLista;
import c.examen2t.modelo.Producto;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, ProductosAdapter.ReproducirSonido {


    private static final int CARGADOR = 1;
    private static final int RC_AGREGAR_PRODUCTO = 5;
    public static final int NO_COMPRADO = 0;
    public static final int COMPRADO = 1;
    private static final float VELOCIDAD_NORMAL = 1f;
    private static final int PRIORIDAD_MAXIMA = 1;
    private static final float VOLUMEN_MAX = 1f;
    private static final int SIN_BUCLE = 0;
    private static final int CALIDAD_NORMAL = 0;
    private static final int PRIORIDAD_NORMAL = 1;
    private LoaderManager mLoaderManager;
    private SoundPool reproductor;
    private int idSonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_main_activity);
        setSupportActionBar(toolbar);


        mLoaderManager = getSupportLoaderManager();
        mLoaderManager.initLoader(CARGADOR, null, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarActivity.start(MainActivity.this, RC_AGREGAR_PRODUCTO);
            }
        });

        cargarFragmentoLista();
        configSonido();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.preferencias) {
            startActivity(new Intent(this, PreferenciasActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarFragmentoLista() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flHueco, FragmentoLista.newInstance()).commit();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MyContentProvider.CONTENT_URI_PRODUCTOS, BddContract.Producto.TODOS, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ArrayList<Producto> productos = new ArrayList<>();
        if (data != null) {
            data.moveToFirst();
            while (!data.isAfterLast()) {
                Producto p = new Producto();
                p.setId(data.getInt(data.getColumnIndexOrThrow(BddContract.Producto.ID)));
                p.setNombre(data.getString(data.getColumnIndexOrThrow(BddContract.Producto.NOMBRE)));
                p.setCantidad(data.getFloat(data.getColumnIndexOrThrow(BddContract.Producto.CANTIDAD)));
                p.setUnidad(data.getString(data.getColumnIndexOrThrow(BddContract.Producto.UNIDAD)));
                p.setComprado(data.getInt(data.getColumnIndexOrThrow(BddContract.Producto.COMPRADO)));
                productos.add(p);
                data.moveToNext();
            }
        }
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flHueco);
        if (f instanceof FragmentoLista)
            ((FragmentoLista) f).getAdaptador().setProductos(productos);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.flHueco);
        if (f instanceof FragmentoLista)
            ((FragmentoLista) f).getAdaptador().setProductos(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_AGREGAR_PRODUCTO && resultCode == RESULT_OK)
            if (data.hasExtra(AgregarActivity.EXTRA_PRODUCTO)) {
                Producto p = data.getParcelableExtra(AgregarActivity.EXTRA_PRODUCTO);
                if (p != null)
                    insertarProducto(p);
            }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertarProducto(Producto p) {
        ContentValues valores = new ContentValues();
        valores.put(BddContract.Producto.NOMBRE, p.getNombre());
        valores.put(BddContract.Producto.CANTIDAD, p.getCantidad());
        valores.put(BddContract.Producto.UNIDAD, p.getUnidad());
        valores.put(BddContract.Producto.COMPRADO, NO_COMPRADO);
        getContentResolver().insert(MyContentProvider.CONTENT_URI_PRODUCTOS, valores);
    }

    private void configSonido() {
        // Se crea el objeto SoundPool con un límite de 8 sonidos simultáneos y
        // calidad estándar.
        reproductor = new SoundPool(8, AudioManager.STREAM_MUSIC, CALIDAD_NORMAL);
        // Se cargan los ficheros de sonido (recibe el contexto, el recurso y la
        // prioridad estándar).
        idSonido = reproductor.load(this, R.raw.disparo, PRIORIDAD_NORMAL);
        // Cuando se termine de cargar el sonido, se activa el botón
        // correspondiente.
        reproductor.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

            }
        });
    }

    private void reproducirSonido(){
        reproductor.play(idSonido, VOLUMEN_MAX, VOLUMEN_MAX, PRIORIDAD_MAXIMA, SIN_BUCLE, VELOCIDAD_NORMAL);
    }

    @Override
    protected void onDestroy() {
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
        super.onDestroy();
    }

    @Override
    public void reproducir() {
        reproducirSonido();
    }
}
