package cristinasola.trabajo01;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Created by Cristina on 06/12/2015.
 */
public class FragmentoSecundario extends Fragment {

    private static final int RESULTADO_FRAGMENT_SEC = 2;
    TextView lblNombre, lblApellidosAl, lblTelefonoAl, lblDireccionAl, lblEmail;
    private static Alumno alumno;
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
        lblNombre = (TextView)getActivity().findViewById(R.id.lblNombreAl);
        lblApellidosAl = (TextView)getActivity().findViewById(R.id.lblApellidosAl);
        lblTelefonoAl = (TextView)getActivity().findViewById(R.id.lblTelefonoAl);
        lblDireccionAl = (TextView)getActivity().findViewById(R.id.lblDireccionAl);
        lblEmail = (TextView)getActivity().findViewById(R.id.lblEmailAl);

        lblNombre.setText(alumno.getNombre());
        lblApellidosAl.setText(alumno.getApellidos());
        lblTelefonoAl.setText(alumno.getTelefono());
        lblDireccionAl.setText(alumno.getDireccion());
        lblEmail.setText(alumno.getEmail());
        super.onActivityCreated(savedInstanceState);
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
                NuevoAlumnoActivity.startForResult(getActivity(), RESULTADO_FRAGMENT_SEC, BddAlumnos.indiceAlumno(alumno));
                r = true;
                break;
            default:
                r = super.onOptionsItemSelected(item);
        }
        return r;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
