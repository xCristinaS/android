package c.trabajo_fct.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import c.trabajo_fct.R;
import c.trabajo_fct.bdd.DAO;
import c.trabajo_fct.dialogs_fragments.SeleccionDirectaDialogFragment;
import c.trabajo_fct.interfaces.Callback_MainActivity;
import c.trabajo_fct.interfaces.GestionFabDesdeFragmento;
import c.trabajo_fct.interfaces.MyDateTimePickerCallBack;
import c.trabajo_fct.modelos.Alumno;
import c.trabajo_fct.modelos.Visita;

/**
 * Created by Cristina on 29/02/2016.
 */
public class Fragmento_Insert_UpdateVisita extends Fragment implements GestionFabDesdeFragmento{


    private MyDateTimePickerCallBack listener;
    private Callback_MainActivity listener2;
    private Toolbar toolbar;
    private ImageView imgCabecera;
    private TextView lblAlumno, lblFecha, lblComentario;
    private DAO gestor;
    private String[] nombres;
    private Date fechaVisita;
    private static Visita visita;
    private SimpleDateFormat formatFecha = new SimpleDateFormat("EEEE dd/MM/yyyy"), formatHora = new SimpleDateFormat("HH:mm:ss");
    private CardView cvComentario;

    public static Fragmento_Insert_UpdateVisita newInstance(Visita v) {
        visita = v;
        return new Fragmento_Insert_UpdateVisita();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_insert_update_visita, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        if (visita != null)
            bindVisita();
        setFabImage();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        gestor = new DAO(getContext());
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);
        lblAlumno = (TextView) getView().findViewById(R.id.txtAlumno);
        lblFecha = (TextView) getView().findViewById(R.id.txtFecha);
        lblComentario = (TextView) getView().findViewById(R.id.lblContenidoComent);
        cvComentario = (CardView) getView().findViewById(R.id.cvComentario);
        ImageView icoAlumno = (ImageView) getView().findViewById(R.id.icoPerson);
        ImageView icoFecha = (ImageView) getView().findViewById(R.id.icoFecha);

        lblAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visita == null)
                    lanzarDialogoSeleccionAlumno();
            }
        });

        icoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visita == null)
                    lanzarDialogoSeleccionAlumno();
            }
        });

        lblFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMyDateTimerDialog().show();
            }
        });

        icoFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMyDateTimerDialog().show();
            }
        });

        cvComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDialogoEditarComentario().show();
            }
        });
    }

    private void bindVisita() {
        Date hoy = new Date();
        Picasso.with(getContext()).load(gestor.selectFotoAlumno(visita.getIdAlumno())).into(imgCabecera);
        lblAlumno.setText(gestor.selectNombreAlumno(visita.getIdAlumno()));
        lblFecha.setText(String.format("%s a las %s", formatFecha.format(visita.getFecha()), formatHora.format(visita.getFecha())));
        if (hoy.compareTo(visita.getFecha()) > 0) {
            lblComentario.setText(visita.getComentario().equals(getString(R.string.sin_comentario)) ? getString(R.string.agregar_comentario) : visita.getComentario());
            cvComentario.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        listener = (MyDateTimePickerCallBack) context;
        listener2 = (Callback_MainActivity) context;
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        listener = null;
        listener2 = null;
        super.onDetach();
    }

    private void lanzarDialogoSeleccionAlumno(){
        ArrayList<Alumno> alumnos = gestor.selectAllAlumnos();
        nombres = new String[alumnos.size()];
        for (int i = 0; i < alumnos.size(); i++)
            nombres[i] = alumnos.get(i).getNombre();
        if (nombres.length > 0)
            (SeleccionDirectaDialogFragment.newInstance("Alumnos", nombres)).show(getChildFragmentManager(), "dialogFragmentAlumnos");
        else
            Snackbar.make(getView(), "NO HAY ALUMNOS", Snackbar.LENGTH_LONG).show();
    }

    private AlertDialog lanzarDialogoEditarComentario(){
        final View dialogView = View.inflate(getActivity(), R.layout.editar_comentario, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        final EditText txtComentario = (EditText) dialogView.findViewById(R.id.txtComentario);

        dialogView.findViewById(R.id.aceptar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblComentario.setText(txtComentario.getText());
                alertDialog.dismiss();
            }
        });

        dialogView.findViewById(R.id.cancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        return alertDialog;
    }

    private AlertDialog lanzarMyDateTimerDialog(){
        final View dialogView = View.inflate(getActivity(), R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                listener.obtenerDate(new Date(calendar.getTimeInMillis()));
                alertDialog.dismiss();
            }
        });

        dialogView.findViewById(R.id.cancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dialogView);
        return alertDialog;
    }

    @Override
    public void onDestroy() {
        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        super.onDestroy();
    }

    public void setLblAlumno(int which){
        lblAlumno.setText(nombres[which]);
    }

    public void setLblFecha(Date date){
        this.fechaVisita = date;
        lblFecha.setText(String.format("%s a las %s", formatFecha.format(date), formatHora.format(date)));
    }

    @Override
    public void onFabPressed() {
        insertarVisita();
    }

    @Override
    public void setFabImage() {
        listener2.setFabImage(R.drawable.ic_done_white);
    }

    @Override
    public void setListener(Callback_MainActivity listener) {

    }

    @Override
    public Callback_MainActivity getListener() {
        return null;
    }

    private void insertarVisita() {
        boolean insertar = false;
        if (camposRellenos()){
            if (visita == null) {
                visita = new Visita();
                visita.setFecha(fechaVisita);
                insertar = true;
            }
            visita.setIdAlumno(gestor.selectIDAlumno(lblAlumno.getText().toString()));
            visita.setComentario(TextUtils.isEmpty(lblComentario.getText()) ? getString(R.string.sin_comentario) : lblComentario.getText().toString());
            if (insertar){
                gestor.insertVisita(visita);
                Snackbar.make(getView(),"NUEVA VISITA CREADA", Snackbar.LENGTH_LONG).show();
                cvComentario.setVisibility(View.GONE);
                limpiarCampos();
                visita = null;
            } else {
                gestor.updateVisita(visita);
                Snackbar.make(getView(),"VISITA ACTUALIZADA", Snackbar.LENGTH_LONG).show();
            }
        } else
            Snackbar.make(getView(),"SELECCIONE ALUMNO Y FECHA", Snackbar.LENGTH_LONG).show();
    }

    private boolean camposRellenos() {
        boolean r = true;
        if (lblFecha.getText().equals(getString(R.string.fecha)) || lblAlumno.getText().equals(getString(R.string.nombre_alumno))) {
            r = false;
        }
        return r;
    }

    private void limpiarCampos() {
        lblFecha.setText(getResources().getString(R.string.fecha));
        lblAlumno.setText(getResources().getString(R.string.nombre_alumno));
        lblComentario.setText(getString(R.string.sin_comentario));
    }
}
