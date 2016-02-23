package c.ejercicio46_firebasealumnos.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import c.ejercicio46_firebasealumnos.adaptadores.AdaptadorAlumnos;
import c.ejercicio46_firebasealumnos.R;

public class MainActivity extends AppCompatActivity implements AdaptadorAlumnos.OnAdapterAlumnoClic {

    public static final String DIR_MYBDD = "https://myfirebase01.firebaseio.com/";
    public static final String ARBOL_ALUMNOS = "alumnos/";
    public static final String ARBOL_NOTAS = "notas/";
    public static final String ARBOL_GRUPOS_ALUMNO = "grupoAlumno/";
    public static final String ARBOL_GRUPOS = "grupos/";

    private RecyclerView lstAlumnos;
    private AdaptadorAlumnos adaptador;
    private LinearLayoutManager mLayoutManager;

    Firebase myFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews(){
        myFirebase = new Firebase(DIR_MYBDD+ARBOL_ALUMNOS);

        lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adaptador = new AdaptadorAlumnos(myFirebase.orderByChild("nombre"));
        adaptador.setListener(this);

        lstAlumnos.setAdapter(adaptador);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
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
    public void onBtnNotasClick(String idAlumno) {
        Intent intento = new Intent(this, NotasActivity.class);
        intento.putExtra(NotasActivity.EXTRA_KEY_ALUMNO, idAlumno);
        startActivity(intento);
    }

    @Override
    public void onBtnGruposClick(String idAlumno) {
        Intent intento = new Intent(this, GruposActivity.class);
        intento.putExtra(GruposActivity.EXTRA_KEY_ALUMNO, idAlumno);
        startActivity(intento);
    }
}
