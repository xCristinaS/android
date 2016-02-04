package cristina.ejercicio37_stetho_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Adaptador.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private static final int RC_NUEVO_ALUMNO = 1;
    private static final int RC_EDIT_ALUMNO = 2;

    private static final String STATE_LISTA = "a";
    private static final String ARRAY_ALUMNOS = "b";

    private RecyclerView lstAlumnos;
    private LinearLayoutManager mLayoutManager;
    private Parcelable mEstadoLista;
    private SwipeRefreshLayout swipeRefresh;
    private DAO gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarActualizarActivity.start(MainActivity.this, null, ((Adaptador) lstAlumnos.getAdapter()).getIdLastAlumno() + 1, RC_NUEVO_ALUMNO);
            }
        });

        initViews();
        if (savedInstanceState == null)
            obtenerAlumnos();
    }

    private void initViews(){
        gestor = new DAO(this);
        configRecyclerView();
        configSwipeRefresh();
    }

    private void configRecyclerView(){
        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        Adaptador adaptador = new Adaptador(new ArrayList<Alumno>());
        adaptador.setOnItemClickListener(this);
        lstAlumnos.setAdapter(adaptador);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }

    private void configSwipeRefresh(){
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        ((Adaptador)lstAlumnos.getAdapter()).removeAllItems();
        obtenerAlumnos();
    }

    private void obtenerAlumnos(){
        ((Adaptador)lstAlumnos.getAdapter()).removeAllItems();
        ((Adaptador)lstAlumnos.getAdapter()).addItems(gestor.selectAllAlumnos());
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(Alumno alumno) {
        AgregarActualizarActivity.start(MainActivity.this, alumno, 0, RC_EDIT_ALUMNO);
    }

    @Override
    public void removeAlumno(int id) {
        gestor.deleteAlumno(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Alumno a;
        if (resultCode == RESULT_OK) {
            if (requestCode == RC_NUEVO_ALUMNO && data.hasExtra(AgregarActualizarActivity.EXTRA_ALUMNO)) {
                a = data.getParcelableExtra(AgregarActualizarActivity.EXTRA_ALUMNO);
                if (gestor.insertAlumno(a) > 0)
                    ((Adaptador) lstAlumnos.getAdapter()).addItem(a);
            } else if (requestCode == RC_EDIT_ALUMNO && data.hasExtra(AgregarActualizarActivity.EXTRA_ALUMNO)){
                a = data.getParcelableExtra(AgregarActualizarActivity.EXTRA_ALUMNO);
                gestor.updateAlumno(a);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se salva el estado del RecyclerView.
        mEstadoLista = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, mEstadoLista);
        outState.putParcelableArrayList(ARRAY_ALUMNOS, ((Adaptador)lstAlumnos.getAdapter()).getDatos());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Se obtiene el estado anterior de la lista.
        mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
        ((Adaptador)lstAlumnos.getAdapter()).setDatos(savedInstanceState.<Alumno>getParcelableArrayList(ARRAY_ALUMNOS));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mEstadoLista != null) {
            mLayoutManager.onRestoreInstanceState(mEstadoLista);
        }
    }

}
