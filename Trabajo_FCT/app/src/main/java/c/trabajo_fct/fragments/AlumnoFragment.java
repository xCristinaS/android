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

import c.trabajo_fct.DividerItemDecoration;
import c.trabajo_fct.R;
import c.trabajo_fct.adapters.AlumnosAdapter;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 27/02/2016.
 */
public class AlumnoFragment extends Fragment implements GestionFabDesdeFragmento {

    private FragmentoPrincipal.Callback_Principal listener;
    private RecyclerView lstAlumnos;
    private AlumnosAdapter adaptador;
    private DAO gestor;

    public AlumnoFragment() {}

    public static AlumnoFragment newInstance() {
        AlumnoFragment fragment = new AlumnoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alumno, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        gestor = new DAO(getContext());
        //insertPrimerosAlumnos();

        lstAlumnos = (RecyclerView) getView().findViewById(R.id.lstAlumnos);
        adaptador = new AlumnosAdapter(gestor.selectAllAlumnos());
        adaptador.setEmptyView(getView().findViewById(R.id.lblNoHayAlumnos));
        lstAlumnos.setAdapter(adaptador);
        lstAlumnos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
        lstAlumnos.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        super.onActivityCreated(savedInstanceState);
    }

    private void insertPrimerosAlumnos() {
        gestor.insertEmpresa(new Empresa("empresa1", "su calle", "98494840", getResources().getString(R.string.default_empresa_img)));
        gestor.insertAlumno(new Alumno("Pepe", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("manuel", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("alex", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("lolo", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("jose", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("maria", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("maria", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("maria", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("maria", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
        gestor.insertAlumno(new Alumno("sara", "calle san jose", "956690654", "2DAM", 19, getResources().getString(R.string.default_alumno_img), 1));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onFabPressed() {
        Toast.makeText(getContext(), "Bien", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFabImage() {
        listener.setFabImage(R.drawable.ic_person_add);
    }

    public void setListener(FragmentoPrincipal.Callback_Principal listener) {
        this.listener = listener;
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
