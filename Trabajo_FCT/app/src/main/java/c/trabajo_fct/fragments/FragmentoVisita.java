package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import c.trabajo_fct.DividerItemDecoration;
import c.trabajo_fct.R;
import c.trabajo_fct.activities.MainActivity;
import c.trabajo_fct.adapters.VisitasAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.interfaces.OnAdapterItemLongClick;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 27/02/2016.
 */
public class FragmentoVisita extends Fragment implements GestionFabDesdeFragmento {

    private Callback_MainActivity listener;
    private RecyclerView lstVisitas;
    private VisitasAdapter adaptador;
    private DAO gestor;
    private static Alumno alumno;

    public static FragmentoVisita newInstance(Alumno a) {
        alumno = a;
        return new FragmentoVisita();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visita, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        gestor = new DAO(getContext());
        lstVisitas = (RecyclerView) getView().findViewById(R.id.lstVisitas);
        if (alumno == null)
            adaptador = new VisitasAdapter((ArrayList<Visita>)gestor.selectAllVisitas().clone());
        else
            adaptador = new VisitasAdapter((ArrayList<Visita>)gestor.selectAllVisitasDeAlumno(alumno.getId()).clone());
        adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayVisitas));
        adaptador.setListenerLongClick((OnAdapterItemLongClick) getActivity());
        lstVisitas.setAdapter(adaptador);
        lstVisitas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstVisitas.setItemAnimator(new DefaultItemAnimator());
        lstVisitas.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onFabPressed() {
        listener.cargarFragmentoSecundario(MainActivity.FRAGMENTO_NEW_VISITA_GENERAL, null);
    }

    @Override
    public void setFabImage() {
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

    public VisitasAdapter getAdaptador() {
        return adaptador;
    }
}
