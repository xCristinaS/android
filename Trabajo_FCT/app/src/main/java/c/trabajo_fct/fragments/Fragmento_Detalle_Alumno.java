package c.trabajo_fct.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import c.trabajo_fct.R;
import c.trabajo_fct.activities.MainActivity;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Empresa;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Detalle_Alumno extends Fragment implements GestionFabDesdeFragmento{

    private Callback_MainActivity listener;
    private ImageView imgCabecera, imgEmpresa;
    private TextView lblNombre, lblCurso, lblEdad, lblTel, lblDir, lblEmpresa;
    private DAO gestor;
    private static Alumno alumno;

    public static Fragmento_Detalle_Alumno newInstance(Alumno a) {
        alumno = a;
        return new Fragmento_Detalle_Alumno();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_detalle_alumno, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        bindAlumno();
        setFabImage();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        gestor = new DAO(getContext());

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);
        //Picasso.with(getContext()).load(getResources().getString(R.string.default_alumno_img)).into(imgCabecera);

        imgEmpresa = (ImageView) getView().findViewById(R.id.icoEmpresa);
        Picasso.with(getContext()).load(getResources().getString(R.string.default_empresa_img)).into(imgEmpresa);

        lblNombre = (TextView) getView().findViewById(R.id.txtNombre);
        lblCurso = (TextView) getView().findViewById(R.id.txtCurso);
        lblDir = (TextView) getView().findViewById(R.id.txtDireccion);
        lblEdad = (TextView) getView().findViewById(R.id.txtEdad);
        lblTel = (TextView) getView().findViewById(R.id.txtTel);
        lblEmpresa = (TextView) getView().findViewById(R.id.lblEmpresa);
    }

    private void bindAlumno() {
        String fotoEmpresa;
        Picasso.with(getContext()).load(alumno.getFoto()).into(imgCabecera);
        int aux = alumno.getEmpresa();
        if (gestor.selectEmpresa(alumno.getEmpresa()) != null) {
            fotoEmpresa = gestor.selectFotoEmpresa(alumno.getEmpresa());
            lblEmpresa.setText(gestor.selectNombreEmpresa(alumno.getEmpresa()));
        } else {
            fotoEmpresa = getString(R.string.default_empresa_img);
            lblEmpresa.setText(R.string.sin_empresa_asignada);
        }
        Picasso.with(getContext()).load(fotoEmpresa).into(imgEmpresa);
        lblNombre.setText(alumno.getNombre());
        lblCurso.setText(alumno.getCurso());
        lblDir.setText(alumno.getDireccion());
        lblEdad.setText(String.valueOf(alumno.getEdad()));
        lblTel.setText(alumno.getTelefono());
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
        super.onDestroy();
    }

    @Override
    public void onFabPressed() {
        listener.cargarFragmentoSecundario(MainActivity.FRAGMENTO_INSERT_UPDATE_ALUMNO, alumno);
    }

    @Override
    public void setFabImage() {
        listener.setFabImage(R.drawable.ic_mode_edit_white);
    }

    public void setListener(Callback_MainActivity listener) {
        this.listener = listener;
    }

    @Override
    public Callback_MainActivity getListener() {
        return null;
    }
}
