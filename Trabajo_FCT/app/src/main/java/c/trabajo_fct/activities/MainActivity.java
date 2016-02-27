package c.trabajo_fct.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.adapters.CachedFragmentPagerAdapter;
import c.trabajo_fct.fragments.PaginaFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private PaginasAdapter mAdaptador;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabL);
        setupViewPager();
    }

    private void setupViewPager() {
        // Se crea y asigna el adaptador de páginas al viewPager.
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList<String> titulosPaginas = new ArrayList<>();
        titulosPaginas.add("Alumnos"); titulosPaginas.add("Empresas"); titulosPaginas.add("Visitas");
        mAdaptador = new PaginasAdapter(getSupportFragmentManager(), titulosPaginas);
        viewPager.setAdapter(mAdaptador);
        viewPager.setOffscreenPageLimit(2);
        // Se configura el tabLayout para que trabaje con el viewPager.
        tabLayout.setupWithViewPager(viewPager);
        // Se añade un listener para que al cambiar la página se cambia la
        // imagen de cabecera.
        //final ImageView imgCabecera = (ImageView) findViewById(R.id.imgCabecera);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //final int[] imagenes = {R.drawable.foto0, R.drawable.foto1, R.drawable.foto2};

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Se establece la nueva imagen de cabecera y se fuerza el repintado.
                //imgCabecera.setImageResource(imagenes[position]);
                //imgCabecera.invalidate();
                // Se muestra u oculta el FAB.
/*                if (position == 1) {
                    fab.hide();
                } else {
                    fab.show();
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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
    
    public class PaginasAdapter extends CachedFragmentPagerAdapter {

        private final ArrayList<String> mTitulos;

        public PaginasAdapter(FragmentManager fm, ArrayList<String> titulos) {
            super(fm);
            this.mTitulos = titulos;
        }

        @Override
        public Fragment getItem(int position) {
            return PaginaFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return mTitulos.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitulos.get(position);
        }
    }
}
