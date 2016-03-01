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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import c.trabajo_fct.R;
import c.trabajo_fct.activities.MainActivity;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.modelos.Empresa;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 01/03/2016.
 */
public class Fragmento_Detalle_Visita extends Fragment implements GestionFabDesdeFragmento {

    private Callback_MainActivity listener;
    private Toolbar toolbar;
    private ImageView imgCabecera;
    private TextView lblNombre, lblFecha, lblComentario;
    private static Visita visita;

    public static Fragmento_Detalle_Visita newInstance(Visita v) {
        visita = v;
        return new Fragmento_Detalle_Visita();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_detalle_visita, container, false);
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

        lblNombre = (TextView) getView().findViewById(R.id.lblAlumno);
        lblFecha = (TextView) getView().findViewById(R.id.lblFecha);
        lblComentario = (TextView) getView().findViewById(R.id.lblContenidoComent);
    }

    private void bindData() {
        SimpleDateFormat formatFecha = new SimpleDateFormat("EEEE dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        DAO gestor = new DAO(getContext());
        Date hoy = new Date();
        Picasso.with(getContext()).load(gestor.selectFotoAlumno(visita.getIdAlumno())).into(imgCabecera);
        lblNombre.setText(gestor.selectNombreAlumno(visita.getIdAlumno()));
        lblFecha.setText(String.format("%s a las %s", formatFecha.format(visita.getFecha()), formatHora.format(visita.getFecha())));
        if (!visita.getComentario().equals(getString(R.string.sin_comentario)))
            lblComentario.setText(visita.getComentario());
        else {
            if (hoy.compareTo(visita.getFecha()) > 0)
                lblComentario.setText(visita.getComentario());
            else
                lblComentario.setText(R.string.visita_no_realizada);
        }
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
        listener.cargarFragmentoSecundario(MainActivity.FRAGMENTO_INSERT_UPDATE_VISITA, visita);
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
