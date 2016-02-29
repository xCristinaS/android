package c.trabajo_fct.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import c.trabajo_fct.R;
import c.trabajo_fct.activities.MainActivity;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Detalle_Empresa extends Fragment implements GestionFabDesdeFragmento{

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera;
    private TextView lblNombre, lblTel, lblDir;
    private static Empresa empresa;

    public static Fragmento_Detalle_Empresa newInstance(Empresa e) {
        empresa = e;
        return new Fragmento_Detalle_Empresa();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_detalles_empresa, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        bindData();
        setFabImage();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);

        lblNombre = (TextView) getView().findViewById(R.id.txtNombre);
        lblDir = (TextView) getView().findViewById(R.id.txtDireccion);
        lblTel = (TextView) getView().findViewById(R.id.txtTel);
    }

    private void bindData() {
        Picasso.with(getContext()).load(empresa.getFoto()).into(imgCabecera);
        lblNombre.setText(empresa.getNombre());
        lblDir.setText(empresa.getDireccion());
        lblTel.setText(empresa.getTelefono());
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
    public void onFabPressed() {
        listener.cargarFragmentoSecundario(MainActivity.FRAGMENTO_INSERT_UPDATE_EMPRESA, empresa);
    }

    @Override
    public void setFabImage() {
        listener.setFabImage(R.drawable.ic_mode_edit_white);
    }

    @Override
    public void setListener(Callback_MainActivity listener) {

    }

    @Override
    public Callback_MainActivity getListener() {
        return null;
    }
}
