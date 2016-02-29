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

import com.squareup.picasso.Picasso;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Insert_UpdateEmpresa extends Fragment {

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera;
    private EditText txtNombre, txtTel, txtDireccion;
    private DAO gestor;
    private static Empresa empresa;

    public static Fragmento_Insert_UpdateEmpresa newInstance(Empresa e) {
        empresa = e;
        return new Fragmento_Insert_UpdateEmpresa();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragmento_insert_update_empresa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        if (empresa != null)
            bindEmpresa();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        gestor = new DAO(getContext());
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);

        txtNombre = (EditText) getView().findViewById(R.id.txtNombre);
        txtDireccion = (EditText) getView().findViewById(R.id.txtDireccion);
        txtTel = (EditText) getView().findViewById(R.id.txtTel);
    }

    private void bindEmpresa() {
        Picasso.with(getContext()).load(empresa.getFoto()).into(imgCabecera);
        txtNombre.setText(empresa.getNombre());
        txtDireccion.setText(empresa.getDireccion());
        txtTel.setText(empresa.getTelefono());
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
                insertarActualizarEmpresa();
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    private void insertarActualizarEmpresa() {
        boolean insertar = false;
        if (camposRellenos()) {
            if (empresa == null) {
                empresa = new Empresa();
                insertar = true;
            }
            empresa.setNombre(txtNombre.getText().toString());
            empresa.setDireccion(txtDireccion.getText().toString());
            empresa.setTelefono(txtTel.getText().toString());
            empresa.setFoto(getResources().getString(R.string.default_empresa_img));
            if (insertar) {
                gestor.insertEmpresa(empresa);
                limpiarCampos();
                Snackbar.make(getView(), "NUEVA EMPRESA INSERTADA", Snackbar.LENGTH_LONG).show();
            } else {
                //empresa.setId(empresa.getId());
                gestor.updateEmpresa(empresa);
                Snackbar.make(getView(), "EMPRESA ACTUALIZADA", Snackbar.LENGTH_LONG).show();
            }
        } else
            Snackbar.make(getView(), "LOS CAMPOS DEBEN ESTAR RELLENOS", Snackbar.LENGTH_LONG).show();
    }

    private boolean camposRellenos() {
        boolean r = true;
        if (TextUtils.isEmpty(txtTel.getText()) || TextUtils.isEmpty(txtNombre.getText()) || TextUtils.isEmpty(txtDireccion.getText())) {
            r = false;
        }
        return r;
    }

    private void limpiarCampos() {
        txtTel.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
    }
}
