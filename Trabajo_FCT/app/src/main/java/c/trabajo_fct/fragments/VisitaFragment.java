package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;

import c.trabajo_fct.DividerItemDecoration;
import c.trabajo_fct.R;
import c.trabajo_fct.adapters.VisitasAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.modelos.Empresa;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 27/02/2016.
 */
public class VisitaFragment extends Fragment {

    private RecyclerView lstVisitas;
    private VisitasAdapter adaptador;
    private DAO gestor;

    public VisitaFragment() {}

    // Retorna el fragmento configurado. Recibe el número de página.
    public static VisitaFragment newInstance() {
        VisitaFragment fragment = new VisitaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visita, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        gestor = new DAO(getContext());
        //insertarVisitas();

        lstVisitas = (RecyclerView) getView().findViewById(R.id.lstVisitas);
        adaptador = new VisitasAdapter(gestor.selectAllVisitas());
        adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayVisitas));
        lstVisitas.setAdapter(adaptador);
        lstVisitas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());
        lstVisitas.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        super.onActivityCreated(savedInstanceState);
    }

    private void insertarVisitas() {
        for (int i = 0; i < 10; i++) {
            gestor.insertVisita(new Visita(1, new Date(1522154 + 55555 * i), ""));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Se almacena el texto mostrado.
        //outState.putString(STATE_TEXTO, mTexto);
    }

    // Cuando se hace click sobre el fab estando en dicho fragmento.
    public void fabOnClick(View view) {
        //mTexto = getString(R.string.has_pulsado_sobre_el_fab, lblTexto.getText());
        //lblTexto.setText(mTexto);
        //Snackbar.make(view,mTexto, Snackbar.LENGTH_LONG).show();
    }
}
