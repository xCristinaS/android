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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.dialogs_fragments.SeleccionDirectaDialogFragment;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Insert_UpdateAlumno  extends Fragment implements GestionFabDesdeFragmento{

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera, imgEmpresa;
    private EditText txtNombre, txtCurso, txtEdad, txtTel, txtDireccion;
    private TextView lblEmpresa;
    private DAO gestor;
    private static Alumno alumno;
    private String[] nombres;

    public static Fragmento_Insert_UpdateAlumno newInstance(Alumno a) {
        alumno = a;
        return new Fragmento_Insert_UpdateAlumno();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
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

        txtNombre = (EditText) getView().findViewById(R.id.txtNombre);
        txtCurso = (EditText) getView().findViewById(R.id.txtCurso);
        txtDireccion = (EditText) getView().findViewById(R.id.txtDireccion);
        txtEdad = (EditText) getView().findViewById(R.id.txtEdad);
        txtTel = (EditText) getView().findViewById(R.id.txtTel);
        lblEmpresa = (TextView) getView().findViewById(R.id.lblEmpresa);

        lblEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDialogFragmentSeleccionEmpresa();
            }
        });
        getView().findViewById(R.id.icoEmpresa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDialogFragmentSeleccionEmpresa();
            }
        });
    }

    private void bindAlumno() {
        String fotoEmpresa;
        Picasso.with(getContext()).load(alumno.getFoto()).into(imgCabecera);
        if (gestor.selectEmpresa(alumno.getEmpresa()) != null) {
            fotoEmpresa = gestor.selectFotoEmpresa(alumno.getEmpresa());
            lblEmpresa.setText(gestor.selectNombreEmpresa(alumno.getEmpresa()));
        } else {
            fotoEmpresa = getString(R.string.default_empresa_img);
            lblEmpresa.setText(R.string.sin_empresa_asignada);
        }
        Picasso.with(getContext()).load(fotoEmpresa).into(imgEmpresa);
        txtNombre.setText(alumno.getNombre());
        txtCurso.setText(alumno.getCurso());
        txtDireccion.setText(alumno.getDireccion());
        txtEdad.setText(String.valueOf(alumno.getEdad()));
        txtTel.setText(alumno.getTelefono());
    }

    private void lanzarDialogFragmentSeleccionEmpresa(){
        ArrayList<Empresa> empresas = gestor.selectAllEmpresa();
        nombres = new String[empresas.size()];
        for (int i = 0; i < empresas.size(); i++)
            nombres[i] = empresas.get(i).getNombre();
        SeleccionDirectaDialogFragment.newInstance("Empresas", nombres).show(getChildFragmentManager(), "fragmentDialogEmpresas");
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
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
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
            if (alumno == null){
                alumno = new Alumno();
                insertar = true;
            }
            alumno.setCurso(txtCurso.getText().toString());
            alumno.setNombre(txtNombre.getText().toString());
            alumno.setDireccion(txtDireccion.getText().toString());
            alumno.setTelefono(txtTel.getText().toString());
            alumno.setEdad(Integer.parseInt(txtEdad.getText().toString()));
            if (!lblEmpresa.getText().equals(getString(R.string.sin_empresa_asignada)))
                alumno.setEmpresa(gestor.selectIDEmpresa(lblEmpresa.getText().toString()));
            else
                alumno.setEmpresa(null);
            alumno.setFoto(getResources().getString(R.string.default_alumno_img));
            if (insertar) {
                gestor.insertAlumno(alumno);
                Snackbar.make(getView(), "NUEVO ALUMNO INSERTADO", Snackbar.LENGTH_LONG).show();
                limpiarCampos();
                alumno = null;
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
                || TextUtils.isEmpty(txtDireccion.getText()))
            r = false;
        return r;
    }

    private void limpiarCampos() {
        lblEmpresa.setText(getString(R.string.sin_empresa_asignada));
        txtCurso.setText("");
        txtTel.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
    }

    public void setLblEmpresa(int which){
        lblEmpresa.setText(nombres[which]);
    }

    @Override
    public void onFabPressed() {

    }

    @Override
    public void setFabImage() {
        listener.setFabImage(R.drawable.ic_camera_alt);
    }

    @Override
    public void setListener(Callback_MainActivity listener) {

    }

    @Override
    public Callback_MainActivity getListener() {
        return null;
    }
}
