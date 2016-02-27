package c.trabajo_fct.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.adapters.AlumnosAdapter;
import c.trabajo_fct.modelos.Alumno;

/**
 * Created by Cristina on 27/02/2016.
 */
public class AlumnoFragment extends Fragment {

    private RecyclerView lstAlumnos;
    private AlumnosAdapter adaptador;

    public AlumnoFragment() {}

    // Retorna el fragmento configurado. Recibe el número de página.
    public static AlumnoFragment newInstance() {
        AlumnoFragment fragment = new AlumnoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.alumno_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        lstAlumnos = (RecyclerView) getView().findViewById(R.id.lstAlumnos);
        ArrayList<Alumno> lista = new ArrayList<>();
        lista.add(new Alumno());
        lista.get(0).setNombre("Manolito");
        lista.get(0).setFoto(getResources().getString(R.string.default_alumno_img));
        adaptador = new AlumnosAdapter(lista);
        lstAlumnos.setAdapter(adaptador);
        lstAlumnos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
        super.onActivityCreated(savedInstanceState);
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
