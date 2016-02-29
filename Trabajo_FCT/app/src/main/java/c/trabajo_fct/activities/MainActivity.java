package c.trabajo_fct.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;

import c.trabajo_fct.R;
import c.trabajo_fct.fragments.FragmentoPrincipal;
import c.trabajo_fct.fragments.Fragmento_Alumno_Visita;
import c.trabajo_fct.fragments.Fragmento_Detalle_Empresa;
import c.trabajo_fct.fragments.Fragmento_Insert_NewVisitaGeneral;
import c.trabajo_fct.fragments.Fragmento_Insert_UpdateAlumno;
import c.trabajo_fct.fragments.Fragmento_Insert_UpdateEmpresa;
import c.trabajo_fct.fragments_dialogs.SeleccionDirectaDialogFragment;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.interfaces.MyDateTimePickerCallBack;
import c.trabajo_fct.interfaces.OnAdapterItemClick;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Callback_MainActivity, SeleccionDirectaDialogFragment.SeleccionDirectaDialogListener
        ,MyDateTimePickerCallBack, OnAdapterItemClick{

    public static final String FRAGMENTO_INSERT_UPDATE_ALUMNO = "nuevo_alumno";
    public static final String FRAGMENTO_INSERT_UPDATE_EMPRESA = "nueva_empresa";
    public static final String FRAGMENTO_NEW_VISITA_GENERAL = "nueva_visita_general";
    public static final String FRAGMENTO_DETALLES_EMPRESA = "fragmento_detalles_empresa";
    public static final String FRAGMENTO_ALUMNO_VISITAS = "fragmento_detalles_de_alumno_y_sus_visitas";

    private static final String FRAGMENTO_PRINCIPAL = "principal";

    private static final String RECUPERAR = "rec";
    private static final int CARGAR_F_NUEVO_ALUMNO = 10;

    private Toolbar toolbar;
    private FragmentManager gestor;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
        cargarFragmentoPrincipal();
    }

    private void initViews() {
        gestor = getSupportFragmentManager();
        configDrawerLayout();
        configFab();
    }

    public void cargarFragmentoPrincipal() {
        FragmentTransaction transaccion = gestor.beginTransaction();
        if (gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL) == null)
            transaccion.replace(R.id.flHueco, FragmentoPrincipal.newInstance(), FRAGMENTO_PRINCIPAL);
        transaccion.commit();
    }

    @Override
    public void cargarFragmentoSecundario(String id_fragmento, Object o) {
        FragmentTransaction transaccion = gestor.beginTransaction();
        switch (id_fragmento) {
            case FRAGMENTO_INSERT_UPDATE_ALUMNO:
                if (gestor.findFragmentByTag(FRAGMENTO_INSERT_UPDATE_ALUMNO) == null)
                    transaccion.replace(R.id.flHueco, Fragmento_Insert_UpdateAlumno.newInstance((Alumno)o), FRAGMENTO_INSERT_UPDATE_ALUMNO);
                transaccion.addToBackStack(FRAGMENTO_INSERT_UPDATE_ALUMNO);
                break;

            case FRAGMENTO_INSERT_UPDATE_EMPRESA:
                if (gestor.findFragmentByTag(FRAGMENTO_INSERT_UPDATE_EMPRESA) == null)
                    transaccion.replace(R.id.flHueco, Fragmento_Insert_UpdateEmpresa.newInstance((Empresa) o), FRAGMENTO_INSERT_UPDATE_EMPRESA);
                transaccion.addToBackStack(FRAGMENTO_INSERT_UPDATE_EMPRESA);
                break;

            case FRAGMENTO_NEW_VISITA_GENERAL:
                if (gestor.findFragmentByTag(FRAGMENTO_NEW_VISITA_GENERAL) == null)
                    transaccion.replace(R.id.flHueco, Fragmento_Insert_NewVisitaGeneral.newInstance(), FRAGMENTO_NEW_VISITA_GENERAL);
                transaccion.addToBackStack(FRAGMENTO_NEW_VISITA_GENERAL);
                break;

            case FRAGMENTO_DETALLES_EMPRESA:
                if (gestor.findFragmentByTag(FRAGMENTO_DETALLES_EMPRESA) == null)
                    transaccion.replace(R.id.flHueco, Fragmento_Detalle_Empresa.newInstance((Empresa)o), FRAGMENTO_DETALLES_EMPRESA);
                transaccion.addToBackStack(FRAGMENTO_DETALLES_EMPRESA);
                break;

            case FRAGMENTO_ALUMNO_VISITAS:
                if (gestor.findFragmentByTag(FRAGMENTO_ALUMNO_VISITAS) == null)
                    transaccion.replace(R.id.flHueco, Fragmento_Alumno_Visita.newInstance((Alumno) o), FRAGMENTO_ALUMNO_VISITAS);
                transaccion.addToBackStack(FRAGMENTO_ALUMNO_VISITAS);
                break;
        }
        transaccion.commit();
    }

    private void configDrawerLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = gestor.findFragmentById(R.id.flHueco);
                if (fragment instanceof  GestionFabDesdeFragmento)
                    ((GestionFabDesdeFragmento)fragment).onFabPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setFabImage(int id) {
        fab.setImageResource(id);
    }

    @Override
    public void onItemClick(DialogFragment dialog, int which) {
        ((Fragmento_Insert_NewVisitaGeneral)gestor.findFragmentByTag(FRAGMENTO_NEW_VISITA_GENERAL)).setLblAlumno(which);
    }

    @Override
    public void obtenerDate(Date date) {
        ((Fragmento_Insert_NewVisitaGeneral)gestor.findFragmentByTag(FRAGMENTO_NEW_VISITA_GENERAL)).setLblFecha(date);
    }

    @Override
    public void onItemClick(Object o) {
        if (o instanceof Empresa)
            cargarFragmentoSecundario(FRAGMENTO_DETALLES_EMPRESA, o);
        else if (o instanceof Alumno)
            cargarFragmentoSecundario(FRAGMENTO_ALUMNO_VISITAS, o);
    }
}
