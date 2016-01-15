package cristina.ejercicio31_recyclerview_listheterogenea;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Adaptador.OnItemClickListener{

    private RecyclerView lstLista;
    private ArrayList<TipoGenerico> datosLista = new ArrayList<>();
    private Adaptador adaptador;
    private FloatingActionButton boton;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        meterDatosEnLista();
        configurarRecyclerView();
        configFloatingButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configurarRecyclerView(){
        lstLista = (RecyclerView) findViewById(R.id.lstLista);
        lstLista.setHasFixedSize(true);
        adaptador = new Adaptador(datosLista);
        adaptador.setOnItemClickListener(this);
        lstLista.setAdapter(adaptador);
        lstLista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstLista.setItemAnimator(new DefaultItemAnimator());
    }

    private void meterDatosEnLista(){
        datosLista.add(new TipoCurso("1CFGS DAM"));
        for (int i = 0; i <= 15; i++)
            datosLista.add(new TipoAlumno("Alumno"+i));
        datosLista.add(new TipoCurso("2CFGS DAM"));
        for (int i = 15; i <= 30; i++)
            datosLista.add(new TipoAlumno("Alumno"+i));
    }

    @Override
    public void onItemClick(View view, TipoAlumno alumno, int position) {
        adaptador.removeItem(position);
    }

    private void configFloatingButton() {
        boton = (FloatingActionButton) findViewById(R.id.fab);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarAlumno(new TipoAlumno("Agregado con botón"));
            }
        });
    }

    private void agregarAlumno(TipoAlumno alumno) {
        // Se agrega el alumno.
        adaptador.addItem(alumno, adaptador.getItemCount());
        lstLista.scrollToPosition(adaptador.getItemCount() - 1);
    }

}
