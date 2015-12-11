package cristina.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstLibros;
    PersonalAdapterLista adaptador;
    CardView cvSinopsis;
    TextView lblSinopsis, lblTituloSinopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews(){
        cvSinopsis = (CardView) findViewById(R.id.cvSinopsis);
        lblSinopsis = (TextView) findViewById(R.id.lblSinopsis);
        lblTituloSinopsis = (TextView) findViewById(R.id.lblTituloSinopsis);
        adaptador = new PersonalAdapterLista(this, Coleccion.getLibros());
        lstLibros = (ListView) findViewById(R.id.lstLibros);
        lstLibros.setAdapter(adaptador);

        lstLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cargarSinopsisLibroSeleccionado(position);
            }
        });

        lstLibros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lstLibros.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                mode.setTitle(lstLibros.getCheckedItemCount() + getString(R.string.de) + lstLibros.getCount());
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_context_borrar, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.borrar:
                        ArrayList<Libro> elems = getElementosSeleccionados(lstLibros, true);
                        // Se eliminan del adaptador.
                        for (Libro elemento : elems) {
                            ((PersonalAdapterLista) lstLibros.getAdapter()).getLibros().remove(elemento);
                        }
                        // Se notifica al adaptador que ha habido cambios.
                        ((PersonalAdapterLista) lstLibros.getAdapter()).notifyDataSetChanged();

                        //if (BddAlumnos.getAlumnos().size() == 0)
                          //  listener.listaAlumnosVacia();
                        break;
                }
                return false;
            }
        });

    }

    private void cargarSinopsisLibroSeleccionado(int position){
        Libro libroSeleccionado = Coleccion.getLibroAtIndex(position);
        lblSinopsis.setText(libroSeleccionado.getSinopsis());
        lblTituloSinopsis.setText(libroSeleccionado.getTitulo());
        cvSinopsis.setVisibility(View.VISIBLE);
    }

    private ArrayList<Libro> getElementosSeleccionados(ListView lista, boolean desmarcar){
        ArrayList<Libro> datos = new ArrayList<>();
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
                datos.add((Libro) lista.getItemAtPosition(selec.keyAt(i)));
            }
        }
        // Se retorna el resultado.
        return datos;
    }
}
