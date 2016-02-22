package c.ejercicio46_firebasealumnos.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.client.Firebase;

import c.ejercicio46_firebasealumnos.R;
import c.ejercicio46_firebasealumnos.adaptadores.AdaptadorGrupos;

public class GruposActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_ALUMNO = "key";

    private RecyclerView lstGrupos;
    private AdaptadorGrupos adaptador;
    private LinearLayoutManager mLayoutManager;
    private String keyAlumno = "";

    private Firebase myFirebase = null;
    private Intent intentRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentRecibido = getIntent();
        if (intentRecibido != null && intentRecibido.hasExtra(EXTRA_KEY_ALUMNO))
            keyAlumno = intentRecibido.getStringExtra(EXTRA_KEY_ALUMNO);

        initViews();
    }

    private void initViews() {
        if (!keyAlumno.equals(""))
            myFirebase = new Firebase(MainActivity.DIR_MYBDD + MainActivity.ARBOL_GRUPOS_ALUMNO + keyAlumno);

        lstGrupos = (RecyclerView) findViewById(R.id.lstGrupos);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adaptador = new AdaptadorGrupos(myFirebase);

        lstGrupos.setAdapter(adaptador);
        lstGrupos.setLayoutManager(mLayoutManager);
        lstGrupos.setItemAnimator(new DefaultItemAnimator());
    }

}
