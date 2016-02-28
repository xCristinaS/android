package c.trabajo_fct.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import c.trabajo_fct.R;
import c.trabajo_fct.fragments.FragmentoPrincipal;
import c.trabajo_fct.fragments.FragmentoNuevoAlumno;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentoPrincipal.Callback_Principal, FragmentoNuevoAlumno.Callback_MainActivity {

    private static final String FRAGMENTO_PRINCIPAL = "principal";
    public static final String FRAGMENTO_NUEVO_ALUMNO = "nuevo alumno";

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

    @Override
    public void cargarFragmentoPrincipal(){
        FragmentTransaction transaccion = gestor.beginTransaction();
        if (gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL) == null)
            transaccion.replace(R.id.flHueco, FragmentoPrincipal.newInstance(), FRAGMENTO_PRINCIPAL);
        else
            transaccion.replace(R.id.flHueco, gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL), FRAGMENTO_PRINCIPAL);
        transaccion.commit();
    }

    @Override
    public void cargarFragmentoSecundario(String id_fragmento) {
        FragmentTransaction transaccion = gestor.beginTransaction();
        switch (id_fragmento) {
            case FRAGMENTO_NUEVO_ALUMNO:
                if (gestor.findFragmentByTag(FRAGMENTO_NUEVO_ALUMNO) == null)
                    transaccion.replace(R.id.flHueco, FragmentoNuevoAlumno.newInstance(), FRAGMENTO_NUEVO_ALUMNO);
                else
                    transaccion.replace(R.id.flHueco, gestor.findFragmentByTag(FRAGMENTO_NUEVO_ALUMNO), FRAGMENTO_NUEVO_ALUMNO);
                transaccion.addToBackStack("asa");
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
                if (gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL) != null){
                    FragmentoPrincipal fragmentoP = (FragmentoPrincipal) gestor.findFragmentByTag(FRAGMENTO_PRINCIPAL);
                    FragmentoPrincipal.PaginasAdapter adaptador = fragmentoP.getAdaptador();
                    ViewPager viewPager = fragmentoP.getViewPager();
                    if (adaptador != null && viewPager != null)
                        ((GestionFabDesdeFragmento)adaptador.getFragment(viewPager.getCurrentItem())).onFabPressed();
                }
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

}
