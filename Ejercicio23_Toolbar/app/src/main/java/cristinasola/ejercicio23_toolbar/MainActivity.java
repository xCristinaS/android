package cristinasola.ejercicio23_toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista; ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    ArrayList<Alumno> datos = new ArrayList<>();
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = (ListView) findViewById(R.id.lstLista);
        listaAlumnos.add(new Alumno("pepito", 12, "956171819"));
        listaAlumnos.add(new Alumno("juanito", 22, "956784512"));
        listaAlumnos.add(new Alumno("lolo", 36, "956142536"));
        listaAlumnos.add(new Alumno("laura", 42, "956968574"));
        listaAlumnos.add(new Alumno("marta", 19, "956784574"));
        listaAlumnos.add(new Alumno("sonia", 10, "856741478"));
        listaAlumnos.add(new Alumno("cristina", 7, "644124578"));

        lista.setAdapter(new PersonalAdapter(this, listaAlumnos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intento = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+((Alumno)parent.getItemAtPosition(position)).getTelefono()));
                startActivity(intento);
            }
        });

        // A partir de aqui ejercicio 24.
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // nada
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // nada
                return false;
            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_lista_alumnos, menu);
                return true;
            }
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(lista.getCheckedItemCount()
                        + getString(R.string.alumnosDe)
                        + lista.getCount());
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()){
                    case R.id.borrar:
                        ArrayList<Alumno> elems = getElementosSeleccionados(lista, true);
                        // Se eliminan del adaptador.
                        for (Alumno elemento : elems) {
                            ((PersonalAdapter)lista.getAdapter()).getListaAlumnos().remove(elemento);
                        }
                        // Se notifica al adaptador que ha habido cambios.
                        ((PersonalAdapter)lista.getAdapter()).notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), elems.size() + getString(R.string.alumnosEliminados), Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }
    private ArrayList<Alumno> getElementosSeleccionados(ListView lista, boolean desmarcar){
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
}
