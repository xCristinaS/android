package cristinasola.trabajo01;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Cristina on 06/12/2015.
 */
public class FragmentoPrincipal extends Fragment {

    ListView lstLista;
    PersonalAdapterLista adaptador;
    private Callback_Principal listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_principal_lista, container, false);
    }

    public static FragmentoPrincipal newInstance(){
        return new FragmentoPrincipal();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        adaptador = new PersonalAdapterLista(getActivity(), BddAlumnos.getAlumnos());
        lstLista = (ListView) getView().findViewById(R.id.lstLista);
        lstLista.setAdapter(adaptador);

        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.cargarFragmentoDetalles(position);
            }
        });

        lstLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lstLista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(lstLista.getCheckedItemCount() + getString(R.string.alumnosDe) + lstLista.getCount());
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_lista_alumnos, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()){
                    case R.id.borrar:
                        ArrayList<Alumno> elems = getElementosSeleccionados(lstLista, true);
                        // Se eliminan del adaptador.
                        for (Alumno elemento : elems) {
                            ((PersonalAdapterLista)lstLista.getAdapter()).getAlumnos().remove(elemento);
                        }
                        // Se notifica al adaptador que ha habido cambios.
                        ((PersonalAdapterLista)lstLista.getAdapter()).notifyDataSetChanged();
                        Toast.makeText(getActivity().getApplicationContext(), elems.size() + getString(R.string.alumnosEliminados), Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        listener = (Callback_Principal) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private ArrayList<Alumno> getElementosSeleccionados(ListView lista, boolean desmarcar){
        ArrayList<Alumno> datos = new ArrayList<>();
        if (datos.size() > 0)
            datos.removeAll(datos);
        // Se obtienen los elementos seleccionados de la lista.
        SparseBooleanArray selec = lista.getCheckedItemPositions();
        for (int i = 0; i < selec.size(); i++) {
            // Si está seleccionado.
            if (selec.valueAt(i)) {
                int position = selec.keyAt(i);
                // Se quita de la selección (si hay que hacerlo).
                if (desmarcar) {
                    lista.setItemChecked(position, false);
                }
                // Se añade al resultado.
                datos.add((Alumno) lista.getItemAtPosition(selec.keyAt(i)));
            }
        }
        // Se retorna el resultado.
        return datos;
    }

        public interface Callback_Principal {
            public void cargarFragmentoDetalles(int posicion);
        }
}
