package c.trabajo_fct.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.adapters.CachedFragmentPagerAdapter;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;

/**
 * Created by Cristina on 27/02/2016.
 */
public class FragmentoPrincipal extends Fragment {

    public interface Callback_Principal {
        public void setFabImage(int id);
        public void cargarFragmentoSecundario(String id_fragmento);
    }

    private Callback_Principal listener;
    private PaginasAdapter mAdaptador;
    private ViewPager viewPager;

    public static FragmentoPrincipal newInstance() {
        return new FragmentoPrincipal();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        listener = (Callback_Principal) getActivity();
        setupViewPager();
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_principal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setupViewPager();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        listener = (Callback_Principal) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private void setupViewPager() {
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.tabL);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        ArrayList<String> titulosPaginas = new ArrayList<>();
        titulosPaginas.add("Alumnos");
        titulosPaginas.add("Empresas");
        titulosPaginas.add("Visitas");
        mAdaptador = new PaginasAdapter(getChildFragmentManager(), titulosPaginas);
        viewPager.setAdapter(mAdaptador);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                GestionFabDesdeFragmento fragmento = (GestionFabDesdeFragmento) mAdaptador.getFragment(viewPager.getCurrentItem());
                if (fragmento != null) {
                    if (fragmento.getListener() == null)
                        fragmento.setListener(listener);

                    fragmento.setFabImage();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    public PaginasAdapter getAdaptador() {
        return mAdaptador;
    }

    public ViewPager getViewPager() {
        return viewPager;
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
                    AlumnoFragment a = AlumnoFragment.newInstance();
                    a.setListener(listener);
                    return a;
                case 1:
                    EmpresaFragment e = EmpresaFragment.newInstance();
                    e.setListener(listener);
                    return e;
                case 2:
                    VisitaFragment v = VisitaFragment.newInstance();
                    v.setListener(listener);
                    return v;
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
