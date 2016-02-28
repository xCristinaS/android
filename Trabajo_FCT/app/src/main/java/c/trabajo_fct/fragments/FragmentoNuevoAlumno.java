package c.trabajo_fct.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.BddContract;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 28/02/2016.
 */
public class FragmentoNuevoAlumno extends Fragment {

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera, imgEmpresa;
    private EditText txtNombre, txtCurso, txtEdad, txtTel, txtDireccion;
    private Spinner spEmpresas;
    private DAO gestor;

    public static FragmentoNuevoAlumno newInstance() {
        return new FragmentoNuevoAlumno();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragmento_nuevo_alumno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        gestor = new DAO(getContext());
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);
        //Picasso.with(getContext()).load(getResources().getString(R.string.default_alumno_img)).into(imgCabecera);

        imgEmpresa = (ImageView) getView().findViewById(R.id.icoEmpresa);
        Picasso.with(getContext()).load(getResources().getString(R.string.default_empresa_img)).into(imgEmpresa);

        configSpinner();
        txtNombre = (EditText) getView().findViewById(R.id.txtNombre);
        txtCurso = (EditText) getView().findViewById(R.id.txtCurso);
        txtDireccion = (EditText) getView().findViewById(R.id.txtDireccion);
        txtEdad = (EditText) getView().findViewById(R.id.txtEdad);
        txtTel = (EditText) getView().findViewById(R.id.txtTel);
    }

    private void configSpinner() {
        spEmpresas = (Spinner) getView().findViewById(R.id.spEmpresas);
        ArrayList<Empresa> empresas = gestor.selectAllEmpresa();
        ArrayList<String> nombres = new ArrayList<>();
        String[] defauultSpinner = {"No hay empresas"};
        ArrayAdapter<String> adapter;
        if (empresas.size() > 0) {
            nombres.add("Sin asignar");
            for (Empresa e : empresas)
                nombres.add(e.getNombre());
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, nombres);
        } else
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, defauultSpinner);

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
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_nuevo_alumno, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()) {
            case R.id.editar:
                insertarAlumno();
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    private void insertarAlumno() {
        Alumno a = new Alumno();
        if (camposRellenos()) {
            a.setCurso(txtCurso.getText().toString());
            a.setNombre(txtNombre.getText().toString());
            a.setDireccion(txtDireccion.getText().toString());
            a.setTelefono(txtTel.getText().toString());
            a.setEdad(Integer.parseInt(txtEdad.getText().toString()));
            a.setEmpresa(gestor.selectIDEmpresa((String) spEmpresas.getSelectedItem()));
            a.setFoto(getResources().getString(R.string.default_alumno_img));
            gestor.insertAlumno(a);
        }
    }

    private boolean camposRellenos() {
        boolean r = true;
        if (TextUtils.isEmpty(txtCurso.getText()) || TextUtils.isEmpty(txtTel.getText()) || TextUtils.isEmpty(txtNombre.getText()) || TextUtils.isEmpty(txtEdad.getText())
                || TextUtils.isEmpty(txtDireccion.getText()) || spEmpresas.getSelectedItemPosition() == 0)
            r = false;
        return r;
    }
}
