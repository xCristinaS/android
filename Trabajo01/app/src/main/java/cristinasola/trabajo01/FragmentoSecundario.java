package cristinasola.trabajo01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Cristina on 06/12/2015.
 */
public class FragmentoSecundario extends Fragment {

    TextView lblNombre, lblApellidosAl, lblTelefonoAl, lblDireccionAl, lblEmail;
    CircleImageView imgFoto;
    private static Alumno alumno;
    private Callback_FragmentoSec listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragmento_secundario_detalles, container, false);
    }

    public static FragmentoSecundario newInstance(int idAlumno){
        alumno = BddAlumnos.seleccionarAlumno(idAlumno);
        return new FragmentoSecundario();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        lblNombre = (TextView)getView().findViewById(R.id.lblNombreAl);
        lblApellidosAl = (TextView)getView().findViewById(R.id.lblApellidosAl);
        lblTelefonoAl = (TextView)getView().findViewById(R.id.lblTelefonoAl);
        lblDireccionAl = (TextView)getView().findViewById(R.id.lblDireccionAl);
        lblEmail = (TextView)getView().findViewById(R.id.lblEmailAl);
        imgFoto = (CircleImageView)getView().findViewById(R.id.imgFoto);
        Picasso.with(getContext()).load("http://lorempixel.com/image_output/cats-q-c-200-200-3.jpg").into(imgFoto);

        actualizarDatos();
        super.onActivityCreated(savedInstanceState);
    }

    public void actualizarDatos(){
        lblNombre.setText(alumno.getNombre());
        lblApellidosAl.setText(alumno.getApellidos());
        lblTelefonoAl.setText(alumno.getTelefono());
        lblDireccionAl.setText(alumno.getDireccion());
        lblEmail.setText(alumno.getEmail());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmento_secundario_detalles, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean r;
        switch (item.getItemId()){
            case R.id.editar:
                listener.editarAlumno(BddAlumnos.indiceAlumno(alumno));
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (Callback_FragmentoSec) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    public interface Callback_FragmentoSec {
            public void editarAlumno(int idAlumno);
        }
}
