package cristinasola.trabajo01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Cristina on 06/12/2015.
 */
public class FragmentoPrincipal extends Fragment {

    ListView lstLista;
    PersonalAdapterLista adaptador;

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
        adaptador = new PersonalAdapterLista(getActivity(), BddAlumnos.alumnos);
        lstLista = (ListView) getActivity().findViewById(R.id.lstLista);
        lstLista.setAdapter(adaptador);

        lstLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(getActivity(), SecundaryActivity.class);
                intento.putExtra(SecundaryActivity.EXTRA_ID_ALUMNO, position);
                startActivity(intento);
            }
        });

        super.onActivityCreated(savedInstanceState);
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
