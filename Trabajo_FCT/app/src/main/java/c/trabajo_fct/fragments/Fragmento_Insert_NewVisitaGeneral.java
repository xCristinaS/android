package c.trabajo_fct.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

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
public class Fragmento_Insert_NewVisitaGeneral extends Fragment implements GestionFabDesdeFragmento{


    private MyDateTimePickerCallBack listener;
    private Callback_MainActivity listener2;
    private Toolbar toolbar;
    private ImageView imgCabecera;
    private TextView lblAlumno, lblFecha;
    private DAO gestor;
    private String[] nombres;
    private Date fechaVisita;

    public static Fragmento_Insert_NewVisitaGeneral newInstance() {
        return new Fragmento_Insert_NewVisitaGeneral();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_insert_new_visita_general, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initViews();
        setFabImage();
        super.onActivityCreated(savedInstanceState);
    }

    private void initViews() {
        gestor = new DAO(getContext());
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.GONE);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        imgCabecera = (ImageView) getView().findViewById(R.id.imgCabecera);
        //Picasso.with(getContext()).load(getResources().getString(R.string.default_alumno_img)).into(imgCabecera);

        lblAlumno = (TextView) getView().findViewById(R.id.txtAlumno);
        lblFecha = (TextView) getView().findViewById(R.id.txtFecha);
        ImageView icoAlumno = (ImageView) getView().findViewById(R.id.icoPerson);
        ImageView icoFecha = (ImageView) getView().findViewById(R.id.icoFecha);


        lblAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarDialogoSeleccionAlumno();
            }
        });

        icoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public void onDestroy() {
        ((Toolbar) getActivity().findViewById(R.id.toolbar)).setVisibility(View.VISIBLE);
        super.onDestroy();
    }

    public void setLblAlumno(int which){
        lblAlumno.setText(nombres[which]);
    }

    public void setLblFecha(Date date){
        this.fechaVisita = date;
        SimpleDateFormat formatFecha = new SimpleDateFormat("EEEE dd/MM/yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        lblFecha.setText(String.format("%s a las %s", formatFecha.format(date), formatHora.format(date)));
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
        if (camposRellenos()){
            Visita v = new Visita();
            v.setFecha(fechaVisita);
            v.setIdAlumno(gestor.selectIDAlumno(lblAlumno.getText().toString()));
            v.setComentario(getString(R.string.sin_comentario));
            gestor.insertVisita(v);
            Snackbar.make(getView(),"NUEVA VISITA CREADA", Snackbar.LENGTH_LONG).show();
            limpiarCampos();
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
    }
}
