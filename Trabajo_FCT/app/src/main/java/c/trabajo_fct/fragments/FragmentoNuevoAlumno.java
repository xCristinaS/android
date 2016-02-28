package c.trabajo_fct.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 28/02/2016.
 */
public class FragmentoNuevoAlumno extends Fragment {

    public interface Callback_MainActivity {
        public void cargarFragmentoPrincipal();
    }

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera, imgEmpresa;
    private EditText txtNombre;
    private Spinner spEmpresas;
    private DAO gestor;

    public static FragmentoNuevoAlumno newInstance(){
        return new FragmentoNuevoAlumno();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_nuevo_alumno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews(){
        gestor = new DAO(getContext());
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView)getView().findViewById(R.id.imgCabecera);
        Picasso.with(getContext()).load(getResources().getString(R.string.default_alumno_img)).into(imgCabecera);

        imgEmpresa = (ImageView)getView().findViewById(R.id.icoEmpresa);
        Picasso.with(getContext()).load(getResources().getString(R.string.default_empresa_img)).into(imgEmpresa);

        configSpinner();
        txtNombre = (EditText) getView().findViewById(R.id.txtNombre);

    }

    private void configSpinner() {
        spEmpresas = (Spinner) getView().findViewById(R.id.spEmpresas);
        ArrayList<String> empresas = gestor.selectAllNombreEmpresas();
        String[] defauultSpinner = {"No hay empresas"};
        ArrayAdapter<String> adapter;
        if (empresas.size() > 0)
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, empresas);
        else
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, defauultSpinner);

        spEmpresas.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        listener = (Callback_MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.VISIBLE);
        //listener.cargarFragmentoPrincipal();
        super.onDestroy();
    }
}
