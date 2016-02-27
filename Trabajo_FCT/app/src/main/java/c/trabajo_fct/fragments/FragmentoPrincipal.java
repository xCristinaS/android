package c.trabajo_fct.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.adapters.CachedFragmentPagerAdapter;

/**
 * Created by Cristina on 27/02/2016.
 */
public class FragmentoPrincipal extends Fragment {

    public interface Callback_Principal {

    }

    private Callback_Principal listener;
    private TabLayout tabLayout;
    private PaginasAdapter mAdaptador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_principal, container, false);
    }

    public static FragmentoPrincipal newInstance(){
        return new FragmentoPrincipal();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        tabLayout = (TabLayout) getView().findViewById(R.id.tabL);
        setupViewPager();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (Callback_Principal) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private void setupViewPager() {
        // Se crea y asigna el adaptador de páginas al viewPager.
        ViewPager viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        ArrayList<String> titulosPaginas = new ArrayList<>();
        titulosPaginas.add("Alumnos"); titulosPaginas.add("Empresas"); titulosPaginas.add("Visitas");
        mAdaptador = new PaginasAdapter(getActivity().getSupportFragmentManager(), titulosPaginas);
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

    public class PaginasAdapter extends CachedFragmentPagerAdapter {

        private final ArrayList<String> mTitulos;

        public PaginasAdapter(FragmentManager fm, ArrayList<String> titulos) {
            super(fm);
            this.mTitulos = titulos;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return AlumnoFragment.newInstance();
                case 1:
                    return EmpresaFragment.newInstance();
                case 2:
                    return VisitaFragment.newInstance();
                default:
                    return null;
            }
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
