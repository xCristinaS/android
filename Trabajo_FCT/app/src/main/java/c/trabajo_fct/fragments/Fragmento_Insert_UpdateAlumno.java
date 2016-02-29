package c.trabajo_fct.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Insert_UpdateAlumno  extends Fragment{

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera, imgEmpresa;
    private EditText txtNombre, txtCurso, txtEdad, txtTel, txtDireccion;
    private Spinner spEmpresas;
    private DAO gestor;
    private static Alumno alumno;
    private ArrayList<String> nombresEmpresa;

    public static Fragmento_Insert_UpdateAlumno newInstance(Alumno a) {
        alumno = a;
        return new Fragmento_Insert_UpdateAlumno();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragmento_insert_update_alumno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        if (alumno != null)
            bindAlumno();
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

    private void bindAlumno() {
        Picasso.with(getContext()).load(alumno.getFoto()).into(imgCabecera);
        String fotoEmpresa = gestor.selectFotoEmpresa(alumno.getEmpresa());
        Picasso.with(getContext()).load(fotoEmpresa).into(imgCabecera);
        txtNombre.setText(alumno.getNombre());
        txtCurso.setText(alumno.getCurso());
        txtDireccion.setText(alumno.getDireccion());
        txtEdad.setText(String.valueOf(alumno.getEdad()));
        txtTel.setText(alumno.getTelefono());
        String nombreE = gestor.selectNombreEmpresa(alumno.getEmpresa());
        spEmpresas.setSelection(nombresEmpresa.indexOf(nombreE));
    }

    private void configSpinner() {
        spEmpresas = (Spinner) getView().findViewById(R.id.spEmpresas);
        ArrayList<Empresa> empresas = gestor.selectAllEmpresa();
        nombresEmpresa = new ArrayList<>();
        String[] defauultSpinner = {"No hay empresas"};
        ArrayAdapter<String> adapter;
        if (empresas.size() > 0) {
            nombresEmpresa.add("Sin asignar");
            for (Empresa e : empresas)
                nombresEmpresa.add(e.getNombre());
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, nombresEmpresa);
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
        inflater.inflate(R.menu.menu_insert_update, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()) {
            case R.id.insertar:
                insertarAlumno();
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    private void insertarAlumno() {
        boolean insertar = false;
        if (camposRellenos()) {
            if (Fragmento_Insert_UpdateAlumno.alumno == null){
                Fragmento_Insert_UpdateAlumno.alumno = new Alumno();
                insertar = true;
            }
            alumno.setCurso(txtCurso.getText().toString());
            alumno.setNombre(txtNombre.getText().toString());
            alumno.setDireccion(txtDireccion.getText().toString());
            alumno.setTelefono(txtTel.getText().toString());
            alumno.setEdad(Integer.parseInt(txtEdad.getText().toString()));
            alumno.setEmpresa(gestor.selectIDEmpresa((String) spEmpresas.getSelectedItem()));
            alumno.setFoto(getResources().getString(R.string.default_alumno_img));
            if (insertar) {
                gestor.insertAlumno(alumno);
                limpiarCampos();
                Snackbar.make(getView(), "NUEVO ALUMNO INSERTADO", Snackbar.LENGTH_LONG).show();
            } else {
                gestor.updateAlumno(alumno);
                Snackbar.make(getView(), "ALUMNO ACTUALIZADO", Snackbar.LENGTH_LONG).show();
            }
        } else
            Snackbar.make(getView(), "LOS CAMPOS DEBEN ESTAR RELLENOS", Snackbar.LENGTH_LONG).show();
    }

    private boolean camposRellenos() {
        boolean r = true;
        if (TextUtils.isEmpty(txtCurso.getText()) || TextUtils.isEmpty(txtTel.getText()) || TextUtils.isEmpty(txtNombre.getText()) || TextUtils.isEmpty(txtEdad.getText())
                || TextUtils.isEmpty(txtDireccion.getText()) || spEmpresas.getSelectedItemPosition() == 0)
            r = false;
        return r;
    }

    private void limpiarCampos() {
        spEmpresas.setSelection(0);
        txtCurso.setText("");
        txtTel.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
    }
}
