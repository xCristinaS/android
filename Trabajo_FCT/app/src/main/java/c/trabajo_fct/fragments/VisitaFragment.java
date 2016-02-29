package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;

import c.trabajo_fct.DividerItemDecoration;
import c.trabajo_fct.R;
import c.trabajo_fct.activities.MainActivity;
import c.trabajo_fct.adapters.VisitasAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 27/02/2016.
 */
public class VisitaFragment extends Fragment implements GestionFabDesdeFragmento {

    private Callback_MainActivity listener;
    private RecyclerView lstVisitas;
    private VisitasAdapter adaptador;
    private DAO gestor;

    public VisitaFragment() {
    }

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

    }


    @Override
    public void onFabPressed() {
        if (listener != null)
            listener.cargarFragmentoSecundario(MainActivity.FRAGMENTO_NEW_VISITA_GENERAL);
        else
            Toast.makeText(getContext(), "listener null", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFabImage() {
        if (listener != null)
            listener.setFabImage(R.drawable.ic_event);
    }

    public void setListener(Callback_MainActivity listener) {
        this.listener = listener;
    }

    @Override
    public Callback_MainActivity getListener() {
        return listener;
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
